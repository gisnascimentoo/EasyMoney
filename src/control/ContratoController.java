package control;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cliente;
import model.Contrato;
import model.PerfilCliente;
import model.PlanoEmprestimo;
import model.StatusContrato;
import view.InterfaceUsuario;
import view.combo.SituacaoCombo;
import db.ManipuladorBanco;

public class ContratoController {

	ManipuladorBanco db;
	Cliente cl;

	public ContratoController() {
		this.db = new ManipuladorBanco();
	}

	public void localizaCliente(int idCliente) {
		cl = (Cliente) db.buscarCliente(idCliente, null, null, null);
		if (cl != null) {
			String agencia = cl.getDadosFinanceiros().getAgencia();
			String banco = cl.getDadosFinanceiros().getBanco();
			int contaCorrente = cl.getDadosFinanceiros().getContaCorrente();
			InterfaceUsuario
					.carregarDadosCliente(agencia, banco, contaCorrente);
		} else {
			InterfaceUsuario
					.exibirMensagemContratoCadastro("Cliente n�o encontrado.");
		}
	}

	// Usado para pre_aprova��o - pre_reprovacao
	private String recuperaPerfilCliente(double renda) {
		// Verifica se a renda esta no perfil A
		if (renda <= PerfilCliente.PERFIL_A.getValorMaximo()) {
			return PerfilCliente.PERFIL_A.getName();
		} else
		// Verifica se a renda esta no perfil B
		{
			if (renda <= PerfilCliente.PERFIL_B.getValorMaximo()) {
				return PerfilCliente.PERFIL_B.getName();
			} else {
				// Verifica se a renda esta no perfil C
				if (renda <= PerfilCliente.PERFIL_C.getValorMaximo()) {
					return PerfilCliente.PERFIL_C.getName();
				} else {
					// Se a rendar n�o estiver em nenhum dos outros, ela � do
					// tipo D
					return PerfilCliente.PERFIL_D.getName();
				}
			}
		}
	}

	//
	private List<PlanoEmprestimo> recuperaPlanosPerfil(String perfilCliente) {
		return db.buscarPlanoEmprestimoPorPerfil(perfilCliente);
	}

	public List<PlanoEmprestimo> recuperaPlanos() {
		return db.buscarPlanoEmprestimo();
	}

	public PlanoEmprestimo recuperaPlanoEmprestimo(int idPlanoEmprestimo) {
		return db.buscarPlanoEmprestimoId(idPlanoEmprestimo);
	}

	// Retorna se o perfil do cliente � aprovado ou reprovado
	public boolean analisaPerfilComPlano(PlanoEmprestimo planoSelecionado) {
		String pfc = recuperaPerfilCliente(cl.getDadosFinanceiros()
				.getRendaPessoal());
		List<PlanoEmprestimo> lPlanosPossiveis = recuperaPlanosPerfil(pfc);
		boolean aprovado = false;
		for (int i = 0; i < lPlanosPossiveis.size(); i++) {
			if (planoSelecionado.getNome().equals(
					lPlanosPossiveis.get(i).getNome())) {
				aprovado = true;
			}
		}
		return aprovado;
	}

	// Calcula o valor das parcelas, aplicando os dados de juros
	public double calculaValorParcelas(int numeroParcelas,
			double valorEmprestimo, PlanoEmprestimo plano) {
		return ((valorEmprestimo * (1 + plano.getJurosTotal()))
				/ (1 - Math.pow((1 + plano.getJurosMensal()), -numeroParcelas)) / plano
					.getJurosMensal());

	}

	// Calcula a data de termino do emprestimo
	public java.util.Date calculaDataTermino(int numeroParcelas) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, numeroParcelas);
		return cal.getTime();
	}

	public void salvarContrato(int idCliente, String status,
			int idPlanoEmprestimo, int numParcelas, double valorEmprestimo,
			double valorParcelas, String dataTermino, String observacoes) {
		PlanoEmprestimo plEmprestimo = recuperaPlanoEmprestimo(idPlanoEmprestimo);

		boolean persistidoSucesso = false;

		Cliente clt = (Cliente) db.buscarCliente(idCliente, null, null, null);
		// Refaz a analise caso o status esteja como pre_aprovado ou
		// pre_rejeitado
		if (analisaPerfilComPlano(plEmprestimo)) {
			status = StatusContrato.PRE_APROVADO.getName();
		} else {
			status = StatusContrato.PRE_REJEITADO.getName();
		}

		Contrato contrato = new Contrato(numParcelas, valorEmprestimo,
				valorParcelas, new java.sql.Date(Calendar.getInstance()
						.getTime().getTime()), this.formatDate(dataTermino),
				clt, status, null, plEmprestimo, observacoes);

		try {
			db.salvarContratoBanco(contrato);
			persistidoSucesso = true;
		} catch (Exception e) {
			persistidoSucesso = false;
		}

		if (persistidoSucesso) {
			InterfaceUsuario
					.exibirMensagemContratoCadastro("Contrato cadastrado com sucesso.");
		} else {
			InterfaceUsuario
					.exibirMensagemContratoCadastro("N�o foi poss�vel cadastrar o contrato.");
		}
	}

	public void editarContrato(int codContrato, int idCliente, String status,
			int idPlanoEmprestimo, int numParcelas, double valorEmprestimo,
			double valorParcelas, String dataTermino, String observacoes) {
		PlanoEmprestimo plEmprestimo = recuperaPlanoEmprestimo(idPlanoEmprestimo);

		Cliente clt = (Cliente) db.buscarCliente(idCliente, null, null, null);

		boolean persistidoSucesso = false;

		if (status == StatusContrato.PRE_APROVADO.getName()
				|| (status == StatusContrato.PRE_REJEITADO.getName())) {

			if (analisaPerfilComPlano(plEmprestimo)) {
				status = StatusContrato.PRE_APROVADO.getName();
			} else {
				status = StatusContrato.PRE_REJEITADO.getName();
			}
		}

		Contrato contrato = db.buscarContratoId(codContrato);
		contrato.setCliente(clt);
		contrato.setDataTerminoContrato(this.formatDate(dataTermino));
		contrato.setQntdParcelas(numParcelas);
		contrato.setValorEmprestimo(valorEmprestimo);
		contrato.setValorParcelas(valorParcelas);
		contrato.setPlanoEmprestimo(plEmprestimo);
		contrato.setStatusContrato(status);
		contrato.setFuncionarioResponsavel(null);
		contrato.setObservacoes(observacoes);
		try {
			db.editarContratoBanco(contrato);
			persistidoSucesso = true;
		} catch (Exception e) {
			persistidoSucesso = false;
		}

		if (persistidoSucesso) {
			InterfaceUsuario
					.exibirMensagemContratoCadastro("Contrato alterado com sucesso.");
		} else {
			InterfaceUsuario
					.exibirMensagemContratoCadastro("N�o foi poss�vel alterar o contrato.");
		}
	}

	public void recuperaContrato(int idContrato) {
		Contrato contrato = db.buscarContratoId(idContrato);
		String cliente = contrato.getCliente().getNomeCompleto();
		String banco = contrato.getCliente().getDadosFinanceiros().getBanco();
		String agencia = contrato.getCliente().getDadosFinanceiros()
				.getAgencia();
		int contaCorrente = contrato.getCliente().getDadosFinanceiros()
				.getContaCorrente();
		Date dataTermino = contrato.getDataTerminoContrato();
		int numParcelas = contrato.getQntdParcelas();
		double valorEmprestimo = contrato.getValorEmprestimo();
		double valorParcelas = contrato.getValorParcelas();
		int idPlanoEmprestimo = contrato.getPlanoEmprestimo()
				.getIdPlanoEmprestimo();
		String status = contrato.getStatusContrato();
		String observacoes = contrato.getObservacoes();
		InterfaceUsuario.carregarContrato(idContrato, cliente, banco, agencia,
				contaCorrente, valorEmprestimo, valorParcelas, dataTermino,
				observacoes, idPlanoEmprestimo, numParcelas, status);
	}

	public boolean verificaCamposObrigatoriosContrato() {
		// TODO
		return true;
	}

	public java.sql.Date formatDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data = null;
		try {
			data = new java.sql.Date(format.parse(date).getTime());
		} catch (ParseException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		return data;
	}

	public void excluirContrato(int codigo) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoContrato();
		if (confirmacao) {
			String mensagem = db.excluiContrato(codigo);
			InterfaceUsuario.exibirMensagemContratoCadastro(mensagem);
		}
	}

	public List<SituacaoCombo> preparaComboSituacao() {
		List<SituacaoCombo> listCombo = new ArrayList<SituacaoCombo>();
		listCombo.add(new SituacaoCombo("Aprovado", "Aprovado"));
		listCombo.add(new SituacaoCombo("Rejeitado", "Rejeitado"));
		listCombo.add(new SituacaoCombo("Pré-rejeitado", "Pré-rejeitado"));
		listCombo.add(new SituacaoCombo("Pré-aprovado", "Pré-aprovado"));
		listCombo.add(new SituacaoCombo("Encerrado", "Encerrado"));
		return listCombo;
	}

	public void buscarContrato(int codigo, String nome, String codigoSitucao) {
		List<Contrato> contratosBusca = db.buscarContrato(codigo, nome, codigoSitucao);
		String[][] dados = new String[contratosBusca.size()][3];
		int indice = 0;
		for (Contrato contrato : contratosBusca) {
			dados[indice][0] = "" + contrato.getIdContrato();
			dados[indice][1] = contrato.getCliente().getNomeCompleto();
			dados[indice][2] = contrato.getStatusContrato();
			indice++;
		}
		InterfaceUsuario.carregaListaCliente(dados);
	}

}
