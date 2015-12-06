package control;

import view.InterfaceUsuario;
import view.LoginView;
import db.ManipuladorBanco;

public class Controller {

	ManipuladorBanco db;

	public Controller() {
		this.db = new ManipuladorBanco();
	}

	// TODO validações
	public void enviarClienteBanco() {

	}

	// TODO
	public void enviarEnderecoBanco() {

	}

	// TODO
	public void enviarCidadeBanco() {

	}

	// TODO validações
	public void enviarContratoBanco() {

	}

	// TODO validações
	public void enviarDadosFinanceirosBanco() {

	}

	// TODO
	public void enviarEstadoBanco() {

	}

	// TODO validações
	public void enviarFuncionarioBanco() {

	}

	// TODO validações
	public void enviarPlanoEmprestimoBanco() {

	}

	// TODO
	public void removerPlanoEmprestimoBanco() {

	}
	
	public void realizarLogin(String nome, String senha){
		boolean logado = db.realizarLogin(nome, senha);
		if(logado){
			InterfaceUsuario.loginValido();
		}else{
			InterfaceUsuario.informaLoginInvalido();
		}
		
	}

	public void logout() {
		boolean confirmacao = InterfaceUsuario.encerrarSessao();
		if(confirmacao)
			InterfaceUsuario.deslogar();
	}
	
	
	
	
}
