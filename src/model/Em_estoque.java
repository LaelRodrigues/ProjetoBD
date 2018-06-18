package model;

public class Em_estoque {
	
	private String cnpjL;
	private Integer codicoP;
	private Integer quantidade;
	
	public Em_estoque() {
		this.cnpjL = null;
		this.codicoP = null;
		this.quantidade = null;
	}
	
	public Em_estoque(String cnpjL, Integer codicoP, Integer quantidade) {
		super();
		this.cnpjL = cnpjL;
		this.codicoP = codicoP;
		this.quantidade = quantidade;
	}

	public String getCnpjL() {
		return cnpjL;
	}
	public void setCnpjL(String cnpjL) {
		this.cnpjL = cnpjL;
	}
	public Integer getCodicoP() {
		return codicoP;
	}
	public void setCodicoP(Integer codicoP) {
		this.codicoP = codicoP;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
}
