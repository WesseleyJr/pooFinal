package br.com.grupo4.enums;

public enum ValeRefeicao {
	CUSTO(20., 0.2);
	
	private Double valorDia;
	private Double taxaDesconto;
	
	private ValeRefeicao(Double valorDia, Double taxaDesconto) {
		this.valorDia = valorDia;
		this.taxaDesconto = taxaDesconto;
	}

	public Double getValorDia() {
		return valorDia;
	}

	public Double getTaxaDesconto() {
		return taxaDesconto;
	}
	
	

}
