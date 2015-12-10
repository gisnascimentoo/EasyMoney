package view;

import java.sql.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import view.cadastros.list.ListClientesView;
import view.cadastros.list.ListContratoView;
import view.cadastros.list.ListFuncionariosView;
import view.cadastros.list.ListPlanosView;
import view.cadastros.viewsCad.CadClienteView;
import view.cadastros.viewsCad.CadContratoView;
import view.cadastros.viewsCad.CadFuncionarioView;
import view.cadastros.viewsCad.CadPlanoEmprestimoView;
import view.combo.ClienteCombo;
import view.combo.EstadoCombo;
import view.combo.PlanoCombo;
import view.combo.SituacaoCombo;
import control.ContratoController;
import control.Controller;
import model.PlanoEmprestimo;

public class InterfaceUsuario {

	private static Controller controller = new Controller();
	private static ContratoController contratoController = new ContratoController();
	private static LoginView loginView;
	private static PrincipalView principalView;
	private static CadClienteView cadClienteView;
	private static CadFuncionarioView cadFuncionarioView;
	private static CadPlanoEmprestimoView cadPlanoEmprestimoView;
	private static CadContratoView novoContratoView;
	private static RelatoriosEmprestimoView relatoriosEmprestimoView;
	private static SimulacaoView simulacaoView;
	private static ListClientesView listClienteView;
	private static ListFuncionariosView listFuncionariosView;
	private static ListPlanosView listPlanosView;
	private static ListContratoView listContratoView;

	public InterfaceUsuario() {
		loginView = new LoginView();
		loginView.setVisible(true);
		loginValido();
	}

	public static void fazerLogin(LoginView loginView, String nome, String senha) {
		controller.realizarLogin(nome, senha);
	}

	public static void listClienteView() {
		listClienteView = new ListClientesView();
		listClienteView.setVisible(true);
	}

	
	public static void listContratoView() {
		listContratoView = new ListContratoView();
		listContratoView.setVisible(true);
	}
	
	public static void loginValido() {
		loginView.dispose();
		principalView = new PrincipalView();
		principalView.setVisible(true);
	}

	public static void informaLoginInvalido() {
		loginView.informaLoginInvalido();
	}

	public static void cadClienteView() {
		cadClienteView = new CadClienteView();
		cadClienteView.setVisible(true);
	}

	public static void cadFuncionarioView() {
		cadFuncionarioView = new CadFuncionarioView();
		cadFuncionarioView.setVisible(true);
	}

	public static void cadPlanoEmprestimoView() {
		cadPlanoEmprestimoView = new CadPlanoEmprestimoView();
		cadPlanoEmprestimoView.setVisible(true);
	}

	public static void novoContratoView() {
		novoContratoView = new CadContratoView();
		novoContratoView.setVisible(true);
	}

	public static void relatoriosEmprestimoView() {
		relatoriosEmprestimoView = new RelatoriosEmprestimoView();
		relatoriosEmprestimoView.setVisible(true);
	}

	public static void simulacaoView() {
		simulacaoView = new SimulacaoView();
		simulacaoView.setVisible(true);
	}

	public static void listFuncionariosView() {
		listFuncionariosView = new ListFuncionariosView();
		listFuncionariosView.setVisible(true);
	}

	public static void logout() {
		controller.logout();

	}

	public static boolean encerrarSessao() {
		return principalView.encerrarSessao();
	}

	public static void deslogar() {
		principalView.dispose();

	}

	public static void carregaListaCliente(String[][] dados) {
		listClienteView.addTabela(dados);
	}

	public static void buscarCliente(int codigo, String nome, String cpf, String date) {
		if(date.equals("  /  /    ")) {
			date = null;
		}
		controller.buscarCliente(codigo, nome, cpf, date);
	}

	public static boolean confirmarExclusaoCliente() {
		return listClienteView.confirmaExclusao();
	}

	public static void exibirMensagemCliente(String mensagem) {
		listClienteView.mostrarMensagem(mensagem);

	}

	public static int exibirMensagemCpfExistente() {
		return listClienteView.MensagemCpfExistente();

	}

	public static void cancelarCriacaoCliente() {
		listClienteView.dispose();
	}

	public static void carregaListaFuncionario(String[][] dados) {
		listFuncionariosView.addTabela(dados);
	}

	public static void exibirMensagemFuncionario(String mensagem) {
		listFuncionariosView.mostrarMensagem(mensagem);
	}

	public static void cancelarCriacaoFuncionario() {
		listFuncionariosView.dispose();
	}

	public static boolean confirmarExclusaoFuncionario() {
		return listFuncionariosView.confirmaExclusao();
	}

	public static int exibirMensagemCpfExistenteFuncionario() {
		return listFuncionariosView.MensagemCpfExistente();
	}

	public static void listPlanosView() {
		listPlanosView = new ListPlanosView();
		listPlanosView.setVisible(true);
	}

	public static void cadastrarCliente(int cpf, String nomeCompleto, int rg, String dataNascimento,
			String logradouro, int numero, String bairro, String nomeCidade, int uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.criarCadastroCliente(cpf, nomeCompleto, rg, dataNascimento, logradouro, numero, bairro,
				nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);

	}

	public static void editarCliente(int codigo, int cpf, String nomeCompleto, int rg, String dataNascimento,
			String logradouro, int numero, String bairro, String nomeCidade, int uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.editarCadastroCliente(codigo, cpf, nomeCompleto, rg, dataNascimento, logradouro, numero,
				bairro, nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);

	}

	public static void cadastrarFuncionario(String nome, String dataNascimento, int CPF, int RG, String cargo,
			String email, int telefone, String logradouro, int numero, String bairro, String nomeCidade,
			int uf) {
		controller.criarCadastrofuncionario(nome, dataNascimento, CPF, RG, cargo, email, telefone, logradouro,
				numero, bairro, nomeCidade, uf);
	}

	public static void editarFuncionario(int codigo, String nome, String dataNascimento, int CPF, int RG,
			String cargo, String email, int telefone, String logradouro, int numero, String bairro,
			String nomeCidade, int uf) {
		controller.editarCadastrofuncionario(codigo, nome, dataNascimento, CPF, RG, cargo, email, telefone,
				logradouro, numero, bairro, nomeCidade, uf);
	}

	public static void carregaEdicaoFuncionario(int codigo, String nome, Date dataNascimento, int CPF, int RG,
			String cargo, String email, int telefone, String logradouro, int numero, String bairro,
			String nomeCidade, String uf, int idUf) {
		cadFuncionarioView = new CadFuncionarioView(codigo, nome, dataNascimento, CPF, RG, cargo, email, telefone,
				logradouro, numero, bairro, nomeCidade, uf, idUf);
	}

	public static void carregaEdicaoCliente(int codigo, int cpf, String nomeCompleto, int rg,
			Date dataNascimento, String logradouro, int numero, String bairro, String nomeCidade,
			int iduf, String uf, String banco, String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal,
			String observacao) {
		cadClienteView = new CadClienteView(codigo, cpf, nomeCompleto, rg, dataNascimento, logradouro, numero, bairro,
				nomeCidade, iduf, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);
		cadClienteView.setVisible(true);
	}

	public static void buscarFuncionario(int codigo, String nome, String cpf, String date) {
		if(date.equals("  /  /    ")) {
			date = null;
		}
		controller.buscarFuncionario(codigo, nome, cpf, date);

	}

	public static void buscarPlano(int codigo, String plano) {
		controller.buscarPlano(codigo, plano);
	}

	public static void carregaListaPlano(String[][] dados) {
		listPlanosView.addTabela(dados);
	}

	public static boolean confirmarExclusaoPlano() {
		return listPlanosView.confirmaExclusao();
	}

	public static void exibirMensagemPlano(String mensagem) {
		listPlanosView.mostrarMensagem(mensagem);
	}

	public static void exibirMensagemPlanoCadastro(String mensagem) {
		cadPlanoEmprestimoView.mostrarMensagem(mensagem);
	}

	public static void exibirMensagemClienteCadastro(String mensagem) {
		cadClienteView.mostrarMensagem(mensagem);
	}

	public static void exibirMensagemFuncionarioCadastro(String mensagem) {
		cadFuncionarioView.mostrarMensagem(mensagem);
	}

	public static void cadastrarPlano(String nome, String dataCadastro, double jurosTotal, double jurosMensal,
			double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas, String observacao) {
		controller.criarCadastroPlano(nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo, valorMaximo,
				minParcelas, maxParcelas, observacao);
	}

	public static void editarPlano(int idPlanoEmprestimo, String nome, String dataCadastro, double jurosTotal,
			double jurosMensal, double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas,
			String observacao) {
		controller.editarCadastroPlano(idPlanoEmprestimo, nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo,
				valorMaximo, minParcelas, maxParcelas, observacao);
	}

	public static void carregarPlano(int idPlanoEmprestimo, String nome, Date dataCadastro, double jurosTotal,
			double jurosMensal, double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas,
			String observacao) {
		cadPlanoEmprestimoView = new CadPlanoEmprestimoView(idPlanoEmprestimo, nome, dataCadastro, jurosTotal,
				jurosMensal, valorMinimo, valorMaximo, minParcelas, maxParcelas, observacao);
	}

	public static void carregarEdicaoPlano(int codigo, String nome, Date dataCadastro, double jurosTotal,
			double jurosMensal, double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas,
			String observacao) {
		cadPlanoEmprestimoView = new CadPlanoEmprestimoView(codigo, nome, dataCadastro, jurosTotal, jurosMensal,
				valorMinimo, valorMaximo, minParcelas, maxParcelas, observacao);

	}

	public static void cadastrarContrato(int idCliente, String status, int idPlanoEmprestimo, int numParcelas,
			double valorEmprestimo, double valorParcelas, String dataTermino, String observacoes) {
		contratoController.salvarContrato(idCliente, status, idPlanoEmprestimo, numParcelas, valorEmprestimo,
				valorParcelas, dataTermino, observacoes);
	}

	public static void editarContrato(int codContrato, int idCliente, String status, int idPlanoEmprestimo,
			int numParcelas, double valorEmprestimo, double valorParcelas, String dataTermino, String observacoes) {
		contratoController.editarContrato(codContrato, idCliente, status, idPlanoEmprestimo, numParcelas,
				valorEmprestimo, valorParcelas, dataTermino, observacoes);
	}

	public static void carregarContrato(int codContrato, String nomeCliente, String banco, String agencia,
			int contaCorrente, double valorEmprestimo, double valorParcelas, Date dataTermino, String observacao,
			String nomePlano, int qntdParelas, String status, int codCliente, int codPlano) {
		novoContratoView = new CadContratoView(codContrato, nomeCliente, banco, agencia, contaCorrente,
				valorEmprestimo, valorParcelas, dataTermino, observacao, nomePlano, qntdParelas, status, codCliente, codPlano);
	}

	public static void novoContratoView(double valorEmprestimo, int valorParcelas, int indexPlanoEmprestimo,
			int indexNumeroParcelas) {
		novoContratoView = new CadContratoView(valorEmprestimo, valorParcelas, indexPlanoEmprestimo,
				indexNumeroParcelas);
	}

	public static void exibirMensagemContratoCadastro(String mensagem) {
		novoContratoView.mostrarMensagem(mensagem);
	}

	public static void buscarDadosCliente(int idCliente) {
		contratoController.localizaCliente(idCliente);
	}

	public static void novoContratoView(int idContrato) {
		contratoController.recuperaContrato(idContrato);
	}

	public static void carregarDadosCliente(String banco, String agencia, int contaCorrente) {
		novoContratoView.povoarDadosCliente(banco, agencia, contaCorrente);
	}

	public static void editarClienteCarregarPorId(int codigo) {
		controller.buscaDadosCliente(codigo);

	}

	public static void excluirCliente(int codigo) {
		controller.excluirCliente(codigo);
	}

	public static void editarFuncionarioCarregarPorId(int codigo) {
		controller.buscaDadosCliente(codigo);

	}

	public static void excluirFuncionario(int codigo) {
		controller.excluirfuncionario(codigo);

	}

	public static void editarPlanoCarregarPorId(int codigo) {
		controller.buscaDadosPlano(codigo);

	}

	public static void excluirPlanoCarregarPorId(int codigo) {
		controller.excluirPlano(codigo);
	}

	public static int transformaStringInt(String texto) {
		int retorno = 0;
		if (texto.trim().length() == 0) {
			retorno = -1;
		} else {
			try {
				retorno = Integer.parseInt(texto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nao pode fazer a conversao");
			}
		}
		return retorno;
	}

	public static double transformaStringDouble(String texto) {
		double retorno = 0;
		if (texto.trim().length() == 0) {
			retorno = -1;
		} else {
			try {
				retorno = Double.parseDouble(texto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nao pode fazer a conversao");
			}
		}
		return retorno;
	}

	public static MaskFormatter createFormatter(String format) {
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter(format);
			formatter.setPlaceholderCharacter(' ');
			formatter.setAllowsInvalid(false);
			formatter.setOverwriteMode(true);
		} catch (java.text.ParseException exc) {
			exc.getMessage();
		}
		return formatter;
	}

	public static void gerarRelatorio(String intervaloInicio, String intervaloFim, int tipoIndex) {
		controller.geraRelatorio(intervaloInicio, intervaloFim, tipoIndex);
	}

	public static void carregaRelatorio(String[][] dados) {
		relatoriosEmprestimoView.addTabela(dados);
	}

	public static void excluirContrato(int codigo) {
		contratoController.excluirContrato(codigo);
		
	}

	public static void editarContratoCarregarPorId(int codigo) {
		contratoController.recuperaContrato(codigo);
		
	}

	public static boolean confirmarExclusaoContrato() {
		return listContratoView.confirmaExclusao();
	}

	public static List<SituacaoCombo> carregaSituacaoCombo() {
		return contratoController.preparaComboSituacao();
	}

	public static void buscarContrato(int codigo, String nome, String codigoSitucao) {
		contratoController.buscarContrato(codigo, nome, codigoSitucao);
	}

	public static List<EstadoCombo> carregaEstadoCombo() {
		 return controller.preparaComboEstado();
	}

	public static List<ClienteCombo> carregaClienteCombo() {
		return controller.preparaComboCliente();
	}

	public static List<PlanoCombo> carregaPlanoCombo() {
		return controller.preparaComboPlano();
	}

	public static int getQntdParcelasParaPlano(int codigo) {
		return contratoController.getQntdParcelasParaPlano(codigo);
	}

	public static double calcularSimulacao(String numMeses, double valorParcelas) {
		return contratoController.calculaValorFinalEmprestimo(Integer.parseInt(numMeses), valorParcelas);
	}
	
	public static double calcularValorParcelas(String planoEmprestimo, String numMeses, String valorFinanciado){
		return contratoController.calculaValorParcelas(Integer.parseInt(numMeses), Double.parseDouble(valorFinanciado), planoEmprestimo);
	}
	
	public static void carregaDadosTabelaSimulacao(String[][] dados, double valorParcela) {
		simulacaoView.setValorParcela(valorParcela);
		simulacaoView.addTabela(dados);
	}

	public static int getMinParcelasParaPlano(int codigo) {
		return contratoController.getMinQntdParcelasParaPlano(codigo);
	}
	
	
}
