package view.cadastros.viewsCad;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import view.InterfaceUsuario;
import view.combo.ClienteCombo;
import view.combo.ParcelasCombo;
import view.combo.PlanoCombo;
import view.combo.SituacaoCombo;

public class CadContratoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodContrato;
	private JTextField textFieldBanco;
	private JTextField textFieldAgencia;
	private JTextField textFieldContaCorrente;
	private JTextField textFieldValorEmprestimo;
	private JTextField textFieldValorParcelas;
	private JFormattedTextField formattedFieldDataNascimento;
	private JTextField textFieldObservacoes;
	private JComboBox comboBoxPlanoEmprestimo;
	private JComboBox comboBoxParcelas;
	private JComboBox comboBoxSituacao;
	private String formatString = "##/##/####";
	private JComboBox comboBoxCliente;
	private boolean edicao;

	// Construtor usado a partir do menu
	public CadContratoView() {
		edicao = false;
		initComponents();
	}

	// Construtor usado a partir da simula��o
	public CadContratoView(double valorEmprestimo, int valorParcelas,
			int indexPlanoEmprestimo, int indexNumeroParcelas) {
		initComponents();
		edicao = true;
		textFieldValorEmprestimo.setText(String.valueOf(valorEmprestimo));
		textFieldValorParcelas.setText(String.valueOf(valorParcelas));
		comboBoxPlanoEmprestimo.setSelectedIndex(indexPlanoEmprestimo);
		comboBoxParcelas.setSelectedIndex(indexNumeroParcelas);
	}

	// Construtor usado a partir de um contrato existente
	public CadContratoView(int codContrato, String nomeCliente, String banco,
			String agencia, int contaCorrente, double valorEmprestimo,
			double valorParcelas, Date dataTermino, String observacao,
			String nomePlano, String nomeParcela, String status,
			int codCliente, int codPlano, int codParcela) {
		initComponents();
		textFieldCodContrato.setText(String.valueOf(codContrato));
		comboBoxCliente.setSelectedItem(new ClienteCombo(codCliente,
				nomeCliente));
		textFieldBanco.setText(banco);
		textFieldAgencia.setText(agencia);
		textFieldContaCorrente.setText(String.valueOf(contaCorrente));
		textFieldValorEmprestimo.setText(String.valueOf(valorEmprestimo));
		textFieldValorParcelas.setText(String.valueOf(valorParcelas));
		formattedFieldDataNascimento.setText(dataTermino.toString());
		textFieldObservacoes.setText(observacao);
		comboBoxPlanoEmprestimo.setSelectedItem(new PlanoCombo(codPlano,
				nomePlano));
		comboBoxParcelas.setSelectedItem(new ParcelasCombo(codParcela,
				nomeParcela));
		comboBoxSituacao.setSelectedItem(new SituacaoCombo(status, status));
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setTitle("Contrato");
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCdigoContrato = new JLabel("C�digo Contrato");
		lblCdigoContrato.setBounds(10, 11, 92, 14);
		contentPane.add(lblCdigoContrato);

		textFieldCodContrato = new JTextField();
		textFieldCodContrato.setBackground(SystemColor.control);
		textFieldCodContrato.setBounds(117, 8, 250, 20);
		contentPane.add(textFieldCodContrato);
		textFieldCodContrato.setColumns(10);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 46, 46, 14);
		contentPane.add(lblCliente);

		JLabel lblSituao = new JLabel("Situa��o");
		lblSituao.setBounds(357, 46, 59, 14);
		contentPane.add(lblSituao);

		comboBoxSituacao = new JComboBox();
		comboBoxSituacao.setBounds(357, 61, 167, 20);
		contentPane.add(comboBoxSituacao);

		JLabel lblBanco = new JLabel("Banco");
		lblBanco.setBounds(10, 92, 46, 14);
		contentPane.add(lblBanco);

		textFieldBanco = new JTextField();
		textFieldBanco.setBounds(10, 107, 210, 20);
		contentPane.add(textFieldBanco);
		textFieldBanco.setColumns(10);

		JLabel lblAgncia = new JLabel("Ag�ncia");
		lblAgncia.setBounds(242, 92, 46, 14);
		contentPane.add(lblAgncia);

		textFieldAgencia = new JTextField();
		textFieldAgencia.setBounds(242, 107, 105, 20);
		contentPane.add(textFieldAgencia);
		textFieldAgencia.setColumns(10);

		JLabel lblContacorrente = new JLabel("Conta-corrente");
		lblContacorrente.setBounds(357, 92, 114, 14);
		contentPane.add(lblContacorrente);

		textFieldContaCorrente = new JTextField();
		textFieldContaCorrente.setBounds(357, 107, 167, 20);
		contentPane.add(textFieldContaCorrente);
		textFieldContaCorrente.setColumns(10);

		JLabel lblPlanoDeEmprestimo = new JLabel("Plano de Empr�stimo");
		lblPlanoDeEmprestimo.setBounds(10, 190, 130, 14);
		contentPane.add(lblPlanoDeEmprestimo);

		comboBoxPlanoEmprestimo = new JComboBox();
		comboBoxPlanoEmprestimo.setBounds(10, 207, 223, 20);
		contentPane.add(comboBoxPlanoEmprestimo);

		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(260, 190, 87, 14);
		contentPane.add(lblParcelas);

		comboBoxParcelas = new JComboBox();
		comboBoxParcelas.setBounds(260, 207, 86, 20);
		contentPane.add(comboBoxParcelas);

		JLabel lblValorDoEmprestimo = new JLabel("Valor do Empr�stimo");
		lblValorDoEmprestimo.setBounds(10, 253, 130, 14);
		contentPane.add(lblValorDoEmprestimo);

		textFieldValorEmprestimo = new JTextField();
		textFieldValorEmprestimo.setBounds(30, 269, 203, 20);
		contentPane.add(textFieldValorEmprestimo);
		textFieldValorEmprestimo.setColumns(10);

		JLabel lblValorDasParcelas = new JLabel("Valor das Parcelas");
		lblValorDasParcelas.setBounds(260, 253, 116, 14);
		contentPane.add(lblValorDasParcelas);

		textFieldValorParcelas = new JTextField();
		textFieldValorParcelas.setBounds(281, 269, 86, 20);
		contentPane.add(textFieldValorParcelas);
		textFieldValorParcelas.setColumns(10);

		JLabel lblDataDeTermino = new JLabel("Data de t�rmino do contrato");
		lblDataDeTermino.setBounds(386, 253, 167, 14);
		contentPane.add(lblDataDeTermino);

		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataNascimento = new JFormattedTextField(maskData);
		formattedFieldDataNascimento.setBounds(386, 269, 138, 20);
		contentPane.add(formattedFieldDataNascimento);
		formattedFieldDataNascimento.setColumns(10);

		JLabel lblObservaes = new JLabel("Observa��es");
		lblObservaes.setBounds(10, 332, 101, 14);
		contentPane.add(lblObservaes);

		textFieldObservacoes = new JTextField();
		textFieldObservacoes.setBounds(10, 348, 543, 84);
		contentPane.add(textFieldObservacoes);
		textFieldObservacoes.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 158, 543, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 319, 543, 2);
		contentPane.add(separator_1);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(435, 466, 89, 23);
		contentPane.add(btnSalvar);

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(327, 466, 89, 23);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

		JLabel lblR = new JLabel("R$");
		lblR.setBounds(10, 272, 24, 14);
		contentPane.add(lblR);

		JLabel label = new JLabel("R$");
		label.setBounds(260, 272, 24, 14);
		contentPane.add(label);

		comboBoxCliente = new JComboBox();
		comboBoxCliente.setBounds(10, 61, 337, 20);
		contentPane.add(comboBoxCliente);
	}

	protected void salvar() {
		/*
		 * if (textFieldCodContrato.getText().trim().length() == 0){
		 * //InterfaceUsuario.cadastrarContrato(
		 * 
		 * ); }else{ //InterfaceUsuario.editarContrato(); }
		 */

	}

	protected void fechar() {
		this.dispose();
	}

	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void povoarDadosCliente(String banco, String agencia,
			int contaCorrente) {
		textFieldBanco.setText(banco);
		;
		textFieldAgencia.setText(agencia);
		textFieldContaCorrente.setText(String.valueOf(contaCorrente));
		;
	}

	private void carregarSituacaoCombo() {
		comboBoxSituacao.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaSituacaoCombo())));
	}

	private void carregarParcelasCombo() {
		comboBoxParcelas.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaParcelasCombo())));
	}

	private void carregarClienteCombo() {
		comboBoxCliente.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaClienteCombo())));
	}

	private void carregarPlanoCombo() {
		comboBoxPlanoEmprestimo.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaPlanoCombo())));
	}
}
