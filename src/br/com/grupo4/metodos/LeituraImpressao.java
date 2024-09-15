package br.com.grupo4.metodos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.grupo4.classes.Dependente;
import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.excecoes.ExcecaoDependente;

public class LeituraImpressao {

	public static List<Funcionario> lerArquivo() {

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
						try {
							Period periodo = Period.between(dataNasc, LocalDate.now());
							int idade = periodo.getYears();
							if (idade < 18) {
								Dependente dependente = new Dependente(nome, cpf, dataNasc, parentesco);
								dependentes.add(dependente);
							} else {
								throw new ExcecaoDependente("Maior de idade não pode!");

							}
						} catch (ExcecaoDependente e2) {
							System.err.println(e2.getMessage());
							System.exit(0);
						}

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
		return funcionarios;
	}

	public static void arquivoSair(List<Funcionario> funcionarios) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\curso\\saida.csv"));
			for (Funcionario funcionario : funcionarios) {
				funcionario.calculoINSS();
				funcionario.calculoIR();
				bw.append(String.format("%s ; %s ; %.2f ; %.2f ; %.2f\n", funcionario.getNome(), funcionario.getCpf(),
						funcionario.getInssfinal(), funcionario.getIrfinal(), funcionario.salarioLiquido()));
			}
			System.out.println("Gravando arquivo....");
			bw.close();
			System.out.println("Arquivo gravado!!");

		} catch (IOException e) {
			System.err.println("Deu merda!");
		}

	}
}
