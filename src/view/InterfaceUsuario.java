package view;

import control.Controller;

public class  InterfaceUsuario {

	private static Controller controller = new Controller();
	private static LoginView loginView;
	private static PrincipalView principalView;
	private static CadClienteView cadClienteView;
	private static CadFuncionarioView cadFuncionarioView;
	private static CadPlanoEmprestimoView cadPlanoEmprestimoView;
	private static NovoContratoView novoContratoView;
	private static RelatoriosEmprestimoView relatoriosEmprestimoView;
	private static SimulacaoView simulacaoView;

	public InterfaceUsuario() {
		loginView = new LoginView();
		loginView.setVisible(true);
		loginValido();
	}
	
	public static void fazerLogin(LoginView loginView, String nome, String senha){
		controller.realizarLogin(nome, senha);
		
	}

	public static void loginValido() {
		loginView.dispose();
		principalView = new PrincipalView();
		principalView.setVisible(true);
	}

	public static void informaLoginInvalido() {
		loginView.informaLoginInvalido();
		
	}
	
	public static void cadClienteView(){
		cadClienteView = new CadClienteView();
		cadClienteView.setVisible(true);
	}
	
	public static void cadFuncionarioView(){
		cadFuncionarioView = new CadFuncionarioView();
		cadFuncionarioView.setVisible(true);
	}
	
	public static void cadPlanoEmprestimoView(){
		cadPlanoEmprestimoView = new CadPlanoEmprestimoView();
		cadPlanoEmprestimoView.setVisible(true);
	}
	
	public static void novoContratoView(){
		novoContratoView = new NovoContratoView();
		novoContratoView.setVisible(true);
	}
	
	public static void relatoriosEmprestimoView(){
		relatoriosEmprestimoView = new RelatoriosEmprestimoView();
		relatoriosEmprestimoView.setVisible(true);
	}
	
	public static void simulacaoView(){
		simulacaoView = new SimulacaoView();
		simulacaoView.setVisible(true);
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
	
	
	

}
