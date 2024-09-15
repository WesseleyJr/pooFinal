package br.com.grupo4.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.grupo4.enums.Parentesco;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.interfaces.Desconto;

public class Funcionario extends Pessoa implements Desconto {

	private Double salarioBruto;
	private Parentesco parentesco;
	private TaxasInss taxasInss;
	private TaxasIr taxasIr;
	private List<Dependente> dependentes;
	private Double irfinal = 0.;
	private Double inssfinal = 0.;

	public Funcionario(String nome, String cpf, LocalDate dataNasc, Double salarioBruto) {
		super(nome, cpf, dataNasc);
		this.salarioBruto = salarioBruto;
		this.dependentes = new ArrayList<>();
	}

	public Funcionario(String nome, String cpf, LocalDate dataNasc) {
		super(nome, cpf, dataNasc);
	}

	@Override
	public String toString() {
		return "salarioBruto: " + salarioBruto + ", parentesco: " + parentesco + ", taxasInss: " + taxasInss
				+ ", taxasIr: " + taxasIr + ", dependentes: " + dependentes + ", irfinal: " + irfinal + ", inssfinal: "
				+ inssfinal;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public Double getIrfinal() {
		return irfinal;
	}

	public Double getInssfinal() {
		return inssfinal;
	}

	@Override
	public void calculoINSS() {
		if (salarioBruto <= taxasInss.FAIXA01.getValorMaximo()) {
			inssfinal = salarioBruto * taxasInss.FAIXA01.getPercentualAliquota() - taxasInss.FAIXA01.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA02.getValorMaximo()) {
			inssfinal = salarioBruto * taxasInss.FAIXA02.getPercentualAliquota() - taxasInss.FAIXA02.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA03.getValorMaximo()) {
			inssfinal = salarioBruto * taxasInss.FAIXA03.getPercentualAliquota() - taxasInss.FAIXA03.getValorDeducao();

		} else if (salarioBruto <= taxasInss.FAIXA04.getValorMaximo()) {
			inssfinal = salarioBruto * taxasInss.FAIXA04.getPercentualAliquota() - taxasInss.FAIXA04.getValorDeducao();

		} else {
			inssfinal = salarioBruto * taxasInss.FAIXA04.getPercentualAliquota();
		}
	}

	@Override
	public void calculoIR() {

		Double salarioBase = salarioBruto - calcularDependentes() - inssfinal;

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
			irfinal = 0.;
		}

	}

	public Double salarioLiquido() {
		return salarioBruto - inssfinal - irfinal;
	}

	public double calcularDependentes() {
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
}
