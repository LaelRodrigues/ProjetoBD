package vision;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Em_estoque;
import model.Produto;
import services.EmEstoqueService;
import services.LojaService;
import services.ProdutoService;
import util.ScreenConstants;
import util.ScreenLibrary;

public class CadQtdEmEstoqueController {

	@FXML
	private Label error;
	
	@FXML
	private TextField nomeLoja;
	
	@FXML
	private TextField qtdProduto;

	@FXML
	private TextField nome;

	@FXML
	private TextField preco;

	@FXML
	private ChoiceBox<String> choiceBoxNomeProdutos = new ChoiceBox<String>();
	
	@FXML
	private ChoiceBox<String> choiceBoxNomeLojas = new ChoiceBox<String>();

	@FXML
	private Pane background;

	@FXML
	private Pane logo;
	// private User user = new User();

	@FXML
	public void initialize() {

		List<String> nomeDasLojas = LojaService.getList();
		choiceBoxNomeLojas.getItems().addAll(nomeDasLojas);
		
		List<Produto> todosProdutos = ProdutoService.getList();
		List<String> nomeDosProdutos = new ArrayList<>();
		for(int i = 0; i < todosProdutos.size(); i++){
			nomeDosProdutos.add(todosProdutos.get(i).getCodigo()+" - "+todosProdutos.get(i).getNome());
		}
		choiceBoxNomeProdutos.getItems().addAll(nomeDosProdutos);
	}

	@FXML
	public void handlerCadastrarQuantidadeProduto() {

		Em_estoque qtdEstoque = new Em_estoque();
		
		final String compair[] = choiceBoxNomeProdutos.getValue().split(" "); //variável para captar o código do produto selecionado no
																				//choicebox
		Produto produto = ProdutoService.getProduto(Integer.parseInt(compair[0])); //produto a ser setada a quantidade
		
		//System.out.println("IMPRIMINDO O CNPJ DA LOJA "+choiceBoxNomeLojas.getValue());
		
		qtdEstoque.setCnpjL(choiceBoxNomeLojas.getValue());
		qtdEstoque.setCodicoP(produto.getCodigo());
		qtdEstoque.setQuantidade(Integer.parseInt(qtdProduto.getText()));

		try {
			EmEstoqueService.cadastrar(qtdEstoque);
		} catch (Exception e1) {
			System.out.println("impossivel cadastrar quantidade do produto " + e1.getMessage());
		}

		try {
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
