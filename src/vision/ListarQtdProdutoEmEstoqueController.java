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
	private List<Loja> listaLojas = new ArrayList<Loja>();
	private List<LojaXEmEstoqueXProduto> listaProdutosEstoque = new ArrayList<LojaXEmEstoqueXProduto>();
	private int listSize;
	
	public List<String> preecheChoiceBoxLoja() { //método para deixar o choicebox mais legível para o usuário
		listaLojas = LojaService.getFullList();
		List<String> nomeEcnpjLojas = new ArrayList<>();
		for(int i = 0; i < listaLojas.size(); i++){
			nomeEcnpjLojas.add(listaLojas.get(i).getCnpj() + " - " + listaLojas.get(i).getNome());
		}
		return nomeEcnpjLojas;
	}
	
	@FXML
	public void initialize() {
		
		choiceBoxListaLojas.getItems().clear();
		choiceBoxListaLojas.getItems().addAll(preecheChoiceBoxLoja()); //inserindo o nome das lojas no choicebox para seleção
		
		//listaLojas = LojaService.getFullList();
		System.out.println(listSize);
		
	}
	
	private void loadPage() {

		canNext = false;
		canPrev = false;
		
		System.out.println("Listsize "+listSize);

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

			//seguindo = seguindoDao.getList();

			for (int i = 0; i < maxSizedList; i++) {

				Label productCode = new Label(listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getCodigo()+" - Nome do Produto : " + listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getNomeProd() + " - Quantidade :" + listaProdutosEstoque.get(i + ((nPagina - 1) * lNumber)).getQuantidade());

				Pane tuple = new Pane();

				tuple.setPrefSize(1150, 50);

				if (i % 2 == 0)
					tuple.setStyle("-fx-background-color: Gainsboro; -fx-border-color: lightgrey;");
				else
					tuple.setStyle("-fx-background-color: whitesmoke; -fx-border-color: lightgrey;");


				tuple.getChildren().add(productCode);

				productCode.setLayoutX(50);

				productCode.setLayoutY(15);

				tuple.setLayoutX(15);
				tuple.setLayoutY((i * 50) + 100);


				panesTuple.add(tuple);

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
		final String compair[] = choiceBoxListaLojas.getValue().split(" ");
		if(compair[0] != null) {
			try {
				System.out.println("AQUI " + compair[0]);
				listaProdutosEstoque = LojaEstoqueProdutoService.buscarcnpj(compair[0]);
				listSize = listaProdutosEstoque.size();
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
