package br.com.grupo4.enums;

public enum ValeTransporte {
	TAXA(0.06);
	
	private Double taxaVT;

	private ValeTransporte(Double taxaVT) {
		this.taxaVT = taxaVT;
	}

	public Double getTaxaVT() {
		return taxaVT;
	}
	
	
}
