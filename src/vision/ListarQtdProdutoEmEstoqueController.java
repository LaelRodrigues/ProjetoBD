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
import javafx.scene.layout.Pane;
import model.Loja;
import model.LojaXEmEstoqueXProduto;
import model.Produto;
import services.LojaEstoqueProdutoService;
import services.LojaService;
import services.ProdutoService;
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
	private Button buscar;
	
	@FXML
	private ChoiceBox<String> choiceBoxListaLojas = new ChoiceBox<>();
	
	
	private boolean canNext;
	private boolean canPrev;
	private int nPagina = 1;
	private int lNumber = 10;
	private List<Produto> listProdutos = new ArrayList<Produto>();
	private List<Loja> listaLojas = new ArrayList<Loja>();
	private List<LojaXEmEstoqueXProduto> listaProdutosEstoque = new ArrayList<LojaXEmEstoqueXProduto>();
	private int listSize;
	
	public List<String> preecheChoiceBoxLoja() { //m�todo para deixar o choicebox mais leg�vel para o usu�rio
		listaLojas = LojaService.getFullList();
		List<String> nomeEcnpjLojas = new ArrayList<>();
		for(int i = 0; i < listaLojas.size(); i++){
			nomeEcnpjLojas.add(listaLojas.get(i).getCnpj() + " - " + listaLojas.get(i).getNome());
		}
		return nomeEcnpjLojas;
	}
	
	@FXML
	public void initialize() {
		System.out.println("VALOR ANTES DE QUALQUER SELECAO "+choiceBoxListaLojas.getValue());
		
		choiceBoxListaLojas.getItems().clear();
		choiceBoxListaLojas.getItems().addAll(preecheChoiceBoxLoja()); //inserindo o nome das lojas no choicebox para sele��o
		
		//listaLojas = LojaService.getFullList();
		listProdutos = ProdutoService.getList();
		listSize = listProdutos.size();
		System.out.println(listSize);
		
	}
	
	private void loadPage() {

		canNext = false;
		canPrev = false;

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

			//seguindo = seguindoDao.getList();

			for (int i = 0; i < maxSizedList; i++) {
				final Button follow = new Button("Editar");
				final int id = i + ((nPagina - 1) * lNumber);

				Label productCode = new Label("Produto codigo: " + listProdutos.get(i + ((nPagina - 1) * lNumber)).getCodigo());

				Pane tuple = new Pane();

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");


				tuple.getChildren().add(productCode);
				tuple.getChildren().add(follow);

				productCode.setLayoutX(50);
				follow.setLayoutX(550);

				productCode.setLayoutY(15);
				follow.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);

				follow.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						SharedInfo.setCodigo(listProdutos.get(id).getCodigo());
						try {
							ScreenLibrary.LoadTela(ScreenConstants.IDCADPROD);
						} catch (UnsupportedEncodingException ee) {
							ee.printStackTrace();
						}
					};
				});


				panesTuple.add(tuple);
				buts.add(follow);

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
		if(choiceBoxListaLojas.getValue() != null) {
			try {
				listaProdutosEstoque = LojaEstoqueProdutoService.buscarcnpj(choiceBoxListaLojas.getValue());
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
