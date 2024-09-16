package br.com.grupo4.enums;

public enum TaxasIr {
	FAIXA01(2259., 0., 0.), 
	FAIXA02(2826.65, 0.075, 169.44), 
	FAIXA03(3751.05, 0.15, 381.44),
	FAIXA04(4664.68, 0.225, 662.77), 
	FAIXA05(4664.68, 0.275, 896.);

	private Double valorMaximo;
	private Double percentualAliquota;
	private Double valorDeducao;

	private TaxasIr(Double valorMaximo, Double percentualAliquota, Double valorDeducao) {
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
