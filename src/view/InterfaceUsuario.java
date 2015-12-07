package view;

import java.sql.Date;

import view.cadastros.list.ListClientesView;
import view.cadastros.list.ListFuncionariosView;
import view.cadastros.list.ListPlanosView;
import view.cadastros.viewsCad.CadClienteView;
import view.cadastros.viewsCad.CadFuncionarioView;
import view.cadastros.viewsCad.CadPlanoEmprestimoView;
import control.Controller;

public class InterfaceUsuario {

	private static Controller controller = new Controller();
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

	public static void loginValido() {
		loginView.dispose();
		principalView = new PrincipalView();
		principalView.setVisible(true);
	}

	public static void informaLoginInvalido() {
		loginView.informaLoginInvalido();

	}

	public static void cadClienteView() {
		cadClienteView = new CadClienteView(false);
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

	public static void buscarCliente(int codigo, String nome, String cpf,
			java.util.Date date) {
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

	public static void carregaListaFuncionario(
			String[][] dados) {
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

	public static void cadastrarCliente(int cpf, String nomeCompleto, int rg,
			java.util.Date dataNascimento, String logradouro, int numero,
			String bairro, String cep, String nomeCidade, String uf,
			String banco, String agencia, int contaCorrente,
			double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.criarCadastroCliente(cpf, nomeCompleto, rg,
				(Date) dataNascimento, logradouro, numero, bairro, cep,
				nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar,
				rendaPessoal, observacao);

	}

	public static void editarCliente(int codigo, int cpf, String nomeCompleto,
			int rg, java.util.Date dataNascimento, String logradouro,
			int numero, String bairro, String cep, String nomeCidade,
			String uf, String banco, String agencia, int contaCorrente,
			double rendaFamiliar, double rendaPessoal, String observacao) {
		controller.editarCadastroCliente(codigo, cpf, nomeCompleto, rg,
				(Date) dataNascimento, logradouro, numero, bairro, cep,
				nomeCidade, uf, banco, agencia, contaCorrente, rendaFamiliar,
				rendaPessoal, observacao);

	}

	public static void cadastrarFuncionario(String nome, java.util.Date dataNascimento,
			int CPF, int RG, String cargo, String email, int telefone,
			String logradouro, int numero, String bairro,
			String CEP, String nomeCidade, String uf) {
		controller.criarCadastrofuncionario(nome, (Date)dataNascimento, CPF, RG,
				cargo, email, telefone, logradouro, numero, bairro,
				CEP, nomeCidade, uf);
	}

	public static void editarFuncionario(int codigo, String nome,
			java.util.Date dataNascimento, int CPF, int RG, String cargo, String email,
			int telefone, String logradouro, int numero,
			String bairro, String CEP, String nomeCidade, String uf) {
		controller.editarCadastrofuncionario(codigo, nome, (Date)dataNascimento, CPF,
				RG, cargo, email, telefone, logradouro, numero,
				bairro, CEP, nomeCidade, uf);
	}
	
	public static void carregaEdicaoFuncionario(int codigo, String nome,
			java.util.Date dataNascimento, int CPF, int RG, String cargo, String email,
			int telefone, String logradouro, int numero,
			String bairro, String CEP, String nomeCidade, String uf){
		cadFuncionarioView = new CadFuncionarioView(codigo, nome,
				dataNascimento, CPF, RG, cargo, email,
				telefone,logradouro, numero,
				bairro, CEP, nomeCidade, uf);
	}
	
	public static void carregaEdicaoCliente(int codigo, int cpf, String nomeCompleto,
			int rg, java.util.Date dataNascimento, String logradouro,
			int numero, String bairro, String cep, String nomeCidade,
			String uf, String banco, String agencia, int contaCorrente,
			double rendaFamiliar, double rendaPessoal, String observacao){
		cadClienteView = new CadClienteView(codigo, cpf, nomeCompleto,
				rg, dataNascimento, logradouro,
				numero, bairro, cep, nomeCidade,
				uf, banco, agencia, contaCorrente,
				rendaFamiliar, rendaPessoal, observacao);
	}

	public static void buscarFuncionario(int codigo, String nome, String cpf,
			java.util.Date date) {
		controller.buscarFuncionario(codigo, nome, cpf, date);
		
	}
	
	
}
