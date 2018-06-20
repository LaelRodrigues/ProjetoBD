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
import model.Pedido;
import model.PedidoPossuiProdutos;
import model.Possui;
import model.Transportadora;
import services.EmEstoqueService;
import services.LojaEstoqueProdutoService;
import services.LojaService;
import services.PedidoPossuiProdutoService;
import services.TransportadoraService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class FazerPedidoController {

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

	@FXML
	private ChoiceBox<String> choiceBoxListaTransportadoras = new ChoiceBox<>();

	private boolean canNext;
	private boolean canPrev;
	private int nPagina = 1;
	private int lNumber = 10;
	private List<Loja> listaLojas = new ArrayList<Loja>();
	private List<Transportadora> listaTransports = new ArrayList<Transportadora>();
	private List<LojaXEmEstoqueXProduto> listaEstoqueLoja = new ArrayList<LojaXEmEstoqueXProduto>();
	private List<PedidoPossuiProdutos> listaProdutosPedido = new ArrayList<PedidoPossuiProdutos>();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private int listSize;

	public List<String> preecheChoiceBoxLoja() { // método para deixar o
													// choicebox mais legível
													// para o usuário
		listaLojas = LojaService.getFullList();
		List<String> nomeEcnpjLojas = new ArrayList<>();
		for (int i = 0; i < listaLojas.size(); i++) {
			nomeEcnpjLojas.add(listaLojas.get(i).getCnpj() + " - " + listaLojas.get(i).getNome());
		}
		return nomeEcnpjLojas;
	}

	public List<String> preecheChoiceBoxTransporters() { // método para deixar o
		// choicebox mais legível
		// para o usuário
		listaTransports = TransportadoraService.getFullList();
		List<String> nomeEcnpjTrans = new ArrayList<>();
		for (int i = 0; i < listaTransports.size(); i++) {
			nomeEcnpjTrans.add(listaTransports.get(i).getCnpj() + " - " + listaTransports.get(i).getNome());
		}
		return nomeEcnpjTrans;
	}

	@FXML
	public void initialize() {

		choiceBoxListaLojas.getItems().clear();
		choiceBoxListaTransportadoras.getItems().clear();
		choiceBoxListaTransportadoras.getItems().addAll(preecheChoiceBoxTransporters());
		choiceBoxListaLojas.getItems().addAll(preecheChoiceBoxLoja()); // inserindo
																		// o
																		// nome
																		// das
																		// lojas
																		// no
																		// choicebox
																		// para
																		// seleção

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
			// ?? não tem nada pra fazer aqui
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

				final Button adicionarCarrinho = new Button("Adicionar ao Carrinho");
				Label productCode = new Label(listaEstoqueLoja.get(i + ((nPagina - 1) * lNumber)).getCodigo()
						+ " - Nome do Produto : " + listaEstoqueLoja.get(i + ((nPagina - 1) * lNumber)).getNomeProd()
						+ " - Quantidade :" + listaEstoqueLoja.get(i + ((nPagina - 1) * lNumber)).getQuantidade());

				Pane tuple = new Pane();
				final int idPagina = i + ((nPagina - 1) * lNumber);

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");

				tuple.getChildren().add(productCode);
				tuple.getChildren().add(adicionarCarrinho);

				productCode.setLayoutX(50);
				adicionarCarrinho.setLayoutX(550);

				productCode.setLayoutY(15);
				adicionarCarrinho.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);

				adicionarCarrinho.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						TextField space = new TextField(listaEstoqueLoja.get(idPagina).getQuantidade() + "");
						space.setLayoutX(700);
						space.setLayoutY(15);

						Button quantidade = new Button("Adicionar quantidade");
						quantidade.setLayoutX(850);
						quantidade.setLayoutY(15);

						quantidade.setOnAction(new EventHandler<ActionEvent>() {
							public void handle(ActionEvent e) {

								Possui novo = new Possui();
								// novo.setIdPedido(listaEstoqueLoja.get(idPagina));
								novo.setCodigo(listaEstoqueLoja.get(idPagina).getCodigo());
								novo.setQuantidade(Integer.parseInt(space.getText()));
								try {
									PedidoPossuiProdutoService.buscarId(idPagina);
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
						tuple.getChildren().add(quantidade);
					};
				});

				panesTuple.add(tuple);
				buts.add(adicionarCarrinho);

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
				listaEstoqueLoja = LojaEstoqueProdutoService.buscarcnpj(compair[0]);
				listSize = listaEstoqueLoja.size();
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
