package model;

import java.sql.Date;

public class Cliente {

	private int idCliente;
	private int CPF;
	private String nomeCompleto;
	private int RG;
	private Date dataNascimento;
	private Endereco endereco;
	private DadosFinanceiros dadosFinanceiros;

	public Cliente(int idCliente, int CPF, String nomeCompleto, int RG, Date dataNascimento, Endereco endereco,
			DadosFinanceiros dadosFinanceiros) {
		super();
		this.idCliente = idCliente;
		this.CPF = CPF;
		this.nomeCompleto = nomeCompleto;
		this.RG = RG;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.dadosFinanceiros = dadosFinanceiros;
	}

	public Cliente(int CPF, String nomeCompleto, int RG, Date dataNascimento, Endereco endereco,
			DadosFinanceiros dadosFinanceiros) {
		super();
		this.CPF = CPF;
		this.nomeCompleto = nomeCompleto;
		this.RG = RG;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.dadosFinanceiros = dadosFinanceiros;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getCPF() {
		return CPF;
	}

	public void setCPF(int CPF) {
		this.CPF = CPF;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public int getRG() {
		return RG;
	}

	public void setRG(int RG) {
		this.RG = RG;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public DadosFinanceiros getDadosFinanceiros() {
		return dadosFinanceiros;
	}

	public void setDadosFinanceiros(DadosFinanceiros dadosFinanceiros) {
		this.dadosFinanceiros = dadosFinanceiros;
	}

}
