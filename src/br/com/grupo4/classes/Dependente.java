package br.com.grupo4.classes;

import java.time.LocalDate;

import br.com.grupo4.enums.Parentesco;

	public class Dependente extends Funcionario {
	    private Parentesco parentesco;
	   

		public Dependente(String nome, String cpf, LocalDate dataNasc, Double salarioBruto, int dep,
				Parentesco parentesco) {
			super(nome, cpf, dataNasc, salarioBruto, dep);
			this.parentesco = parentesco;
		}

		@Override
		public String toString() {
			return "\nParentesco: " + parentesco;
		}

		public Parentesco getParentesco() {
			return parentesco;
		}

		public void setParentesco(Parentesco parentesco) {
			this.parentesco = parentesco;
		}

    
		
		
}	

