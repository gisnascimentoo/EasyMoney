package model;

public class Usuario {
	
	private int idUsuario;
	private String nome;
	private String login;
	private String passwd;
	private GrupoUsuario grupoUsuario;

	public Usuario(int idUsuario, String nome, String login, String passwd, GrupoUsuario grupoUsuario) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.login = login;
		this.passwd = passwd;
		this.grupoUsuario = grupoUsuario;
	}

	public Usuario(String nome, String login, String passwd, GrupoUsuario grupoUsuario) {
		super();
		this.nome = nome;
		this.login = login;
		this.passwd = passwd;
		this.grupoUsuario = grupoUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}