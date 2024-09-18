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
	private Double fgts = 0.;
	private Double planoDeSaude = 0.;
	private Double valeRefeicao = 0.;
	private Double salarioLiquido = 0.;
	private int id;
	private CalculoDescontos calculoDescontos = new CalculoDescontos();
	

	public Funcionario(String nome, String cpf, LocalDate dataNasc, Double salarioBruto) {
		super(nome, cpf, dataNasc);
		this.salarioBruto = salarioBruto;
		this.dependentes = new ArrayList<>();
	}
	

	@Override
	public String toString() {
		return String.format("Nome: %s ; CPF: %s ; INSS: %.2f ; IR: %.2f ; FGTS: %.2f ; Plano de saude: %.2f ; Vale Refeição: %.2f ; Salario liquido: %.2f\n", nome, cpf, inss, ir, fgts, planoDeSaude, valeRefeicao, salarioLiquido );
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
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	//Get metodos



	public Double getInss() {
		return inss = calculoDescontos.calculoINSS(salarioBruto);
	}

	public Double getIr() {
		return ir = calculoDescontos.calculoIR(salarioBruto, calculoDescontos.calcularDependentes(dependentes), inss);
	}

	public Double getFgts() {
		return fgts = calculoDescontos.fgts(salarioBruto);
	}
	
	public Double getPlanoDeSaude() {
		
		return planoDeSaude = calculoDescontos.planoDeSaude(dependentes.size());
	}

	public Double getValeRefeicao() {
		return valeRefeicao = calculoDescontos.valeRefeicao();
	}

	public Double getSalarioLiquido() {
		return salarioLiquido = calculoDescontos.salarioLiquido(salarioBruto, inss, ir);
	}
	
	

}
