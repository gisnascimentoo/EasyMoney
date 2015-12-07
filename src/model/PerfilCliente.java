package model;

public enum PerfilCliente {
	//Nome, Teto valor, Parcelas Min, Parcelas Max
	
	PERFIL_A("Perfil A", 1500), 
	PERFIL_B("Perfil B", 5000), 
	PERFIL_C("Perfil C", 10000), 
	PERFIL_D("Perfil D", Double.MAX_VALUE);
	
	private final String nomePerfil;
	private final double valorMaximo;
	
    private PerfilCliente(String nomePerfil, double valorMaximo) {
        this.nomePerfil = nomePerfil;
        this.valorMaximo = valorMaximo;
    }

    public String getName() {
        return nomePerfil;
    }
    
    public double getValorMaximo(){
    	return valorMaximo;
    }
  }