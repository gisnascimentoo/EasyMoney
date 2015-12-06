package model;

public enum StatusContrato {

	APROVADO ("Aprovado"), REJEITADO ("Rejeitado"), PRE_REJEITADO ("Pré-rejeitado"), PRE_APROVADO ("Pré-aprovado"), ENCERRADO ("Encerrado");
	
	private final String name;
	
    private StatusContrato (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
