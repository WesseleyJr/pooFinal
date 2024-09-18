package br.com.grupo4.enums;

public enum PlanoSaude {
	CUSTO(280., 30.);
	
	private Double valorPlano;
	private Double custoDependente;
	
	private PlanoSaude(Double valorPlano, Double custoDependente) {
		this.valorPlano = valorPlano;
		this.custoDependente = custoDependente;
	}

	public Double getValorPlano() {
		return valorPlano;
	}

	public Double getCustoDependente() {
		return custoDependente;
	}	
		
}
