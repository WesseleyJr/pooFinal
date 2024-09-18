package br.com.grupo4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.connection.ConnectionFactory;

public class FolhaPagamentoDao {

	private Connection connection;

	public FolhaPagamentoDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserir(List<Funcionario> funcionarios) {
		String sql = "INSERT INTO Folha_pagamento( inss, ir, valeTransporte, planoSaude, valeRefeicao, salarioLiquido, id_funcionario) values(?,?,?,?,?,?,?)";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			for (Funcionario funcionario : funcionarios) {
		
				stmt.setDouble(1, funcionario.getInss());
				stmt.setDouble(2, funcionario.getIr());
				stmt.setDouble(3, funcionario.getValeTransporte());
				stmt.setDouble(4, funcionario.getPlanoDeSaude());
				stmt.setDouble(5, funcionario.getValeRefeicao());
				stmt.setDouble(6, funcionario.getSalarioLiquido());
				stmt.setInt(7, funcionario.getId());

				stmt.execute();
			}
			stmt.close();
			connection.close();
			JOptionPane.showMessageDialog(null, "Gravado com suscesso!", "Banco de dados", 1);
		} catch (SQLException e) {
			System.err.println("Erro ao gravar registro");
			e.printStackTrace();
		}
	}
}
