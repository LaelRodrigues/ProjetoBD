package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
