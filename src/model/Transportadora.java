package model;

public class Transportadora {
	
	private String cnpj;
	private String nome;
	private String email;
	private String cepTransportadora;
	
	public Transportadora() {}
	
	public Transportadora(String cnpj, String nome, String email, String cepTransportadora) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.cepTransportadora = cepTransportadora;
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getcepTransportadora() {
		return cepTransportadora;
	}
	public void setCepTransportadora(String cepTransportadora) {
		this.cepTransportadora = cepTransportadora;
	}
	
	
	
}