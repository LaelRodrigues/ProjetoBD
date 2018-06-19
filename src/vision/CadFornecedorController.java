package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Endereco;
import model.Fornecedor;
import services.EnderecoService;
import services.FornecedorService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class CadFornecedorController {

	@FXML
	private Label error;

	@FXML
	private TextField nome;

	@FXML
	private TextField telefone;

	@FXML
	private TextField cnpj;

	@FXML
	private TextField email;

	@FXML
	private TextField cep;
	
	@FXML
	private TextField uf;
	
	@FXML
	private TextField cidade;
	
	@FXML
	private TextField bairro;
	
	@FXML
	private TextField logradouro;
	
	

	@FXML
	public void initialize() {
	}

	@FXML
	public void handlerCadastrarFornecedor() {
		Endereco novoend = new Endereco();
		Fornecedor novo = new Fornecedor();
		
		novoend.setCep(cep.getText());
		novoend.setUf(uf.getText());
		novoend.setCidade(cidade.getText());
		novoend.setBairro(bairro.getText());
		novoend.setLogradouro(logradouro.getText());
		
		try {
			EnderecoService.cadastrar(novoend);
		} catch (Exception e) {
			error.setText("incapaz de cadastrar endereço");
			System.out.println("incapaz de cadastrar endereço " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		novo.setCnpj(cnpj.getText());
		novo.setNome(nome.getText());
		novo.setEmail(email.getText());
		novo.setCepFornecedor(cep.getText());

		try {
			try {
				FornecedorService.cadastrar(novo);
			} catch (Exception e) {
				EnderecoService.deletar(novoend);
				error.setText("incapaz de cadastrar fornecedor");
				System.out.println("incapaz de cadastrar fornecedor");
			}
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handlerVoltar() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
