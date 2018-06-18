package model;

public class Telefone {
	
	private String telefone;
	private String cnpj;
	
	public Telefone() {
		this.telefone = null;
		this.cnpj = null;
	}
	
	public Telefone(String telefone, String cnpj) {
		super();
		this.telefone = telefone;
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
	
}
