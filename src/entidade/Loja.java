package entidade;

public class Loja {
	
	private String cnpj;
	private String nome;
	private String email;
	private String cepLoja;
	
	public Loja() {}
	
	public Loja(String cnpj, String nome, String email, String cepLoja) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.cepLoja = cepLoja;
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
	public String getCepLoja() {
		return cepLoja;
	}
	public void setCepLoja(String cepLoja) {
		this.cepLoja = cepLoja;
	}
	
	
	
}
