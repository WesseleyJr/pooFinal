package br.com.grupo4.interfaces;

public interface Desconto {
	public Double calculoINSS(Double salarioBruto);
	public Double calculoIR(Double salarioBruto, Double numeroDependentes, Double inss);
	public Double valeTransporte(Double salarioBruto);
	public Double planoDeSaude(int numeroDependentes);
}
