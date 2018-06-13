package model;

public class Fornecedor {
	
	private String cnpj;
	private String nome;
	private String email;
	private String cepFornecedor;
	
	public Fornecedor() {}
	
	public Fornecedor(String cnpj, String nome, String email, String cepFornecedor) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.cepFornecedor = cepFornecedor;
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
	public String getcepFornecedor() {
		return cepFornecedor;
	}
	public void setCepFornecedor(String cepFornecedor) {
		this.cepFornecedor = cepFornecedor;
	}
	
	
	
}