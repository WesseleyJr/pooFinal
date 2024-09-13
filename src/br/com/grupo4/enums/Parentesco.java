package br.com.grupo4.enums;

	public enum Parentesco {

		FILHO(189.59),
		SOBRINHO(189.59),
		OUTRO(189.59);
		
	    private Double valorDependente;
	  

		private Parentesco(Double valorDependente) {
			this.valorDependente = valorDependente;
		}

		public Double getValorDependente() {
			return valorDependente;
		}

		public void setValorDependente(Double valorDependente) {
			this.valorDependente = valorDependente;
		}
		

}
