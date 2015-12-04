package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManipuladorBanco {
	
	/* OPERAÇÕES DDL
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	Connection conexao;
	
	public ManipuladorBanco() {
		try {
			this.conexao = this.conectasgbd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection conectasgbd() throws SQLException {
		Connection conectando = null;
		try {
			Class.forName("org.postgresql.Driver");
			conectando = DriverManager.getConnection("jdbc:postgresql://localhost:5432/distribuidora", "everton",
					"everton");
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

}
