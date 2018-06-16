package vision;

import java.io.UnsupportedEncodingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import util.ScreenConstants;
import util.ScreenLibrary;

public class HomePage_ScreenController {

	@FXML
	private Pane backPane;

	@FXML
	private Button novaPergunta;

	@FXML
	private Button listaPergunta;

	@FXML
	public void initialize() {
		
	}

	@FXML
	public void handlerCadastrarProduto() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDCADPROD);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
	}

	@FXML
	public void handlerListaPergunta() {
		
	}
	
	@FXML
	public void handlerCadastrarFornecedor() {
		
	}

	@FXML
	public void handlerContraOTempo() {
		
	}

	@FXML
	public void handlerClassico() {
		
	}

	@FXML
	public void handlerFriends() {
		
	}
}
