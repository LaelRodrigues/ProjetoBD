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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import services.UserServices;
import util.SceneBuilder;
import util.ScreenConstants;
import util.SourcesLoader;

public class Login_ScreenController {

	@FXML
	private Button login;

	@FXML
	private Button cad;

	@FXML
	private TextField user;

	@FXML
	private PasswordField pass;

	@FXML
	private Pane logicPane;

	@FXML
	private Pane cadPane;

	@FXML
	private Pane background;

	@FXML
	private Pane logo;

	@FXML
	private AnchorPane masterPane;

	@FXML
	private Label error;

	// private User remote = new User();

	@FXML
	public void initialize() {
		SourcesLoader.loadBackground(background);
		SourcesLoader.loadLogo(logo);

		logicPane.setLayoutX(533);
		logicPane.setLayoutY(174);
	}

	@FXML
	public void LoginHandler() throws UnsupportedEncodingException {

		if (UserServices.login(user.getText(), pass.getText()))
			SceneBuilder.LoadScreen(ScreenConstants.IDHOME);
		else {
			error.setTextFill(Color.RED);
			error.setText("usuario ou senha incorreto");
		}

	}

	@FXML
	public void CadastroHandler() throws ParseException {

		cadPane.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");
		cadPane.setVisible(true);

		final TextField nomeT;
		final PasswordField senhaT;
		final ChoiceBox<String> tipo;

		Button cadastrar, cancelar;
		Label text;

		text = new Label("Cadastro de usuario");
		nomeT = new TextField();
		senhaT = new PasswordField();

		cadastrar = new Button("cadastrar");
		cancelar = new Button("cancelar");

		nomeT.setPromptText("Nome*");
		senhaT.setPromptText("Senha*");
		tipo = new ChoiceBox<String>(FXCollections.observableArrayList("Supervisor", "Gerente", "Admin"));
		text.setFont(Font.font("arial", FontWeight.BOLD, 20));

		text.setLayoutX(250);
		text.setLayoutY(50);

		nomeT.setLayoutX(25);
		nomeT.setLayoutY(125);
		nomeT.setPrefSize(650, 10);

		senhaT.setLayoutX(25);
		senhaT.setLayoutY(165);
		senhaT.setPrefSize(650, 10);

		tipo.setLayoutX(25);
		tipo.setLayoutY(205);
		tipo.setPrefSize(650, 10);

		error.setLayoutX(100);
		error.setLayoutY(600);

		cadastrar.setLayoutX(25);
		cadastrar.setLayoutY(365);
		cadastrar.setPrefSize(320, 10);

		cancelar.setLayoutX(355);
		cancelar.setLayoutY(365);
		cancelar.setPrefSize(320, 10);

		cadPane.getChildren().add(text);
		cadPane.getChildren().add(nomeT);
		cadPane.getChildren().add(senhaT);
		cadPane.getChildren().add(tipo);
		cadPane.getChildren().add(cadastrar);
		cadPane.getChildren().add(cancelar);

		cadPane.setLayoutX(185);
		cadPane.setLayoutY(-400);
		cadPane.setPrefSize(700, 600);
		
		cadastrar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				User novo = new User();
				novo.setNome(nomeT.getText());
				novo.setSenha(senhaT.getText());
				novo.setCargo(tipo.getValue());

				try {
					UserServices.cadastrar(novo);
					error.setTextFill(Color.GREEN);
					error.setText("Cadastro realizado com sucesso");
				} catch (Exception ee) {
					error.setTextFill(Color.RED);
					error.setText("Impossivel criar nova conta: " + ee.getMessage());
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
}
