/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Ajudante;
import model.Caminhao;
import model.Caminhoneiro;
import model.Cidade;
import model.Endereco;
import model.Estabelecimento;
import model.Fruta;
import model.Gerente;
import model.Lote;
import model.Pedido;
import model.Rota;
import model.TabelaPreco;
import model.Telefone;
import model.Tipo;
import model.Viagem;

/**
 *
 * @author Giselle
 */
public class ManipuladorBancoDados {

	// INSERT
	private final String INSERT_ENDERECO = "INSERT INTO ENDERECO(nm_rua, nu_cep, nm_bairro, nu_numero, nm_complemento) VALUES(?,?,?,?,?)";
	private final String INSERT_AJUDANTE = "INSERT INTO AJUDANTE(idgerente, idendereco, nm_pessoa, nu_cpf, vl_salario, dt_nascimento, tp_sexo, nm_email) VALUES(?,?,?,?,?,?,?,?)";
	private final String INSERT_GERENTE = "INSERT INTO GERENTE(idendereco, nm_pessoa, nu_cpf, vl_salario, dt_nascimento, tp_sexo, nm_email) VALUES(?,?,?,?,?,?,?)";
	private final String INSERT_CAMINHONEIRO = "INSERT INTO CAMINHONEIRO(idgerente, idendereco, nm_pessoa, nu_cpf, vl_salario, dt_nascimento, tp_sexo, nm_email) VALUES(?,?,?,?,?,?,?,?)";
	private final String INSERT_ESTABELECIMENTO = "INSERT INTO ESTABELECIMENTO(cnpj, idendereco, idtelefone, idcidade, nome, email, tp_estabelecimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_LOTE = "INSERT INTO LOTE(idtipo, idestabelecimento, nu_peso, nu_quantidade, dt_validade) VALUES (?, ?, ?, ?, ?)";
	private final String INSERT_PEDIDO = "INSERT INTO PEDIDO(idviagem, idlote) VALUES (?, ?)";
	private final String INSERT_ROTA = "INSERT INTO ROTA(idcaminhoneiro, nm_linha, nu_distancia) VALUES (?, ?, ?)";
	private final String INSERT_TABELA_PRECO = "INSERT INTO TABELA_PRECO(idtipo, idestabelecimento, nu_preco, nu_peso) VALUES (?, ?, ?, ?)";
	private final String INSERT_VIAGEM = "INSERT INTO VIAGEM(hora, dt_chegada, dt_partida, idrota, idajudante, idcaminhao, nu_carga, tp_viagem, vl_custo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String INSERT_TELEFONE = "INSERT INTO TELEFONE(nu_ddd, nu_numero) VALUES (?, ?)";

	// SELECTS PARA COMBOBOX
	private final String SELECT_AJUDANTE = "SELECT id, nm_pessoa FROM AJUDANTE";
	private final String SELECT_CIDADE = "SELECT id, nome FROM CIDADE";
	private final String SELECT_CAMINHONEIRO = "SELECT id, nm_pessoa FROM CAMINHONEIRO";
	private final String SELECT_CAMINHAO = "SELECT id, nu_placa FROM CAMINHAO";
	private final String SELECT_ESTABELECIMENTO = "SELECT id, nome FROM ESTABELECIMENTO";
	private final String SELECT_GERENTE = "SELECT id, nm_pessoa FROM GERENTE";
	private final String SELECT_ROTA = "SELECT id, nm_linha FROM ROTA";
	private final String SELECT_TIPOFRUTA = "SELECT T.id id, F.nome nomefruta, T.nome nometipo FROM TIPO T INNER JOIN FRUTA F ON T.idfruta = F.id";
	private final String SELECT_VIAGEM = "SELECT V.id id, V.tp_viagem tipoviagem, R.nm_linha nomelinha, V.dt_partida dt_partida FROM VIAGEM V INNER JOIN ROTA R ON V.idrota = R.id";
	private final String SELECT_LOTE = "SELECT E.nome nomeestab, F.nome nomefruta, T.nome nometipo, L.nu_quantidade nuquantidade FROM LOTE L INNER JOIN ESTABELECIMENTO E ON L.idestabelecimento = E.id INNER JOIN TIPO T ON T.id = L.idtipo INNER JOIN FRUTA F ON T.idfruta = F.id";

	// CONNECTION
	Connection conexao;

	public void conectasgbd() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			this.conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/distribuidora", "everton",
					"everton");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro classe: " + e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro conexão: " + e.getMessage());
		}
	}

	public void closeConnnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int salvarEnderecoBanco(Endereco endereco) {
		int idEndereco = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ENDERECO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, endereco.getNm_rua());
			prepared.setString(2, endereco.getNu_cep());
			prepared.setString(3, endereco.getNm_bairro());
			prepared.setInt(4, endereco.getNu_numero());
			prepared.setString(5, endereco.getNm_complemento());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idEndereco = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idEndereco;
	}

	public int salvarTelefoneBanco(Telefone telefone) {
		int idTelefone = 0;
		try {
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_TELEFONE,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, telefone.getNu_ddd());
			prepared.setString(2, telefone.getNumero());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idTelefone = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idTelefone;
	}

	public int salvarAjudanteBanco(Ajudante ajudante) {
		int idAjudante = 0;
		int idFKEndereco;
		try {
			this.conectasgbd();
			idFKEndereco = this.salvarEnderecoBanco(ajudante.getEndereco());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_AJUDANTE,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, ajudante.getGerente().getId());
			prepared.setInt(2, idFKEndereco);
			prepared.setString(3, ajudante.getNm_pessoa());
			prepared.setString(4, ajudante.getNu_cpf());
			prepared.setDouble(5, ajudante.getVl_salario());
			prepared.setDate(6, ajudante.getDt_nascimento());
			prepared.setString(7, ajudante.getTp_sexo());
			prepared.setString(8, ajudante.getNm_email());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idAjudante = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}
		return idAjudante;
	}

	public int salvarCaminhoneiroBanco(Caminhoneiro caminhoneiro) {
		int idCaminhoneiro = 0;
		int idFKEndereco;
		try {
			this.conectasgbd();
			idFKEndereco = this.salvarEnderecoBanco(caminhoneiro.getEndereco());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_CAMINHONEIRO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, caminhoneiro.getGerente().getId());
			prepared.setInt(2, idFKEndereco);
			prepared.setString(3, caminhoneiro.getNm_pessoa());
			prepared.setString(4, caminhoneiro.getNu_cpf());
			prepared.setDouble(5, caminhoneiro.getVl_salario());
			prepared.setDate(6, caminhoneiro.getDt_nascimento());
			prepared.setString(7, caminhoneiro.getTp_sexo());
			prepared.setString(8, caminhoneiro.getNm_email());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idCaminhoneiro = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}
		return idCaminhoneiro;
	}

	public int salvarGerenteBanco(Gerente gerente) {
		int idGerente = 0;
		int idFKEndereco;
		try {
			this.conectasgbd();
			idFKEndereco = this.salvarEnderecoBanco(gerente.getEndereco());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_GERENTE, Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, idFKEndereco);
			prepared.setString(2, gerente.getNm_pessoa());
			prepared.setString(3, gerente.getNu_cpf());
			prepared.setDouble(4, gerente.getVl_salario());
			prepared.setDate(5, gerente.getDt_nascimento());
			prepared.setString(6, gerente.getTp_sexo());
			prepared.setString(7, gerente.getNm_email());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idGerente = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}
		return idGerente;
	}

	public int salvarEstabelecimentoBanco(Estabelecimento estabelecimento) {

		int idEstabelecimento = 0;
		int idFKEndereco;
		int idFKTelefone;
		try {
			this.conectasgbd();
			idFKEndereco = this.salvarEnderecoBanco(estabelecimento.getEndereco());
			idFKTelefone = this.salvarTelefoneBanco(estabelecimento.getTelefone());
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ESTABELECIMENTO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setString(1, estabelecimento.getCnpj());
			prepared.setInt(2, idFKEndereco);
			prepared.setInt(3, idFKTelefone);
			prepared.setInt(4, estabelecimento.getCidade().getId());
			prepared.setString(5, estabelecimento.getNome());
			prepared.setString(6, estabelecimento.getEmail());
			prepared.setString(7, estabelecimento.getTp_estabelecimento());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idEstabelecimento = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idEstabelecimento;
	}

	public int salvarLoteBanco(Lote lote) {
		int idLote = 0;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_LOTE, Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, lote.getTipo().getId());
			prepared.setInt(2, lote.getEstabelecimento().getId());
			prepared.setInt(3, lote.getNu_peso());
			prepared.setInt(4, lote.getNu_quantidade());
			prepared.setDate(5, lote.getDt_validade());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idLote = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idLote;

	}

	public int salvarPedidoBanco(Pedido pedido) {
		int idPedido = 0;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, pedido.getViagem().getId());
			prepared.setInt(2, pedido.getLote().getId());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idPedido = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idPedido;
	}

	public int salvarRotaBanco(Rota rota) {
		int idRota = 0;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_ROTA, Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, rota.getCaminhoneiro().getId());
			prepared.setString(2, rota.getNm_linha());
			prepared.setDouble(3, rota.getNu_distancia());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idRota = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idRota;
	}

	public int salvarTabelaPrecoBanco(TabelaPreco tabelapreco) {
		int idTabelaPreco = 0;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_TABELA_PRECO,
					Statement.RETURN_GENERATED_KEYS);
			prepared.setInt(1, tabelapreco.getTipo().getId());
			prepared.setInt(2, tabelapreco.getEstabelecimento().getId());
			prepared.setDouble(3, tabelapreco.getNu_preco());
			prepared.setDouble(4, tabelapreco.getNu_peso());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idTabelaPreco = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idTabelaPreco;
	}

	public int salvarViagemBanco(Viagem viagem) {
		int idViagem = 0;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(INSERT_VIAGEM, Statement.RETURN_GENERATED_KEYS);
			prepared.setTimestamp(1, viagem.getHora());
			prepared.setDate(2, viagem.getDt_chegada());
			prepared.setDate(3, viagem.getDt_partida());
			prepared.setInt(4, viagem.getRota().getId());
			prepared.setInt(5, viagem.getAjudante().getId());
			prepared.setInt(6, viagem.getCaminhao().getId());
			prepared.setDouble(7, viagem.getNu_carga());
			prepared.setString(8, viagem.getTp_viagem());
			prepared.setDouble(9, viagem.getVl_custo());
			prepared.executeUpdate();
			ResultSet set = prepared.getGeneratedKeys();
			if (set.next()) {
				idViagem = set.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return idViagem;
	}

	public List<Ajudante> recuperarAjudanteParaComboBoxBanco() {
		List<Ajudante> listaAjudantes = new ArrayList<Ajudante>();
		Ajudante ajudante;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_AJUDANTE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				ajudante = new Ajudante();
				ajudante.setId(resultSet.getInt("id"));
				ajudante.setNm_pessoa(resultSet.getString("nm_pessoa"));
				listaAjudantes.add(ajudante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaAjudantes;
	}

	public List<Cidade> recuperarCidadeParaComboBoxBanco() {
		List<Cidade> listaCidades = new ArrayList<Cidade>();
		Cidade cidade;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_CIDADE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				cidade = new Cidade();
				cidade.setId(resultSet.getInt("id"));
				cidade.setNome(resultSet.getString("nome"));
				listaCidades.add(cidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaCidades;
	}

	public List<Caminhoneiro> recuperarCaminhoneiroParaComboBoxBanco() {
		List<Caminhoneiro> listaCaminhoneiros = new ArrayList<Caminhoneiro>();
		Caminhoneiro caminhoneiro;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_CAMINHONEIRO);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				caminhoneiro = new Caminhoneiro();
				caminhoneiro.setId(resultSet.getInt("id"));
				caminhoneiro.setNm_pessoa(resultSet.getString("nm_pessoa"));
				listaCaminhoneiros.add(caminhoneiro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaCaminhoneiros;
	}

	public List<Caminhao> recuperarCaminhaoParaComboBoxBanco() {
		List<Caminhao> listaCaminhoes = new ArrayList<Caminhao>();
		Caminhao caminhao;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_CAMINHAO);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				caminhao = new Caminhao();
				caminhao.setId(resultSet.getInt("id"));
				caminhao.setNu_placa(resultSet.getString("nu_placa"));
				listaCaminhoes.add(caminhao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaCaminhoes;
	}

	public List<Gerente> recuperarGerenteParaComboBoxBanco() {
		List<Gerente> listaGerentes = new ArrayList<Gerente>();
		Gerente gerente;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_GERENTE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				gerente = new Gerente();
				gerente.setId(resultSet.getInt("id"));
				gerente.setNm_pessoa(resultSet.getString("nm_pessoa"));
				listaGerentes.add(gerente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaGerentes;
	}

	public List<Estabelecimento> recuperarEstabelecimentoParaComboBoxBanco() {
		List<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
		Estabelecimento estabelecimento;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ESTABELECIMENTO);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				estabelecimento = new Estabelecimento();
				estabelecimento.setId(resultSet.getInt("id"));
				estabelecimento.setNome(resultSet.getString("nome"));
				listaEstabelecimentos.add(estabelecimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaEstabelecimentos;
	}

	public List<Rota> recuperarRotaParaComboBoxBanco() {
		List<Rota> listaRotas = new ArrayList<Rota>();
		Rota rota;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_ROTA);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				rota = new Rota();
				rota.setId(resultSet.getInt("id"));
				rota.setNm_linha(resultSet.getString("nm_linha"));
				listaRotas.add(rota);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaRotas;
	}

	public List<Viagem> recuperarViagemParaComboBoxBanco() {
		List<Viagem> listaViagens = new ArrayList<Viagem>();
		Viagem viagem;
		Rota rota;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_VIAGEM);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				viagem = new Viagem();
				rota = new Rota();
				viagem.setId(resultSet.getInt("id"));
				viagem.setTp_viagem(resultSet.getString("tipoviagem"));
				rota.setNm_linha(resultSet.getString("nomelinha"));
				viagem.setDt_partida(resultSet.getDate("dt_partida"));
				viagem.setRota(rota);
				listaViagens.add(viagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaViagens;
	}

	public List<Lote> recuperarLoteParaComboBoxBanco() {
		List<Lote> listaLotes = new ArrayList<Lote>();
		Lote lote;
		Estabelecimento estabelecimento;
		Tipo tipo;
		Fruta fruta;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_LOTE);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				lote = new Lote();
				estabelecimento = new Estabelecimento();
				tipo = new Tipo();
				fruta = new Fruta();
				estabelecimento.setNome(resultSet.getString("nomeestab"));
				lote.setEstabelecimento(estabelecimento);
				fruta.setNome(resultSet.getString("nomefruta"));
				tipo.setFruta(fruta);
				tipo.setNome(resultSet.getString("nometipo"));
				lote.setNu_quantidade(resultSet.getInt("nuquantidade"));
				lote.setTipo(tipo);
				listaLotes.add(lote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaLotes;
	}

	public List<Tipo> recuperarTipoFrutaParaComboBoxBanco() {
		List<Tipo> listaTipos = new ArrayList<Tipo>();
		Tipo tipo;
		Fruta fruta;
		try {
			this.conectasgbd();
			PreparedStatement prepared = this.conexao.prepareStatement(SELECT_TIPOFRUTA);
			ResultSet resultSet = prepared.executeQuery();
			while (resultSet.next()) {
				tipo = new Tipo();
				fruta = new Fruta();
				tipo.setId(resultSet.getInt("id"));
				tipo.setNome(resultSet.getString("nometipo"));
				fruta.setNome(resultSet.getString("nomefruta"));
				tipo.setFruta(fruta);
				listaTipos.add(tipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnnection(conexao);
		}

		return listaTipos;
	}
}
