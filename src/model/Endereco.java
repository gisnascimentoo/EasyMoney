package model;

public class Endereco {

	private int idEndereco;
	private String logradouro;
	private int numero;
	private String bairro;
	private Cidade cidade;
	private String complemento;
	
	public Endereco(int idEndereco, String logradouro, int numero, String bairro, Cidade cidade, String complemento) {
		super();
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
	}

	public Endereco(String logradouro, int numero, String bairro, Cidade cidade, String complemento) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public int getIdEndereco() {
		return idEndereco;
	}
	
	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}
}
