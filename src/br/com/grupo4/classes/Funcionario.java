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
		return super.toString() + "SalarioBruto: " + salarioBruto + "\n INSS: " + calculoINSS() + "\nIR: "
				+ String.format("%.2f", calculoIR()) + "\nSal liq: " + String.format("%.2f", salarioLiquido());
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

	@Override
	public Double calculoINSS() {
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
	public Double calculoIR() {
		Double ir = salarioBruto - calcularDependentes() - calculoINSS();
		Double irTotal;

		if (salarioBruto <= taxasIr.FAIXA01.getValorMaximo()) {
			irTotal = ir * taxasIr.FAIXA01.getPercentualAliquota() - taxasIr.FAIXA01.getValorDeducao();

		} else if (salarioBruto <= taxasIr.FAIXA02.getValorMaximo()) {
			irTotal = ir * taxasIr.FAIXA02.getPercentualAliquota() - taxasIr.FAIXA02.getValorDeducao();

		} else if (salarioBruto <= taxasIr.FAIXA03.getValorMaximo()) {
			irTotal = ir * taxasIr.FAIXA03.getPercentualAliquota() - taxasIr.FAIXA03.getValorDeducao();

		} else if (salarioBruto <= taxasIr.FAIXA04.getValorMaximo()) {
			irTotal = ir * taxasIr.FAIXA04.getPercentualAliquota() - taxasIr.FAIXA04.getValorDeducao();

		} else {
			irTotal = ir * taxasIr.FAIXA05.getPercentualAliquota() - taxasIr.FAIXA05.getValorDeducao();

		}

		if (irTotal <= 0) {
			return irTotal = 0.;
		} else {
			return irTotal;
		}

	}

	public Double salarioLiquido() {
		return salarioBruto - calculoINSS() - calculoIR();
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


