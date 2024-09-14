package br.com.grupo4.classes;

import java.time.LocalDate;

public class Dependente extends Funcionario {
	private String parentesco;

	public Dependente(String nome, String cpf, LocalDate dataNasc, String parentesco) {
		super(nome, cpf, dataNasc);
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return "Dependente{" +
                "nome='" + getNome() + "\n" +
                ", cpf='" + getCpf() + "\n" +
                ", dataNascimento='" + getDataNasc() + "\n" +
                ", parentesco='" + parentesco + "\n" +
                '}';
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

}
