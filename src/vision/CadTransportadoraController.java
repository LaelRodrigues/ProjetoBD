package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Endereco;
import model.Transportadora;
import services.EnderecoService;
import services.TransportadoraService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class CadTransportadoraController {

	
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
	private Pane background;
	
	@FXML
	private Pane logo;
	//private User user = new User();
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void handlerPreencheCB(){
	}
	
	@FXML
	public void handlerCadastrarTransportadora(){
		Endereco novoend = new Endereco();
		Transportadora transportadora = new Transportadora();
		
		novoend.setCep(cep.getText());
		novoend.setUf(uf.getText());
		novoend.setCidade(cidade.getText());
		novoend.setBairro(bairro.getText());
		novoend.setLogradouro(logradouro.getText());
		
		try {
			EnderecoService.cadastrar(novoend);
		} catch (Exception e) {
			error.setText("incapaz de cadastrar endere�o");
			System.out.println("incapaz de cadastrar endere�o " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		transportadora.setCnpj(cnpj.getText());
		transportadora.setNome(nome.getText());
		transportadora.setEmail(email.getText());
		transportadora.setCepTransportadora(cep.getText());

		try {
			try {
				TransportadoraService.cadastrar(transportadora);
			} catch (Exception e) {
				EnderecoService.deletar(novoend);
				error.setText("incapaz de cadastrar Transportadora");
				System.out.println("incapaz de cadastrar Transportadora");
			}
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}

	@FXML
	public void handlerVoltar(){
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
