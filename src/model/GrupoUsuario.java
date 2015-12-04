package model;

public class GrupoUsuario {
	
	private int idGrupoUsuario;
	private String nomeGrupoUsuario;
	
	public GrupoUsuario(int idGrupoUsuario, String nomeGrupoUsuario) {
		super();
		this.idGrupoUsuario = idGrupoUsuario;
		this.nomeGrupoUsuario = nomeGrupoUsuario;
	}

	public GrupoUsuario(String nomeGrupoUsuario) {
		super();
		this.nomeGrupoUsuario = nomeGrupoUsuario;
	}

	public int getIdGrupoUsuario() {
		return idGrupoUsuario;
	}

	public void setIdGrupoUsuario(int idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}

	public String getNomeGrupoUsuario() {
		return nomeGrupoUsuario;
	}

	public void setNomeGrupoUsuario(String nomeGrupoUsuario) {
		this.nomeGrupoUsuario = nomeGrupoUsuario;
	}
}
