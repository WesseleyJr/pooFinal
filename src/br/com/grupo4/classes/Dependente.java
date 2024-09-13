package br.com.grupo4.classes;

import br.com.grupo4.enums.Parentesco;

	public class Dependente extends Funcionario {
	    private Parentesco parentesco;
	    private static int totalDependente= 0;
	    
		public Dependente(Parentesco parentesco) {
			super();
			this.parentesco = parentesco;
			totalDependente++;
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

    
		public Double calcularDependentes() {
			if (parentesco.FILHO != null) {
				return parentesco.FILHO.getValorDependente() * totalDependente;

			} else if (parentesco.SOBRINHO != null) {
				return parentesco.SOBRINHO.getValorDependente() * totalDependente;				
				
			} else if (parentesco.OUTRO != null) {
				return parentesco.OUTRO.getValorDependente() * totalDependente;
				
			} else {
				return null;
			}
		
		}
		
}	

