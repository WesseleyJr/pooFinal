package br.com.grupo4.classes;

public class Funcionario {
	
    private Double salarioBruto;
    private int dep;

    public Funcionario(Double salarioBruto, int dep) {
        this.salarioBruto = salarioBruto;
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "SalarioBruto: " + salarioBruto + "\n INSS: " + calculoINSS() +
                "\nIR: " + String.format("%.2f", calculoIR()) + "\nSal liq: " + String.format("%.2f", salarioLiquido());
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

    public Double calculoINSS () {
        if (salarioBruto < 1412.) {
            return salarioBruto * 0.075;
        } else if (salarioBruto >= 1412.01 && salarioBruto <= 2666.68) {
            return (salarioBruto * 0.09) - 21.18;
        } else if (salarioBruto >= 2666.69 && salarioBruto <= 4000.03) {
            return (salarioBruto * 0.12) - 101.18;
        } else if (salarioBruto >= 4000.04 && salarioBruto <= 7786.02) {
            return (salarioBruto * 0.12) - 181.18;
        }else {
            return salarioBruto;
        }
    }

    public Double calculoIR(){
        Double renda;
        if (salarioBruto < 2259){
             renda = salarioBruto - (dep * 189.59) - calculoINSS();

        } else if (salarioBruto >= 2259.21 && salarioBruto <= 2826.65) {
            renda = ((salarioBruto - (dep * 189.59) - calculoINSS())* 0.075) - 169.44;

        } else if (salarioBruto >= 2826.66 && salarioBruto <= 3751.05) {
            renda = ((salarioBruto - (dep * 189.59) - calculoINSS())* 0.15) - 381.44;

        } else if (salarioBruto >= 3751.06 && salarioBruto <= 4664.68) {
            renda = ((salarioBruto - (dep * 189.59) - calculoINSS())* 0.225) - 662.77;

        }else{
            renda = ((salarioBruto - (dep * 189.59) - calculoINSS())* 0.275) - 896.00;

        }
        if(renda <= 0){
            return 0.;
        }else
            return renda;
    }

    public Double salarioLiquido() {
        return salarioBruto - calculoINSS() - calculoIR();
    }

}
