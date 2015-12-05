package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cidade;
import model.Cliente;
import model.Contrato;
import model.DadosFinanceiros;
import model.Endereco;
import model.Estado;
import model.Funcionario;
import model.PlanoEmprestimo;
import model.StatusContrato;

public class ManipuladorBanco {
	
	private final String INSERT_ENDERECO = "INSERT INTO ENDERECO(logradouro, numero, bairro, CEP, idCidade) VALUES(?, ?, ?, ?)";
	private final String INSERT_CIDADE = "INSERT INTO CIDADE(nome, idEstado) VALUES(?, ?)";
	private final String INSERT_ESTADO = "INSERT INTO ESTADO(sigla) VALUES(?)";
	private final String INSERT_DADOSFINANCEIROS = "INSERT INTO DADOSFINANCEIROS(banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_CLIENTE = "INSERT INTO CLIENTE(nomeCompleto, dataNascimento, CPF, RG, idEndereco, idDadosFinanceiros) VALUES(?, ?, ?, ?, ?, ?)";
	private final String INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(nome, dataNascimento, CPF, RG, cargo, email, telefone, idEndereco) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_PLANOEMPRESTIMO = "INSERT INTO PLANOEMPRESTIMO(nome, dataCadastro, jurosTotal, jurosMensal, valorMinimo, valorMaximo, maxParcelas, minParcelas, observacao, idFuncionarioResponsavel) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_CONTRATO = "INSERT INTO CONTRATO(qtdParcelas, valorEmprestimo, valorParcelas, dataCriacaoContrato, dataTerminoContrato, statusContrato, idCliente, idFuncionarioResponsavel, idplanoEmprestimo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
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
			conectando = DriverManager.getConnection("jdbc:mysql://localhost:5432/easymoney", "??", "??");
		} catch (ClassNotFoundException e) {
			//JOptionPane.showMessageDialog(null, "Erro classe: " + e.getMessage());
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Erro conexão: " + e.getMessage());
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
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CIDADE,
					Statement.RETURN_GENERATED_KEYS);
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
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ESTADO,
					Statement.RETURN_GENERATED_KEYS);
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
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CLIENTE,
					Statement.RETURN_GENERATED_KEYS);
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
}
