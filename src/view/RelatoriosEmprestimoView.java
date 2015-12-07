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
import javax.swing.JButton;
import javax.swing.JScrollBar;

public class RelatoriosEmprestimoView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldBuscaCliente;
	private JTextField textField;

	

	/**
	 * Create the frame.
	 */
	public RelatoriosEmprestimoView() {
		setTitle("Relat\u00F3rio Empr\u00E9stimo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscaCliente = new JLabel("Busca Cliente");
		lblBuscaCliente.setBounds(23, 24, 76, 14);
		contentPane.add(lblBuscaCliente);
		
		textFieldBuscaCliente = new JTextField();
		textFieldBuscaCliente.setBounds(23, 47, 370, 20);
		contentPane.add(textFieldBuscaCliente);
		textFieldBuscaCliente.setColumns(10);
		
		JLabel lblTipoDeRelatrio = new JLabel("Tipo de Relat\u00F3rio:");
		lblTipoDeRelatrio.setBounds(23, 92, 101, 14);
		contentPane.add(lblTipoDeRelatrio);
		
		JRadioButton rdbtnEmprestimosAprovados = new JRadioButton("Empr\u00E9stimos Aprovados");
		rdbtnEmprestimosAprovados.setBounds(59, 113, 148, 23);
		contentPane.add(rdbtnEmprestimosAprovados);
		
		JRadioButton rdbtnEmprestimosReprovados = new JRadioButton("Empr\u00E9stimos Reprovados");
		rdbtnEmprestimosReprovados.setBounds(59, 139, 148, 23);
		contentPane.add(rdbtnEmprestimosReprovados);
		
		textField = new JTextField();
		textField.setBounds(23, 180, 370, 154);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(322, 364, 89, 23);
		contentPane.add(btnFechar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(394, 180, 17, 154);
		contentPane.add(scrollBar);
	}
}
