package model;

import java.sql.Date;

public class Contrato {

	private int idContrato;
	private int qntdParcelas;
	private double valorEmprestimo;
	private double valorParcelas;
	private Date dataCriacaoContrato;
	private Date dataTerminoContrato;
	private Cliente cliente;
	private StatusContrato statusContrato;
	private Funcionario funcionarioResponsavel;
	private PlanoEmprestimo planoEmprestimo;

	public Contrato(int idContrato, int qntdParcelas, double valorEmprestimo, double valorParcelas,
			Date dataCriacaoContrato, Date dataTerminoContrato, Cliente cliente, StatusContrato statusContrato,
			Funcionario funcionarioResponsavel, PlanoEmprestimo planoEmprestimo) {
		super();
		this.idContrato = idContrato;
		this.qntdParcelas = qntdParcelas;
		this.valorEmprestimo = valorEmprestimo;
		this.valorParcelas = valorParcelas;
		this.dataCriacaoContrato = dataCriacaoContrato;
		this.dataTerminoContrato = dataTerminoContrato;
		this.cliente = cliente;
		this.statusContrato = statusContrato;
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.planoEmprestimo = planoEmprestimo;
	}

	public Contrato(int qntdParcelas, double valorEmprestimo, double valorParcelas, Date dataCriacaoContrato,
			Date dataTerminoContrato, Cliente cliente, StatusContrato statusContrato,
			Funcionario funcionarioResponsavel, PlanoEmprestimo planoEmprestimo) {
		super();
		this.qntdParcelas = qntdParcelas;
		this.valorEmprestimo = valorEmprestimo;
		this.valorParcelas = valorParcelas;
		this.dataCriacaoContrato = dataCriacaoContrato;
		this.dataTerminoContrato = dataTerminoContrato;
		this.cliente = cliente;
		this.statusContrato = statusContrato;
		this.funcionarioResponsavel = funcionarioResponsavel;
		this.planoEmprestimo = planoEmprestimo;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public int getQntdParcelas() {
		return qntdParcelas;
	}

	public void setQntdParcelas(int qntdParcelas) {
		this.qntdParcelas = qntdParcelas;
	}

	public double getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(double valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public double getValorParcelas() {
		return valorParcelas;
	}

	public void setValorParcelas(double valorParcelas) {
		this.valorParcelas = valorParcelas;
	}

	public Date getDataCriacaoContrato() {
		return dataCriacaoContrato;
	}

	public void setDataCriacaoContrato(Date dataCriacaoContrato) {
		this.dataCriacaoContrato = dataCriacaoContrato;
	}

	public Date getDataTerminoContrato() {
		return dataTerminoContrato;
	}

	public void setDataTerminoContrato(Date dataTerminoContrato) {
		this.dataTerminoContrato = dataTerminoContrato;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusContrato getStatusContrato() {
		return statusContrato;
	}

	public void setStatusContrato(StatusContrato statusContrato) {
		this.statusContrato = statusContrato;
	}

	public Funcionario getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
		this.funcionarioResponsavel = funcionarioResponsavel;
	}

	public PlanoEmprestimo getPlanoEmprestimo() {
		return planoEmprestimo;
	}

	public void setPlanoEmprestimo(PlanoEmprestimo planoEmprestimo) {
		this.planoEmprestimo = planoEmprestimo;
	}

}
