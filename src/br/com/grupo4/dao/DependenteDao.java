package br.com.grupo4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.grupo4.classes.Dependente;
import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.connection.ConnectionFactory;

public class DependenteDao {
	private Connection connection;
	
	public DependenteDao() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(List<Funcionario> funcionarios) {
		String sql = "INSERT INTO dependentes(nome, cpf, dataNasc, parentesco, id_funcionario) values(?,?,?,?,?)"
				+ "ON CONFLICT (cpf) DO UPDATE SET nome = EXCLUDED.nome, dataNasc = EXCLUDED.dataNasc,"
				+ "parentesco = EXCLUDED.parentesco, id_funcionario = EXCLUDED.id_funcionario";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			for (Funcionario funcionario : funcionarios) {
				for (Dependente dependente : funcionario.getDependentes()) {
					
					stmt.setString(1, dependente.getNome());
					stmt.setString(2, dependente.getCpf());
					stmt.setDate(3, java.sql.Date.valueOf(dependente.getDataNasc()));
					stmt.setString(4, dependente.getParentesco());
					stmt.setInt(5, funcionario.getId());
					stmt.execute();
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
