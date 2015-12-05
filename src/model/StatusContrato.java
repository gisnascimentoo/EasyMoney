package model;

public enum StatusContrato {

	APROVADO ("Aprovado"), REJEITADO ("Rejeitado"), PRÉ_REJEITADO ("Pré-rejeitado"), PRÉ_APROVADO ("Pré-aprovado"), ENCERRADO ("Encerrado");
	
	private final String name;
	
    private StatusContrato (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
