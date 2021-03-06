package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import view.InterfaceUsuario;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListClientesView extends JFrame {

	private JButton jButtonAdicionarCliente;
	private JButton jButtonBuscar;
	private JButton jButtonFechar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane1_1;
	private JTable jTableListagemCliente;
	private JTextField jTextFieldCPF;
	private JTextField jTextFieldCodigo;
	private JFormattedTextField formattedFieldDataNascimento;
	private JTextField jTextFieldNome;
	private String formatString = "##/##/####";
	DefaultTableModel modelo;
	String[] colunas = new String[] { "Codigo", "Nome", "Data de Nascimento",
			"CPF"};

	public ListClientesView() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Cliente");
		setBounds(100, 100, 600, 500);
		jTextFieldCodigo = new JTextField();
		jLabel1 = new JLabel();
		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataNascimento = new JFormattedTextField(maskData);
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

		jLabel1.setText("Codigo");

		jLabel3.setText("Data de Nascimento");

		jLabel2.setText("Nome");

		jButtonFechar.setText("Fechar");

		jLabel4.setText("CPF");
       
		jScrollPane1.setViewportView(jTableListagemCliente);

		jButtonBuscar.setText("Buscar");

		jButtonAdicionarCliente.setText("Adicionar Cliente");
		
		modelo = new DefaultTableModel(null, colunas);
		jTableListagemCliente = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTableListagemCliente);
		
		JButton btnEditar = new JButton("Editar");
		
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			    editar();
				
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			     excluir();
				
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(22)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnEditar)
							.addGap(18)
							.addComponent(jButtonFechar))
						.addGroup(layout.createParallelGroup(Alignment.LEADING)
							.addComponent(jButtonAdicionarCliente)
							.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(jLabel1)
									.addComponent(jTextFieldCodigo, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
									.addComponent(jLabel3)
									.addComponent(formattedFieldDataNascimento))
								.addGap(34)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(jLabel4)
									.addComponent(jLabel2)
									.addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
									.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
										.addComponent(jTextFieldCPF)
										.addGap(18)
										.addComponent(jButtonBuscar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))))
							.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel1)
						.addComponent(jLabel2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel3)
						.addComponent(jLabel4))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedFieldDataNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jTextFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButtonBuscar))
					.addGap(18)
					.addComponent(jButtonAdicionarCliente)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButtonFechar)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);
		jButtonAdicionarCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				adicionarCliente();

			}
		});
		jButtonBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				buscar();

			}
		});
		jButtonFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});

	}

	protected void excluir() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.excluirCliente(codigo);
		}
	}

	protected void editar() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.editarClienteCarregarPorId(codigo);
		}
	}
	
	private int getIdTabela(){
		int row = jTableListagemCliente.getSelectedRow();
		int codigo = -1;
		if(row >= 0){
			String codigoS = (String)jTableListagemCliente.getModel().getValueAt(row, 0);
			codigo  = InterfaceUsuario.transformaStringInt(codigoS); 
		}else{
			JOptionPane.showMessageDialog(null, "Selecione uma linha para acao");
		}
		return codigo;
	}

	protected void fechar() {
		this.dispose();

	}

	protected void buscar() {
		int codigo = 0;
		if (jTextFieldCodigo.getText().trim().length() > 0) {
				codigo = InterfaceUsuario.transformaStringInt(jTextFieldCodigo.getText());
		}
		InterfaceUsuario.buscarCliente(codigo, jTextFieldNome.getText(),
				jTextFieldCPF.getText(), formattedFieldDataNascimento.getText());

	}

	protected void adicionarCliente() {
		InterfaceUsuario.cadClienteView();

	}

	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		jTableListagemCliente.setModel(modelo);;
	}

	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Nao" };
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
		return JOptionPane
				.showOptionDialog(
						null,
						"Ja existe um cliente cadastrado com este cpf. Retornar ao cadasto ou cancelar acao?",
						"CPF Existente", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	}
}
