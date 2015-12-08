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

public class ListPlanosView extends JFrame {
	private JButton jButtonAdicionarPlano;
	private JButton jButtonBuscar;
	private JButton jButtonFechar;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JScrollPane jScrollPane1;
	private JTable jTableListagemPlano;
	private JTextField jTextFieldCodigo;
	private JTextField jTextFieldPlano;
	String[] colunas = new String[] { "Código", "Plano" };
	DefaultTableModel modelo;

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
		jScrollPane1 = new JScrollPane(jTableListagemPlano);

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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
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
																				.addComponent(
																						jButtonAdicionarPlano))
																.addGap(37, 37,
																		37)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel2)
																				.addComponent(
																						jTextFieldPlano,
																						GroupLayout.PREFERRED_SIZE,
																						333,
																						GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButtonBuscar,
																		GroupLayout.PREFERRED_SIZE,
																		141,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														jScrollPane1,
														GroupLayout.PREFERRED_SIZE,
														653,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jButtonFechar).addContainerGap()));
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
														jTextFieldPlano,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jButtonBuscar))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jButtonAdicionarPlano)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jScrollPane1,
										GroupLayout.PREFERRED_SIZE, 199,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButtonFechar)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

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
		jScrollPane1 = new JScrollPane(jTableListagemPlano);
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

	protected void buscar() {
		int codigo = 0;
		if (jTextFieldCodigo.getText().trim().length() > 0)
			Integer.parseInt(jTextFieldCodigo.getText());
		InterfaceUsuario.buscarPlano(codigo, jTextFieldPlano.getText());

	}
}
