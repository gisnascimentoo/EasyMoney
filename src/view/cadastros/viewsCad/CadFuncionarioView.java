package view.cadastros.viewsCad;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;

import view.InterfaceUsuario;
import view.combo.EstadoCombo;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class CadFuncionarioView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodFuncionario;
	private JLabel lblNome;
	private JTextField textFieldNome;
	private JLabel lblDataNascimento;
	private JFormattedTextField formattedFieldDataNascimento;
	private JLabel lblCpf;
	private JTextField textFieldCPF;
	private JLabel lblRg;
	private JTextField textFieldRG;
	private JTextField textFieldLogradouro;
	private JTextField textFieldNumero;
	private JTextField textFieldBairro;
	private JTextField textFieldCidade;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefone;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private boolean edicao;
	private JComboBox comboBoxCargo;
	private JComboBox comboBoxUF;
	private String formatString = "##/##/####";
	private JLabel lblComplemento;
	private JTextField textFieldComplemento;

	/**
	 * Create the frame.
	 */
	public CadFuncionarioView() {
		init();
		edicao = false;
	}
	
	public void init(){
		setTitle("Cadastro Funcionario");
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelCodFuncionario = new JLabel("Codigo Funcionario");
		labelCodFuncionario.setBounds(10, 12, 136, 19);
		contentPane.add(labelCodFuncionario);

		textFieldCodFuncionario = new JTextField();
		textFieldCodFuncionario.setBackground(SystemColor.control);
		textFieldCodFuncionario.setSelectedTextColor(Color.LIGHT_GRAY);
		textFieldCodFuncionario.setBounds(108, 12, 110, 19);
		contentPane.add(textFieldCodFuncionario);
		textFieldCodFuncionario.setColumns(10);
		textFieldCodFuncionario.setEditable(false);

		lblNome = new JLabel("* Nome");
		lblNome.setBounds(10, 41, 95, 19);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 61, 390, 19);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		lblDataNascimento = new JLabel("* Data Nascimento");
		lblDataNascimento.setBounds(400, 43, 95, 14);
		contentPane.add(lblDataNascimento);

		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataNascimento = new JFormattedTextField(maskData);
		formattedFieldDataNascimento.setBounds(410, 61, 100, 19);
		contentPane.add(formattedFieldDataNascimento);
		formattedFieldDataNascimento.setColumns(10);

		lblCpf = new JLabel("* CPF");
		lblCpf.setBounds(10, 91, 46, 14);
		contentPane.add(lblCpf);

		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(10, 108, 210, 19);
		contentPane.add(textFieldCPF);
		textFieldCPF.setColumns(10);

		lblRg = new JLabel("* RG");
		lblRg.setBounds(245, 91, 46, 14);
		contentPane.add(lblRg);

		textFieldRG = new JTextField();
		textFieldRG.setBounds(230, 108, 170, 19);
		contentPane.add(textFieldRG);
		textFieldRG.setColumns(10);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 141, 46, 14);
		contentPane.add(lblCargo);

		comboBoxCargo = new JComboBox(new String[] { "Gerente", "Corretor"});
		comboBoxCargo.setBounds(10, 159, 210, 19);
		contentPane.add(comboBoxCargo);

		JLabel lblEndereo = new JLabel("ENDERE\u00C7O");
		lblEndereo.setBounds(10, 200, 168, 14);
		contentPane.add(lblEndereo);

		JLabel lblLogradouro = new JLabel("* Logradouro");
		lblLogradouro.setBounds(10, 225, 136, 14);
		contentPane.add(lblLogradouro);

		textFieldLogradouro = new JTextField();
		textFieldLogradouro.setBounds(10, 242, 390, 20);
		contentPane.add(textFieldLogradouro);
		textFieldLogradouro.setColumns(10);

		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setBounds(415, 225, 95, 14);
		contentPane.add(lblNumero);

		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(410, 242, 100, 20);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(8, 273, 46, 14);
		contentPane.add(lblBairro);

		textFieldBairro = new JTextField();
		textFieldBairro.setBounds(8, 292, 212, 19);
		contentPane.add(textFieldBairro);
		textFieldBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(232, 273, 46, 14);
		contentPane.add(lblCidade);

		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(230, 292, 170, 19);
		contentPane.add(textFieldCidade);
		textFieldCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(421, 273, 46, 14);
		contentPane.add(lblUf);

		comboBoxUF = new JComboBox();
		comboBoxUF.setBounds(410, 293, 100, 19);
		contentPane.add(comboBoxUF);
		
		carregarEstadoCombo();
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 322, 208, 14);
		contentPane.add(lblComplemento);
		
		textFieldComplemento = new JTextField();
		textFieldComplemento.setBounds(10, 347, 208, 20);
		contentPane.add(textFieldComplemento);
		textFieldComplemento.setColumns(10);

		JLabel lblContato = new JLabel("CONTATO");
		lblContato.setBounds(10, 397, 110, 14);
		contentPane.add(lblContato);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(144, 422, 46, 14);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(144, 447, 366, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 422, 95, 14);
		contentPane.add(lblTelefone);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(10, 447, 124, 19);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 384, 500, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 187, 500, 2);
		contentPane.add(separator_1);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(421, 477, 89, 23);
		contentPane.add(btnSalvar);
		
		btnSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				salvar();

			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(318, 477, 89, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});
	}

	public CadFuncionarioView(int codigo, String nome,
			java.util.Date dataNascimento, int CPF, int RG, String cargo,
			String email, int telefone, String logradouro, String complemento,int numero,
			String bairro, String nomeCidade, String uf, int idUf) {
		init();
		Date dateToFormat = new Date(dataNascimento.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formatted = formatter.format(dateToFormat);
		textFieldCodFuncionario.setText(""+codigo);
		textFieldNome.setText(nome);
		formattedFieldDataNascimento.setText(""+formatted);
		textFieldCPF.setText(""+CPF);
		textFieldRG.setText(""+RG);
		comboBoxCargo.setSelectedItem(cargo);
		textFieldEmail.setText(email);
		textFieldTelefone.setText(""+telefone);
		textFieldLogradouro.setText(logradouro);
		textFieldComplemento.setText(complemento);
		textFieldNumero.setText(""+numero);
		textFieldBairro.setText(bairro);
		textFieldCidade.setText(nomeCidade);
		setSelectedValue(comboBoxUF, idUf);
		edicao = true;
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

	protected void fechar() {
		this.dispose();

	}

	protected void salvar() {
		EstadoCombo estadoCombo = (EstadoCombo)comboBoxUF.getSelectedItem();
		int idEstado = -1;
		if(estadoCombo != null)
			idEstado = estadoCombo.getCodigo();
		if (edicao) {
			InterfaceUsuario.editarFuncionario(InterfaceUsuario.transformaStringInt(textFieldCodFuncionario.getText()), textFieldNome.getText(),
					formattedFieldDataNascimento.getText(), InterfaceUsuario.transformaStringInt(textFieldCPF.getText()), InterfaceUsuario.transformaStringInt(textFieldRG.getText()),
					comboBoxCargo.getSelectedItem().toString(), textFieldEmail.getText(),
					InterfaceUsuario.transformaStringInt(textFieldTelefone.getText()), 
					textFieldLogradouro.getText(), textFieldComplemento.getText(), InterfaceUsuario.transformaStringInt(textFieldNumero.getText()),
					textFieldBairro.getText(), textFieldCidade.getText(), idEstado);
		} else {
			InterfaceUsuario.cadastrarFuncionario(textFieldNome.getText(),
					formattedFieldDataNascimento.getText(), InterfaceUsuario.transformaStringInt(textFieldCPF.getText()), InterfaceUsuario.transformaStringInt(textFieldRG.getText()),
					comboBoxCargo.getSelectedItem().toString(), textFieldEmail.getText(),
					InterfaceUsuario.transformaStringInt(textFieldTelefone.getText()), 
					textFieldLogradouro.getText(), textFieldComplemento.getText(),InterfaceUsuario.transformaStringInt(textFieldNumero.getText()),
					textFieldBairro.getText(), textFieldCidade.getText(), idEstado);
		}
	}
	
	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
	private void carregarEstadoCombo() {
		comboBoxUF.setModel(new DefaultComboBoxModel(new Vector(
				InterfaceUsuario.carregaEstadoCombo())));
	}

}
