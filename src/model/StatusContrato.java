package model;

public enum StatusContrato {

	APROVADO ("Aprovado"), REJEITADO ("Rejeitado"), PRE_REJEITADO ("Pre-rejeitado"), PRE_APROVADO ("Pre-aprovado"), ENCERRADO ("Encerrado");
	
	private final String name;
	
    private StatusContrato (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
