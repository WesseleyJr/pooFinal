package br.com.grupo4.enums;

public enum TaxasInss {
	FAIXA01 (1412., 0.075, 0.),
	FAIXA02 (2666.68, 0.09, 21.18),
	FAIXA03 (4000.03, 0.12, 101.18),
	FAIXA04 (7786.02, 0.14, 181.18);
	
	private Double valorMaximo;
	private Double percentualAliquota;
	private Double valorDeducao;
	
	private TaxasInss(Double valorMaximo, Double percentualAliquota, Double valorDeducao) {
		this.valorMaximo = valorMaximo;
		this.percentualAliquota = percentualAliquota;
		this.valorDeducao = valorDeducao;
	}

	public Double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(Double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Double getPercentualAliquota() {
		return percentualAliquota;
	}

	public void setPercentualAliquota(Double percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	public Double getValorDeducao() {
		return valorDeducao;
	}

	public void setValorDeducao(Double valorDeducao) {
		this.valorDeducao = valorDeducao;
	}
	
	
	
	
	
}
