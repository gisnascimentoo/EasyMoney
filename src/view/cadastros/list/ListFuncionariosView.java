package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import view.InterfaceUsuario;

public class ListFuncionariosView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JLabel lblNome;
	private JTextField textFieldNome;
	private JLabel lblDataDeNascimento;
	private JFormattedTextField formattedFieldDataNascimento;
	private String formatString = "##/##/####";
	private JLabel lblCpf;
	private JTextField textFieldCPF;
	JScrollPane rolagem;
	JTable tabela;
	DefaultTableModel modelo;
	String[] colunas = new String[] { "Código", "Nome", "Data de Nascimento", "CPF" };
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Create the frame.
	 */
	public ListFuncionariosView() {
		setTitle("Buscar Funcion�rios");
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCodigo = new JLabel("C�digo");
		lblCodigo.setBounds(25, 22, 76, 14);
		contentPane.add(lblCodigo);

		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(25, 38, 93, 20);
		contentPane.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(159, 22, 134, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(159, 38, 388, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(25, 69, 119, 14);
		contentPane.add(lblDataDeNascimento);

		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataNascimento = new JFormattedTextField(maskData);
		formattedFieldDataNascimento.setBounds(25, 85, 101, 20);
		contentPane.add(formattedFieldDataNascimento);
		formattedFieldDataNascimento.setColumns(10);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(159, 69, 76, 14);
		contentPane.add(lblCpf);

		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(159, 85, 236, 20);
		contentPane.add(textFieldCPF);
		textFieldCPF.setColumns(10);

		modelo = new DefaultTableModel(null, colunas);
		tabela = new JTable(modelo);
		rolagem = new JScrollPane(tabela);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(428, 85, 119, 21);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				buscar();

			}
		});

		JButton btnAdicionarFuncionario = new JButton("Adicionar Funcion�rio");
		btnAdicionarFuncionario.setBounds(25, 122, 164, 23);
		contentPane.add(btnAdicionarFuncionario);
		btnAdicionarFuncionario.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				adicionar();

			}
		});

		rolagem.setBounds(25, 168, 520, 157);
		contentPane.add(rolagem);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(446, 343, 89, 20);
		contentPane.add(btnFechar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(347, 340, 89, 23);
		contentPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(242, 340, 89, 23);
		contentPane.add(btnExcluir);
		btnFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});
		
		btnEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editar();

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				excluir();

			}
		});

	}

	protected void excluir() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.excluirFuncionario(codigo);
		}

	}

	protected void editar() {
		int codigo = getIdTabela();
		if (codigo >= 0) {
			InterfaceUsuario.editarFuncionarioCarregarPorId(codigo);
		}
	}

	private int getIdTabela() {
		int row = tabela.getSelectedRow();
		int codigo = -1;
		if (row >= 0) {
			String codigoS = (String) tabela.getModel().getValueAt(row, 0);
			codigo = InterfaceUsuario.transformaStringInt(codigoS);
		} else {
			JOptionPane.showMessageDialog(null, "Selecione uma linha para ação");
		}
		return codigo;
	}

	protected void fechar() {
		this.dispose();
	}

	protected void buscar() {
		int codigo = 0;
		if (textFieldCodigo.getText().trim().length() > 0)
			InterfaceUsuario.transformaStringInt(textFieldCodigo.getText());
		InterfaceUsuario.buscarFuncionario(codigo, textFieldNome.getText(), textFieldCPF.getText(),
				formattedFieldDataNascimento.getText());

	}

	protected void adicionar() {
		InterfaceUsuario.cadFuncionarioView();
	}

	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null, "Deseja excluir o funcionário?", "Excluir Funcionário",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (opcao == 0)
			return true;
		else
			return false;
	}

	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public int MensagemCpfExistente() {
		Object[] options = { "Retornar", "Cancelar" };
		return JOptionPane.showOptionDialog(null,
				"Já existe um funcionário cadastrado com este cpf. Retornar ao cadasto ou cancelar ação?",
				"CPF Existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}

	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		tabela = new JTable(modelo);
		rolagem = new JScrollPane(tabela);
	}
}
