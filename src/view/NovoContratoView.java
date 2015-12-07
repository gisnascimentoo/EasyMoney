package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class NovoContratoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodContrato;
	private JTextField textFieldCliente;
	private JTextField textFieldBanco;
	private JTextField textFieldAgencia;
	private JTextField textFieldContaCorrente;
	private JTextField textFieldValorEmprestimo;
	private JTextField textFieldValorParcelas;
	private JTextField textFieldDataTerminoContrato;
	private JTextField textFieldObservacoes;

	

	/**
	 * Create the frame.
	 */
	public NovoContratoView() {
		setTitle("Novo Contrato");
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigoContrato = new JLabel("C\u00F3digo Contrato");
		lblCdigoContrato.setBounds(10, 11, 92, 14);
		contentPane.add(lblCdigoContrato);
		
		textFieldCodContrato = new JTextField();
		textFieldCodContrato.setBackground(SystemColor.control);
		textFieldCodContrato.setBounds(97, 8, 101, 20);
		contentPane.add(textFieldCodContrato);
		textFieldCodContrato.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 46, 46, 14);
		contentPane.add(lblCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(10, 61, 322, 20);
		contentPane.add(textFieldCliente);
		textFieldCliente.setColumns(10);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(357, 46, 46, 14);
		contentPane.add(lblSituao);
		
		JComboBox comboBoxSituacao = new JComboBox();
		comboBoxSituacao.setBounds(357, 61, 167, 20);
		contentPane.add(comboBoxSituacao);
		
		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(10, 92, 46, 14);
		contentPane.add(lblBanco);
		
		textFieldBanco = new JTextField();
		textFieldBanco.setBounds(10, 107, 210, 20);
		contentPane.add(textFieldBanco);
		textFieldBanco.setColumns(10);
		
		JLabel lblAgncia = new JLabel("Ag\u00EAncia");
		lblAgncia.setBounds(242, 92, 46, 14);
		contentPane.add(lblAgncia);
		
		textFieldAgencia = new JTextField();
		textFieldAgencia.setBounds(242, 107, 125, 20);
		contentPane.add(textFieldAgencia);
		textFieldAgencia.setColumns(10);
		
		JLabel lblContacorrente = new JLabel("Conta-corrente");
		lblContacorrente.setBounds(386, 92, 114, 14);
		contentPane.add(lblContacorrente);
		
		textFieldContaCorrente = new JTextField();
		textFieldContaCorrente.setBounds(386, 107, 138, 20);
		contentPane.add(textFieldContaCorrente);
		textFieldContaCorrente.setColumns(10);
		
		JLabel lblPlanoDeEmprestimo = new JLabel("Plano de Empr\u00E9stimo");
		lblPlanoDeEmprestimo.setBounds(10, 190, 130, 14);
		contentPane.add(lblPlanoDeEmprestimo);
		
		JComboBox comboBoxPlanoEmprestimo = new JComboBox();
		comboBoxPlanoEmprestimo.setBounds(10, 207, 223, 20);
		contentPane.add(comboBoxPlanoEmprestimo);
		
		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(260, 190, 46, 14);
		contentPane.add(lblParcelas);
		
		JComboBox comboBoxParcelas = new JComboBox();
		comboBoxParcelas.setBounds(260, 207, 86, 20);
		contentPane.add(comboBoxParcelas);
		
		JLabel lblValorDoEmprestimo = new JLabel("Valor do Empr\u00E9stimo");
		lblValorDoEmprestimo.setBounds(10, 253, 130, 14);
		contentPane.add(lblValorDoEmprestimo);
		
		textFieldValorEmprestimo = new JTextField();
		textFieldValorEmprestimo.setBounds(30, 269, 203, 20);
		contentPane.add(textFieldValorEmprestimo);
		textFieldValorEmprestimo.setColumns(10);
		
		JLabel lblValorDasParcelas = new JLabel("Valor das Parcelas");
		lblValorDasParcelas.setBounds(260, 253, 107, 14);
		contentPane.add(lblValorDasParcelas);
		
		textFieldValorParcelas = new JTextField();
		textFieldValorParcelas.setBounds(281, 269, 86, 20);
		contentPane.add(textFieldValorParcelas);
		textFieldValorParcelas.setColumns(10);
		
		JLabel lblDataDeTermino = new JLabel("Data de t\u00E9rmino do contrato");
		lblDataDeTermino.setBounds(386, 253, 138, 14);
		contentPane.add(lblDataDeTermino);
		
		textFieldDataTerminoContrato = new JTextField();
		textFieldDataTerminoContrato.setBounds(386, 269, 107, 20);
		contentPane.add(textFieldDataTerminoContrato);
		textFieldDataTerminoContrato.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(10, 332, 101, 14);
		contentPane.add(lblObservaes);
		
		textFieldObservacoes = new JTextField();
		textFieldObservacoes.setBounds(10, 348, 514, 84);
		contentPane.add(textFieldObservacoes);
		textFieldObservacoes.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 158, 514, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 319, 514, 2);
		contentPane.add(separator_1);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(435, 466, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(327, 466, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(10, 272, 24, 14);
		contentPane.add(lblR);
		
		JLabel label = new JLabel("R$");
		label.setBounds(260, 272, 24, 14);
		contentPane.add(label);
	}

}
