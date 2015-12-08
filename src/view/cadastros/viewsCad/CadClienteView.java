package view.cadastros.viewsCad;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JButton;

import view.InterfaceUsuario;

public class CadClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodCliente;
	private JTextField textFieldNome;
	private JTextField textFieldDataNascimento;
	private JTextField textFieldCPF;
	private JTextField textFieldRG;
	private JLabel lblEndereco;
	private JLabel lblLogradouro;
	private JTextField textFieldLogradouro;
	private JLabel lblNumero;
	private JTextField textFieldNumero;
	private JLabel lblBairro;
	private JTextField textFieldBairro;
	private JLabel lblCidade;
	private JTextField textFieldCidade;
	private JLabel lblUf;
	private JComboBox comboBoxUF;
	private JLabel lblNewLabel;
	private JTextField textFieldComplemento;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblFinanceiro;
	private JLabel lblBanco;
	private JTextField textFieldBanco;
	private JLabel lblAgencia;
	private JTextField textFieldAgencia;
	private JLabel lblContaCorrente;
	private JTextField textFieldContaCorrente;
	private JLabel lblRendaFamiliar;
	private JTextField textFieldRendaFamiliar;
	private JLabel lblRendaPessoal;
	private JTextField textFieldRendaPessoal;
	private JLabel lblObservacoesFinanceiras;
	private JTextField textFieldObsFinanceiras;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JLabel lblR;
	private JLabel label;
	private boolean edicao;

	/**
	 * Create the frame.
	 */
	public CadClienteView() {
		this.edicao = false;
		setTitle("Cadastro Cliente");
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCdigoCliente = new JLabel("C\u00F3digo cliente");
		lblCdigoCliente.setBounds(10, 11, 100, 14);
		contentPane.add(lblCdigoCliente);

		textFieldCodCliente = new JTextField();
		textFieldCodCliente.setBackground(SystemColor.control);
		textFieldCodCliente.setBounds(120, 8, 153, 20);
		contentPane.add(textFieldCodCliente);
		textFieldCodCliente.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 32, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 48, 364, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(387, 32, 110, 14);
		contentPane.add(lblDataDeNascimento);

		textFieldDataNascimento = new JTextField();
		textFieldDataNascimento.setText("dd / mm / aaaa");
		textFieldDataNascimento.setBounds(384, 48, 113, 20);
		contentPane.add(textFieldDataNascimento);
		textFieldDataNascimento.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(14, 79, 46, 14);
		contentPane.add(lblCpf);

		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(14, 94, 218, 20);
		contentPane.add(textFieldCPF);
		textFieldCPF.setColumns(10);

		textFieldCodCliente.setEnabled(false);

		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(261, 79, 46, 14);
		contentPane.add(lblRg);

		textFieldRG = new JTextField();
		textFieldRG.setBounds(261, 94, 113, 20);
		contentPane.add(textFieldRG);
		textFieldRG.setColumns(10);

		lblEndereco = new JLabel("ENDERE\u00C7O");
		lblEndereco.setBounds(10, 132, 64, 14);
		contentPane.add(lblEndereco);

		lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 157, 55, 14);
		contentPane.add(lblLogradouro);

		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setBounds(10, 174, 394, 20);
		contentPane.add(textFieldLogradouro);
		textFieldLogradouro.setColumns(10);

		lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(425, 157, 72, 14);
		contentPane.add(lblNumero);

		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(425, 174, 99, 20);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);

		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 205, 46, 14);
		contentPane.add(lblBairro);

		textFieldBairro = new JTextField();
		textFieldBairro.setBounds(10, 220, 218, 20);
		contentPane.add(textFieldBairro);
		textFieldBairro.setColumns(10);

		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(238, 205, 46, 14);
		contentPane.add(lblCidade);

		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(238, 220, 207, 20);
		contentPane.add(textFieldCidade);
		textFieldCidade.setColumns(10);

		lblUf = new JLabel("UF");
		lblUf.setBounds(451, 205, 46, 14);
		contentPane.add(lblUf);

		comboBoxUF = new JComboBox();
		comboBoxUF.setBounds(455, 220, 69, 20);
		contentPane.add(comboBoxUF);

		lblNewLabel = new JLabel("Complemento");
		lblNewLabel.setBounds(10, 251, 131, 14);
		contentPane.add(lblNewLabel);

		textFieldComplemento = new JTextField();
		textFieldComplemento.setBounds(10, 268, 514, 20);
		contentPane.add(textFieldComplemento);
		textFieldComplemento.setColumns(10);

		separator = new JSeparator();
		separator.setBounds(10, 125, 514, 2);
		contentPane.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(10, 299, 503, 2);
		contentPane.add(separator_1);

		lblFinanceiro = new JLabel("FINANCEIRO");
		lblFinanceiro.setBounds(10, 304, 120, 14);
		contentPane.add(lblFinanceiro);

		lblBanco = new JLabel("Banco");
		lblBanco.setBounds(10, 329, 46, 14);
		contentPane.add(lblBanco);

		textFieldBanco = new JTextField();
		textFieldBanco.setBounds(10, 344, 218, 20);
		contentPane.add(textFieldBanco);
		textFieldBanco.setColumns(10);

		lblAgencia = new JLabel("Ag\u00EAncia");
		lblAgencia.setBounds(238, 329, 46, 14);
		contentPane.add(lblAgencia);

		textFieldAgencia = new JTextField();
		textFieldAgencia.setBounds(238, 344, 123, 20);
		contentPane.add(textFieldAgencia);
		textFieldAgencia.setColumns(10);

		lblContaCorrente = new JLabel("Conta Corrente");
		lblContaCorrente.setBounds(371, 329, 104, 14);
		contentPane.add(lblContaCorrente);

		textFieldContaCorrente = new JTextField();
		textFieldContaCorrente.setBounds(371, 344, 153, 20);
		contentPane.add(textFieldContaCorrente);
		textFieldContaCorrente.setColumns(10);

		lblRendaFamiliar = new JLabel("Renda Familiar");
		lblRendaFamiliar.setBounds(10, 374, 104, 14);
		contentPane.add(lblRendaFamiliar);

		textFieldRendaFamiliar = new JTextField();
		textFieldRendaFamiliar.setBounds(33, 388, 145, 20);
		contentPane.add(textFieldRendaFamiliar);
		textFieldRendaFamiliar.setColumns(10);

		lblRendaPessoal = new JLabel("Renda Pessoal");
		lblRendaPessoal.setBounds(209, 375, 110, 14);
		contentPane.add(lblRendaPessoal);

		textFieldRendaPessoal = new JTextField();
		textFieldRendaPessoal.setBounds(232, 388, 120, 20);
		contentPane.add(textFieldRendaPessoal);
		textFieldRendaPessoal.setColumns(10);

		lblObservacoesFinanceiras = new JLabel(
				"Observa\u00E7\u00F5es Financeiras");
		lblObservacoesFinanceiras.setBounds(10, 419, 153, 14);
		contentPane.add(lblObservacoesFinanceiras);

		textFieldObsFinanceiras = new JTextField();
		textFieldObsFinanceiras.setBounds(160, 416, 364, 41);
		contentPane.add(textFieldObsFinanceiras);
		textFieldObsFinanceiras.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(435, 477, 89, 23);
		contentPane.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(330, 477, 89, 23);
		contentPane.add(btnCancelar);

		lblR = new JLabel("R$");
		lblR.setBounds(10, 391, 23, 14);
		contentPane.add(lblR);

		label = new JLabel("R$");
		label.setBounds(209, 391, 23, 14);
		contentPane.add(label);

		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar();

			}
		});
		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});
	}

	public CadClienteView(int codigo, int cpf, String nomeCompleto, int rg,
			java.util.Date dataNascimento, String logradouro, int numero,
			String bairro, String cep, String nomeCidade, String uf,
			String banco, String agencia, int contaCorrente,
			double rendaFamiliar, double rendaPessoal, String observacao) {
		textFieldCodCliente.setText(""+codigo); 
		textFieldCPF.setText(""+cpf); 
		textFieldNome.setText(nomeCompleto); 
		textFieldRG.setText(""+rg);
		textFieldDataNascimento.setText(""+dataNascimento); 
		textFieldLogradouro.setText(logradouro); 
		textFieldNumero.setText(""+numero);
		textFieldBairro.setText(bairro); 
		//cep, 
		textFieldCidade.setText(nomeCidade); 
		comboBoxUF.setSelectedItem(uf);
		textFieldBanco.setText(banco); 
		textFieldAgencia.setText(agencia); 
		textFieldContaCorrente.setText(""+contaCorrente);
		textFieldRendaFamiliar.setText(""+rendaFamiliar); 
		textFieldRendaPessoal.setText(""+rendaPessoal);
		textFieldObsFinanceiras.setText(observacao);
		edicao = true;
	}

	protected void salvar() {
		DateFormat formatter = new SimpleDateFormat("dd/mm/aaaa");
		Date dateNasc = null;
		if (textFieldDataNascimento.getText().trim().length() > 0)
			try {
				dateNasc = (Date) formatter.parse(textFieldDataNascimento
						.getText());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if (!edicao) {
			InterfaceUsuario.cadastrarCliente(InterfaceUsusario.transformaStringInt(textFieldCPF
					.getText()), textFieldNome.getText(),InterfaceUsusario.transformaStringInt(textFieldRG.getText()), dateNasc,
					textFieldLogradouro.getText(), InterfaceUsusario.transformaStringInt(textFieldNumero.getText()),
					textFieldBairro.getText(), "cep",
					textFieldCidade.getText(), comboBoxUF.getSelectedItem()
							.toString(), textFieldBanco.getText(),
					textFieldAgencia.getText(),InterfaceUsusario.transformaStringInt(textFieldContaCorrente.getText()), transformaStringDouble(textFieldRendaFamiliar.getText()),
					transformaStringDouble(textFieldRendaPessoal.getText()),
					textFieldObsFinanceiras.getText());
		} else {
			InterfaceUsuario.editarCliente(InterfaceUsusario.transformaStringInt(textFieldCodCliente
					.getText()), InterfaceUsusario.transformaStringInt(textFieldCPF.getText()),
					textFieldNome.getText(), InterfaceUsusario.transformaStringInt(textFieldRG
							.getText()), dateNasc, textFieldLogradouro
							.getText(), InterfaceUsusario.transformaStringInt(textFieldNumero
							.getText()), textFieldBairro.getText(), "cep",
					textFieldCidade.getText(), comboBoxUF.getSelectedItem()
							.toString(), textFieldBanco.getText(),
					textFieldAgencia.getText(), InterfaceUsusario.transformaStringInt(textFieldContaCorrente.getText()), transformaStringDouble(textFieldRendaFamiliar.getText()),
					transformaStringDouble(textFieldRendaPessoal.getText()),
					textFieldObsFinanceiras.getText());
		}

	}

	protected void cancelar() {
		this.dispose();
	}
	
	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}


}
