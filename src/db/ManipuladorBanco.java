package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cidade;
import model.Cliente;
import model.Contrato;
import model.DadosFinanceiros;
import model.Endereco;
import model.Estado;
import model.Funcionario;
import model.PerfilCliente;
import model.PlanoEmprestimo;

public class ManipuladorBanco {

	/*
	 * Insert
	 */
	private final String INSERT_ENDERECO = "INSERT INTO ENDERECO(logradouro, numero, bairro, CEP, idCidade) VALUES(?, ?, ?, ?)";
	private final String INSERT_CIDADE = "INSERT INTO CIDADE(nome, idEstado) VALUES(?, ?)";
	private final String INSERT_ESTADO = "INSERT INTO ESTADO(sigla) VALUES(?)";
	private final String INSERT_DADOSFINANCEIROS = "INSERT INTO DADOSFINANCEIROS(banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_CLIENTE = "INSERT INTO CLIENTE(nomeCompleto, dataNascimento, CPF, RG, idEndereco, idDadosFinanceiros) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(nome, dataNascimento, CPF, RG, cargo, email, telefone, idEndereco) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_PLANOEMPRESTIMO = "INSERT INTO PLANOEMPRESTIMO(nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo, valorMaximo, maxParcelas, minParcelas, observacao, idFuncionarioResponsavel) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_CONTRATO = "INSERT INTO CONTRATO(qtdParcelas, valorEmprestimo, valorParcelas, dataCriacaoContrato, dataTerminoContrato, statusContrato, idCliente, idFuncionarioResponsavel, idplanoEmprestimo, observacoes) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String SELECT_PLANOEMPRESTIMO_BY_ID = "SELECT * FROM PLANOEMPRESTIMO WHERE IDPLANOEMPRESTIMO = ?";
	private final String SELECT_FUNCIONARIO_BY_ID = "SELECT * FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";
	private final String SELECT_ENDERECO_BY_ID = "SELECT * FROM ENDERECO WHERE IDENDERECO = ?";
	private final String SELECT_CONTRATO_BY_ID = "SELECT * FROM CONTRATO WHERE IDCONTRATO = ?";
	private final String SELECT_DADOSFINANCEIROS_BY_ID = "SELECT * FROM DADOSFINANCEIROS WHERE IDDADOSFINANCEIROS = ?";
	private final String SELECT_USUARIO_LOGIN = "SELECT * FROM tb_user_login WHERE login = ? and password = ?";

	private final String SELECT_FUNCIONARIO_BY_CPF = "SELECT * FROM FUNCIONARIO WHERE CPF = ?";
	private final String SELECT_CLIENTE_BY_CPF = "SELECT * FROM CLIENTE WHERE CPF = ?";
	/*
	 * Update
	 */
	private final String UPDATE_CLIENTE_BY_ID = "UPDATE CLIENTE SET nomeCompleto = ?, dataNascimento = ?, CPF = ?, RG = ?, idEndereco = ?, idDadosFinanceiros = ? WHERE IDCLIENTE = ?";
	private final String UPDATE_PLANOEMPRESTIMO_BY_ID = "UPDATE PLANOEMPRESTIMO SET nome = ?, dataCadastro = ?, jurosTotal = ?, jurosMensal = ?, valorMinimo = ?, valorMaximo = ?, minParcelas = ?, maxParcelas = ?, observacao = ?, idFuncionario = ? WHERE IDPLANOEMPRESTIMO = ?";
	private final String UPDATE_FUNCIONARIO_BY_ID = "UPDATE FUNCIONARIO SET nome = ?, dataNascimento = ?, CPF = ?, RG = ?, cargo = ?, email = ?, telefone = ?, idEndereco = ? WHERE IDFUNCIONARIO = ?";
	private final String UPDATE_ENDERECO_BY_ID = "UPDATE ENDERECO SET logradouro = ?, numero = ?, bairro = ?, CEP = ?, cidade = ? WHERE IDENDERECO = ?";
	private final String UPDATE_CONTRATO_BY_ID = "UPDATE CONTRATO SET qntdParcelas = ?, valorEmprestimo = ?, valorParcelas = ?, dadaTerminoContrato = ?, idCliente = ?, idFuncionarioResponsavel = ?, idPlanoEmprestimo = ? WHERE IDCONTRATO = ?";
	private final String UPDATE_DADOSFINANCEIROS_BY_ID = "UPDATE DADOSFINANCEIROS SET banco = ?, agencia = ?, contaCorrente = ?, rendaFamiliar = ?, rendaPessoal = ?, observacao = ? WHERE IDDADOSFINANCEIROS = ?";

	/*
	 * Delete
	 */
	private final String DELETE_PLANOEMPRESTIMO_BY_ID = "DELETE FROM PLANOEMPRESTIMO WHERE IDPLANOEMPRESTIMO = ?";
	private final String DELETE_CLIENTE_BY_ID = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
	private final String DELETE_FUNCIONARIO_BY_ID = "DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";

	Connection conexao;

	public ManipuladorBanco() {
		/*try {
			this.conexao = this.conectasgbd();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

	public Connection conectasgbd() throws SQLException {
		Connection conectando = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conectando = DriverManager.getConnection("jdbc:mysql://localhost:5432/easymoney", "??", "??");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conectando;
	}

	public void closeConnnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvarEnderecoBanco(Endereco endereco) {
		int idEndereco = 0;
		int idFKCidade = 0;
		try {
			idFKCidade = this.salvarCidadeBanco(endereco.getCidade());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ENDERECO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, endereco.getLogradouro());
			prepared.setInt(2, endereco.getNumero());
			prepared.setString(3, endereco.getBairro());
			prepared.setString(4, endereco.getCEP());
			prepared.setInt(5, idFKCidade);
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idEndereco = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idEndereco;
	}

	public int salvarCidadeBanco(Cidade cidade) {
		int idCidade = 0;
		int idFKEstado = 0;
		try {
			idFKEstado = this.salvarEstadoBanco(cidade.getEstado());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CIDADE, Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, cidade.getNome());
			prepared.setInt(2, idFKEstado);
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idCidade = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCidade;
	}

	public int salvarEstadoBanco(Estado estado) {
		int idEstado = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ESTADO, Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, estado.getUf());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idEstado = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idEstado;
	}

	public int salvarDadosFinanceirosBanco(DadosFinanceiros dadosFinanceiros) {
		int idDadosFinanceiros = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_DADOSFINANCEIROS,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, dadosFinanceiros.getBanco());
			prepared.setString(2, dadosFinanceiros.getAgencia());
			prepared.setInt(3, dadosFinanceiros.getContaCorrente());
			prepared.setDouble(4, dadosFinanceiros.getRendaFamiliar());
			prepared.setDouble(5, dadosFinanceiros.getRendaPessoal());
			prepared.setString(6, dadosFinanceiros.getObservacao());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idDadosFinanceiros = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idDadosFinanceiros;
	}

	public int salvarClienteBanco(Cliente cliente) {
		int idCliente = 0;
		int idFKEndereco = 0;
		int idFKDadosFinanceiros = 0;
		try {
			idFKEndereco = this.salvarEnderecoBanco(cliente.getEndereco());
			idFKDadosFinanceiros = this.salvarDadosFinanceirosBanco(cliente.getDadosFinanceiros());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CLIENTE, Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, cliente.getNomeCompleto());
			prepared.setDate(2, cliente.getDataNascimento());
			prepared.setInt(3, cliente.getCPF());
			prepared.setInt(4, cliente.getRG());
			prepared.setInt(5, idFKEndereco);
			prepared.setInt(6, idFKDadosFinanceiros);
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idCliente = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCliente;
	}

	public int salvarFuncionarioBanco(Funcionario funcionario) {
		int idFuncionario = 0;
		int idFKEndereco = 0;
		try {
			idFKEndereco = this.salvarEnderecoBanco(funcionario.getEndereco());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_FUNCIONARIO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, funcionario.getNome());
			prepared.setDate(2, funcionario.getDataNascimento());
			prepared.setInt(3, funcionario.getCPF());
			prepared.setInt(4, funcionario.getRG());
			prepared.setString(5, funcionario.getCargo());
			prepared.setString(6, funcionario.getEmail());
			prepared.setInt(7, funcionario.getTelefone());
			prepared.setInt(8, idFKEndereco);
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idFuncionario = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idFuncionario;
	}

	public int salvarPlanoEmprestimoBanco(PlanoEmprestimo planoEmprestimo) {
		int idPlanoEmprestimo = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_PLANOEMPRESTIMO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, planoEmprestimo.getNome());
			prepared.setDate(2, planoEmprestimo.getDataCadastro());
			prepared.setDouble(3, planoEmprestimo.getJurosTotal());
			prepared.setDouble(4, planoEmprestimo.getJurosMensal());
			prepared.setDouble(5, planoEmprestimo.getValorMinimo());
			prepared.setDouble(6, planoEmprestimo.getValorMaximo());
			prepared.setInt(7, planoEmprestimo.getMaxParcelas());
			prepared.setInt(8, planoEmprestimo.getMinParcelas());
			prepared.setString(9, planoEmprestimo.getObservacao());
			prepared.setInt(10, planoEmprestimo.getFuncionario().getIdFuncionario());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idPlanoEmprestimo = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idPlanoEmprestimo;
	}

	public int salvarContratoBanco(Contrato contrato) {
		int idContrato = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CONTRATO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, contrato.getQntdParcelas());
			prepared.setDouble(2, contrato.getValorEmprestimo());
			prepared.setDouble(3, contrato.getValorParcelas());
			prepared.setDate(4, contrato.getDataCriacaoContrato());
			prepared.setDate(5, contrato.getDataTerminoContrato());
			prepared.setString(6, contrato.getStatusContrato().getName());
			prepared.setInt(7, contrato.getCliente().getIdCliente());
			prepared.setInt(8, contrato.getFuncionarioResponsavel().getIdFuncionario());
			prepared.setInt(9, contrato.getPlanoEmprestimo().getIdPlanoEmprestimo());
			prepared.setString(10, contrato.getObservacoes());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idContrato = set.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idContrato;
	}

	public void editarEnderecoBanco(Endereco endereco) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_ENDERECO_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, endereco.getLogradouro());
			prepared.setInt(2, endereco.getNumero());
			prepared.setString(3, endereco.getBairro());
			prepared.setString(4, endereco.getCEP());
			prepared.setInt(5, endereco.getCidade().getIdCidade());
			prepared.setInt(6, endereco.getIdEndereco());
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarDadosFinanceirosBanco(DadosFinanceiros dadosFinanceiros) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_DADOSFINANCEIROS_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, dadosFinanceiros.getBanco());
			prepared.setString(2, dadosFinanceiros.getAgencia());
			prepared.setInt(3, dadosFinanceiros.getContaCorrente());
			prepared.setDouble(4, dadosFinanceiros.getRendaFamiliar());
			prepared.setDouble(5, dadosFinanceiros.getRendaPessoal());
			prepared.setString(6, dadosFinanceiros.getObservacao());
			prepared.setInt(7, dadosFinanceiros.getIdDadosFinanceiros());
			prepared.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public String editarClienteBanco(Cliente cliente) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_CLIENTE_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, cliente.getNomeCompleto());
			prepared.setDate(2, cliente.getDataNascimento());
			prepared.setInt(3, cliente.getCPF());
			prepared.setInt(4, cliente.getRG());
			prepared.setInt(5, cliente.getEndereco().getIdEndereco());
			prepared.setInt(6, cliente.getDadosFinanceiros().getIdDadosFinanceiros());
			prepared.setInt(7, cliente.getIdCliente());
			prepared.executeUpdate();
			return "Cadastro alterado com sucesso";
		} catch (Exception e) {
			return ""+ e.getMessage();
		}
	}
	
	public String editarFuncionarioBanco(Funcionario funcionario) {
		try {
			this.editarEnderecoBanco(funcionario.getEndereco());
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_FUNCIONARIO_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, funcionario.getNome());
			prepared.setDate(2, funcionario.getDataNascimento());
			prepared.setInt(3, funcionario.getCPF());
			prepared.setInt(4, funcionario.getRG());
			prepared.setString(5, funcionario.getCargo());
			prepared.setString(6, funcionario.getEmail());
			prepared.setInt(7, funcionario.getTelefone());
			prepared.setInt(8, funcionario.getEndereco().getIdEndereco());
			prepared.setInt(9, funcionario.getIdFuncionario());
			prepared.executeUpdate();
			return "Cadastro alterado com sucesso";
		} catch (Exception e) {
			return ""+ e.getMessage();
		}
	}

	public void editarPlanoEmprestimoBanco(PlanoEmprestimo planoEmprestimo) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_PLANOEMPRESTIMO_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, planoEmprestimo.getNome());
			prepared.setDate(2, planoEmprestimo.getDataCadastro());
			prepared.setDouble(3, planoEmprestimo.getJurosTotal());
			prepared.setDouble(4, planoEmprestimo.getJurosMensal());
			prepared.setDouble(5, planoEmprestimo.getValorMinimo());
			prepared.setDouble(6, planoEmprestimo.getValorMaximo());
			prepared.setInt(7, planoEmprestimo.getMaxParcelas());
			prepared.setInt(8, planoEmprestimo.getMinParcelas());
			prepared.setString(9, planoEmprestimo.getObservacao());
			prepared.setInt(10, planoEmprestimo.getFuncionario().getIdFuncionario());
			prepared.setInt(11, planoEmprestimo.getIdPlanoEmprestimo());
			prepared.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarContratoBanco(Contrato contrato) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_CONTRATO_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, contrato.getQntdParcelas());
			prepared.setDouble(2, contrato.getValorEmprestimo());
			prepared.setDouble(3, contrato.getValorParcelas());
			prepared.setDate(4, contrato.getDataTerminoContrato());
			prepared.setString(5, contrato.getStatusContrato().getName());
			prepared.setInt(6, contrato.getCliente().getIdCliente());
			prepared.setInt(7, contrato.getFuncionarioResponsavel().getIdFuncionario());
			prepared.setInt(8, contrato.getPlanoEmprestimo().getIdPlanoEmprestimo());
			prepared.setInt(9, contrato.getIdContrato());
			prepared.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean realizarLogin(String nome, String senha) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_USUARIO_LOGIN);
			prepared.setString(1, nome);
			prepared.setString(2, senha);
			ResultSet rs;
			rs = prepared.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Cliente> buscarCliente(int codigo, String nome, String cpf, Date dataNasc) {
		List<Cliente> buscarCliente = new ArrayList<Cliente>();
		String SELECT_CLIENTE_BY_= "SELECT * FROM CLIENTE WHERE 1 = 1";
		
		if (codigo > 0) {
			SELECT_CLIENTE_BY_ += "AND idCliente = " + codigo;
		} 
		if (nome != null) {
			SELECT_CLIENTE_BY_ += "AND nomeCompleto = " + nome;
		}
		if (cpf != null) {
			SELECT_CLIENTE_BY_ += "AND cpf" + cpf;
		}
		if (dataNasc != null) {
			SELECT_CLIENTE_BY_ += "AND dataNascimento = " + dataNasc;
		}
		
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CLIENTE_BY_);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Endereco endereco = new Endereco();
				endereco.setIdEndereco(set.getInt("idEndereco"));
				DadosFinanceiros dadosFinanceiros = new DadosFinanceiros();
				dadosFinanceiros.setIdDadosFinanceiros(set.getInt("idDadosFinanceiros"));
				Cliente cliente = new Cliente(set.getInt("idCliente"), set.getInt("CPF"), set.getString("nomeCompleto"), set.getInt("RG"),
						set.getDate("dataNascimento"), endereco, dadosFinanceiros);
				buscarCliente.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buscarCliente;
		
	}
	
	public List<Funcionario> buscarFuncionario(int codigo, String nome, String cpf, Date date) {
		List<Funcionario> buscarFuncionario = new ArrayList<Funcionario>();
		String SELECT_FUNCIONARIO_BY_= "SELECT * FROM FUNCIONARIO WHERE 1 = 1";
		
		if (codigo > 0) {
			SELECT_FUNCIONARIO_BY_ += "AND idFuncionario = " + codigo;
		} 
		if (nome != null) {
			SELECT_FUNCIONARIO_BY_ += "AND nome = " + nome;
		}
		if (cpf != null) {
			SELECT_FUNCIONARIO_BY_ += "AND cpf" + cpf;
		}
		if (date != null) {
			SELECT_FUNCIONARIO_BY_ += "AND dataNascimento = " + date;
		}
		
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_FUNCIONARIO_BY_);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Endereco endereco = new Endereco();
				endereco.setIdEndereco(set.getInt("idEndereco"));
				Funcionario funcionario = new Funcionario(set.getInt("idFuncionario"), set.getString("nome"), set.getDate("dataNascimento"), set.getInt("CPF"), set.getInt("RG"),
						set.getString("cargo"), set.getString("email"), set.getInt("telefone"), endereco);
				buscarFuncionario.add(funcionario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buscarFuncionario;
	}
	
	public void removerPlanoEmprestimoBanco(PlanoEmprestimo planoEmprestimo) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_PLANOEMPRESTIMO_BY_ID);
			prepared.setInt(1, planoEmprestimo.getIdPlanoEmprestimo());
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String excluiCliente(int id) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_CLIENTE_BY_ID);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			return "Cliente deletado com sucesso!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Cliente não pôde ser deletado!";
		}
	}
	
	public String excluiFuncionario(int id) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_FUNCIONARIO_BY_ID);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			return "Funcionário deletado com sucesso!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Funcionário não pôde ser deletado!";
		}
	}

	public boolean verificarCpfCliente(int cpf) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_CLIENTE_BY_CPF);
			prepared.setInt(1, cpf);
			ResultSet set = prepared.executeQuery();
			return set.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean verificarCpfFuncionario(int cpf) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_FUNCIONARIO_BY_CPF);
			prepared.setInt(1, cpf);
			ResultSet set = prepared.executeQuery();
			return set.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Contrato buscarContratoId(int codContrato) {
		// TODO Auto-generated method stub
		//Retorna contrato por id
		
		return null;
	}

	public PlanoEmprestimo buscarPlanoEmprestimoId(int idPlanoEmprestimo) {
		// TODO Auto-generated method stub
		//Retorna o Plano de Empr�stimo de acordo com o ID
		return null;
	}

	public List<PlanoEmprestimo> buscarPlanoEmprestimo() {
		// TODO Auto-generated method stub
		//Retorna todos os planos de empr�stimo
		return null;
	}

	public List<PlanoEmprestimo> buscarPlanoEmprestimoPorPerfil(PerfilCliente perfilCliente) {
		// TODO Auto-generated method stub
		//Retorna todos os planos de determinado perfil
		return null;
	}
}