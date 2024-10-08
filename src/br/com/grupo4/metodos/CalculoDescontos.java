package br.com.grupo4.metodos;


import java.util.List;

import br.com.grupo4.classes.Dependente;
import br.com.grupo4.enums.Parentesco;
import br.com.grupo4.enums.PlanoSaude;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.enums.ValeRefeicao;
import br.com.grupo4.enums.Fgts;
import br.com.grupo4.interfaces.Desconto;

public class CalculoDescontos implements Desconto {
	
	private TaxasInss taxasInss;
	private TaxasIr taxasIr;
	private PlanoSaude planoSaude;
	private Parentesco parentesco;
	private Fgts fgts;
	private ValeRefeicao valeRefeicao;
	private Double totalRefeicao;
	private Double descontoPlanoSaude;
	
	
	public Double calcularDependentes(List<Dependente> dependentes) {
		Double totalDependente = 0.;

		for (Dependente dependente : dependentes) {

			if (parentesco.FILHO.getTipoDependente().equals(dependente.getParentesco())) {
				totalDependente += parentesco.FILHO.getValorDependente();

			} else if (parentesco.SOBRINHO.getTipoDependente().equals(dependente.getParentesco())) {
				totalDependente += parentesco.SOBRINHO.getValorDependente();

			} else if (parentesco.OUTRO.getTipoDependente().equals(dependente.getParentesco())) {
				totalDependente += parentesco.OUTRO.getValorDependente();

			} else {
				return 0.;
			}
		}
		return totalDependente;

	}
	
	@Override
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
		Double irfinal = 0.;

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
	@Override
	public Double fgts(Double salarioBruto) {

		Double fgtsTotal = salarioBruto * fgts.TAXA.getTaxaVT();
		return fgtsTotal;

	}

	@Override
	public Double planoDeSaude(int numeroDependentes) {
		
		
		Double valorTotalPlanoSaude =   planoSaude.CUSTO.getValorPlano() + (numeroDependentes * planoSaude.CUSTO.getCustoDependente());
		descontoPlanoSaude = valorTotalPlanoSaude * 0.3;
		valorTotalPlanoSaude -= descontoPlanoSaude;

		return valorTotalPlanoSaude;

	}
	
	public Double valeRefeicao() {
		int diasTrabalhados = 22;
		Double total;
		total = valeRefeicao.CUSTO.getValorDia() * diasTrabalhados;
		totalRefeicao = total * valeRefeicao.CUSTO.getTaxaDesconto();
		total -= totalRefeicao;
		return total;
	}
	
	public Double salarioLiquido(Double salarioBruto, Double inss, Double ir) {
		return salarioBruto - inss - ir - descontoPlanoSaude - totalRefeicao;
	}

}
