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
	public void handlerCadastrarFornecedor() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.ICADFORN);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handlerCadastrarTransportadora() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDCADTRANSP);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void handlerCadastrarLoja() {
		
	}
}
