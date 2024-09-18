package br.com.grupo4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.connection.ConnectionFactory;

public class FuncionarioDao {
	private Connection connection;

	public FuncionarioDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserir(List<Funcionario> funcionarios) {
		String sql = "INSERT INTO funcionario(nome, cpf, dataNasc, salariobruto) values(?,?,?,?)"
				+ "ON CONFLICT (cpf) DO UPDATE SET nome = EXCLUDED.nome, dataNasc = EXCLUDED.dataNasc,"
				+ "salarioBruto = EXCLUDED.salariobruto";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (Funcionario funcionario : funcionarios) {

				stmt.setString(1, funcionario.getNome());
				stmt.setString(2, funcionario.getCpf());
				stmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataNasc()));
				stmt.setDouble(4, funcionario.getSalarioBruto());
				stmt.execute();
				
				ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next()) {
					int idFuncionario = rs.getInt(1);
					funcionario.setId(idFuncionario);
				}
				
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("Erro ao gravar registro");
			e.printStackTrace();
		}
	}
}
