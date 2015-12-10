package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class RelatoriosEmprestimoView extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField formattedFieldDataInicio;
	private JTable table;
	private JFormattedTextField formattedFieldDataTermino;
	private ButtonGroup grupo;
	private JScrollPane jScrollPane1_1;
	DefaultTableModel modelo;
	private String formatString = "##/##/####";
	JRadioButton rdbtnEmprestimosAprovados;
	JRadioButton rdbtnEmprestimosReprovados;
	String[] colunas = new String[] { "Codigo", "Cliente", "Valor contrato" };

	/**
	 * Create the frame.
	 */
	public RelatoriosEmprestimoView() {
		grupo = new ButtonGroup();

		setTitle("Relatorio Emprestimo");
		setBounds(100, 100, 465, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBuscaCliente = new JLabel("Intervalo Inicio");
		lblBuscaCliente.setBounds(23, 24, 139, 14);
		contentPane.add(lblBuscaCliente);

		MaskFormatter maskData = InterfaceUsuario.createFormatter(formatString);
		formattedFieldDataInicio = new JFormattedTextField(maskData);
		formattedFieldDataInicio.setBounds(23, 47, 86, 20);
		contentPane.add(formattedFieldDataInicio);
		formattedFieldDataInicio.setColumns(10);

		JLabel lblTipoDeRelatrio = new JLabel("Tipo de Relatorio:");
		lblTipoDeRelatrio.setBounds(23, 92, 139, 14);
		contentPane.add(lblTipoDeRelatrio);

		rdbtnEmprestimosAprovados = new JRadioButton(
				"Empr\u00E9stimos Aprovados");
		rdbtnEmprestimosAprovados.setBounds(59, 113, 195, 23);
		contentPane.add(rdbtnEmprestimosAprovados);
		rdbtnEmprestimosAprovados.setSelected(true);
		grupo.add(rdbtnEmprestimosAprovados);

		rdbtnEmprestimosReprovados = new JRadioButton(
				"Empr\u00E9stimos Reprovados");
		rdbtnEmprestimosReprovados.setBounds(59, 139, 195, 23);
		contentPane.add(rdbtnEmprestimosReprovados);
		grupo.add(rdbtnEmprestimosReprovados);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(322, 364, 89, 23);
		contentPane.add(btnFechar);

		btnFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fechar();

			}
		});

		modelo = new DefaultTableModel(null, colunas);
		table = new JTable(modelo);
		jScrollPane1_1 = new JScrollPane(table);
		jScrollPane1_1.setBounds(39, 180, 354, 154);
		contentPane.add(jScrollPane1_1);

		JButton btnGerar = new JButton("Gerar");
		btnGerar.setBounds(322, 139, 89, 23);
		contentPane.add(btnGerar);

		btnGerar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gerar();

			}
		});

		JLabel lblNewLabel = new JLabel("Intervalo Fim");
		lblNewLabel.setBounds(145, 24, 98, 14);
		contentPane.add(lblNewLabel);

		formattedFieldDataTermino = new JFormattedTextField(maskData);
		formattedFieldDataTermino.setBounds(145, 47, 86, 20);
		contentPane.add(formattedFieldDataTermino);
		formattedFieldDataTermino.setColumns(10);
	}

	protected void gerar() {
		String tipo = null;
		for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				tipo = button.getText();
			}
		}
		int tipoIndex;
		if(tipo.equals("Emprestimos Aprovados")){
			tipoIndex = 0;
		}else{
			tipoIndex = 1;
		}
		 InterfaceUsuario.gerarRelatorio(formattedFieldDataInicio.getText(),
		formattedFieldDataTermino.getText(), tipoIndex);
	}

	protected void fechar() {
		this.dispose();

	}

	public void addTabela(String[][] dados) {
		modelo = new DefaultTableModel(dados, colunas);
		table.setModel(modelo);
		
	}
}
