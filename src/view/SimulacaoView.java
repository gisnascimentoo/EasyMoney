package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollBar;

public class SimulacaoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValorFinanciado;
	private JTextField textFieldValorPrestacao;
	private JTextField textFieldNumMeses;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public SimulacaoView() {
		setTitle("Simula\u00E7\u00E3o");
		setBounds(100, 100, 439, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblValorFinanciado = new JLabel("Valor Financiado");
		lblValorFinanciado.setBounds(24, 25, 89, 14);
		contentPane.add(lblValorFinanciado);
		
		textFieldValorFinanciado = new JTextField();
		textFieldValorFinanciado.setBounds(45, 47, 147, 20);
		contentPane.add(textFieldValorFinanciado);
		textFieldValorFinanciado.setColumns(10);
		
		JLabel lblValorDaPrestao = new JLabel("Valor da presta\u00E7\u00E3o");
		lblValorDaPrestao.setBounds(231, 25, 98, 14);
		contentPane.add(lblValorDaPrestao);
		
		textFieldValorPrestacao = new JTextField();
		textFieldValorPrestacao.setBounds(250, 47, 147, 20);
		contentPane.add(textFieldValorPrestacao);
		textFieldValorPrestacao.setColumns(10);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(231, 50, 20, 14);
		contentPane.add(lblR);
		
		JLabel label = new JLabel("R$");
		label.setBounds(24, 50, 20, 14);
		contentPane.add(label);
		
		JLabel lblNmeroDeMeses = new JLabel("N\u00FAmero de meses");
		lblNmeroDeMeses.setBounds(24, 92, 104, 14);
		contentPane.add(lblNmeroDeMeses);
		
		textFieldNumMeses = new JTextField();
		textFieldNumMeses.setBounds(24, 113, 86, 20);
		contentPane.add(textFieldNumMeses);
		textFieldNumMeses.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Plano de Empr\u00E9stimo");
		lblNewLabel.setBounds(152, 92, 104, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBoxPlanoEmprestimo = new JComboBox();
		comboBoxPlanoEmprestimo.setBounds(152, 113, 104, 20);
		contentPane.add(comboBoxPlanoEmprestimo);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(299, 101, 98, 34);
		contentPane.add(btnCalcular);
		
		JLabel lblResultadoDaSimulao = new JLabel("Resultado da Simula\u00E7\u00E3o");
		lblResultadoDaSimulao.setBounds(21, 157, 119, 14);
		contentPane.add(lblResultadoDaSimulao);
		
		textField = new JTextField();
		textField.setBounds(21, 182, 360, 86);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(380, 181, 17, 87);
		contentPane.add(scrollBar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(269, 289, 119, 23);
		contentPane.add(btnFechar);
		
		JButton btnGerarContrato = new JButton("Gerar Contrato");
		btnGerarContrato.setBounds(24, 289, 138, 23);
		contentPane.add(btnGerarContrato);
	}
}
