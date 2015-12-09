package view;

import java.sql.Date;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import view.cadastros.list.ListClientesView;
import view.cadastros.list.ListContratoView;
import view.cadastros.list.ListFuncionariosView;
import view.cadastros.list.ListPlanosView;
import view.cadastros.viewsCad.CadClienteView;
import view.cadastros.viewsCad.CadFuncionarioView;
import view.cadastros.viewsCad.CadPlanoEmprestimoView;
import control.ContratoController;
import control.Controller;

public class InterfaceUsuario {

	private static Controller controller = new Controller();
	private static ContratoController contratoController = new ContratoController();
	private static LoginView loginView;
	private static PrincipalView principalView;
	private static CadClienteView cadClienteView;
	private static CadFuncionarioView cadFuncionarioView;
	private static CadPlanoEmprestimoView cadPlanoEmprestimoView;
	private static NovoContratoView novoContratoView;
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
		novoContratoView = new NovoContratoView();
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
			String logradouro, int numero, String bairro, String cep, String nomeCidade, String uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.criarCadastroCliente(cpf, nomeCompleto, rg, dataNascimento, logradouro, numero, bairro, cep,
				nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);

	}

	public static void editarCliente(int codigo, int cpf, String nomeCompleto, int rg, String dataNascimento,
			String logradouro, int numero, String bairro, String cep, String nomeCidade, String uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.editarCadastroCliente(codigo, cpf, nomeCompleto, rg, dataNascimento, logradouro, numero,
				bairro, cep, nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);

	}

	public static void cadastrarFuncionario(String nome, String dataNascimento, int CPF, int RG, String cargo,
			String email, int telefone, String logradouro, int numero, String bairro, String CEP, String nomeCidade,
			String uf) {
		controller.criarCadastrofuncionario(nome, dataNascimento, CPF, RG, cargo, email, telefone, logradouro,
				numero, bairro, CEP, nomeCidade, uf);
	}

	public static void editarFuncionario(int codigo, String nome, String dataNascimento, int CPF, int RG,
			String cargo, String email, int telefone, String logradouro, int numero, String bairro, String CEP,
			String nomeCidade, String uf) {
		controller.editarCadastrofuncionario(codigo, nome, dataNascimento, CPF, RG, cargo, email, telefone,
				logradouro, numero, bairro, CEP, nomeCidade, uf);
	}

	public static void carregaEdicaoFuncionario(int codigo, String nome, Date dataNascimento, int CPF, int RG,
			String cargo, String email, int telefone, String logradouro, int numero, String bairro, String CEP,
			String nomeCidade, String uf) {
		cadFuncionarioView = new CadFuncionarioView(codigo, nome, dataNascimento, CPF, RG, cargo, email, telefone,
				logradouro, numero, bairro, CEP, nomeCidade, uf);
	}

	public static void carregaEdicaoCliente(int codigo, int cpf, String nomeCompleto, int rg,
			Date dataNascimento, String logradouro, int numero, String bairro, String cep, String nomeCidade,
			String uf, String banco, String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal,
			String observacao) {
		cadClienteView = new CadClienteView(codigo, cpf, nomeCompleto, rg, dataNascimento, logradouro, numero, bairro,
				cep, nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao);
	}

	public static void buscarFuncionario(int codigo, String nome, String cpf, String date) {
		controller.buscarFuncionario(codigo, nome, cpf, date);

	}

	public static void buscarPlano(int codigo, String plano) {
		controller.buscarPlano(codigo, plano);
		System.out.println("Passei aqyu");

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
			int indexPlanoEmprestimo, int indexParcelas, String status) {
		novoContratoView = new NovoContratoView(codContrato, nomeCliente, banco, agencia, contaCorrente,
				valorEmprestimo, valorParcelas, dataTermino, observacao, indexPlanoEmprestimo, indexParcelas, status);

	}

	public static void novoContratoView(double valorEmprestimo, int valorParcelas, int indexPlanoEmprestimo,
			int indexNumeroParcelas) {
		novoContratoView = new NovoContratoView(valorEmprestimo, valorParcelas, indexPlanoEmprestimo,
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
		if (texto.trim().length() > 0) {
			retorno = -1;
		} else {
			try {
				retorno = Integer.parseInt(texto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não pôde fazer a conversão");
			}
		}
		return retorno;
	}

	public static double transformaStringDouble(String texto) {
		double retorno = 0;
		if (texto.trim().length() > 0) {
			retorno = -1;
		} else {
			try {
				retorno = Double.parseDouble(texto);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não pôde fazer a conversão");
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
			System.err.println("formatter is bad: " + exc.getMessage());
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
}
