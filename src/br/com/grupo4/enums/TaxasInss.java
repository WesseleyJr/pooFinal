package br.com.grupo4.enums;

public enum TaxasInss {
	ALFAIXA01(0.075), 
	ALFAIXA02(0.09), 
	ALFAIXA03(0.12), 
	ALFAIXA04(0.14), 
	DEFAIXA01(0.0), 
	DEFAIXA02(21.18),
	DEFAIXA03(101.18), 
	DEFAIXA04(181.18);

	public Double taxasIN;

	private TaxasInss(Double taxas) {
		this.taxasIN = taxas;
	}

	public Double getTaxas() {
		return taxasIN;
	}

	public void setTaxas(Double taxas) {
		this.taxasIN = taxas;
	}

}
