package br.com.grupo4.enums;

public enum TaxasIr {
	ALFAIXA01 (0.0),
    ALFAIXA02 (0.075),
    ALFAIXA03 (0.15),
    ALFAIXA04 (0.225),
    ALFAIXA05 (0.275),
    DEFAIXA01 (0.0),
    DEFAIXA02 (169.44),
    DEFAIXA03 (381.44),
    DEFAIXA04 (662.77),
    DEFAIXA05 (896.00);
	
	public Double taxasIr;


	private TaxasIr(Double taxasIr) {
		this.taxasIr = taxasIr;
	}

	public Double getTaxasIr() {
		return taxasIr;
	}

	public void setTaxasIr(Double taxasIr) {
		this.taxasIr = taxasIr;
	}
	 

}
