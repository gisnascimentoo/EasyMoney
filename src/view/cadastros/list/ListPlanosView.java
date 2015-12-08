package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import view.InterfaceUsuario;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListPlanosView extends JFrame {
	private JButton jButtonAdicionarPlano;
	private JButton jButtonBuscar;
	private JButton jButtonFechar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane1_1;
	private JTable jTableListagemPlano;
	private JTextField jTextFieldCodigo;
	private JTextField jTextFieldPlano;
	String[] colunas = new String[] { "Código", "Plano" };
	DefaultTableModel modelo;
	private JButton btnEditar;
	private JButton btnExcluir;

	public ListPlanosView() {
		init();
	}

	private void init() {
		setTitle("Planos");
		setBounds(100, 100, 698, 381);
		jTextFieldCodigo = new JTextField();
		jTextFieldPlano = new JTextField();
		jLabel1 = new JLabel();
		jButtonFechar = new JButton();
		jLabel2 = new JLabel();
		jButtonBuscar = new JButton();
		jButtonAdicionarPlano = new JButton();
		jScrollPane1 = new JScrollPane();
		jTableListagemPlano = new JTable();

		jLabel1.setText("Código");

		modelo = new DefaultTableModel(null, colunas);
		jTableListagemPlano = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTableListagemPlano);

		jButtonFechar.setText("Fechar");
		jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				fechar(evt);
			}
		});

		jLabel2.setText("Plano");

		jButtonBuscar.setText("Buscar");
		
		jButtonBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});

		jButtonAdicionarPlano.setText("Adicionar Plano");

		jButtonAdicionarPlano.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarPlano();

			}
		});
		
		btnEditar = new JButton("Editar");
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		
		btnExcluir = new JButton("Excluir");
		
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				excluir();
				
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGap(22)
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel1)
								.addComponent(jTextFieldCodigo, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonAdicionarPlano))
							.addGap(37)
							.addGroup(layout.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel2)
								.addComponent(jTextFieldPlano, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jButtonBuscar, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
					.addContainerGap(441, Short.MAX_VALUE)
					.addComponent(btnExcluir)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnEditar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jButtonFechar)
					.addContainerGap())
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
						.addComponent(jTextFieldPlano, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jButtonBuscar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jButtonAdicionarPlano)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jScrollPane1_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButtonFechar)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);

	}

	protected void excluir() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.excluirPlanoCarregarPorId(codigo);
		}
	}

	protected void editar() {
		int codigo = getIdTabela();
		if(codigo >= 0 ){
			InterfaceUsuario.editarPlanoCarregarPorId(codigo);
		}
	}

	protected void adicionarPlano() {
		InterfaceUsuario.cadPlanoEmprestimoView();

	}

	protected void fechar(ActionEvent evt) {
		this.dispose();
	}

	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		jTableListagemPlano = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(jTableListagemPlano);
	}

	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null,
				"Deseja excluir o plano?", "Excluir Plano",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		if (opcao == 0)
			return true;
		else
			return false;
	}
	
	private int getIdTabela(){
		int row = jTableListagemPlano.getSelectedRow();
		int codigo = -1;
		if(row >= 0){
			String codigoS = (String)jTableListagemPlano.getModel().getValueAt(row, 0);
			codigo  = Integer.parseInt(codigoS); 
		}else{
			JOptionPane.showMessageDialog(null, "Selecione uma linha para ação");
		}
		return codigo;
	}

	protected void buscar() {
		int codigo = 0;
		if (jTextFieldCodigo.getText().trim().length() > 0)
			Integer.parseInt(jTextFieldCodigo.getText());
		InterfaceUsuario.buscarPlano(codigo, jTextFieldPlano.getText());

	}
}
