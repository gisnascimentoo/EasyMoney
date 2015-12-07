package view.cadastros.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Funcionario;

import view.InterfaceUsuario;

public class ListFuncionariosView extends JFrame {


	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JLabel lblNome;
	private JTextField textFieldNome;
	private JLabel lblDataDeNascimento;
	private JTextField textFieldDataNasc;
	private JLabel lblCpf;
	private JTextField textFieldCPF;

	
	/**
	 * Create the frame.
	 */
	public ListFuncionariosView() {
		setTitle("Buscar Funcion�rios");
		setBounds(100, 100, 586, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("C�digo");
		lblCodigo.setBounds(25, 22, 76, 14);
		contentPane.add(lblCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(25, 38, 93, 20);
		contentPane.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(159, 22, 134, 14);
		contentPane.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(159, 38, 388, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(25, 69, 119, 14);
		contentPane.add(lblDataDeNascimento);
		
		textFieldDataNasc = new JTextField();
		textFieldDataNasc.setBounds(25, 85, 101, 20);
		contentPane.add(textFieldDataNasc);
		textFieldDataNasc.setColumns(10);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(159, 69, 76, 14);
		contentPane.add(lblCpf);
		
		textFieldCPF = new JTextField();
		textFieldCPF.setBounds(159, 85, 181, 20);
		contentPane.add(textFieldCPF);
		textFieldCPF.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(373, 75, 164, 36);
		contentPane.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
				
			}
		});
		
		JButton btnAdicionarFuncionario = new JButton("Adicionar Funcion�rio");
		btnAdicionarFuncionario.setBounds(25, 122, 164, 23);
		contentPane.add(btnAdicionarFuncionario);
		btnAdicionarFuncionario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adicionar();
				
			}
		});
		
		JList listResultadoBusca = new JList();
		listResultadoBusca.setBounds(25, 168, 520, 157);
		contentPane.add(listResultadoBusca);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(446, 336, 101, 30);
		contentPane.add(btnFechar);
		btnFechar.addActionListener(new ActionListener() {
			
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
			if (textFieldDataNasc.getText().trim().length() > 0)
				date = (Date) formatter.parse(textFieldDataNasc
						.getText());
			if (textFieldCodigo.getText().trim().length() > 0)
				Integer.parseInt(textFieldCodigo.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InterfaceUsuario.buscarCliente(codigo, textFieldNome.getText(),
				textFieldCPF.getText(), date);

	}


	protected void adicionar() {
		InterfaceUsuario.cadFuncionarioView();
	}
	
	public boolean confirmaExclusao() {
		Object[] options = { "Sim", "Não" };
		int opcao = JOptionPane.showOptionDialog(null,
				"Deseja excluir o funcionário?", "Excluir Funcionário",
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
				"Já existe um funcionário cadastrado com este cpf. Retornar ao cadasto ou cancelar ação?", "CPF Existente",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
	}


	public void addTabela(List<Funcionario> funcionariosBusca) {
		// TODO Auto-generated method stub
		
	}
}

