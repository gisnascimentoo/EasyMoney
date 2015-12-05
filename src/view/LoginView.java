package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.SwingConstants;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNomeUsuario;
	private JPasswordField pwdFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setTitle("EasyMoney");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 424);
		contentPane = new JPanel();
		contentPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNomeDeUsuario = new JLabel("Nome de usu\u00E1rio");
		lblNomeDeUsuario.setBounds(54, 111, 137, 26);
		panel_1.add(lblNomeDeUsuario);
		lblNomeDeUsuario.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblNomeDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldNomeUsuario = new JTextField();
		textFieldNomeUsuario.setBounds(10, 148, 228, 20);
		panel_1.add(textFieldNomeUsuario);
		textFieldNomeUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblSenha.setBounds(93, 190, 60, 32);
		panel_1.add(lblSenha);
		
		pwdFieldSenha = new JPasswordField();
		pwdFieldSenha.setBounds(10, 223, 228, 20);
		panel_1.add(pwdFieldSenha);
		
		JLabel lblAcesso = new JLabel("ACESSO");
		lblAcesso.setBounds(78, 49, 94, 33);
		panel_1.add(lblAcesso);
		lblAcesso.setFont(new Font("Calibri Light", Font.BOLD, 26));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(149, 328, 89, 23);
		panel_1.add(btnEntrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(22, 328, 89, 23);
		panel_1.add(btnCancelar);
	}
}