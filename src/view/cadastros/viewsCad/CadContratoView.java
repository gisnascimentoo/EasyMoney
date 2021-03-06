package view.cadastros.viewsCad;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import view.combo.EstadoCombo;
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
	private JFormattedTextField formattedFieldDataTermino;
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
			String nomePlano, int qntdParcelas, String status, int codCliente,
			int codPlano) {
		initComponents();
		textFieldCodContrato.setText(String.valueOf(codContrato));
		comboBoxCliente.setSelectedItem(new ClienteCombo(codCliente,
				nomeCliente));
		textFieldBanco.setText(banco);
		textFieldAgencia.setText(agencia);
		textFieldContaCorrente.setText(String.valueOf(contaCorrente));
		textFieldValorEmprestimo.setText(String.valueOf(valorEmprestimo));
		textFieldValorParcelas.setText(String.valueOf(valorParcelas));
		/**
		 * Conversao de sql.Date para maskformatter
		 */
		Date dateToFormat = new Date(dataTermino.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formatted = formatter.format(dateToFormat);
		
		formattedFieldDataTermino.setText(formatted);
		textFieldObservacoes.setText(observacao);
		comboBoxPlanoEmprestimo.setSelectedItem(new PlanoCombo(codPlano,
				nomePlano));
		planoAlterado();
		comboBoxParcelas.setSelectedItem(new ParcelasCombo(qntdParcelas,
				qntdParcelas));
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

		JLabel lblCdigoContrato = new JLabel("Codigo Contrato");
		lblCdigoContrato.setBounds(10, 11, 101, 14);
		contentPane.add(lblCdigoContrato);

		textFieldCodContrato = new JTextField();
		textFieldCodContrato.setBackground(SystemColor.control);
		textFieldCodContrato.setBounds(112, 8, 255, 20);
		contentPane.add(textFieldCodContrato);
		textFieldCodContrato.setColumns(10);
		textFieldCodContrato.setEnabled(false);

		JLabel lblCliente = new JLabel("* Cliente");
		lblCliente.setBounds(10, 46, 46, 14);
		contentPane.add(lblCliente);

		JLabel lblSituao = new JLabel("* Situacao");
		lblSituao.setBounds(386, 46, 59, 14);
		contentPane.add(lblSituao);

		comboBoxSituacao = new JComboBox();
		comboBoxSituacao.setBounds(386, 61, 138, 20);
		contentPane.add(comboBoxSituacao);

		JLabel lblBanco = new JLabel("* Banco");
		lblBanco.setBounds(10, 92, 46, 14);
		contentPane.add(lblBanco);

		textFieldBanco = new JTextField();
		textFieldBanco.setBounds(10, 107, 223, 20);
		contentPane.add(textFieldBanco);
		textFieldBanco.setColumns(10);
		textFieldBanco.setEnabled(false);

		JLabel lblAgncia = new JLabel("* Agencia");
		lblAgncia.setBounds(242, 92, 92, 14);
		contentPane.add(lblAgncia);

		textFieldAgencia = new JTextField();
		textFieldAgencia.setBounds(242, 107, 125, 20);
		contentPane.add(textFieldAgencia);
		textFieldAgencia.setColumns(10);
		textFieldAgencia.setEnabled(false);
		
		JLabel lblContacorrente = new JLabel("* Conta-corrente");
		lblContacorrente.setBounds(386, 92, 138, 14);
		contentPane.add(lblContacorrente);

		textFieldContaCorrente = new JTextField();
		textFieldContaCorrente.setBounds(386, 107, 138, 20);
		contentPane.add(textFieldContaCorrente);
		textFieldContaCorrente.setColumns(10);
		textFieldContaCorrente.setEnabled(false);

		JLabel lblPlanoDeEmprestimo = new JLabel("* Plano de Emprestimo");
		lblPlanoDeEmprestimo.setBounds(10, 190, 188, 14);
		contentPane.add(lblPlanoDeEmprestimo);

		comboBoxPlanoEmprestimo = new JComboBox();
		comboBoxPlanoEmprestimo.setBounds(10, 207, 223, 20);
		contentPane.add(comboBoxPlanoEmprestimo);

		JLabel lblParcelas = new JLabel("* Parcelas");
		lblParcelas.setBounds(260, 190, 87, 14);
		contentPane.add(lblParcelas);

		comboBoxParcelas = new JComboBox();
		comboBoxParcelas.setBounds(242, 207, 125, 20);
		contentPane.add(comboBoxParcelas);

		JLabel lblValorDoEmprestimo = new JLabel("* Valor do Emprestimo");
		lblValorDoEmprestimo.setBounds(10, 253, 188, 14);
		contentPane.add(lblValorDoEmprestimo);

		textFieldValorEmprestimo = new JTextField();
		textFieldValorEmprestimo.setBounds(30, 269, 203, 20);
		contentPane.add(textFieldValorEmprestimo);
		textFieldValorEmprestimo.setColumns(10);

		JLabel lblValorDasParcelas = new JLabel("* Valor das Parcelas");
		lblValorDasParcelas.setBounds(260, 253, 116, 14);
		contentPane.add(lblValorDasParcelas);

		textFieldValorParcelas = new JTextField();
		textFieldValorParcelas.setBounds(260, 269, 107, 20);
		contentPane.add(textFieldValorParcelas);
		textFieldValorParcelas.setColumns(10);

		JLabel lblDataDeTermino = new JLabel("* Data de termino do contrato");
		lblDataDeTermino.setBounds(386, 253, 167, 14);
		contentPane.add(lblDataDeTermino);

		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataTermino = new JFormattedTextField(maskData);
		formattedFieldDataTermino.setBounds(386, 269, 138, 20);
		contentPane.add(formattedFieldDataTermino);
		formattedFieldDataTermino.setColumns(10);

		JLabel lblObservaes = new JLabel("Observacoes");
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
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(327, 466, 89, 23);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});

		JLabel lblR = new JLabel("R$");
		lblR.setBounds(10, 272, 24, 14);
		contentPane.add(lblR);

		JLabel label = new JLabel("R$");
		label.setBounds(242, 272, 24, 14);
		contentPane.add(label);

		comboBoxCliente = new JComboBox();
		comboBoxCliente.setBounds(10, 61, 357, 20);
		contentPane.add(comboBoxCliente);
		
		comboBoxCliente.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				clienteAlterado();
			}
			
		});
		
		
		comboBoxPlanoEmprestimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planoAlterado();
			}
		});

		carregarClienteCombo();
		carregarPlanoCombo();
		carregarSituacaoCombo();
	}

	protected void clienteAlterado() {
		ClienteCombo clienteCombo = (ClienteCombo) comboBoxCliente.getSelectedItem();
		int codigo = clienteCombo.getCodigo();
		carregarDadosBancarios(codigo);
	}

	private void carregarDadosBancarios(int codigo) {
		InterfaceUsuario.getDadosBancariosPorCodigoCliente(codigo);
	}

	protected void planoAlterado() {
		PlanoCombo planoCombo = (PlanoCombo)comboBoxPlanoEmprestimo.getSelectedItem();
		carregarParcelasCombo(InterfaceUsuario.getQntdParcelasParaPlano(planoCombo.getCodigo()), InterfaceUsuario.getMinParcelasParaPlano(planoCombo.getCodigo()));
		
	}

	protected void salvar() {
		ClienteCombo cliente = (ClienteCombo)comboBoxCliente.getSelectedItem();
		PlanoCombo planoCombo = (PlanoCombo)comboBoxPlanoEmprestimo.getSelectedItem();
		SituacaoCombo situacaoCombo = (SituacaoCombo)comboBoxSituacao.getSelectedItem();
		ParcelasCombo parcelaCombo = (ParcelasCombo)comboBoxParcelas.getSelectedItem();
		int idCliente = -1, idPlano = -1, qntdParcela = -1;
		String situacao = null;
		if(cliente != null)
			idCliente = cliente.getCodigo();
		if(planoCombo != null)
			idPlano = planoCombo.getCodigo();
		if(situacaoCombo != null)
			situacao = situacaoCombo.getNome();
		if(parcelaCombo != null)
			qntdParcela = parcelaCombo.getParcela();
			
		double valorEmprestimo = InterfaceUsuario.transformaStringDouble(textFieldValorEmprestimo.getText());
		double valorParcelas = InterfaceUsuario.transformaStringDouble(textFieldValorParcelas.getText());
		if(edicao){
			int codContrato = InterfaceUsuario.transformaStringInt(textFieldCodContrato.getText());
			InterfaceUsuario.editarContrato(codContrato, idCliente, situacao, idPlano, qntdParcela, valorEmprestimo, valorParcelas, formattedFieldDataTermino.getText(), textFieldObservacoes.getText());
		}else{
			InterfaceUsuario.cadastrarContrato(idCliente, situacao, idPlano, qntdParcela, valorEmprestimo, valorParcelas, formattedFieldDataTermino.getText(), textFieldObservacoes.getText());
		}
	}

	protected void fechar() {
		this.dispose();
	}

	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void povoarDadosCliente(String banco, String agencia, int contaCorrente) {
		textFieldBanco.setText(banco);
		textFieldAgencia.setText(agencia);
		textFieldContaCorrente.setText(String.valueOf(contaCorrente));
	}
	
	public static void setSelectedValue(JComboBox comboBox, int value)
    {
        EstadoCombo item;
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
            item = (EstadoCombo)comboBox.getItemAt(i);
            if (item.getCodigo() == value)
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

	private void carregarSituacaoCombo() {
		comboBoxSituacao.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaSituacaoCombo())));
	}

	private void carregarParcelasCombo(int qndMax, int qndMin) {
		List<ParcelasCombo> listParcela = new ArrayList<ParcelasCombo>();
		for (int i = qndMin; i <= qndMax; i++) {
			listParcela.add(new ParcelasCombo(i, i));
		}
		comboBoxParcelas.setModel(new DefaultComboBoxModel(new Vector(
				listParcela)));
	}

	private void carregarClienteCombo() {
		comboBoxCliente.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaClienteCombo())));
	}

	private void carregarPlanoCombo() {
		comboBoxPlanoEmprestimo.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaPlanoCombo())));
	}

	public void habilitarEdicao() {
		edicao = true;
	}
}
