package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollBar;

public class SimulacaoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldValorFinanciado;
	private JTextField textFieldValorPrestacao;
	private JTextField textFieldNumMeses;
	private JTextField textFieldValorFinal;
	DefaultTableModel modelo;
	private JScrollPane jScrollPane1_1;
	private JTable jTable;
	private JComboBox comboBoxPlanoEmprestimo;
	
	//TODO
	//Verificar quais colunas mostradas
	String[] colunas = new String[] { "Código", "Cliente", "Situação",
			"CPF"};


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
		

		modelo = new DefaultTableModel(null, colunas);
		jTable = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTable);
		
		
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
		lblNmeroDeMeses.setBounds(24, 92, 118, 14);
		contentPane.add(lblNmeroDeMeses);
		
		textFieldNumMeses = new JTextField();
		textFieldNumMeses.setBounds(24, 113, 104, 20);
		contentPane.add(textFieldNumMeses);
		textFieldNumMeses.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Plano de Empr\u00E9stimo");
		lblNewLabel.setBounds(152, 92, 104, 14);
		contentPane.add(lblNewLabel);
		
		comboBoxPlanoEmprestimo = new JComboBox();
		comboBoxPlanoEmprestimo.setBounds(152, 113, 104, 20);
		contentPane.add(comboBoxPlanoEmprestimo);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(299, 112, 98, 23);
		contentPane.add(btnCalcular);
		
		JLabel lblResultadoDaSimulao = new JLabel("Resultado da Simula\u00E7\u00E3o");
		lblResultadoDaSimulao.setBounds(21, 157, 119, 14);
		contentPane.add(lblResultadoDaSimulao);
		
		textFieldValorFinal = new JTextField();
		textFieldValorFinal.setBounds(21, 182, 360, 86);
		contentPane.add(textFieldValorFinal);
		textFieldValorFinal.setColumns(10);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(380, 181, 17, 87);
		contentPane.add(scrollBar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(269, 289, 119, 23);
		contentPane.add(btnFechar);
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();
				
			}
		});
		
		btnCalcular.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calcular();
				
			}
		});
		
		JButton btnGerarContrato = new JButton("Gerar Contrato");
		btnGerarContrato.setBounds(24, 289, 117, 23);
		contentPane.add(btnGerarContrato);
		
		btnGerarContrato.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gerarContrato();
				
			}
		});
	}


	protected void gerarContrato() {
		// TODO Auto-generated method stub
		//Pegar os dados da tabela e gerar um contrato conforme as regras. Exemplo de pegar dados da tabela na edicao de cadastros
	}


	protected void calcular() {
		double valorParcela = InterfaceUsuario.calcularValorParcelas(comboBoxPlanoEmprestimo.getSelectedItem().toString(), textFieldNumMeses.getText(), textFieldValorFinanciado.getText());
		double valorFinal = InterfaceUsuario.calcularSimulacao(textFieldNumMeses.getText(), valorParcela);
		String[][] dados = new String[3][2];
		dados[0][0] = "Valor Parcela";
		dados[0][1] = "Valor Final Parcelamento";
		dados[1][0] = "R$ "+String.valueOf(valorParcela);
		dados[1][1] = "R$ "+String.valueOf(valorFinal);
		InterfaceUsuario.carregaDadosTabelaSimulacao(dados);
	}


	protected void fechar() {
		this.dispose();
		
	}


	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		jTable = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTable);
	}
}
