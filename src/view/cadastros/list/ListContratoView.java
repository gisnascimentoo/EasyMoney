package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import view.InterfaceUsuario;
import view.combo.SituacaoCombo;

public class ListContratoView extends JFrame {

	private JButton jButtonAdicionarCliente;
	private JButton jButtonBuscar;
	private JButton jButtonFechar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane1_1;
	private JTable jTableListagemContrato;
	private JTextField jTextFieldCodigo;
	private JTextField jTextFieldNome;
	DefaultTableModel modelo;
	JComboBox comboBox;
	String[] colunas = new String[] { "Codigo", "Cliente", "Situacao",
			"CPF"};

	public ListContratoView() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Contrato");
		setBounds(100, 100, 600, 500);
		jTextFieldCodigo = new JTextField();
		jLabel1 = new JLabel();
		jLabel3 = new JLabel();
		jTextFieldNome = new JTextField();
		jLabel2 = new JLabel();
		jButtonFechar = new JButton();
		jScrollPane1 = new JScrollPane();
		jTableListagemContrato = new JTable();
		jButtonBuscar = new JButton();
		jButtonAdicionarCliente = new JButton();

		jLabel1.setText("Codigo");

		jLabel3.setText("Situacao");

		jLabel2.setText("Cliente");

		jButtonFechar.setText("Fechar");
       
		jScrollPane1.setViewportView(jTableListagemContrato);

		jButtonBuscar.setText("Buscar");

		jButtonAdicionarCliente.setText("Adicionar Contrato");
		
		modelo = new DefaultTableModel(null, colunas);
		jTableListagemContrato = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTableListagemContrato);
		//Adicionar ScrollPanel no lugar da listagem atual
		
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
		
		comboBox = new JComboBox();
		carregarSituacaoCombo();

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGap(22)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
									.addComponent(btnExcluir)
									.addGap(18)
									.addComponent(btnEditar)
									.addGap(18)
									.addComponent(jButtonFechar))
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(Alignment.TRAILING)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
											.addComponent(jLabel1)
											.addComponent(jTextFieldCodigo, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
											.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
											.addComponent(comboBox, 0, 147, Short.MAX_VALUE))
										.addComponent(jButtonAdicionarCliente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addGap(34)
									.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
											.addGap(192)
											.addComponent(jButtonBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
							.addGap(55))))
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
					.addComponent(jLabel3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButtonBuscar)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(jButtonAdicionarCliente)
					.addGap(18)
					.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
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

	private void carregarSituacaoCombo() {
			comboBox.setModel(new DefaultComboBoxModel(new Vector(InterfaceUsuario.carregaSituacaoCombo())));
	}

	protected void excluir() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.excluirContrato(codigo);
		}
	}

	protected void editar() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.editarContratoCarregarPorId(codigo);
		}
	}
	
	private int getIdTabela(){
		int row = jTableListagemContrato.getSelectedRow();
		int codigo = -1;
		if(row >= 0){
			String codigoS = (String)jTableListagemContrato.getModel().getValueAt(row, 0);
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
			if (jTextFieldCodigo.getText().trim().length() > 0)
				codigo = InterfaceUsuario.transformaStringInt(jTextFieldCodigo.getText());
			SituacaoCombo situacaoCombo = (SituacaoCombo)comboBox.getSelectedItem();
			System.out.println(situacaoCombo);
		InterfaceUsuario.buscarContrato(codigo, jTextFieldNome.getText(), situacaoCombo.getCodigo()); 

	}

	protected void adicionarCliente() {
		InterfaceUsuario.novoContratoView();

	}

	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		jTableListagemContrato.setModel(modelo);;
	}

	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Nao" };
		int opcao = JOptionPane.showOptionDialog(null,
				"Deseja excluir o contrato?", "Excluir Contrato",
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

}
