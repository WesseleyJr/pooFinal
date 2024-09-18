package br.com.grupo4.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	String url = "jdbc:postgresql://localhost:5432/folha";
	String usuario = "postgres";
	String senha = "142536";
	
	private Connection connection;
	
	public Connection getConnection() {
		try {
			
			connection = DriverManager.getConnection(url,usuario,senha);
			
		} catch (SQLException e) {
				System.err.println("Não foi possivel conectar.....");
		}
		
		return connection;
	}
}
