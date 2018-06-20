package vision;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Em_estoque;
import model.Loja;
import model.LojaXEmEstoqueXProduto;
import services.EmEstoqueService;
import services.LojaEstoqueProdutoService;
import services.LojaService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class ListarQtdProdutoEmEstoqueController {

	private List<Pane> panesTuple = new ArrayList<Pane>();
	private List<Button> buts = new ArrayList<Button>();

	@FXML
	private Pane pane;

	@FXML
	private Button bNext;

	@FXML
	private Button bPrev;

	@FXML
	private ChoiceBox<String> choiceBoxListaLojas = new ChoiceBox<>();

	private boolean canNext;
	private boolean canPrev;
	private int nPagina = 1;
	private int lNumber = 10;
	private List<Loja> listaLojas = new ArrayList<Loja>();
	private List<LojaXEmEstoqueXProduto> listaProdutosEstoque = new ArrayList<LojaXEmEstoqueXProduto>();
	private int listSize;

	public List<String> preecheChoiceBoxLoja() { // m�todo para deixar o
													// choicebox mais leg�vel
													// para o usu�rio
		listaLojas = LojaService.getFullList();
		List<String> nomeEcnpjLojas = new ArrayList<>();
		for (int i = 0; i < listaLojas.size(); i++) {
			nomeEcnpjLojas.add(listaLojas.get(i).getCnpj() + " - " + listaLojas.get(i).getNome());
		}
		return nomeEcnpjLojas;
	}

	@FXML
	public void initialize() {

		choiceBoxListaLojas.getItems().clear();
		choiceBoxListaLojas.getItems().addAll(preecheChoiceBoxLoja()); // inserindo
																		// o
																		// nome
																		// das
																		// lojas
																		// no
																		// choicebox
																		// para
																		// sele��o

		choiceBoxListaLojas.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				handlerBuscar();
			};
		});
		System.out.println(listSize);

	}

	private void loadPage() {

		canNext = false;
		canPrev = false;

		System.out.println("Listsize " + listSize);

		if (listSize == 0) {
			// ?? n�o tem nada pra fazer aqui
		} else {

			int maxSizedList = ((listSize - 1) - (((nPagina - 1) * lNumber) - 1));

			if (nPagina > 1) {
				canPrev = true;
			}
			if (maxSizedList < lNumber) {
				canNext = false;

			} else {
				canNext = true;
				maxSizedList = lNumber;
			}

			for (int i = 0; i < maxSizedList; i++) {

				final Button editar = new Button("Editar");
				Label productCode = new Label(listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getCodigo()
						+ " - Nome do Produto : "
						+ listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getNomeProd() + " - Quantidade :"
						+ listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getQuantidade());

				Pane tuple = new Pane();
				final int id = i + ((nPagina - 1) * lNumber);

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");

				tuple.getChildren().add(productCode);
				tuple.getChildren().add(editar);

				productCode.setLayoutX(50);
				editar.setLayoutX(550);

				productCode.setLayoutY(15);
				editar.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);

				editar.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						TextField space = new TextField(listaProdutosEstoque.get(id).getQuantidade() + "");
						space.setLayoutX(600);
						space.setLayoutY(15);

						Button ok = new Button("Atualizar");
						ok.setLayoutX(750);
						ok.setLayoutY(15);

						ok.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {

								Em_estoque novo = new Em_estoque();
								novo.setCnpjL(listaProdutosEstoque.get(id).getCnpj());
								novo.setCodicoP(listaProdutosEstoque.get(id).getCodigo());
								novo.setQuantidade(Integer.parseInt(space.getText()));
								try {
									EmEstoqueService.atualizar(novo);
								} catch (Exception e1) {
									System.out.println("incapaz de atualizar o estoque");
								}
								// tuple.getChildren().remove(space);
								// tuple.getChildren().remove(ok);
								unloadTuplesOnScreen();
								loadTuplesOnScreen();
								handlerBuscar();

							};
						});

						tuple.getChildren().add(space);
						tuple.getChildren().add(ok);
					};
				});

				panesTuple.add(tuple);
				buts.add(editar);

			}
		}

		bNext.setDisable(!canNext);
		bPrev.setDisable(!canPrev);

		loadTuplesOnScreen();

	}

	private void loadTuplesOnScreen() {

		for (int i = 0; i < panesTuple.size(); i++)
			pane.getChildren().add(panesTuple.get(i));
	}

	private void unloadTuplesOnScreen() {

		for (int i = 0; i < panesTuple.size(); i++)
			pane.getChildren().remove(panesTuple.get(i));
		panesTuple.clear();
	}

	@FXML
	private void handlerBuscar() {
		try {
			unloadTuplesOnScreen();
		} catch (NullPointerException e) {
			System.out.println("affe");
		}

		final String compair[] = choiceBoxListaLojas.getValue().split(" ");
		if (compair[0] != null) {
			try {
				System.out.println("AQUI " + compair[0]);
				listaProdutosEstoque = LojaEstoqueProdutoService.buscarcnpj(compair[0]);
				listSize = listaProdutosEstoque.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
			loadPage();
		}
	}

	@FXML
	private void handlerVoltar() {
		try {
			ScreenLibrary.LoadTela(ScreenConstants.IDHOME);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handlerNextPage() {
		unloadTuplesOnScreen();
		nPagina += 1;
		loadPage();
	}

	@FXML
	private void handlerPrevPage() {
		unloadTuplesOnScreen();
		nPagina -= 1;
		loadPage();
	}

}
