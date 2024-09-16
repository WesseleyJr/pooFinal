package br.com.grupo4.classes;

import java.util.List;

import br.com.grupo4.metodos.LeituraImpressao;

public class Main {
	public static void main(String[] args) {
		List<Funcionario> funcionarios = LeituraImpressao.lerArquivo();
		LeituraImpressao.arquivoSair(funcionarios);
	}

}