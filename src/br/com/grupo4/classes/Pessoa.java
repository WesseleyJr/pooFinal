package br.com.grupo4.classes;

import java.time.LocalDate;

public abstract class Pessoa {

	protected String nome;
	protected String cpf;
	protected LocalDate dataNasc;

	public Pessoa(String nome, String cpf, LocalDate dataNasc) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return "\nNome: " + nome + "\nCpf: " + cpf + "\nData de Nascimento: " + dataNasc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

}
