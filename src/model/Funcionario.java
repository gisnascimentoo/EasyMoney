package model;

import java.sql.Date;

public class Funcionario {
	
	private int idFuncionario;
	private String nome;
	private Date dataNascimento;
	private int CPF;
	private int RG;
	private String cargo;
	private String email;
	private int telefone;
	private Endereco endereco;
	
	public Funcionario(int idFuncionario, String nome, Date dataNascimento, int CPF, int RG, String cargo, String email,
			int telefone, Endereco endereco) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.CPF = CPF;
		this.RG = RG;
		this.cargo = cargo;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public Funcionario(String nome, Date dataNascimento, int CPF, int RG, String cargo, String email, int telefone,
			Endereco endereco) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.CPF = CPF;
		this.RG = RG;
		this.cargo = cargo;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCPF() {
		return CPF;
	}

	public void setCPF(int cPF) {
		CPF = cPF;
	}

	public int getRG() {
		return RG;
	}

	public void setRG(int rG) {
		RG = rG;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
