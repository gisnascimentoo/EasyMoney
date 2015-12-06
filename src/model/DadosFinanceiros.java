package model;

public class DadosFinanceiros {

	private int idDadosFinanceiros;
	private String banco;
	private String agencia;
	private int contaCorrente;
	private double rendaFamiliar;
	private double rendaPessoal;
	private String observacao;
	
	public DadosFinanceiros(int idDadosFinanceiros, String banco, String agencia, int contaCorrente,
			double rendaFamiliar, double rendaPessoal, String observacao) {
		super();
		this.idDadosFinanceiros = idDadosFinanceiros;
		this.banco = banco;
		this.agencia = agencia;
		this.contaCorrente = contaCorrente;
		this.rendaFamiliar = rendaFamiliar;
		this.rendaPessoal = rendaPessoal;
		this.observacao = observacao;
	}
	
	public DadosFinanceiros(String banco, String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal,
			String observacao) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.contaCorrente = contaCorrente;
		this.rendaFamiliar = rendaFamiliar;
		this.rendaPessoal = rendaPessoal;
		this.observacao = observacao;
	}

	public DadosFinanceiros() {
		// TODO Auto-generated constructor stub
	}

	public int getIdDadosFinanceiros() {
		return idDadosFinanceiros;
	}

	public void setIdDadosFinanceiros(int idDadosFinanceiros) {
		this.idDadosFinanceiros = idDadosFinanceiros;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public int getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(int contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public double getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(double rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public double getRendaPessoal() {
		return rendaPessoal;
	}

	public void setRendaPessoal(double rendaPessoal) {
		this.rendaPessoal = rendaPessoal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
