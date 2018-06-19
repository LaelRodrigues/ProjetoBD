package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Endereco;
import model.Loja;
import services.EnderecoService;
import services.LojaService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class CadLojaController {

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
	// private User user = new User();

	@FXML
	public void initialize() {

	}

	@FXML
	public void handlerCadastraLoja() {

		Endereco novoend = new Endereco();
		Loja loja = new Loja();

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

		loja.setCnpj(cnpj.getText());
		loja.setNome(nome.getText());
		loja.setEmail(email.getText());
		loja.setCepLoja(cep.getText());

		try {
			try {
				LojaService.cadastrar(loja);
			} catch (Exception e) {
				EnderecoService.deletar(novoend);
				error.setText("incapaz de cadastrar Loja");
				System.out.println("incapaz de cadastrar Loja");
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
