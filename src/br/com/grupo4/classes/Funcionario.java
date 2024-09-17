package br.com.grupo4.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.grupo4.enums.Parentesco;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.interfaces.Desconto;
import br.com.grupo4.metodos.CalculoContas;

public class Funcionario extends Pessoa {

	private Double salarioBruto;
	private Parentesco parentesco;
	private List<Dependente> dependentes;
	private Double inss = 0.;
	private Double ir = 0.;
	private Double valeTransporte = 0.;
	private Double planoDeSaude = 0.;
	private Double salarioLiquido = 0.;
	private CalculoContas calc = new CalculoContas();

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
		return "salarioBruto: " + salarioBruto + ", parentesco: " + parentesco + ", dependentes: " + dependentes;
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

	public Double getInss() {
		return inss = calc.calculoINSS(salarioBruto);
	}

	public Double getIr() {
		return ir = calc.calculoIR(salarioBruto, calcularDependentes(), inss);
	}

	public Double getValeTransporte() {
		return valeTransporte = calc.valeTransporte(salarioBruto);
	}
	
	public Double getPlanoDeSaude() {
		
		return planoDeSaude = calc.planoDeSaude(dependentes.size());
	}

	public Double getSalarioLiquido() {
		return salarioLiquido = calc.salarioLiquido(salarioBruto, inss, ir, valeTransporte, planoDeSaude);
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
