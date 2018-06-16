package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.User;
import services.UserServices;
import util.ScreenConstants;
import util.ScreenLibrary;

public class CadFornecedorController {

	
	@FXML
	private Label error;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField cnpj;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField cep;

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
	public void handlerCadastrarFornecedor(){
		
		User user = new User();
		
		user.setNome("teste");
		user.setSenha("123");
		user.setCargo("");
		user.setId(34.0);
		
		try {
			UserServices.cadastrar(user);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //enviando o usuário para o UserServices para cadastrar no banco
		
		try {
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
