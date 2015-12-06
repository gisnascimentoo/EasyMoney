package model;
//DEFINE OS LIMITES SUPERIORES PARA CADA PERFIL DE CLIENTE 
public enum PerfilCliente {

	 PERFIL_A_SUP (1500), 
	 PERFIL_B_INF (1500.01),
	 PERFIL_B_SUP (5000),
	 PERFIL_C_INF (5000.01),
	 PERFIL_C_SUP (10000),
	 PERFIL_D_INF (10000.01);
	
	private final double perfil;
	
    private PerfilCliente (double perfil) {
        this.perfil = perfil;
    }

    public double getName() {
        return perfil;
    }
}
