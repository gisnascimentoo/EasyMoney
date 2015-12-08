package control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Cliente;
import model.Contrato;
import model.PerfilCliente;
import model.PlanoEmprestimo;
import model.StatusContrato;
import db.ManipuladorBanco;

public class ContratoController {
	
	ManipuladorBanco db;
	Cliente cl;
	
	public ContratoController() {
		this.db = new ManipuladorBanco();
	}
	
	//DEVE SER ACIONADO QUANDO UM VALOR NO COMBO CLIENTE FOR SELECIONADO
	public void localizaCliente(int idCliente)
	{
		//Recebe o cliente
		cl = (Cliente) db.buscarCliente(idCliente, null, null, null);
		if (cl != null){
			//Repassa as infos para a tela
			//TODO
		}else{
			//retorna erro cliente n�o encontrado
			//TODO
		}
	}
	
	
	public PerfilCliente recuperaPerfilCliente(double renda)
	{
		//Verifica se a renda esta no perfil A
		if (renda <= PerfilCliente.PERFIL_A.getValorMaximo()){
			return PerfilCliente.PERFIL_A;
		}else
			//Verifica se a renda esta no perfil B
		{ if (renda <= PerfilCliente.PERFIL_B.getValorMaximo()){
			return PerfilCliente.PERFIL_B;
		} else {
			//Verifica se a renda esta no perfil C
			if (renda <= PerfilCliente.PERFIL_C.getValorMaximo()){
				return PerfilCliente.PERFIL_C;
			}else{
				//Se a rendar n�o estiver em nenhum dos outros, ela � do tipo D
				return PerfilCliente.PERFIL_D;
			}
		}}
	}
	
	public List<PlanoEmprestimo> recuperaPlanosPerfil(PerfilCliente perfilCliente)
	{
		return db.buscarPlanoEmprestimoPorPerfil(perfilCliente); 
	}
	
	public List<PlanoEmprestimo> recuperaPlanos(){
		return db.buscarPlanoEmprestimo();
	}
	
	public PlanoEmprestimo recuperaPlanoEmprestimo(int idPlanoEmprestimo){
		return db.buscarPlanoEmprestimoId(idPlanoEmprestimo);
	}
	
	//Retorna se o perfil do cliente � pr�-aprovado ou pr�-rejeitado
	public boolean analisaPerfilComPlano(PlanoEmprestimo planoSelecionado){
		PerfilCliente pfc = recuperaPerfilCliente(cl.getDadosFinanceiros().getRendaPessoal());
		List<PlanoEmprestimo> lPlanosPossiveis = recuperaPlanosPerfil(pfc);
		boolean aprovado = false;
		for (int i = 0; i < lPlanosPossiveis.size(); i++){
			if (planoSelecionado.getNome().equals(lPlanosPossiveis.get(i).getNome())){
				aprovado = true;
			}
		}
		return aprovado;
	}  
	
	//Calcula o valor das parcelas, aplicando os dados de juros
	public double calculaValorParcelas(int numeroParcelas, double valorEmprestimo, PlanoEmprestimo plano){
		return ((valorEmprestimo*(1+plano.getJurosTotal()))/(1-Math.pow((1+ plano.getJurosMensal()), -numeroParcelas))/plano.getJurosMensal());
		
	}
	
	//Calcula a data de termino do emprestimo
	public Date calculaDataTermino(int numeroParcelas){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, numeroParcelas);
		return cal.getTime();
	}
	
	//SALVA ou edita o contrato
	public boolean enviarContrato(Integer codContrato, int idCliente, String status, int idPlanoEmprestimo, int numParcelas, double valorEmprestimo, double valorParcelas, Date dataTermino, String observacoes)
	{
		PlanoEmprestimo plEmprestimo = recuperaPlanoEmprestimo(idPlanoEmprestimo);
		
		Cliente clt = (Cliente) db.buscarCliente(idCliente, null, null, null);
		
		boolean persistidoSucesso = false;
		
		//CONTRATO NOVO!
		if (codContrato == null){
			
			//Refaz a analise caso o status esteja como pre_aprovado ou pre_rejeitado
			if (analisaPerfilComPlano(plEmprestimo)&&
					((status == null)||(status == StatusContrato.PRE_APROVADO.getName())||(status == StatusContrato.PRE_REJEITADO.getName())))
			{
				status = StatusContrato.PRE_APROVADO.getName(); 
			} else {
				status = StatusContrato.PRE_REJEITADO.getName();
			}	
			
			Contrato contrato = new Contrato(numParcelas, valorEmprestimo, valorParcelas, (java.sql.Date) new Date(), (java.sql.Date) dataTermino, clt, status, null, plEmprestimo, observacoes);
			Integer idCont = db.salvarContratoBanco(contrato);
			if (idCont != null){
				persistidoSucesso = true;
			}

		//EDICAOO DE CONTRATO
		} else {
			
			if (analisaPerfilComPlano(plEmprestimo))
			{
				status = StatusContrato.PRE_APROVADO.getName(); 
			} else {
				status = StatusContrato.PRE_REJEITADO.getName();
			}	
			
			Contrato contrato = db.buscarContratoId(codContrato);
			contrato.setCliente(clt);
			contrato.setDataTerminoContrato((java.sql.Date) dataTermino);
			contrato.setQntdParcelas(numParcelas);
			contrato.setValorEmprestimo(valorEmprestimo);
			contrato.setValorParcelas(valorParcelas);
			contrato.setPlanoEmprestimo(plEmprestimo);
			contrato.setStatusContrato(status);
			contrato.setFuncionarioResponsavel(null);
			contrato.setPlanoEmprestimo(plEmprestimo);
			contrato.setObservacoes(observacoes);
			db.editarContratoBanco(contrato);
			persistidoSucesso = true;
		}
		return persistidoSucesso;		
	}
	
	public void verificaCamposObrigatoriosContrato(){
		
	}
	
	
}
