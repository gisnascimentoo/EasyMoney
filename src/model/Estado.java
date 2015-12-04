package model;

public class Estado {
	
	private int idEstado;
	private String uf;
	
	public Estado(int idEstado, String uf) {
		super();
		this.idEstado = idEstado;
		this.uf = uf;
	}

	public Estado(String uf) {
		super();
		this.uf = uf;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
