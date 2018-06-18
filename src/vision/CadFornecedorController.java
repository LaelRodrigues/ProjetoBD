package vision;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Endereco;
import model.Fornecedor;
import model.User;
import services.EnderecoService;
import services.FornecedorServices;
import services.UserServices;
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
	private Pane background;

	@FXML
	private Pane logo;
	// private User user = new User();

	@FXML
	private Pane cadPane;

	@FXML
	public void initialize() {

	}

	@FXML
	public void CadastroHandler() throws ParseException {

		cadPane.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");
		cadPane.setVisible(true);

		final TextField cep = new TextField();
		final TextField uf = new TextField();
		final TextField cidade = new TextField();
		final TextField bairro = new TextField();
		final TextField logradouro = new TextField();

		Button cadastrar, cancelar;
		Label text;

		text = new Label("Cadastro de fornecerdor");

		cadastrar = new Button("cadastrar");
		cancelar = new Button("cancelar");

		cep.setPromptText("cep*");
		uf.setPromptText("uf*");
		cidade.setPromptText("cidade*");
		bairro.setPromptText("bairro*");
		logradouro.setPromptText("logradouro*");

		text.setFont(Font.font("arial", FontWeight.BOLD, 20));

		text.setLayoutX(250);
		text.setLayoutY(50);

		cep.setLayoutX(25);
		cep.setLayoutY(125);
		cep.setPrefSize(650, 10);

		uf.setLayoutX(25);
		uf.setLayoutY(165);
		uf.setPrefSize(650, 10);

		cidade.setLayoutX(25);
		cidade.setLayoutY(205);
		cidade.setPrefSize(650, 10);

		bairro.setLayoutX(25);
		bairro.setLayoutY(245);
		bairro.setPrefSize(650, 10);

		logradouro.setLayoutX(25);
		logradouro.setLayoutY(285);
		logradouro.setPrefSize(650, 10);

		error.setLayoutX(100);
		error.setLayoutY(600);

		cadastrar.setLayoutX(25);
		cadastrar.setLayoutY(365);
		cadastrar.setPrefSize(320, 10);

		cancelar.setLayoutX(355);
		cancelar.setLayoutY(365);
		cancelar.setPrefSize(320, 10);

		cadPane.getChildren().add(text);
		cadPane.getChildren().add(cep);
		cadPane.getChildren().add(uf);
		cadPane.getChildren().add(cidade);
		cadPane.getChildren().add(bairro);
		cadPane.getChildren().add(logradouro);
		cadPane.getChildren().add(cadastrar);
		cadPane.getChildren().add(cancelar);

		cadPane.setLayoutX(185);
		cadPane.setLayoutY(-400);
		cadPane.setPrefSize(700, 600);

		cadastrar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				Endereco novo = new Endereco();
				novo.setCep(cep.getText());
				novo.setUf(uf.getText());
				novo.setCidade(cidade.getText());
				novo.setBairro(bairro.getText());
				novo.setLogradouro(logradouro.getText());

				try {
					EnderecoService.cadastrar(novo);
					error.setTextFill(Color.GREEN);
					error.setText("Cadastro realizado com sucesso");
				} catch (Exception ee) {
					error.setTextFill(Color.RED);
					error.setText("Impossivel cadastrar endereço: " + ee.getMessage());
				}
				cadPane.setVisible(false);
				return;
			}
		});
		
		cancelar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				cadPane.setVisible(false);
				return;
			}
		});
	}

	@FXML
	public void handlerPreencheCB() {
	}

	@FXML
	public void handlerCadastrarFornecedor() {
		Fornecedor novo = new Fornecedor();

		novo.setCnpj(cnpj.getText());
		novo.setNome(nome.getText());
		novo.setEmail(email.getText());
		novo.setCepFornecedor(cep.getText());

		try {
			try {
				FornecedorServices.cadastrar(novo);
			} catch (Exception e) {
				System.out.println("incapaz de cadastrar");
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
