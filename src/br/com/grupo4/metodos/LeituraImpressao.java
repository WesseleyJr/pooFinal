package br.com.grupo4.metodos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.grupo4.classes.Dependente;
import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.excecoes.ExcecaoDependente;
import br.com.grupo4.excecoes.ExcecaoPessoa;

public class LeituraImpressao {

	public static List<Funcionario> lerArquivo() {

		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionarioAtual = null;
		List<Dependente> dependentes = new ArrayList<>();
		new JOptionPane();

		try {
			DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String endereco = JOptionPane.showInputDialog(null, "Caminho do arquivo");

			Scanner sc = new Scanner(new File(endereco));
			while (sc.hasNext()) {
				String linha = sc.nextLine();
				if (!linha.isEmpty()) {

					String[] dados = linha.split(";");
					String nome = dados[0];
					String cpf = dados[1];
					LocalDate dataNasc = LocalDate.parse(dados[2], dataFormato);

					verificaCpf(funcionarios, cpf, nome);

					try {
						Double salarioLiquido = Double.parseDouble(dados[3]);
						funcionarioAtual = new Funcionario(nome, cpf, dataNasc, salarioLiquido);
					} catch (NumberFormatException e) {
						String parentesco = dados[3].toUpperCase();
						
						verificaIdade(dataNasc, nome);
						
						Dependente dependente = new Dependente(nome, cpf, dataNasc, parentesco);
						dependentes.add(dependente);
					}

				} else {

					funcionarioAtual.setDependentes(new ArrayList<Dependente>(dependentes));
					funcionarios.add(funcionarioAtual);
					dependentes.clear();
					funcionarioAtual = null;
				}
			}
			if (funcionarioAtual != null) {
				funcionarioAtual.setDependentes(new ArrayList<Dependente>(dependentes));
				funcionarios.add(funcionarioAtual);
			}
			sc.close();
		} catch (ExcecaoPessoa e1) {
			JOptionPane.showInternalMessageDialog(null, e1.getMessage() + " Está com CPF invalido", null,
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (ExcecaoDependente e2) {
			JOptionPane.showInternalMessageDialog(null,
					"O dependente " + e2.getMessage() + " é maior de idade!\nPor favor o remova da lista!", null,
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Arquivo não encontrado", null, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return funcionarios;
	}

	public static void verificaCpf(List<Funcionario> funcionarios, String cpf, String nome) {
		for (Funcionario funcionario : funcionarios) {
			List<Dependente> dependenteVerificacao = funcionario.getDependentes();

			if (funcionario.getCpf().equals(cpf)) {
				throw new ExcecaoPessoa(nome);
			} else {
				for (Dependente dependente : dependenteVerificacao) {
					if (dependente.getCpf().equals(cpf)) {
						throw new ExcecaoPessoa(nome);
					}
				}
			}
		}
	}

	public static void verificaIdade(LocalDate dataNasc, String nome) {
		Period periodo = Period.between(dataNasc, LocalDate.now());
		int idade = periodo.getYears();
		if (idade > 18) {
			throw new ExcecaoDependente(nome);
		}
	}

	public static void arquivoSair(List<Funcionario> funcionarios) {
		try {
			String saida = JOptionPane.showInputDialog(null, "nome do arquivo");
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:/" + saida + ".csv"));
			for (Funcionario funcionario : funcionarios) {
				bw.append(String.format("%s ; %s ; %.2f ; %.2f ; %.2f ; %.2f ; %.2f\n", funcionario.getNome(),
						funcionario.getCpf(), funcionario.getInss(), funcionario.getIr(),
						funcionario.getValeTransporte(), funcionario.getPlanoDeSaude(), funcionario.getSalarioLiquido()));
			}
			bw.close();
			JOptionPane.showInternalMessageDialog(null, "Arquivo gerado com sucesso na pasta de Downloads");

		} catch (IOException e) {
			JOptionPane.showInternalMessageDialog(null, "Arquivo não gerado", null, JOptionPane.ERROR_MESSAGE);
		}

	}

}