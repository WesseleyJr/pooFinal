package br.com.grupo4.enums;

public enum Fgts {
	TAXA(0.08);
	
	private Double taxaFgts;

	private Fgts(Double taxaVT) {
		this.taxaFgts = taxaVT;
	}

	public Double getTaxaVT() {
		return taxaFgts;
	}
	
	
}
