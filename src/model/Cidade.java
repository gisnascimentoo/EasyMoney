package model;

public class Cidade {

	private int idCidade;
	private String nome;
	private Endereco endereco;
	
	public Cidade(String nome, Endereco endereco) {
		super();
		this.nome = nome;
		this.endereco = endereco;
	}
	public Cidade(int idCidade, String nome, Endereco endereco) {
		super();
		this.idCidade = idCidade;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public int getIdCidade() {
		return idCidade;
	}
	
	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
