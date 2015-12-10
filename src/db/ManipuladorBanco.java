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
import view.InterfaceUsuario;

public class ManipuladorBanco {

	/*
	 * Insert
	 */
	private final String INSERT_ENDERECO = "INSERT INTO Endereco(logradouro, complemento, numero, bairro, idCidade) VALUES(?, ?, ?, ?, ?)";
	private final String INSERT_CIDADE = "INSERT INTO Cidade(nome, idEstado) VALUES(?, ?)";
	private final String INSERT_DADOSFINANCEIROS = "INSERT INTO DadosFinanceiros(banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_CLIENTE = "INSERT INTO Cliente(nomeCompleto, dataNascimento, CPF, RG, idEndereco, idDadosFinanceiros) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_FUNCIONARIO = "INSERT INTO Funcionario(nome, dataNascimento, CPF, RG, cargo, email, telefone, idEndereco) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_PLANOEMPRESTIMO = "INSERT INTO PlanoEmprestimo(nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo, valorMaximo, maxParcelas, minParcelas, observacao) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_CONTRATO = "INSERT INTO Contrato(qtdParcelas, valorEmprestimo, valorParcelas, dataCriacaoContrato, dataTerminoContrato, statusContrato, idCliente, idplanoEmprestimo, observacoes) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final String SELECT_ALL_CLIENTE = "SELECT * FROM Cliente";
	private final String SELECT_ALL_ESTADO = "SELECT * FROM Estado";
	private final String SELECT_ALL_PLANOEMPRESTIMO = "SELECT * FROM PlanoEmprestimo";

	private final String SELECT_PLANOEMPRESTIMO_BY_ID = "SELECT * FROM PlanoEmprestimo WHERE idPlanoEmprestimo = ?";
	private final String SELECT_FUNCIONARIO_BY_ID = "SELECT * FROM Funcionario WHERE idFuncionario = ?";
	private final String SELECT_ENDERECO_BY_ID = "SELECT * FROM Endereco WHERE idEndereco = ?";
	private final String SELECT_CONTRATO_BY_ID = "SELECT * FROM Contrato WHERE idContrato = ?";
	private final String SELECT_DADOSFINANCEIROS_BY_ID = "SELECT * FROM DadosFinanceiros WHERE idDadosFinanceiros = ?";

	private final String SELECT_USUARIO_LOGIN = "SELECT * FROM Usuario WHERE login = ? and passwd = ?";

	private final String SELECT_INFO_CLIENTE_BY_ID = "SELECT C.idCliente AS idcliente, C.CPF AS cpf, C.nomeCompleto AS nomecliente, "
			+ "C.RG AS rg, C.dataNascimento AS datanascimento, E.logradouro AS logradouro, E.complemento as complemento,E.numero AS numero, E.bairro AS bairro,"
			+ "E.CEP AS cep, CD.nome AS nomecidade, EST.idEstado AS iduf, EST.sigla AS siglauf, DF.banco AS banco, DF.agencia AS agencia, DF.contaCorrente AS contacorrente, "
			+ "DF.rendaFamiliar AS rendafamiliar, DF.rendaPessoal AS rendapessoal, DF.observacao AS observacao "
			+ "FROM Cliente C LEFT JOIN Endereco E ON C.idEndereco = E.idEndereco LEFT JOIN Cidade CD ON E.idCidade = CD.idCidade "
			+ "LEFT JOIN DadosFinanceiros DF ON DF.idDadosFinanceiros = C.idDadosFinanceiros "
			+ "LEFT JOIN Estado EST ON EST.idEstado = CD.idEstado WHERE C.idCliente = ?";

	private final String SELECT_INFO_FUNCIONARIO_BY_ID = "SELECT F.idFuncionario AS idfuncionario, F.CPF AS cpf, F.nome AS nome, "
			+ "F.RG AS rg, F.dataNascimento AS datanascimento, F.cargo AS cargo, F.email AS email, F.telefone AS telefone, E.logradouro AS logradouro, E.complemento AS complemento, E.numero AS numero, E.bairro AS bairro, "
			+ "E.CEP AS cep, CD.nome AS nomecidade, EST.idEstado AS iduf, EST.sigla AS siglauf "
			+ "FROM Funcionario F LEFT JOIN Endereco E ON F.idEndereco = E.idEndereco LEFT JOIN Cidade CD ON E.idCidade = CD.idCidade "
			+ "LEFT JOIN Estado EST ON EST.idEstado = CD.idEstado WHERE F.idFuncionario = ?";

	private final String SELECT_FUNCIONARIO_BY_CPF = "SELECT * FROM Funcionario WHERE CPF = ?";
	private final String SELECT_CLIENTE_BY_CPF = "SELECT * FROM Cliente WHERE CPF = ?";

	/*
	 * Update
	 */
	private final String UPDATE_CLIENTE_BY_ID = "UPDATE Cliente SET nomeCompleto = ?, dataNascimento = ?, CPF = ?, RG = ?, idEndereco = ?, idDadosFinanceiros = ? WHERE idCliente = ?";
	private final String UPDATE_PLANOEMPRESTIMO_BY_ID = "UPDATE PlanoEmprestimo SET nome = ?, dataCadastro = ?, jurosTotal = ?, jurosMensal = ?, valorMinimo = ?, valorMaximo = ?, minParcelas = ?, maxParcelas = ?, observacao = ? WHERE idPlanoEmprestimo = ?";
	private final String UPDATE_FUNCIONARIO_BY_ID = "UPDATE Funcionario SET nome = ?, dataNascimento = ?, CPF = ?, RG = ?, cargo = ?, email = ?, telefone = ?, idEndereco = ? WHERE idFuncionario = ?";
	private final String UPDATE_ENDERECO_BY_ID = "UPDATE Endereco SET logradouro = ?, complemento = ?, numero = ?, bairro = ?, idCidade = ? WHERE idEndereco = ?";
	private final String UPDATE_CONTRATO_BY_ID = "UPDATE Contrato SET qtdParcelas = ?, valorEmprestimo = ?, valorParcelas = ?, dataTerminoContrato = ?, statusContrato = ?,  idCliente = ?, idPlanoEmprestimo = ? WHERE idContrato = ?";
	private final String UPDATE_DADOSFINANCEIROS_BY_ID = "UPDATE DadosFinanceiros SET banco = ?, agencia = ?, contaCorrente = ?, rendaFamiliar = ?, rendaPessoal = ?, observacao = ? WHERE idDadosFinanceiros = ?";
	private final String UPDATE_CIDADE_BY_ID = "UPDATE Cidade SET nome = ?, idEstado = ? WHERE idCidade = ?";
	/*
	 * Delete
	 */
	private final String DELETE_CONTRATO_BY_ID = "DELETE FROM Contrato WHERE idContrato = ?";
	private final String DELETE_PLANOEMPRESTIMO_BY_ID = "DELETE FROM PlanoEmprestimo WHERE idPlanoEmprestimo = ?";
	private final String DELETE_CLIENTE_BY_ID = "DELETE FROM Cliente c LEFT JOIN Contrato ci ON c.idCliente = ci.idCliente LEFT JOIN Endereco "
			+ " e ON c.idEndereco = e.idEndereco LEFT JOIN DadosFinanciados d ON c.idDadosFinanciados = d.idDadosFinanciados "
			+ " LEFT JOIN PlanoEmprestimo p ON ci.idPlanoEmprestimo =  p.idPlanoEmprestimo WHERE c.idCliente = ?";
	private final String DELETE_FUNCIONARIO_BY_ID = "DELETE FROM Funcionario WHERE idFuncionario = ?";

	Connection conexao;

	public ManipuladorBanco() {
		try {
			this.conexao = this.conectasgbd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection conectasgbd() throws SQLException {
		Connection conectando = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conectando = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
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

	public void closeConnnection() {
		try {
			conexao.close();
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
			prepared.setString(2, endereco.getComplemento());
			prepared.setInt(3, endereco.getNumero());
			prepared.setString(4, endereco.getBairro());
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
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CIDADE, Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, cidade.getNome());
			prepared.setInt(2, cidade.getEstado().getIdEstado());
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
			prepared.setString(6, contrato.getStatusContrato());
			prepared.setInt(7, contrato.getCliente().getIdCliente());
			prepared.setInt(8, contrato.getPlanoEmprestimo().getIdPlanoEmprestimo());
			prepared.setString(9, contrato.getObservacoes());
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
			prepared.setString(2, endereco.getComplemento());
			prepared.setInt(3, endereco.getNumero());
			prepared.setString(4, endereco.getBairro());
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

		String sqlParaRecuperarIds = "SELECT C.idEndereco AS idEndereco, C.idDadosFinanceiros as idDadosFinanceiros,"
				+ " ci.idCidade AS idCidade, ci.idEstado as idEstado FROM Cliente C JOIN Endereco E "
				+ "ON C.idEndereco = E.idEndereco JOIN Cidade ci ON E.idCidade = ci.idCidade WHERE idCliente = ?";
		int idEndereco;
		int idDadosFinanceiros;
		int idCidade;
		try {
			PreparedStatement preparedEndereco = this.conexao.prepareStatement(sqlParaRecuperarIds);
			preparedEndereco.setInt(1, cliente.getIdCliente());
			ResultSet set = preparedEndereco.executeQuery();
			if (set.next()) {
				idEndereco = set.getInt("idEndereco");
				idDadosFinanceiros = set.getInt("idDadosFinanceiros");
				idCidade = set.getInt("idCidade");
				cliente.getEndereco().setIdEndereco(idEndereco);
				cliente.getDadosFinanceiros().setIdDadosFinanceiros(idDadosFinanceiros);
				cliente.getEndereco().getCidade().setIdCidade(idCidade);
				cliente.getEndereco().getCidade().getEstado().setIdEstado(set.getInt("idEstado"));
				this.editarEnderecoBanco(cliente.getEndereco());
				this.editarDadosFinanceirosBanco(cliente.getDadosFinanceiros());
			}
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
			return "" + e.getMessage();
		}
	}

	public String editarFuncionarioBanco(Funcionario funcionario) {

		String sqlParaRecuperarIds = "SELECT F.idEndereco AS idEndereco, "
				+ " ci.idCidade AS idCidade, ci.idEstado as idEstado FROM Funcionario F JOIN Endereco E "
				+ "ON F.idEndereco = F.idEndereco JOIN Cidade ci ON E.idCidade = ci.idCidade WHERE F.idFuncionario = ?";
		int idEndereco;
		int idCidade;
		int idEstado;

		try {
			PreparedStatement preparedEndereco = this.conexao.prepareStatement(sqlParaRecuperarIds);
			preparedEndereco.setInt(1, funcionario.getIdFuncionario());
			ResultSet set = preparedEndereco.executeQuery();
			if (set.next()) {
				idEndereco = set.getInt("idEndereco");
				idCidade = set.getInt("idCidade");
				idEstado = set.getInt("idEstado");
				funcionario.getEndereco().setIdEndereco(idEndereco);
				funcionario.getEndereco().getCidade().setIdCidade(idCidade);
				funcionario.getEndereco().getCidade().getEstado().setIdEstado(idEstado);
				this.editarEnderecoBanco(funcionario.getEndereco());
			}
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
			return "" + e.getMessage();
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
			prepared.setInt(10, planoEmprestimo.getIdPlanoEmprestimo());
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
			prepared.setString(5, contrato.getStatusContrato());
			prepared.setInt(6, contrato.getCliente().getIdCliente());
			prepared.setInt(7, contrato.getPlanoEmprestimo().getIdPlanoEmprestimo());
			prepared.setInt(8, contrato.getIdContrato());
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
		String SELECT_CLIENTE_BY_ = "SELECT * FROM Cliente WHERE 1 = 1";

		if (codigo > 0) {
			SELECT_CLIENTE_BY_ += " AND idCliente = " + codigo;
		} else if (cpf != null && cpf.trim().length() > 0) {
			SELECT_CLIENTE_BY_ += " AND CPF = '" + cpf + "'";
		} else if (nome != null && nome.trim().length() > 0) {
			if (dataNasc != null) {
				SELECT_CLIENTE_BY_ += " AND nomeCompleto LIKE '%" + nome + "%'" + " AND dataNascimento = '" + dataNasc
						+ "'";
			} else {
				SELECT_CLIENTE_BY_ += " AND nomeCompleto LIKE '%" + nome + "%'";
			}
		} else if (dataNasc != null) {
			SELECT_CLIENTE_BY_ += " AND dataNascimento = '" + dataNasc + "'";
		}

		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CLIENTE_BY_);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Endereco endereco = new Endereco();
				endereco.setIdEndereco(set.getInt("idEndereco"));
				DadosFinanceiros dadosFinanceiros = new DadosFinanceiros();
				dadosFinanceiros.setIdDadosFinanceiros(set.getInt("idDadosFinanceiros"));
				Cliente cliente = new Cliente(set.getInt("idCliente"), set.getInt("CPF"), set.getString("nomeCompleto"),
						set.getInt("RG"), set.getDate("dataNascimento"), endereco, dadosFinanceiros);
				buscarCliente.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return buscarCliente;

	}

	public List<Funcionario> buscarFuncionario(int codigo, String nome, String cpf, Date date) {
		List<Funcionario> buscarFuncionario = new ArrayList<Funcionario>();
		String SELECT_FUNCIONARIO_BY_ = "SELECT * FROM Funcionario WHERE 1 = 1";

		if (codigo > 0) {
			SELECT_FUNCIONARIO_BY_ += " AND idFuncionario = " + codigo;
		}

		if (!cpf.isEmpty()) {
			SELECT_FUNCIONARIO_BY_ += " AND CPF = '" + cpf + "'";
		}

		if (!nome.isEmpty()) {
			SELECT_FUNCIONARIO_BY_ += " AND nome LIKE '%" + nome + "%'";
		}

		if (date != null) {
			SELECT_FUNCIONARIO_BY_ += " AND dataNascimento = '" + date + "'";
		}

		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_FUNCIONARIO_BY_);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Endereco endereco = new Endereco();
				endereco.setIdEndereco(set.getInt("idEndereco"));
				Funcionario funcionario = new Funcionario(set.getInt("idFuncionario"), set.getString("nome"),
						set.getDate("dataNascimento"), set.getInt("CPF"), set.getInt("RG"), set.getString("cargo"),
						set.getString("email"), set.getInt("telefone"), endereco);
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

		// String sqlParaRecuperarIds = "SELECT idContrato FROM Cliente c JOIN
		// Contrato co WHERE c.idCliente = co.idCliente";
		// int idContrato;
		//
		// PreparedStatement preparedEndereco =
		// this.conexao.prepareStatement(sqlParaRecuperarIds);
		// ResultSet set = preparedEndereco.executeQuery();
		// while (set.next()) {
		// idContrato = set.getInt("idContrato");
		// funcionario.getEndereco().setIdEndereco(idEndereco);
		// this.editarEnderecoBanco(funcionario.getEndereco());

		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_CLIENTE_BY_ID);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			return "Cliente deletado com sucesso!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Cliente n�o pode ser deletado!";
		}
	}

	public String excluiFuncionario(int id) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_FUNCIONARIO_BY_ID);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			return "Funcion�rio deletado com sucesso!";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Funcion�rio n�o pode ser deletado!";
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
		Contrato contrato = null;
		Cliente cliente = null;
		PlanoEmprestimo planoEmprestimo = null;
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CONTRATO_BY_ID);
			preparedStatement.setInt(1, codContrato);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(set.getInt("idCliente"));
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				contrato = new Contrato(set.getInt("qtdParcelas"), set.getFloat("valorEmprestimo"),
						set.getFloat("valorParcelas"), set.getDate("dataCriacaoContrato"),
						set.getDate("dataTerminoContrato"), cliente, set.getString("statusContrato"), planoEmprestimo,
						set.getString("observacoes"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contrato;
	}

	public PlanoEmprestimo buscarPlanoEmprestimoId(int idPlanoEmprestimo) {
		PlanoEmprestimo planoEmprestimo = null;
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_PLANOEMPRESTIMO_BY_ID);
			preparedStatement.setInt(1, idPlanoEmprestimo);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setNome(set.getString("nome"));
				planoEmprestimo.setDataCadastro(set.getDate("dataCadastro"));
				planoEmprestimo.setJurosTotal(set.getDouble("jurosTotal"));
				planoEmprestimo.setJurosMensal(set.getDouble("jurosMensal"));
				planoEmprestimo.setValorMinimo(set.getDouble("valorMinimo"));
				planoEmprestimo.setValorMaximo(set.getDouble("valorMaximo"));
				planoEmprestimo.setMaxParcelas(set.getInt("maxParcelas"));
				planoEmprestimo.setMinParcelas(set.getInt("minParcelas"));
				planoEmprestimo.setObservacao(set.getString("observacao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planoEmprestimo;
	}

	public List<PlanoEmprestimo> buscarPlanoEmprestimo() {
		List<PlanoEmprestimo> listaPlanoEmprestimo = new ArrayList<PlanoEmprestimo>();
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_ALL_PLANOEMPRESTIMO);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				PlanoEmprestimo planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setNome(set.getString("nome"));
				planoEmprestimo.setDataCadastro(set.getDate("dataCadastro"));
				planoEmprestimo.setJurosTotal(set.getDouble("jurosTotal"));
				planoEmprestimo.setJurosMensal(set.getDouble("jurosMensal"));
				planoEmprestimo.setValorMinimo(set.getDouble("valorMinimo"));
				planoEmprestimo.setValorMaximo(set.getDouble("valorMaximo"));
				planoEmprestimo.setMaxParcelas(set.getInt("maxParcelas"));
				planoEmprestimo.setMinParcelas(set.getInt("minParcelas"));
				planoEmprestimo.setObservacao(set.getString("observacao"));
				listaPlanoEmprestimo.add(planoEmprestimo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPlanoEmprestimo;
	}

	public List<PlanoEmprestimo> buscarPlano(int codigo, String plano) {
		List<PlanoEmprestimo> listaPlanos = new ArrayList<PlanoEmprestimo>();
		String SELECT_PLANO_EMPRESTIMO_BY = "SELECT idPlanoEmprestimo, nome FROM PlanoEmprestimo WHERE 1=1 ";
		if (codigo > 0) {
			SELECT_PLANO_EMPRESTIMO_BY += " AND idPlanoEmprestimo = " + codigo;
		}
		if (plano.trim().length() > 0) {
			SELECT_PLANO_EMPRESTIMO_BY += " AND nome = '" + plano + "'";
		}
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_PLANO_EMPRESTIMO_BY);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				PlanoEmprestimo planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setNome(set.getString("nome"));
				listaPlanos.add(planoEmprestimo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPlanos;
	}

	public String excluiPlano(int id) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_PLANOEMPRESTIMO_BY_ID);
			prepared.setInt(1, id);
			prepared.executeUpdate();
			return "Plano deletado com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Plano não pode ser deletado! Tente novamente!";
		}
	}

	public Cliente buscarDadosCliente(int codigo) {
		Cliente cliente = null;

		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_INFO_CLIENTE_BY_ID);
			preparedStatement.setInt(1, codigo);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Estado estado = new Estado(set.getInt("iduf"), set.getString("siglauf"));
				Cidade cidade = new Cidade();
				cidade.setEstado(estado);
				cidade.setNome(set.getString("nomecidade"));
				Endereco endereco = new Endereco();
				endereco.setComplemento(set.getString("complemento"));
				endereco.setBairro(set.getString("bairro"));
				endereco.setNumero(set.getInt("numero"));
				endereco.setLogradouro(set.getString("logradouro"));
				endereco.setCidade(cidade);
				DadosFinanceiros dadosFinanceiros = new DadosFinanceiros();
				dadosFinanceiros.setAgencia(set.getString("agencia"));
				dadosFinanceiros.setRendaFamiliar(set.getDouble("rendafamiliar"));
				dadosFinanceiros.setRendaPessoal(set.getDouble("rendapessoal"));
				dadosFinanceiros.setObservacao(set.getString("observacao"));
				dadosFinanceiros.setContaCorrente(set.getInt("contacorrente"));
				dadosFinanceiros.setBanco(set.getString("banco"));
				cliente = new Cliente();
				cliente.setDadosFinanceiros(dadosFinanceiros);
				cliente.setEndereco(endereco);
				cliente.setIdCliente(set.getInt("idcliente"));
				cliente.setCPF(set.getInt("cpf"));
				cliente.setNomeCompleto(set.getString("nomecliente"));
				cliente.setRG(set.getInt("rg"));
				cliente.setDataNascimento(set.getDate("datanascimento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	public Funcionario buscarDadosFuncionario(int codigo) {
		Funcionario funcionario = null;

		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_INFO_FUNCIONARIO_BY_ID);
			preparedStatement.setInt(1, codigo);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Estado estado = new Estado(set.getInt("iduf"), set.getString("siglauf"));
				Cidade cidade = new Cidade();
				cidade.setEstado(estado);
				cidade.setNome(set.getString("nomecidade"));
				Endereco endereco = new Endereco();
				endereco.setBairro(set.getString("bairro"));
				endereco.setNumero(set.getInt("numero"));
				endereco.setLogradouro(set.getString("logradouro"));
				endereco.setComplemento(set.getString("complemento"));
				endereco.setCidade(cidade);
				funcionario = new Funcionario();
				funcionario.setEndereco(endereco);
				funcionario.setIdFuncionario(set.getInt("idfuncionario"));
				funcionario.setCPF(set.getInt("cpf"));
				funcionario.setNome(set.getString("nome"));
				funcionario.setRG(set.getInt("rg"));
				funcionario.setDataNascimento(set.getDate("datanascimento"));
				funcionario.setCargo(set.getString("cargo"));
				funcionario.setEmail(set.getString("email"));
				funcionario.setTelefone(set.getInt("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	public List<Contrato> buscarRelatorio(Date intervaloInicio, Date intervaloFinal, int tipoIndex) {
		List<Contrato> listaContrato = new ArrayList<Contrato>();
		String statusContrato;
		String SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX = "SELECT CON.idContrato as idContrato , CON.valorEmprestimo as valorEmprestimo, "
				+ "C.nomeCompleto as nomeCompleto FROM Contrato CON JOIN Cliente C ON CON.idCliente = C.idCliente WHERE statusContrato = ? "
				+ "AND dataCriacaoContrato >= ? AND dataTerminoContrato <= ?";

		if (intervaloInicio == null && intervaloFinal == null) {
			SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX = "SELECT CON.idContrato as idContrato , CON.valorEmprestimo as valorEmprestimo, "
					+ "C.nomeCompleto as nomeCompleto FROM Contrato CON JOIN Cliente C ON CON.idCliente = C.idCliente WHERE statusContrato = ? ";
		} else if (intervaloInicio == null) {
			SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX = "SELECT CON.idContrato as idContrato , CON.valorEmprestimo as valorEmprestimo, "
					+ "C.nomeCompleto as nomeCompleto FROM Contrato CON JOIN Cliente C ON CON.idCliente = C.idCliente WHERE statusContrato = ? AND "
					+ "dataTerminoContrato <= ?";
		} else if (intervaloFinal == null) {
			SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX = "SELECT CON.idContrato as idContrato , CON.valorEmprestimo as valorEmprestimo, "
					+ "C.nomeCompleto as nomeCompleto FROM Contrato CON JOIN Cliente C ON CON.idCliente = C.idCliente WHERE statusContrato = ? AND "
					+ "dataCriacaoContrato >= ?";
		}

		if (tipoIndex == 0) {
			statusContrato = "Aprovado";
		} else {
			statusContrato = "Rejeitado";
		}

		try {
			PreparedStatement preparedStatement = this.conexao
					.prepareStatement(SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX);
			if (intervaloInicio == null && intervaloFinal == null) {
				preparedStatement.setString(1, statusContrato);
			} else if (intervaloInicio == null) {
				preparedStatement.setString(1, statusContrato);
				preparedStatement.setDate(2, intervaloFinal);
			} else if (intervaloFinal == null) {
				preparedStatement.setString(1, statusContrato);
				preparedStatement.setDate(2, intervaloInicio);
			}

			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Cliente cliente = new Cliente();
				cliente.setNomeCompleto(set.getString("nomeCompleto"));
				Contrato contrato = new Contrato();
				contrato.setIdContrato(set.getInt("idContrato"));
				contrato.setValorEmprestimo(set.getFloat("valorEmprestimo"));
				contrato.setCliente(cliente);
				listaContrato.add(contrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaContrato;
	}

	public String excluiContrato(int codigo) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_CONTRATO_BY_ID);
			prepared.setInt(1, codigo);
			prepared.executeUpdate();
			return "Contrato deletado com sucesso";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Contrato n�oo pode ser deletado! Tente novamente!";
		}
	}

	public void excluiContratoAntesCliente(int codigo) {
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(DELETE_CONTRATO_BY_ID);
			prepared.setInt(1, codigo);
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Contrato> buscarContrato(int codigo, String nome, String codigoSitucao) {
		List<Contrato> listaContratos = new ArrayList<Contrato>();
		String SELECT_CONTRATO_BY = "SELECT CON.idContrato AS id, C.nomeCompleto AS nomecliente, CON.statusContrato AS status, C.cpf as cpf FROM Contrato CON JOIN Cliente C ON CON.idCliente = C.idCliente WHERE  ";
		if (codigo > 0) {
			SELECT_CONTRATO_BY += " CON.idContrato = " + codigo;
		} else if (codigoSitucao != null) {
			if (nome != null && !nome.isEmpty()) {
				SELECT_CONTRATO_BY += " C.nomeCompleto LIKE '%" + nome + "%' AND CON.statusContrato = '" + codigoSitucao
						+ "'";
			} else {
				SELECT_CONTRATO_BY += " CON.statusContrato = '" + codigoSitucao + "'";
			}
		} else if (nome != null) {
			SELECT_CONTRATO_BY += " C.nomeCompleto LIKE '%" + nome + "%'";
		}

		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CONTRATO_BY);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Cliente cliente = new Cliente();
				cliente.setNomeCompleto(set.getString("nomecliente"));
				cliente.setCPF(set.getInt("cpf"));
				Contrato contrato = new Contrato();
				contrato.setIdContrato(set.getInt("id"));
				contrato.setCliente(cliente);
				contrato.setStatusContrato(set.getString("status"));
				listaContratos.add(contrato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaContratos;
	}

	public String editarPlanoBanco(PlanoEmprestimo planoEmprestimo) {
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
			prepared.setInt(10, planoEmprestimo.getIdPlanoEmprestimo());
			prepared.executeUpdate();
			return "Plano cadastrado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Plano não pode ser cadastrado! Tente novamente";
		}
	}

	public List<PlanoEmprestimo> buscarPlanoEmprestimoPorPerfil(String perfilCliente) {
		List<PlanoEmprestimo> listaPlanos = new ArrayList<PlanoEmprestimo>();
		if (perfilCliente == PerfilCliente.PERFIL_A.getName()) {
			listaPlanos.add(buscarPlano(0, "Plano 1").get(0));
		} else {
			if (perfilCliente == PerfilCliente.PERFIL_B.getName()) {
				listaPlanos.add(buscarPlano(0, "Plano 1").get(0));
				listaPlanos.add(buscarPlano(0, "Plano 2").get(0));
			} else {
				if (perfilCliente == PerfilCliente.PERFIL_C.getName()) {
					listaPlanos.add(buscarPlano(0, "Plano 1").get(0));
					listaPlanos.add(buscarPlano(0, "Plano 2").get(0));
					listaPlanos.add(buscarPlano(0, "Plano 3").get(0));
				} else if (perfilCliente == PerfilCliente.PERFIL_D.getName()) {
					listaPlanos.add(buscarPlano(0, "Plano 1").get(0));
					listaPlanos.add(buscarPlano(0, "Plano 2").get(0));
					listaPlanos.add(buscarPlano(0, "Plano 3").get(0));
					listaPlanos.add(buscarPlano(0, "Plano 4").get(0));
				}
			}
		}
		// List<PlanoEmprestimo> listaPlanos = new ArrayList<PlanoEmprestimo>();
		// SELECT * FROM PlanoEmprestimo WHERE ? >= valorMinimo AND ? <=
		// valorMaximo
		return listaPlanos;
	}

	public List<Estado> recuperarEstadosParaComboBoxBanco() {
		List<Estado> listaEstados = new ArrayList<Estado>();
		Estado estado;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ALL_ESTADO);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				estado = new Estado();
				estado.setIdEstado(resultSet.getInt("idEstado"));
				estado.setUf(resultSet.getString("sigla"));
				listaEstados.add(estado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEstados;
	}

	public List<Cliente> recuperarClienteParaComboBoxBanco() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Cliente cliente;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ALL_CLIENTE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(resultSet.getInt("idCliente"));
				cliente.setNomeCompleto(resultSet.getString("nomeCompleto"));
				listaClientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaClientes;
	}

	public List<PlanoEmprestimo> recuperarPlanoParaComboBoxBanco() {
		List<PlanoEmprestimo> listaPlanos = new ArrayList<PlanoEmprestimo>();
		PlanoEmprestimo planoEmprestimo;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ALL_PLANOEMPRESTIMO);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(resultSet.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setNome(resultSet.getString("nome"));
				listaPlanos.add(planoEmprestimo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPlanos;
	}

	public int getQntdParcelasParaPlano(int codigo) {
		PlanoEmprestimo planoEmprestimo = null;
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_PLANOEMPRESTIMO_BY_ID);
			preparedStatement.setInt(1, codigo);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setMaxParcelas(set.getInt("maxParcelas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planoEmprestimo.getMaxParcelas();
	}

	public int getQntdMinParcelasParaPlano(int codigo) {
		PlanoEmprestimo planoEmprestimo = null;
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_PLANOEMPRESTIMO_BY_ID);
			preparedStatement.setInt(1, codigo);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				planoEmprestimo = new PlanoEmprestimo();
				planoEmprestimo.setIdPlanoEmprestimo(set.getInt("idPlanoEmprestimo"));
				planoEmprestimo.setMinParcelas(set.getInt("minParcelas"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return planoEmprestimo.getMinParcelas();
	}
}