package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public class RelatoriosEmprestimoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBuscaCliente;
	private JTable table;
	private JTextField textField;
	private ButtonGroup grupo;
	

	/**
	 * Create the frame.
	 */
	public RelatoriosEmprestimoView() {
		ButtonGroup grupo = new ButtonGroup();  
		
		setTitle("Relat\u00F3rio Empr\u00E9stimo");
		setBounds(100, 100, 465, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscaCliente = new JLabel("M\u00EAs In\u00EDcio");
		lblBuscaCliente.setBounds(23, 24, 139, 14);
		contentPane.add(lblBuscaCliente);
		
		textFieldBuscaCliente = new JTextField();
		textFieldBuscaCliente.setBounds(23, 47, 86, 20);
		contentPane.add(textFieldBuscaCliente);
		textFieldBuscaCliente.setColumns(10);
		
		JLabel lblTipoDeRelatrio = new JLabel("Tipo de Relat\u00F3rio:");
		lblTipoDeRelatrio.setBounds(23, 92, 139, 14);
		contentPane.add(lblTipoDeRelatrio);
		
		JRadioButton rdbtnEmprestimosAprovados = new JRadioButton("Empr\u00E9stimos Aprovados");
		rdbtnEmprestimosAprovados.setBounds(59, 113, 195, 23);
		contentPane.add(rdbtnEmprestimosAprovados);
		grupo.add(rdbtnEmprestimosAprovados);
		
		JRadioButton rdbtnEmprestimosReprovados = new JRadioButton("Empr\u00E9stimos Reprovados");
		rdbtnEmprestimosReprovados.setBounds(59, 139, 195, 23);
		contentPane.add(rdbtnEmprestimosReprovados);
		grupo.add(rdbtnEmprestimosReprovados);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(322, 364, 89, 23);
		contentPane.add(btnFechar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(394, 180, 17, 154);
		contentPane.add(scrollBar);
		
		table = new JTable();
		table.setBounds(39, 180, 354, 154);
		contentPane.add(table);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.setBounds(322, 139, 89, 23);
		contentPane.add(btnGerar);
		
		JLabel lblNewLabel = new JLabel("M\u00EAs Fim");
		lblNewLabel.setBounds(145, 24, 98, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(145, 47, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
