package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.ComponentOrientation;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JSeparator;

public class PrincipalView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView frame = new PrincipalView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalView() {
		setTitle("EasyMoney");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 27);
		menuBar.setBackground(Color.LIGHT_GRAY);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setActionCommand("");
		mnCadastro.setBackground(Color.LIGHT_GRAY);
		menuBar.add(mnCadastro);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		mnCadastro.add(mntmCliente);
		
		JSeparator separator = new JSeparator();
		mnCadastro.add(separator);
		
		JMenuItem mntmFuncionario = new JMenuItem("Funcion\u00E1rios");
		mnCadastro.add(mntmFuncionario);
		
		JSeparator separator_1 = new JSeparator();
		mnCadastro.add(separator_1);
		
		JMenuItem mntmPlanos = new JMenuItem("Planos");
		mnCadastro.add(mntmPlanos);
		
		JSeparator separator_2 = new JSeparator();
		mnCadastro.add(separator_2);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnCadastro.add(mntmSair);
		
		mntmSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sair();
				
			}
		});
		
		JMenu mnEmprestimo = new JMenu("Empr\u00E9stimo");
		menuBar.add(mnEmprestimo);
		
		JMenuItem mntmContratos = new JMenuItem("Contratos");
		mnEmprestimo.add(mntmContratos);
		
		JSeparator separator_3 = new JSeparator();
		mnEmprestimo.add(separator_3);
		
		JMenuItem mntmSimulacao = new JMenuItem("Simula\u00E7\u00E3o");
		mnEmprestimo.add(mntmSimulacao);
		
		JMenu mnRelatorio = new JMenu("Relat\u00F3rio");
		menuBar.add(mnRelatorio);
		
		JMenuItem mntmEmprestimos = new JMenuItem("Empr\u00E9stimos");
		mnRelatorio.add(mntmEmprestimos);
	}

	protected void sair() {
		InterfaceUsuario.logout();
		
	}

	public boolean encerrarSessao() {
		Object[] options = { "Sim", "Não" };  
		int opcao = JOptionPane.showOptionDialog(null,  
		                "Deseja fazer logout?",  
		                "Exemplo", JOptionPane.YES_NO_OPTION,  
		                JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
		if(opcao == 0)
			return true;
		else
			return false;
	}
}
