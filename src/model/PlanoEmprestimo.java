package model;

import java.sql.Date;

public class PlanoEmprestimo {

	private int idPlanoEmprestimo;
	private String nome;
	private Date dataCadastro;
	private double jurosTotal;
	private double jurosMensal;
	private double valorMinimo;
	private double valorMaximo;
	private int minParcelas;
	private int maxParcelas;
	private String observacao;
	private Funcionario funcionario;

	public PlanoEmprestimo(int idPlanoEmprestimo, String nome, Date dataCadastro, double jurosTotal, double jurosMensal,
			double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas, String observacao,
			Funcionario funcionario) {
		super();
		this.idPlanoEmprestimo = idPlanoEmprestimo;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.jurosTotal = jurosTotal;
		this.jurosMensal = jurosMensal;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.minParcelas = minParcelas;
		this.maxParcelas = maxParcelas;
		this.observacao = observacao;
		this.funcionario = funcionario;
	}

	public PlanoEmprestimo(String nome, Date dataCadastro, double jurosTotal, double jurosMensal, double valorMinimo,
			double valorMaximo, int minParcelas, int maxParcelas, String observacao, Funcionario funcionario) {
		super();
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.jurosTotal = jurosTotal;
		this.jurosMensal = jurosMensal;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.minParcelas = minParcelas;
		this.maxParcelas = maxParcelas;
		this.observacao = observacao;
		this.funcionario = funcionario;
	}

	public int getIdPlanoEmprestimo() {
		return idPlanoEmprestimo;
	}

	public void setIdPlanoEmprestimo(int idPlanoEmprestimo) {
		this.idPlanoEmprestimo = idPlanoEmprestimo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public double getJurosTotal() {
		return jurosTotal;
	}

	public void setJurosTotal(double jurosTotal) {
		this.jurosTotal = jurosTotal;
	}

	public double getJurosMensal() {
		return jurosMensal;
	}

	public void setJurosMensal(double jurosMensal) {
		this.jurosMensal = jurosMensal;
	}

	public double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public int getMinParcelas() {
		return minParcelas;
	}

	public void setMinParcelas(int minParcelas) {
		this.minParcelas = minParcelas;
	}

	public int getMaxParcelas() {
		return maxParcelas;
	}

	public void setMaxParcelas(int maxParcelas) {
		this.maxParcelas = maxParcelas;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
