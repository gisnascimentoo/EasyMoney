package view.combo;

public class ParcelasCombo {

	int codigo;
	int parcela;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getParcela() {
		return parcela;
	}

	public void setParcela(int parcela) {
		this.parcela = parcela;
	}

	public ParcelasCombo(int codigo, int parcela) {
		super();
		this.codigo = codigo;
		this.parcela = parcela;
	}

}
