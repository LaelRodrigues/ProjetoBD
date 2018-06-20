package model;

public class FornecedorXEndereco {

	private String cnpjForne;
	private String nome;
	private String email;
	private String cepForne;
	private String uf;
	private String cidade;
	private String bairro;
	private String logradouro;
	
	public String getCnpjForne() {
		return cnpjForne;
	}

	public void setCnpjForne(String cnpjForne) {
		this.cnpjForne = cnpjForne;
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

	public String getCepForne() {
		return cepForne;
	}

	public void setCepForne(String cepForne) {
		this.cepForne = cepForne;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
}
