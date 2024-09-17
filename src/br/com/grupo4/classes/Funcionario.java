package br.com.grupo4.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.grupo4.enums.Parentesco;
import br.com.grupo4.enums.TaxasInss;
import br.com.grupo4.enums.TaxasIr;
import br.com.grupo4.interfaces.Desconto;
import br.com.grupo4.metodos.CalculoDescontos;

public class Funcionario extends Pessoa {

	private Double salarioBruto;
	private List<Dependente> dependentes;
	private Double inss = 0.;
	private Double ir = 0.;
	private Double valeTransporte = 0.;
	private Double planoDeSaude = 0.;
	private Double salarioLiquido = 0.;
	private CalculoDescontos calculoDescontos = new CalculoDescontos();

	public Funcionario(String nome, String cpf, LocalDate dataNasc, Double salarioBruto) {
		super(nome, cpf, dataNasc);
		this.salarioBruto = salarioBruto;
		this.dependentes = new ArrayList<>();
	}
	

	@Override
	public String toString() {
		return "Funcionario [salarioBruto=" + salarioBruto + ", dependentes=" + dependentes + ", inss=" + inss + ", ir="
				+ ir + ", valeTransporte=" + valeTransporte + ", planoDeSaude=" + planoDeSaude + ", salarioLiquido="
				+ salarioLiquido + ", calculoDescontos=" + calculoDescontos + "]";
	}



	public Funcionario(String nome, String cpf, LocalDate dataNasc) {
		super(nome, cpf, dataNasc);
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
	
	//Get metodos

	public Double getInss() {
		return inss = calculoDescontos.calculoINSS(salarioBruto);
	}

	public Double getIr() {
		return ir = calculoDescontos.calculoIR(salarioBruto, calculoDescontos.calcularDependentes(dependentes), inss);
	}

	public Double getValeTransporte() {
		return valeTransporte = calculoDescontos.valeTransporte(salarioBruto);
	}
	
	public Double getPlanoDeSaude() {
		
		return planoDeSaude = calculoDescontos.planoDeSaude(dependentes.size());
	}

	public Double getSalarioLiquido() {
		return salarioLiquido = calculoDescontos.salarioLiquido(salarioBruto, inss, ir, valeTransporte, planoDeSaude);
	}
	
	

}
