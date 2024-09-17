package br.com.grupo4.metodos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import br.com.grupo4.classes.Funcionario;

public class Tela  implements ActionListener {
	List<Funcionario> funcionarios = new ArrayList<>();
	
	public Tela() {
		JFrame telaInicial = new JFrame();
		telaInicial.setTitle("Folha de Pagamentos");
		telaInicial.setSize(600,300);
		telaInicial.setVisible(true);
		telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaInicial.setLayout(null);
		telaInicial.setResizable(false);
		telaInicial.setLocationRelativeTo(null);
		
		JLabel bemvindo = new JLabel("Bem vindo ao gerador de Folha de Pagamentos.");
		bemvindo.setFont(new Font("Arial", Font.PLAIN, 22));
		bemvindo.setBackground(new Color(0,0,0));
		bemvindo.setBounds(50, -110, 610, 300);
		telaInicial.add(bemvindo);
		
		JLabel texto = new JLabel("Por favor pressione o botão abaixo para continuar!");
		texto.setFont(new Font("Arial", Font.BOLD, 17));
		texto.setBounds(70, 100, 550, 60);
		telaInicial.add(texto);
		
		JButton button = new JButton("Ler arquivo");
		button.setBounds(220, 200, 150, 60);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.addActionListener(this);
		telaInicial.add(button);
		
	}

	public void actionPerformed(ActionEvent e) {
		funcionarios = LeituraImpressao.lerArquivo();
		LeituraImpressao.visualizar(funcionarios);
		JFrame escolha = new JFrame();
		escolha.setTitle("Folha de Pagamentos");
		escolha.setSize(600,300);
		escolha.setVisible(true);
		escolha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		escolha.setLayout(null);
		escolha.setResizable(false);
		escolha.setLocationRelativeTo(null);
		
		JLabel texto = new JLabel("Escolha uma das opções!");
		texto.setFont(new Font("Arial", Font.PLAIN, 17));
		texto.setBounds(200, 50, 550, 60);
		escolha.add(texto);
		
		JButton banco = new JButton("Gravar no banco de dados");
		banco.setBounds(50, 100, 250, 60);
		banco.setFont(new Font("Arial", Font.BOLD, 12));
//		banco.addActionListener(chamarBanco());
		escolha.add(banco);
		
		JButton scv = new JButton("Gerar arquivo");
		scv.setBounds(330, 100, 200, 60);
		scv.setFont(new Font("Arial", Font.BOLD, 12));
		scv.addActionListener(scval);
		escolha.add(scv);
		
	}
	
//	ActionListener chamarBanco = new ActionListener() {
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			gravarbanco();
//		    System.exit(0);
//			
//		}
//	};
	
	ActionListener scval = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			LeituraImpressao.arquivoSair(funcionarios);
			System.exit(0);
			
		}
	};
}
