package view.cadastros.representacoesInterface;

import java.sql.Date;

public class PessoaView {

	int codigo;
	String nome;
	Date dataNasc;
	String cpf;

	public PessoaView(int codigo, String nome, Date dataNasc, String cpf) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
