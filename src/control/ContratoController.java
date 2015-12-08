package control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Contrato;
import model.PerfilCliente;
import model.PlanoEmprestimo;
import model.StatusContrato;
import view.InterfaceUsuario;
import db.ManipuladorBanco;

public class ContratoController {
	
	ManipuladorBanco db;
	Cliente cl;
	
	public ContratoController() {
		this.db = new ManipuladorBanco();
	}
	
	public void localizaCliente(int idCliente)
	{
		cl = (Cliente) db.buscarCliente(idCliente, null, null, null);
		if (cl != null){
			String agencia = cl.getDadosFinanceiros().getAgencia();
			String banco = cl.getDadosFinanceiros().getBanco();
			int contaCorrente = cl.getDadosFinanceiros().getContaCorrente();
			InterfaceUsuario.carregarDadosCliente(agencia, banco, contaCorrente);
		}else{
			InterfaceUsuario.exibirMensagemContratoCadastro("Cliente não encontrado.");
		}
	}
	
	//Usado para pre_aprovação - pre_reprovacao
	private String recuperaPerfilCliente(double renda)
	{
		//Verifica se a renda esta no perfil A
		if (renda <= PerfilCliente.PERFIL_A.getValorMaximo()){
			return PerfilCliente.PERFIL_A.getName();
		}else
			//Verifica se a renda esta no perfil B
		{ if (renda <= PerfilCliente.PERFIL_B.getValorMaximo()){
			return PerfilCliente.PERFIL_B.getName();
		} else {
			//Verifica se a renda esta no perfil C
			if (renda <= PerfilCliente.PERFIL_C.getValorMaximo()){
				return PerfilCliente.PERFIL_C.getName();
			}else{
				//Se a rendar nï¿½o estiver em nenhum dos outros, ela ï¿½ do tipo D
				return PerfilCliente.PERFIL_D.getName();
			}
		}}
	}
	
	//
	private List<PlanoEmprestimo> recuperaPlanosPerfil(String perfilCliente)
	{
		return db.buscarPlanoEmprestimoPorPerfil(perfilCliente); 
	}
	
	public List<PlanoEmprestimo> recuperaPlanos(){
		return db.buscarPlanoEmprestimo();
	}
	
	public PlanoEmprestimo recuperaPlanoEmprestimo(int idPlanoEmprestimo){
		return db.buscarPlanoEmprestimoId(idPlanoEmprestimo);
	}
	
	//Retorna se o perfil do cliente é aprovado ou reprovado
	public boolean analisaPerfilComPlano(PlanoEmprestimo planoSelecionado){
		String pfc = recuperaPerfilCliente(cl.getDadosFinanceiros().getRendaPessoal());
		List<PlanoEmprestimo> lPlanosPossiveis = recuperaPlanosPerfil(pfc);
		boolean aprovado = false;
		for (int i = 0; i < lPlanosPossiveis.size(); i++){
			if (planoSelecionado.getNome().equals(lPlanosPossiveis.get(i).getNome())){
				aprovado = true;
			}
		}
		return aprovado;
	}  
	
	//Calcula o valor das parcelas, aplicando os dados de juros
	public double calculaValorParcelas(int numeroParcelas, double valorEmprestimo, PlanoEmprestimo plano){
		return ((valorEmprestimo*(1+plano.getJurosTotal()))/(1-Math.pow((1+ plano.getJurosMensal()), -numeroParcelas))/plano.getJurosMensal());
		
	}
	
	//Calcula a data de termino do emprestimo
	public Date calculaDataTermino(int numeroParcelas){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, numeroParcelas);
		return cal.getTime();
	}
	
	public void salvarContrato(int idCliente, String status, int idPlanoEmprestimo, int numParcelas, double valorEmprestimo, double valorParcelas, Date dataTermino, String observacoes)
	{
		PlanoEmprestimo plEmprestimo = recuperaPlanoEmprestimo(idPlanoEmprestimo);
		
		boolean persistidoSucesso = false;
		
		Cliente clt = (Cliente) db.buscarCliente(idCliente, null, null, null);
		//Refaz a analise caso o status esteja como pre_aprovado ou pre_rejeitado
		if (analisaPerfilComPlano(plEmprestimo))
		{
			status = StatusContrato.PRE_APROVADO.getName(); 
		} else {
			status = StatusContrato.PRE_REJEITADO.getName();
		}	
			
		Contrato contrato = new Contrato(numParcelas, valorEmprestimo, valorParcelas, (java.sql.Date) new Date(), (java.sql.Date) dataTermino, clt, status, null, plEmprestimo, observacoes);
		
		try
		{
			db.salvarContratoBanco(contrato);
			persistidoSucesso = true;
		} catch (Exception e) {
			persistidoSucesso = false;
		}
				
		if (persistidoSucesso){
			InterfaceUsuario.exibirMensagemContratoCadastro("Contrato cadastrado com sucesso.");
		}else{
			InterfaceUsuario.exibirMensagemContratoCadastro("Não foi possível cadastrar o contrato.");
		}
	}

	public void editarContrato(int codContrato, int idCliente, String status, int idPlanoEmprestimo, int numParcelas, double valorEmprestimo, double valorParcelas, Date dataTermino, String observacoes)
	{
		PlanoEmprestimo plEmprestimo = recuperaPlanoEmprestimo(idPlanoEmprestimo);
		
		Cliente clt = (Cliente) db.buscarCliente(idCliente, null, null, null);
		
		boolean persistidoSucesso = false;
						
		if (status == StatusContrato.PRE_APROVADO.getName()||(status == StatusContrato.PRE_REJEITADO.getName())){
		
			if (analisaPerfilComPlano(plEmprestimo)){
				status = StatusContrato.PRE_APROVADO.getName(); 
			} else {
				status = StatusContrato.PRE_REJEITADO.getName();
			}
		}
			
		Contrato contrato = db.buscarContratoId(codContrato);
		contrato.setCliente(clt);
		contrato.setDataTerminoContrato((java.sql.Date) dataTermino);
		contrato.setQntdParcelas(numParcelas);
		contrato.setValorEmprestimo(valorEmprestimo);
		contrato.setValorParcelas(valorParcelas);
		contrato.setPlanoEmprestimo(plEmprestimo);
		contrato.setStatusContrato(status);
		contrato.setFuncionarioResponsavel(null);
		contrato.setObservacoes(observacoes);
		try{
			db.editarContratoBanco(contrato);
			persistidoSucesso = true;
		} catch (Exception e) {
			persistidoSucesso = false;
		}
		
		if (persistidoSucesso){
			InterfaceUsuario.exibirMensagemContratoCadastro("Contrato alterado com sucesso.");
		}else{
			InterfaceUsuario.exibirMensagemContratoCadastro("Não foi possível alterar o contrato.");
		}
	}
	
	public void recuperaContrato(int idContrato){
		Contrato contrato = db.buscarContratoId(idContrato);
		String cliente = contrato.getCliente().getNomeCompleto();
		String banco = contrato.getCliente().getDadosFinanceiros().getBanco();
		String agencia = contrato.getCliente().getDadosFinanceiros().getAgencia();
		int contaCorrente = contrato.getCliente().getDadosFinanceiros().getContaCorrente();
		Date dataTermino = contrato.getDataTerminoContrato();
		int numParcelas = contrato.getQntdParcelas();
		double valorEmprestimo = contrato.getValorEmprestimo();
		double valorParcelas= contrato.getValorParcelas();
		int idPlanoEmprestimo = contrato.getPlanoEmprestimo().getIdPlanoEmprestimo();
		String status = contrato.getStatusContrato();
		String observacoes = contrato.getObservacoes();
		InterfaceUsuario.carregarContrato(idContrato, cliente, banco, agencia, contaCorrente, valorEmprestimo, valorParcelas, (java.sql.Date) dataTermino, observacoes, idPlanoEmprestimo, numParcelas, status);
	}
	public boolean verificaCamposObrigatoriosContrato(){
		//TODO
		return true;
	}
	
	
}
