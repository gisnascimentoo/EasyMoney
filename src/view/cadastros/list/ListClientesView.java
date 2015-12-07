package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import model.Cliente;
import view.InterfaceUsuario;

public class ListClientesView extends JFrame {

	private JButton jButtonAdicionarCliente;
	private JButton jButtonBuscar;
	private JButton jButtonFechar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JScrollPane jScrollPane1;
	private JTable jTableListagemCliente;
	private JTextField jTextFieldCPF;
	private JTextField jTextFieldCodigo;
	private JTextField jTextFieldDataNascimento;
	private JTextField jTextFieldNome;

	public ListClientesView() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Cliente");
		setBounds(100, 100, 541, 425);
		jTextFieldCodigo = new JTextField();
		jLabel1 = new JLabel();
		jTextFieldDataNascimento = new JTextField();
		jLabel3 = new JLabel();
		jTextFieldNome = new JTextField();
		jLabel2 = new JLabel();
		jButtonFechar = new JButton();
		jLabel4 = new JLabel();
		jScrollPane1 = new JScrollPane();
		jTableListagemCliente = new JTable();
		jButtonBuscar = new JButton();
		jButtonAdicionarCliente = new JButton();
		jTextFieldCPF = new JTextField();

		jLabel1.setText("Código");

		jLabel3.setText("Data de Nascimento");

		jLabel2.setText("Nome");

		jButtonFechar.setText("Fechar");

		jLabel4.setText("CPF");

		jTableListagemCliente.setModel(new javax.swing.table.DefaultTableModel(
				null, new String[] { "Código", "Nome", "Data de Nascimento",
						"CPF", "Ação" }) {
			Class[] types = new Class[] { java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class,
					java.lang.Object.class, java.lang.Object.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTableListagemCliente);

		jButtonBuscar.setText("Buscar");

		jButtonAdicionarCliente.setText("Adicionar Cliente");

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
												.addComponent(jButtonFechar)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING)
																.addComponent(
																		jButtonAdicionarCliente)
																.addGroup(
																		layout.createSequentialGroup()
																				.addGroup(
																						layout.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																								.addComponent(
																										jLabel1)
																								.addComponent(
																										jTextFieldCodigo,
																										GroupLayout.PREFERRED_SIZE,
																										137,
																										GroupLayout.PREFERRED_SIZE)
																								.addGroup(
																										layout.createParallelGroup(
																												GroupLayout.Alignment.TRAILING,
																												false)
																												.addComponent(
																														jTextFieldDataNascimento,
																														GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jLabel3,
																														GroupLayout.Alignment.LEADING,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)))
																				.addGap(34,
																						34,
																						34)
																				.addGroup(
																						layout.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																								.addComponent(
																										jLabel4)
																								.addComponent(
																										jLabel2)
																								.addComponent(
																										jTextFieldNome,
																										GroupLayout.PREFERRED_SIZE,
																										333,
																										GroupLayout.PREFERRED_SIZE)
																								.addGroup(
																										layout.createSequentialGroup()
																												.addComponent(
																														jTextFieldCPF,
																														GroupLayout.PREFERRED_SIZE,
																														186,
																														GroupLayout.PREFERRED_SIZE)
																												.addPreferredGap(
																														LayoutStyle.ComponentPlacement.RELATED)
																												.addComponent(
																														jButtonBuscar,
																														GroupLayout.DEFAULT_SIZE,
																														GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE))))
																.addComponent(
																		jScrollPane1,
																		GroupLayout.PREFERRED_SIZE,
																		507,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														jTextFieldCodigo,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextFieldNome,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(jLabel4))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														jTextFieldDataNascimento,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jTextFieldCPF,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jButtonBuscar))
								.addGap(18, 18, 18)
								.addComponent(jButtonAdicionarCliente)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										GroupLayout.PREFERRED_SIZE, 199,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonFechar)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		jButtonAdicionarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();

			}
		});
		jButtonBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();

			}
		});
		jButtonFechar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});

	}

	protected void fechar() {
		this.dispose();

	}

	protected void buscar() {
		DateFormat formatter = new SimpleDateFormat("dd/mm/aaaa");
		Date date = null;
		int codigo = 0;
		try {
			if (jTextFieldDataNascimento.getText().trim().length() > 0)
				date = (Date) formatter.parse(jTextFieldDataNascimento
						.getText());
			if (jTextFieldCodigo.getText().trim().length() > 0)
				Integer.parseInt(jTextFieldCodigo.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InterfaceUsuario.buscarCliente(codigo, jTextFieldNome.getText(),
				jTextFieldCPF.getText(), date);

	}

	protected void adicionarCliente() {
		InterfaceUsuario.cadClienteView();

	}

	public void addTabela(List<Cliente> clientesBusca) {
		// TODO
	}

	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null,
				"Deseja excluir o cliente?", "Excluir Cliente",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
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
				"Já existe um cliente cadastrado com este cpf. Retornar ao cadasto ou cancelar ação?", "CPF Existente",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		
	}
}
