package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DadosFinanceiros;
import model.Endereco;

public class ManipuladorBanco {
	
	private final String INSERT_ENDERECO = "INSERT INTO ENDERECO(logradouro, numero, bairro, CEP, idCidade) VALUES(?, ?, ?, ?)";
	private final String INSERT_DADOSFINANCEIROS = "INSERT INTO DADOSFINANCEIROS(banco, agencia, contaCorrente, rendaFamiliar, rendaPessoal, observacao) VALUES(?, ?, ?, ?, ?, ?)";

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
			conectando = DriverManager.getConnection("jdbc:mysql://localhost:5432/easymoney", "??",
					"??");
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
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ENDERECO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, endereco.getLogradouro());
			prepared.setInt(2, endereco.getNumero());
			prepared.setString(3, endereco.getBairro());
			prepared.setString(4, endereco.getCEP());
			prepared.setInt(5, endereco.getCidade().getIdCidade());
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
	
	public int salvarDadosFinanceiros(DadosFinanceiros dadosFinanceiros) {
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

}
