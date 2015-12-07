package control;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import model.Cidade;
import model.Cliente;
import model.DadosFinanceiros;
import model.Endereco;
import model.Estado;
import model.Funcionario;
import view.InterfaceUsuario;
import db.ManipuladorBanco;

public class Controller {

	ManipuladorBanco db;

	public Controller() {
		this.db = new ManipuladorBanco();
	}

	// TODO validações
	public void enviarClienteBanco() {

	}

	// TODO
	public void enviarEnderecoBanco() {

	}

	// TODO
	public void enviarCidadeBanco() {

	}

	// TODO validações
	public void enviarContratoBanco() {

	}

	// TODO validações
	public void enviarDadosFinanceirosBanco() {

	}

	// TODO
	public void enviarEstadoBanco() {

	}

	// TODO validações
	public void enviarFuncionarioBanco() {

	}

	// TODO validações
	public void enviarPlanoEmprestimoBanco() {

	}

	// TODO
	public void removerPlanoEmprestimoBanco() {

	}

	public void realizarLogin(String nome, String senha) {
		boolean logado = db.realizarLogin(nome, senha);
		if (logado) {
			InterfaceUsuario.loginValido();
		} else {
			InterfaceUsuario.informaLoginInvalido();
		}

	}

	public void logout() {
		boolean confirmacao = InterfaceUsuario.encerrarSessao();
		if (confirmacao)
			InterfaceUsuario.deslogar();
	}

	public void buscarCliente(int codigo, String nome, String cpf,
			java.util.Date date) {
		List<Cliente> clientesBusca = db.buscarCliente(codigo, nome, cpf,
				(Date) date);
		List<view.cadastros.representacoesInterface.PessoaView> clientesView = new ArrayList<view.cadastros.representacoesInterface.PessoaView>();
		for (Cliente cliente : clientesBusca) {
			clientesView
					.add(new view.cadastros.representacoesInterface.PessoaView(
							cliente.getIdCliente(), cliente.getNomeCompleto(),
							cliente.getDataNascimento(), "" + cliente.getCPF()));
		}
		InterfaceUsuario.carregaListaCliente(clientesBusca);
	}

	public void excluirCliente(int id) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoCliente();
		if (confirmacao) {
			String mensagem = db.excluiCliente(id);
			InterfaceUsuario.exibirMensagemCliente(mensagem);
		}
	}

	public void editarCadastroCliente(int codigo, int cpf, String nomeCompleto, int rg,
			Date dataNascimento, String logradouro, int numero, String bairro,
			String cep, String nomeCidade, String uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar,
			double rendaPessoal, String observacao) {
		Cliente cliente = new Cliente(codigo, cpf, nomeCompleto, rg, dataNascimento,
				new Endereco(logradouro, numero, bairro, cep, new Cidade(
						nomeCidade, new Estado(uf))), new DadosFinanceiros(
						banco, agencia, contaCorrente, rendaFamiliar,
						rendaPessoal, observacao));
		String msg = db.editarClienteBanco(cliente);
		InterfaceUsuario.exibirMensagemCliente(msg);
	}
	
	public void criarCadastroCliente(int cpf, String nomeCompleto, int rg,
			Date dataNascimento, String logradouro, int numero, String bairro,
			String cep, String nomeCidade, String uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar,
			double rendaPessoal, String observacao){
		boolean cpfExiste = db.verificarCpfCliente(cpf);
		if(cpfExiste){
			int opcao = InterfaceUsuario.exibirMensagemCpfExistente();
			if(opcao == 1){
				InterfaceUsuario.cancelarCriacaoCliente();
			}
		}else{
			boolean idadeValida = verificarIdadeCliente(dataNascimento);
			if(!idadeValida){
				//TODO
			}else{
			   boolean camposOK =verificaCamposObrigatoriosCliente();	
			   if(!camposOK){
				   InterfaceUsuario.exibirMensagemCliente("Existem campos obrigatórios não preenchidos");
			   }else{
				   Cliente cliente = new Cliente(cpf, nomeCompleto, rg, dataNascimento,
							new Endereco(logradouro, numero, bairro, cep, new Cidade(
									nomeCidade, new Estado(uf))), new DadosFinanceiros(
									banco, agencia, contaCorrente, rendaFamiliar,
									rendaPessoal, observacao));
				   db.salvarClienteBanco(cliente);
				   InterfaceUsuario.exibirMensagemCliente("Cadastro realizado com sucesso");
			   }
			}
		}
		
		
	}

	private boolean verificaCamposObrigatoriosCliente() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean verificarIdadeCliente(Date dataNascimento) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void buscarFuncionario(int codigo, String nome, String cpf,
			java.util.Date date) {
		List<Funcionario> funcionariosBusca = db.buscarFuncionario(codigo, nome, cpf,
				(Date) date);
		List<view.cadastros.representacoesInterface.PessoaView> funcionariosView = new ArrayList<view.cadastros.representacoesInterface.PessoaView>();
		for (Funcionario funcionario : funcionariosBusca) {
			funcionariosView
					.add(new view.cadastros.representacoesInterface.PessoaView(
							funcionario.getIdFuncionario(), funcionario.getNome(),
							funcionario.getDataNascimento(), "" + funcionario.getCPF()));
		}
		InterfaceUsuario.carregaListaFuncionario(funcionariosBusca);
	}

	public void excluirfuncionario(int id) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoFuncionario();
		if (confirmacao) {
			String mensagem = db.excluiFuncionario(id);
			InterfaceUsuario.exibirMensagemFuncionario(mensagem);
		}
	}

	public void editarCadastrofuncionario(int codigo,String nome, Date dataNascimento, int CPF, int RG, String cargo, String email, int telefone,
			String logradouro, int numero, String bairro, String CEP, String nomeCidade, String uf) {
		Funcionario funcionario = new Funcionario(codigo, nome, dataNascimento, CPF, RG, cargo, email, telefone, new Endereco(logradouro, numero, bairro, CEP, new Cidade(
				nomeCidade, new Estado(uf))));
		String msg = db.editarFuncionarioBanco(funcionario);
		InterfaceUsuario.exibirMensagemFuncionario(msg);
	}
	
	public void criarCadastrofuncionario(String nome, Date dataNascimento, int CPF, int RG, String cargo, String email, int telefone,
			String logradouro, int numero, String bairro, String CEP, String nomeCidade, String uf){
		boolean cpfExiste = db.verificarCpfFuncionario(CPF);
		if(cpfExiste){
			int opcao = InterfaceUsuario.exibirMensagemCpfExistenteFuncionario();
			if(opcao == 1){
				InterfaceUsuario.cancelarCriacaoFuncionario();
			}
		}else{
			   boolean camposOK =verificaCamposObrigatoriosFuncionario();	
			   if(!camposOK){
				   InterfaceUsuario.exibirMensagemFuncionario("Campos obrigatórios não preenchidos");
			   }else{
				   Funcionario funcionario = new Funcionario(nome, dataNascimento, CPF, RG, cargo, email, telefone, new Endereco(logradouro, numero, bairro, CEP, new Cidade(
							nomeCidade, new Estado(uf))));
				   db.salvarFuncionarioBanco(funcionario);
				   InterfaceUsuario.exibirMensagemFuncionario("Cadastro realizado com sucesso");
			   }
			}
		}

	private boolean verificaCamposObrigatoriosFuncionario() {
		// TODO Auto-generated method stub
		return false;
	}
}
