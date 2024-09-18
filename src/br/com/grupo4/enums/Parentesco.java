package br.com.grupo4.enums;

public enum Parentesco {

	FILHO(189.59, "FILHO"), 
	SOBRINHO(189.59, "SOBRINHO"), 
	OUTRO(189.59, "OUTRO");

	private Double valorDependente;
	private String tipoDependente;

	private Parentesco(Double valorDependente, String tipoDependente) {
		this.valorDependente = valorDependente;
		this.tipoDependente = tipoDependente;
	}

	public Double getValorDependente() {
		return valorDependente;
	}

	public String getTipoDependente() {
		return tipoDependente;
	}

}
