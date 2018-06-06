package entidade;

public class Loja {
	
	private int cnpj;
	private String nome;
	private String email;
	private int cepLoja;
	
	public Loja(int cnpj, String nome, String email, int cepLoja) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.email = email;
		this.cepLoja = cepLoja;
	}
	
	public int getCnpj() {
		return cnpj;
	}
	public void setCnpj(int cnpj) {
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
	public int getCepLoja() {
		return cepLoja;
	}
	public void setCepLoja(int cepLoja) {
		this.cepLoja = cepLoja;
	}
	
	
	
}
