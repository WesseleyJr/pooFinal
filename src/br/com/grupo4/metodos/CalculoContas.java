package br.com.grupo4.metodos;

import br.com.grupo4.classes.Funcionario;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.interfaces.Desconto;

public class CalculoContas implements Desconto {

	private Funcionario funcionario;
	private TaxasInss taxasInss;
	private TaxasIr taxasIr;
	private Double irfinal = 0.;
	private Double inssfinal = 0.;

	@Override
	public String toString() {
		return "funcionario: " + funcionario + ", taxasInss: " + taxasInss + ", taxasIr: " + taxasIr + ", irfinal: "
				+ irfinal + ", inssfinal: " + inssfinal;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public TaxasInss getTaxasInss() {
		return taxasInss;
	}

	public TaxasIr getTaxasIr() {
		return taxasIr;
	}

	public Double getIrfinal() {
		return irfinal;
	}

	public Double getInssfinal() {
		return inssfinal;
	}

	public Double calculoINSS(Double salarioBruto) {

		if (salarioBruto <= taxasInss.FAIXA01.getValorMaximo()) {
			return salarioBruto * taxasInss.FAIXA01.getPercentualAliquota() - taxasInss.FAIXA01.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA02.getValorMaximo()) {
			return salarioBruto * taxasInss.FAIXA02.getPercentualAliquota() - taxasInss.FAIXA02.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA03.getValorMaximo()) {
			return salarioBruto * taxasInss.FAIXA03.getPercentualAliquota() - taxasInss.FAIXA03.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA04.getValorMaximo()) {
			return salarioBruto * taxasInss.FAIXA04.getPercentualAliquota() - taxasInss.FAIXA04.getValorDeducao();

		} else {
			return salarioBruto * taxasInss.FAIXA04.getPercentualAliquota();
		}
	}

	@Override
	public Double calculoIR(Double salarioBruto, Double numeroDependentes, Double inss) {

		Double salarioBase = salarioBruto - numeroDependentes - inss;

		if (salarioBase <= taxasIr.FAIXA01.getValorMaximo()) {
			irfinal = (salarioBase * taxasIr.FAIXA01.getPercentualAliquota()) - taxasIr.FAIXA01.getValorDeducao();

		} else if (salarioBase <= taxasIr.FAIXA02.getValorMaximo()) {
			irfinal = (salarioBase * taxasIr.FAIXA02.getPercentualAliquota()) - taxasIr.FAIXA02.getValorDeducao();

		} else if (salarioBase <= taxasIr.FAIXA03.getValorMaximo()) {
			irfinal = (salarioBase * taxasIr.FAIXA03.getPercentualAliquota()) - taxasIr.FAIXA03.getValorDeducao();

		} else if (salarioBase <= taxasIr.FAIXA04.getValorMaximo()) {
			irfinal = (salarioBase * taxasIr.FAIXA04.getPercentualAliquota()) - taxasIr.FAIXA04.getValorDeducao();

		} else if (salarioBase > taxasIr.FAIXA05.getValorMaximo()) {
			irfinal = (salarioBase * taxasIr.FAIXA05.getPercentualAliquota()) - taxasIr.FAIXA05.getValorDeducao();

		}

		if (irfinal <= 0) {
			return irfinal = 0.;
		} else {
			return irfinal;
		}

	}

	public Double salarioLiquido(Double salarioBruto, Double inss, Double ir, Double valeTransporte) {
		return salarioBruto - inss - ir - valeTransporte;
	}

	public Double valeTransporte(Double salarioBruto) {

		Double vt = salarioBruto * 0.06;

		if (vt > 500) {
			return vt = 500.;
		} else {
			return vt;
		}

	}

}
