package model;

public enum PerfilCliente {
	PERFIL_A("Perfil A"), PERFIL_B("Perfil B"), PERFIL_C("Perfil C"), PERFIL_D("Perfil D");
	
	private final String nomePerfil;
	
    private PerfilCliente(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getName() {
        return nomePerfil;
    }
}
