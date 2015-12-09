package control;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Cidade;
import model.Cliente;
import model.Contrato;
import model.DadosFinanceiros;
import model.Endereco;
import model.Estado;
import model.Funcionario;
import model.PlanoEmprestimo;
import view.InterfaceUsuario;
import db.ManipuladorBanco;

public class Controller {

	ManipuladorBanco db;

	public Controller() {
		this.db = new ManipuladorBanco();
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

	public void buscarCliente(int codigo, String nome, String cpf, String date) {

		List<Cliente> clientesBusca = db.buscarCliente(codigo, nome, cpf, this.formatDate(date));
		String[][] dados = new String[clientesBusca.size()][4];
		int indice = 0;
		for (Cliente cliente : clientesBusca) {
			dados[indice][0] = "" + cliente.getIdCliente();
			dados[indice][1] = cliente.getNomeCompleto();
			dados[indice][2] = "" + cliente.getDataNascimento();
			dados[indice][3] = "" + cliente.getCPF();
			indice++;
		}
		InterfaceUsuario.carregaListaCliente(dados);
	}

	public void excluirCliente(int id) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoCliente();
		if (confirmacao) {
			String mensagem = db.excluiCliente(id);
			InterfaceUsuario.exibirMensagemCliente(mensagem);
		}
	}

	public void editarCadastroCliente(int codigo, int cpf, String nomeCompleto, int rg, String dataNascimento,
			String logradouro, int numero, String bairro, String cep, String nomeCidade, String uf, String banco,
			String agencia, int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		Estado novoUf = new Estado(uf);
		Cidade novaCidade = new Cidade(nomeCidade, novoUf);
		Endereco novoEndereco = new Endereco(logradouro, numero, bairro, cep, novaCidade);
		DadosFinanceiros novoDadosFinanceiro = new DadosFinanceiros(banco, agencia, contaCorrente, rendaFamiliar,
				rendaPessoal, observacao);
		Cliente cliente = new Cliente(cpf, nomeCompleto, rg, this.formatDate(dataNascimento), novoEndereco, novoDadosFinanceiro);
		String msg = db.editarClienteBanco(cliente);
		InterfaceUsuario.exibirMensagemClienteCadastro(msg);
	}

	public void criarCadastroCliente(int cpf, String nomeCompleto, int rg, String dataNascimento, String logradouro,
			int numero, String bairro, String cep, String nomeCidade, String uf, String banco, String agencia,
			int contaCorrente, double rendaFamiliar, double rendaPessoal, String observacao) {
		boolean cpfExiste = db.verificarCpfCliente(cpf);
		if (cpfExiste) {
			int opcao = InterfaceUsuario.exibirMensagemCpfExistente();
			if (opcao == 1) {
				InterfaceUsuario.cancelarCriacaoCliente();
			}
		} else {
			boolean idadeValida = verificarIdadeCliente(this.formatDate(dataNascimento));
			if (!idadeValida) {
				InterfaceUsuario.exibirMensagemClienteCadastro("Idade do cliente inválida");
			} else {
				boolean camposOK = verificaCamposObrigatoriosCliente(nomeCompleto, this.formatDate(dataNascimento), cpf, rg);
				if (!camposOK) {
					InterfaceUsuario.exibirMensagemClienteCadastro("Existem campos obrigatórios não preenchidos");
				} else {
					Estado novoUf = new Estado(uf);
					Cidade novaCidade = new Cidade(nomeCidade, novoUf);
					Endereco novoEndereco = new Endereco(logradouro, numero, bairro, cep, novaCidade);
					DadosFinanceiros novoDadosFinanceiro = new DadosFinanceiros(banco, agencia, contaCorrente,
							rendaFamiliar, rendaPessoal, observacao);
					Cliente cliente = new Cliente(cpf, nomeCompleto, rg, this.formatDate(dataNascimento), novoEndereco,
							novoDadosFinanceiro);
					db.salvarClienteBanco(cliente);
					InterfaceUsuario.exibirMensagemCliente("Cadastro realizado com sucesso");
				}
			}
		}

	}

	private boolean verificaCamposObrigatoriosCliente(String nome, Date dataNascimento, int CPF, int RG) {
		if (CPF > 0 && RG > 0 && nome.trim().length() > 0 && dataNascimento != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean verificarIdadeCliente(Date dataNascimento) {
		// entre 18 e 75
		// TODO VERIFICAR COMO FAZER ISSO COM JAVA.SQL.DATE
		return false;
	}

	public void buscarFuncionario(int codigo, String nome, String cpf, String date) {
		List<Funcionario> funcionariosBusca = db.buscarFuncionario(codigo, nome, cpf, this.formatDate(date));
		String[][] dados = new String[funcionariosBusca.size()][4];
		int indice = 0;
		for (Funcionario funcionario : funcionariosBusca) {
			dados[indice][0] = "" + funcionario.getIdFuncionario();
			dados[indice][1] = funcionario.getNome();
			dados[indice][2] = "" + funcionario.getDataNascimento();
			dados[indice][3] = "" + funcionario.getCPF();
			indice++;
		}
		InterfaceUsuario.carregaListaFuncionario(dados);
	}

	public void excluirfuncionario(int id) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoFuncionario();
		if (confirmacao) {
			String mensagem = db.excluiFuncionario(id);
			InterfaceUsuario.exibirMensagemFuncionario(mensagem);
		}
	}

	public void editarCadastrofuncionario(int codigo, String nome, String dataNascimento, int CPF, int RG, String cargo,
			String email, int telefone, String logradouro, int numero, String bairro, String CEP, String nomeCidade,
			String uf) {
		Estado estado = new Estado(uf);
		Cidade cidade = new Cidade(nomeCidade, estado);
		Endereco endereco = new Endereco(logradouro, numero, bairro, CEP, cidade);
		Funcionario funcionario = new Funcionario(nome, this.formatDate(dataNascimento), CPF, RG, cargo, email, telefone, endereco);
		String msg = db.editarFuncionarioBanco(funcionario);
		InterfaceUsuario.exibirMensagemFuncionario(msg);
	}

	public void criarCadastrofuncionario(String nome, String dataNascimento, int CPF, int RG, String cargo, String email,
			int telefone, String logradouro, int numero, String bairro, String CEP, String nomeCidade, String uf) {
		boolean cpfExiste = db.verificarCpfFuncionario(CPF);
		if (cpfExiste) {
			int opcao = InterfaceUsuario.exibirMensagemCpfExistenteFuncionario();
			if (opcao == 1) {
				InterfaceUsuario.cancelarCriacaoFuncionario();
			}
		} else {
			boolean camposOK = verificaCamposObrigatoriosFuncionario(nomeCidade, this.formatDate(dataNascimento), CPF, RG);
			if (!camposOK) {
				InterfaceUsuario.exibirMensagemFuncionarioCadastro("Campos obrigatórios não preenchidos");
			} else {
				Estado estado = new Estado(uf);
				Cidade cidade = new Cidade(nomeCidade, estado);
				Endereco endereco = new Endereco(logradouro, numero, bairro, CEP, cidade);
				Funcionario funcionario = new Funcionario(nome, this.formatDate(dataNascimento), CPF, RG, cargo, email, telefone,
						endereco);
				db.salvarFuncionarioBanco(funcionario);
				InterfaceUsuario.exibirMensagemFuncionarioCadastro("Cadastro realizado com sucesso");
			}
		}
	}

	private boolean verificaCamposObrigatoriosFuncionario(String nome, Date dataNascimento, int CPF, int RG) {
		if (CPF > 0 && RG > 0 && nome.trim().length() > 0 && dataNascimento != null) {
			return true;
		} else {
			return false;
		}
	}

	public void buscaDadosCliente(int codigo) {
		Cliente cliente = db.buscarDadosCliente(codigo);
		Endereco endereco = cliente.getEndereco();
		DadosFinanceiros dadosFinanceiros = cliente.getDadosFinanceiros();
		InterfaceUsuario.carregaEdicaoCliente(cliente.getIdCliente(), cliente.getCPF(), cliente.getNomeCompleto(),
				cliente.getRG(), cliente.getDataNascimento(), endereco.getLogradouro(), endereco.getNumero(),
				endereco.getBairro(), endereco.getCEP(), endereco.getCidade().getNome(),
				endereco.getCidade().getEstado().getUf(), dadosFinanceiros.getBanco(), dadosFinanceiros.getAgencia(),
				dadosFinanceiros.getContaCorrente(), dadosFinanceiros.getRendaFamiliar(),
				dadosFinanceiros.getRendaPessoal(), dadosFinanceiros.getObservacao());
	}

	public void buscaDadosFuncionario(int codigo) {
		Funcionario funcionario = db.buscarDadosFuncionario(codigo);
		InterfaceUsuario.carregaEdicaoFuncionario(funcionario.getIdFuncionario(), funcionario.getNome(),
				funcionario.getDataNascimento(), funcionario.getCPF(), funcionario.getRG(), funcionario.getCargo(),
				funcionario.getEmail(), funcionario.getTelefone(), funcionario.getEndereco().getLogradouro(),
				funcionario.getEndereco().getNumero(), funcionario.getEndereco().getBairro(),
				funcionario.getEndereco().getCEP(), funcionario.getEndereco().getCidade().getNome(),
				funcionario.getEndereco().getCidade().getEstado().getUf());
	}

	public void buscaDadosPlano(int codigo) {
		PlanoEmprestimo plano = db.buscarDadosPlano(codigo);
		InterfaceUsuario.carregarEdicaoPlano(plano.getIdPlanoEmprestimo(), plano.getNome(), plano.getDataCadastro(),
				plano.getJurosTotal(), plano.getJurosMensal(), plano.getValorMinimo(), plano.getValorMaximo(),
				plano.getMinParcelas(), plano.getMaxParcelas(), plano.getObservacao());
	}

	public void buscarPlano(int codigo, String plano) {
		List<PlanoEmprestimo> planosBusca = db.buscarPlano(codigo, plano);
		String[][] dados = new String[planosBusca.size()][2];
		int indice = 0;
		for (PlanoEmprestimo planoEmprestimo : planosBusca) {
			dados[indice][0] = "" + planoEmprestimo.getIdPlanoEmprestimo();
			dados[indice][1] = planoEmprestimo.getNome();
			indice++;
		}
		InterfaceUsuario.carregaListaPlano(dados);
	}

	public void excluirPlano(int id) {
		boolean confirmacao = InterfaceUsuario.confirmarExclusaoPlano();
		if (confirmacao) {
			String mensagem = db.excluiPlano(id);
			InterfaceUsuario.exibirMensagemPlano(mensagem);
		}
	}

	public void editarCadastroPlano(int idPlanoEmprestimo, String nome, String dataCadastro, double jurosTotal,
			double jurosMensal, double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas,
			String observacao) {
		PlanoEmprestimo planoEmprestimo = new PlanoEmprestimo(idPlanoEmprestimo, nome, this.formatDate(dataCadastro), jurosTotal,
				jurosMensal, valorMinimo, valorMaximo, minParcelas, maxParcelas, observacao, null);
		String msg = db.editarPlanoBanco(planoEmprestimo);
		InterfaceUsuario.exibirMensagemPlanoCadastro(msg);
	}

	public void criarCadastroPlano(String nome, String dataCadastro, double jurosTotal, double jurosMensal,
			double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas, String observacao) {
		boolean camposOK = verificaCamposObrigatoriosPlano(nome, this.formatDate(dataCadastro), jurosTotal, jurosMensal, valorMinimo,
				valorMaximo, minParcelas, maxParcelas, observacao);
		if (!camposOK) {
			InterfaceUsuario.exibirMensagemPlanoCadastro("Campos obrigatórios não preenchidos");
		} else {
			PlanoEmprestimo planoEmprestimo = new PlanoEmprestimo(nome, this.formatDate(dataCadastro), jurosTotal, jurosMensal,
					valorMinimo, valorMaximo, minParcelas, maxParcelas, observacao, null);
			db.salvarPlanoEmprestimoBanco(planoEmprestimo);
			InterfaceUsuario.exibirMensagemPlanoCadastro("Cadastro realizado com sucesso");
		}
	}

	private boolean verificaCamposObrigatoriosPlano(String nome, Date dataCadastro, double jurosTotal,
			double jurosMensal, double valorMinimo, double valorMaximo, int minParcelas, int maxParcelas,
			String observacao) {
		if (nome.trim().length() > 0
				|| dataCadastro != null && jurosTotal > 0 && jurosMensal > 0 && valorMinimo > 0 && valorMaximo > 0
						&& minParcelas > 0 && maxParcelas > 0 && maxParcelas > 0 && observacao.trim().length() > 0)
			return true;
		else
			return false;
	}

	public void geraRelatorio(String mesInicio, String mesFim, int tipoIndex) {
		List<Contrato> listContrato = db.buscarRelatorio();
		String[][] dados = new String[listContrato.size()][3];
		int indice = 0;
		for (Contrato contrato : listContrato) {
			dados[indice][0] = "" + contrato.getIdContrato();
			dados[indice][1] = contrato.getCliente().getNomeCompleto();
			dados[indice][2] = ""+contrato.getValorEmprestimo();
			indice++;
		}
		InterfaceUsuario.carregaRelatorio(dados);
		
		
	}

	public java.sql.Date formatDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.sql.Date data = null;
		try {
			data = new java.sql.Date(format.parse(date).getTime());
		} catch (ParseException ex) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
		}

		return data;
	}
}
