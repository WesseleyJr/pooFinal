package br.com.grupo4.classes;

import java.time.LocalDate;

import br.com.grupo4.enums.Parentesco;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.interfaces.Desconto;

public class Funcionario extends Pessoa implements Desconto {

	private Double salarioBruto;
	private int dep;
	private Parentesco parentesco;

	public Funcionario(String nome, String cpf, LocalDate dataNasc, Double salarioBruto, int dep) {
		super(nome, cpf, dataNasc);
		this.salarioBruto = salarioBruto;
		this.dep = dep;
	}

	@Override
	public String toString() {
		return "SalarioBruto: " + salarioBruto + "\n INSS: " + calcularINSS() + "\nIR: "
				+ String.format("%.2f", calculoIR()) + "\nSal liq: " + String.format("%.2f", salarioLiquido());
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public int getDep() {
		return dep;
	}

	public void setDep(int dep) {
		this.dep = dep;
	}

	public Double calcularINSS() {
		if (salarioBruto <= TaxasInss.FAIXA01.getValorMaximo()) {
			return salarioBruto * TaxasInss.FAIXA01.getPercentualAliquota() - TaxasInss.FAIXA01.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA02.getValorMaximo()) {
			return salarioBruto * TaxasInss.FAIXA02.getPercentualAliquota() - TaxasInss.FAIXA02.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA03.getValorMaximo()) {
			return salarioBruto * TaxasInss.FAIXA03.getPercentualAliquota() - TaxasInss.FAIXA03.getValorDeducao();

		} else if (salarioBruto <= TaxasInss.FAIXA04.getValorMaximo()) {
			return salarioBruto * TaxasInss.FAIXA04.getPercentualAliquota() - TaxasInss.FAIXA04.getValorDeducao();

		} else {
			return salarioBruto * TaxasInss.FAIXA04.getPercentualAliquota();
		}
	}

	public Double calculoIR() {
		Double ir = salarioBruto - calcularDependentes() - calcularINSS();
		Double irTotal;

		if (salarioBruto <= TaxasIr.FAIXA01.getValorMaximo()) {
			irTotal = ir * TaxasIr.FAIXA01.getPercentualAliquota() - TaxasIr.FAIXA01.getValorDeducao();

		} else if (salarioBruto <= TaxasIr.FAIXA02.getValorMaximo()) {
			irTotal = ir * TaxasIr.FAIXA02.getPercentualAliquota() - TaxasIr.FAIXA02.getValorDeducao();

		} else if (salarioBruto <= TaxasIr.FAIXA03.getValorMaximo()) {
			irTotal = ir * TaxasIr.FAIXA03.getPercentualAliquota() - TaxasIr.FAIXA03.getValorDeducao();

		} else if (salarioBruto <= TaxasIr.FAIXA04.getValorMaximo()) {
			irTotal = ir * TaxasIr.FAIXA04.getPercentualAliquota() - TaxasIr.FAIXA04.getValorDeducao();

		} else {
			irTotal = ir * TaxasIr.FAIXA05.getPercentualAliquota() - TaxasIr.FAIXA05.getValorDeducao();

		}

		if (irTotal <= 0) {
			return irTotal = 0.;
		} else {
			return irTotal;
		}

	}

	public Double salarioLiquido() {
		return salarioBruto - calcularINSS() - calculoIR();
	}

	private Double totalDependente = 0.;

	public Double calcularDependentes() {

		if (parentesco.FILHO != null) {
			totalDependente += parentesco.FILHO.getValorDependente();

		} else if (parentesco.SOBRINHO != null) {
			totalDependente += parentesco.SOBRINHO.getValorDependente();

		} else if (parentesco.OUTRO != null) {
			totalDependente += parentesco.OUTRO.getValorDependente();

		} else {
			return null;
		}

		return totalDependente;
	}
}
