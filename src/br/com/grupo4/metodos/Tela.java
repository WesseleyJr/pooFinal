
package br.com.grupo4.metodos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.com.grupo4.classes.Funcionario;

public class Tela extends JFrame implements ActionListener {
	public Tela() {

		setTitle("Folha de Pagamentos");
		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JLabel bemvindo = new JLabel("Bem vindo ao gerador de Folha de Pagamentos.");
		bemvindo.setFont(new Font("Arial", Font.PLAIN, 22));
		bemvindo.setBackground(new Color(0, 0, 0));
		bemvindo.setBounds(50, -110, 610, 300);
		add(bemvindo);

		JLabel texto = new JLabel("Por favor pressione o botão abaixo para continuar!");
		texto.setFont(new Font("Arial", Font.BOLD, 17));
		texto.setBounds(70, 100, 550, 60);
		add(texto);

		JButton button = new JButton("Gerar arquivo");
		button.setBounds(220, 200, 150, 60);
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.addActionListener(this);
		add(button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Funcionario> funcionarios = LeituraImpressao.lerArquivo();
		LeituraImpressao.arquivoSair(funcionarios);
		System.exit(0);
	}
}
