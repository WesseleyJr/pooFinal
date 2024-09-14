package br.com.grupo4.metodos;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.grupo4.classes.Dependente;
import br.com.grupo4.classes.Funcionario;

public class LeituraImpressao {

	public static void lerArquivo() {

		List<Funcionario> funcionarios = new ArrayList<>();
		Funcionario funcionarioAtual = null;
		List<Dependente> dependentes = new ArrayList<>();

		try {
			DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			Scanner leitura = new Scanner(System.in);
			System.out.println("Digite o nome do arquivo:");
			String endereco = leitura.next();
			leitura.close();

			Scanner sc = new Scanner(new File(endereco));

			while (sc.hasNext()) {
				String linha = sc.nextLine();
				if (!linha.isEmpty()) {

					String[] dados = linha.split(";");
					String nome = dados[0];
					String cpf = dados[1];
					LocalDate dataNasc = LocalDate.parse(dados[2], dataFormato);
					try {
						Double salarioLiquido = Double.parseDouble(dados[3]);
						funcionarioAtual = new Funcionario(nome, cpf, dataNasc, salarioLiquido);
					} catch (NumberFormatException e) {
						String parentesco = dados[3].toUpperCase();
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
		} catch (Exception e) {
			System.err.println("Deu ruim");
		}
	}

}
