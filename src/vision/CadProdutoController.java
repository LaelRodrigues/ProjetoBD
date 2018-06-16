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

public class CadProdutoController {

	
	@FXML
	private Label error;
	
	@FXML
	private TextField codigo;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField descricao;
	
	@FXML
	private TextField preco;
	
	@FXML
	private ChoiceBox<String> cnpjForn = new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> vdia = new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> vmes = new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> vano = new ChoiceBox<String>();

	@FXML
	private Pane background;
	
	@FXML
	private Pane logo;
	//private User user = new User();
	
	@FXML
	public void initialize() {
		cnpjForn.getItems().addAll("CNPJ 1", "CNPJ 2", "CNPJ 3", "CNPJ 4");
	}
	
	@FXML
	public void handlerPreencheCB(){
	}
	
	@FXML
	public void handlerCadastrarProduto(){
		
		User user = new User();
		
		user.setNome(nome.getText());
		user.setSenha("");
		user.setCargo("");
		user.setId(1.0);
		
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
	
	/*public void recebeIdPergunta(long id){
		idPerguntaRecebida = id;
		System.out.println("ESTAMOS RECEBENDO NO CONTROLLER COM O ID: "+idPerguntaRecebida);
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDCADPERG);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}*/
	
}
