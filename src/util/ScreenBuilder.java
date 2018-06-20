package util;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScreenBuilder {
	
	//para criar as telas � preciso criar um metodo no setor ali em baixo

	public static String path;
	public static String title;
	private static AnchorPane pane;
	private static Stage primaryStage;

	// carrega o pane e o stage
	public static void load_stage(AnchorPane paneout, Stage primaryStageout) {
		pane = paneout;
		primaryStage = primaryStageout;
		
	}

	// -----------------------------------constroi as telas
	public static void buildScreen() {
		pane = (AnchorPane) SceneBuilder.getComponent(path);
		primaryStage.setTitle(title);

		primaryStage.setMaximized(true);
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(pane));

		primaryStage.show();

	}

	// ------------------------------ cria as telas apartir daqui --------------------------------------//
	//usem uma assinatura parecida !!
	//public static void renderNomeDaTelaQVcsQuiserem();
	//blz, mas cheguei at� aqui e n sei como trocar de tela...
	//� SOMENTE CHAMAR O ScreenLibrary.loadTela(id da tela);
	//sim, � s� isso, chama o metodo estatico la em qualquer parte do controller
	//se eu n caguei nada na refatora��o ele deve trocar automaticamente de tela
	//PELO AMOR DE DEUS, n precisa declarar variavel tipo ScreenLibrary no controller(nem funcionaria)
	//afinal quem renderiza sao metodos estaticos!!!
	
	
	public static void renderLoginScreen() {

		path = "/vision/Login_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Login"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderHomePage() {

		path = "/vision/HomePage_Screen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Home"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroProduto() {

		path = "/vision/CadProdutoScreen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastro de Produto"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroFornecedor() {

		path = "/vision/CadFornecedorScreen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastro de Fornecedor"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroTransportadora() {

		path = "/vision/CadTransportadoraScreen.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastro de Transportadora"; // nome q aparece no topo da tela
		buildScreen();

	}
	
	public static void renderCadastroLoja() {

		path = "/vision/CadLoja.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Cadastrod de loja"; // nome q aparece no topo da tela
		buildScreen();

	}

	public static void renderEditProduto() {

		path = "/vision/EditProdutoSelect.fxml"; // arquivo fxml q vcs v�o abrir la no screnebuilder (o programa)
		title = "Editar Produto"; // nome q aparece no topo da tela
		buildScreen();
		
	}

}
