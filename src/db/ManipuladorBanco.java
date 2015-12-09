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
	private final String INSERT_ENDERECO = "INSERT INTO ENDERECO(logradouro, numero, bairro, CEP, idCidade) VALUES(?, ?, ?, ?, ?)";
	private final String INSERT_CIDADE = "INSERT INTO CIDADE(nome, idEstado) VALUES(?, ?)";
	private final String INSERT_DADOSFINANCEIROS = "INSERT INTO DADOSFINANCEIROS(banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_CLIENTE = "INSERT INTO CLIENTE(nomeCompleto, dataNascimento, CPF, RG, idEndereco, idDadosFinanceiros) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(nome, dataNascimento, CPF, RG, cargo, email, telefone, idEndereco) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_PLANOEMPRESTIMO = "INSERT INTO PLANOEMPRESTIMO(nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo, valorMaximo, maxParcelas, minParcelas, observacao) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_CONTRATO = "INSERT INTO CONTRATO(qtdParcelas, valorEmprestimo, valorParcelas, dataCriacaoContrato, dataTerminoContrato, statusContrato, idCliente, idplanoEmprestimo, observacoes) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private final String SELECT_ALL_CLIENTE = "SELECT * FROM CLIENTE";
	private final String SELECT_ALL_ESTADO = "SELECT * FROM ESTADO"; 
	private final String SELECT_ALL_PLANOEMPRESTIMO = "SELECT * FROM PLANOEMPRESTIMO";
	
	private final String SELECT_PLANOEMPRESTIMO_BY_ID = "SELECT * FROM PLANOEMPRESTIMO WHERE IDPLANOEMPRESTIMO = ?";
	private final String SELECT_FUNCIONARIO_BY_ID = "SELECT * FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";
	private final String SELECT_ENDERECO_BY_ID = "SELECT * FROM ENDERECO WHERE IDENDERECO = ?";
	private final String SELECT_CONTRATO_BY_ID = "SELECT * FROM CONTRATO WHERE IDCONTRATO = ?";
	private final String SELECT_DADOSFINANCEIROS_BY_ID = "SELECT * FROM DADOSFINANCEIROS WHERE IDDADOSFINANCEIROS = ?";

	private final String SELECT_USUARIO_LOGIN = "SELECT * FROM Usuario WHERE login = ? and passwd = ?";
	
	private final String SELECT_INFO_CLIENTE_BY_ID = "SELECT C.IDCLIENTE AS IDCLIENTE, C.CPF AS CPF, C.NOMECOMPLETO AS NOMECLIENTE, "
			+ "C.RG AS RG, C.DATANASCIMENTO AS DATANASCIMENTO, E.LOGRADOURO AS LOGRADOURO, E.NUMERO AS NUMERO, E.BAIRRO AS BAIRRO, E.CEP AS CEP"
			+ "E.CEP AS CEP, CD.NOME AS NOMECIDADE, EST.IDESTADO AS IDUF, EST.SIGLA AS SIGLAUF, DF.BANCO AS BANCO, DF.AGENCIA AS AGENCIA, DF.CONTACORRENTE AS CONTACORRENTE, "
			+ "DF.RENDAFAMILIAR AS RENDAFAMILIAR, DF.RENDAPESSOAL AS RENDAPESSOAL, DF.OBSERVACAO AS OBSERVACAO "
			+ "FROM CLIENTE C LEFT JOIN ENDERECO E ON C.IDENDERECO = E.IDENDERECO LEFT JOIN CIDADE CD ON E.IDCIDADE = CD.IDCIDADE "
			+ "LEFT JOIN DADOSFINANCEIROS DF ON DF.IDDADOSFINANCEIROS = C.IDDADOSFINANCEIROS "
			+ "LEFT JOIN ESTADO EST ON EST.IDESTADO = CD.IDESTADO WHERE C.IDCLIENTE = ?";
	
	private final String SELECT_INFO_FUNCIONARIO_BY_ID = "SELECT F.IDFUNCIONARIO AS IDFUNCIONARIO, F.CPF AS CPF, F.NOME AS NOME, "
			+ "F.RG AS RG, F.DATANASCIMENTO AS DATANASCIMENTO, F.CARGO AS CARGO, F.EMAIL AS EMAIL, F.TELEFONE AS TELEFONE, E.LOGRADOURO AS LOGRADOURO, E.NUMERO AS NUMERO, E.BAIRRO AS BAIRRO, E.CEP AS CEP"
			+ "E.CEP AS CEP, CD.NOME AS NOMECIDADE, EST.IDESTADO AS IDUF, EST.SIGLA AS SIGLAUF"
			+ "FROM FUNCIONARIO F LEFT JOIN ENDERECO E ON F.IDENDERECO = E.IDENDERECO LEFT JOIN CIDADE CD ON E.IDCIDADE = CD.IDCIDADE "
			+ "LEFT JOIN ESTADO EST ON EST.IDESTADO = CD.IDESTADO WHERE F.IDFUNCIONARIO = ?";

	private final String SELECT_FUNCIONARIO_BY_CPF = "SELECT * FROM FUNCIONARIO WHERE CPF = ?";
	private final String SELECT_CLIENTE_BY_CPF = "SELECT * FROM CLIENTE WHERE CPF = ?";
	
	private final String SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX = "SELECT CON.idContrato as idContrato , CON.valorEmprestimo as valorEmprestimo, "
			+ "C.nomeCompleto as nomeCompleto FROM CONTRATO CON JOIN CLIENTE C ON CON.IDCLIENTE = C.IDCLIENTE WHERE STATUSCONTRATO = ? "
			+ "AND DATACRIACAOCONTRATO >= ? AND DATATERMINOCONTRATO <= ?";
	
	/*
	 * Update
	 */
	private final String UPDATE_CLIENTE_BY_ID = "UPDATE CLIENTE SET nomeCompleto = ?, dataNascimento = ?, CPF = ?, RG = ?, idEndereco = ?, idDadosFinanceiros = ? WHERE IDCLIENTE = ?";
	private final String UPDATE_PLANOEMPRESTIMO_BY_ID = "UPDATE PLANOEMPRESTIMO SET nome = ?, dataCadastro = ?, jurosTotal = ?, jurosMensal = ?, valorMinimo = ?, valorMaximo = ?, minParcelas = ?, maxParcelas = ?, observacao = ? WHERE IDPLANOEMPRESTIMO = ?";
	private final String UPDATE_FUNCIONARIO_BY_ID = "UPDATE FUNCIONARIO SET nome = ?, dataNascimento = ?, CPF = ?, RG = ?, cargo = ?, email = ?, telefone = ?, idEndereco = ? WHERE IDFUNCIONARIO = ?";
	private final String UPDATE_ENDERECO_BY_ID = "UPDATE ENDERECO SET logradouro = ?, numero = ?, bairro = ?, CEP = ?, cidade = ? WHERE IDENDERECO = ?";
	private final String UPDATE_CONTRATO_BY_ID = "UPDATE CONTRATO SET qntdParcelas = ?, valorEmprestimo = ?, valorParcelas = ?, dadaTerminoContrato = ?, idCliente = ?, idPlanoEmprestimo = ? WHERE IDCONTRATO = ?";
	private final String UPDATE_DADOSFINANCEIROS_BY_ID = "UPDATE DADOSFINANCEIROS SET banco = ?, agencia = ?, contaCorrente = ?, rendaFamiliar = ?, rendaPessoal = ?, observacao = ? WHERE IDDADOSFINANCEIROS = ?";

	/*
	 * Delete
	 */
	private final String DELETE_CONTRATO_BY_ID = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
	private final String DELETE_PLANOEMPRESTIMO_BY_ID = "DELETE FROM PLANOEMPRESTIMO WHERE IDPLANOEMPRESTIMO = ?";
	private final String DELETE_CLIENTE_BY_ID = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
	private final String DELETE_FUNCIONARIO_BY_ID = "DELETE FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";

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
		
		String sqlParaRecuperarIds = "SELECT IDENDERECO, IDDADOSFINANCEIROS FROM CLIENTE WHERE IDCLIENTE = ?";
		int idEndereco;
		int idDadosFinanceiros;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(UPDATE_CLIENTE_BY_ID,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, cliente.getNomeCompleto());
			prepared.setDate(2, cliente.getDataNascimento());
			prepared.setInt(3, cliente.getCPF());
			prepared.setInt(4, cliente.getRG());
			prepared.setInt(5, cliente.getIdCliente());
			prepared.executeUpdate();
			PreparedStatement preparedEndereco = this.conexao.prepareStatement(sqlParaRecuperarIds);
			ResultSet set = preparedEndereco.executeQuery();
			while (set.next()) {
				idEndereco = set.getInt("idEndereco");
				cliente.getEndereco().setIdEndereco(idEndereco);
				idDadosFinanceiros = set.getInt("idDadosFinanceiros");
				cliente.getDadosFinanceiros().setIdDadosFinanceiros(idDadosFinanceiros);
				this.editarEnderecoBanco(cliente.getEndereco());
				this.editarDadosFinanceirosBanco(cliente.getDadosFinanceiros());
			}
			return "Cadastro alterado com sucesso";
		} catch (Exception e) {
			return ""+ e.getMessage();
		}
	}
	
	public String editarFuncionarioBanco(Funcionario funcionario) {
		
		String sqlParaRecuperarIds = "SELECT IDENDERECO FROM FUNCIONARIO WHERE IDFUNCIONARIO = ?";
		int idEndereco;
		
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
			PreparedStatement preparedEndereco = this.conexao.prepareStatement(sqlParaRecuperarIds);
			ResultSet set = preparedEndereco.executeQuery();
			while (set.next()) {
				idEndereco = set.getInt("idEndereco");
				funcionario.getEndereco().setIdEndereco(idEndereco);
				this.editarEnderecoBanco(funcionario.getEndereco());
			}
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
			return "Cliente não pode ser deletado!";
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
			return "Funcionário não pode ser deletado!";
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
		Funcionario funcionario = null;
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
				contrato = new Contrato(set.getInt("qntdParcelas"), set.getFloat("valorEmprestimo"), set.getFloat("valorParcelas"),
						set.getDate("dataCriacaoContrato"), set.getDate("dataTerminoContrato"), cliente, set.getString("statusContrato"), planoEmprestimo, set.getString("observacoes"));
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
		String SELECT_PLANO_EMPRESTIMO_BY= "SELECT IDPLANOEMPRESTIMO, NOME FROM PLANOEMPRESTIMO WHERE 1 = 1";
		
		if (codigo > 0) {
			SELECT_PLANO_EMPRESTIMO_BY += "AND IDPLANOEMPRESTIMO = " + codigo;
		} 
		if (plano != null) {
			SELECT_PLANO_EMPRESTIMO_BY += "AND nome = " + plano;
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
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_INFO_CLIENTE_BY_ID);
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
		
		if (tipoIndex == 0) {
			statusContrato = "Aprovado";
		} else {
			statusContrato = "Rejeitado";
		}
		
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CONTRATO_BY_INTERVALO_MES_E_INDEX);
			preparedStatement.setString(1, statusContrato);
			preparedStatement.setDate(2, intervaloInicio);
			preparedStatement.setDate(3, intervaloFinal);
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
			return "Contrato não pode ser deletado! Tente novamente!";
		}
	}
	
	public List<Contrato> buscarContrato(int codigo, String nome, String codigoSitucao) {
		List<Contrato> listaContratos = new ArrayList<Contrato>();
		String SELECT_CONTRATO_BY= "SELECT CON.IDCONTRATO AS ID, C.NOMECOMPLETO AS NOMECLIENTE, CON.STATUSCONTRATO AS STATUS FROM CONTRATO CON JOIN CLIENTE C ON CON.IDCLIENTE = C.IDCLIENTE WHERE 1 = 1";
		
		if (codigo > 0) {
			SELECT_CONTRATO_BY += "AND CON.IDCONTRATO = " + codigo;
		} 
		if (nome != null) {
			SELECT_CONTRATO_BY += "AND C.nomeCompleto = " + nome;
		}
		if (codigoSitucao != null) {
			SELECT_CONTRATO_BY += "AND CON.statusContrato = " + codigoSitucao;
		}
		
		try {
			PreparedStatement preparedStatement = this.conexao.prepareStatement(SELECT_CONTRATO_BY);
			ResultSet set = preparedStatement.executeQuery();
			while (set.next()) {
				Cliente cliente = new Cliente();
				cliente.setNomeCompleto(set.getString("nome"));
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

	//nao entendi p ara que serve essa funcao
	public List<PlanoEmprestimo> buscarPlanoEmprestimoPorPerfil(String perfilCliente) {
		List<PlanoEmprestimo> listaPlanos = new ArrayList<PlanoEmprestimo>();
		//SELECT * FROM PLANOEMPRESTIMO WHERE ? >= valorMinimo AND ? <= valorMaximo
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
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ALL_CLIENTE);
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
}