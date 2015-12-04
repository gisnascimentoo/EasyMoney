	package model;

public class StatusContrato {

	private int idStatusContrato;
	private String nome;

	public StatusContrato(int idStatusContrato, String nome) {
		super();
		this.idStatusContrato = idStatusContrato;
		this.nome = nome;
	}
	
	public StatusContrato(String nome) {
		super();
		this.nome = nome;
	}
	
	public int getIdStatusContrato() {
		return idStatusContrato;
	}
	
	public void setIdStatusContrato(int idStatusContrato) {
		this.idStatusContrato = idStatusContrato;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
