package vision;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Produto;
import services.FornecedorService;
import services.ProdutoService;
import util.ScreenConstants;
import util.ScreenLibrary;
import util.SharedInfo;

public class CadProdutoController {

	@FXML
	private Label error;

	@FXML
	private TextField codigo;

	@FXML
	private TextField nome;

	@FXML
	private TextField descricao;

	@FXML
	private TextField preco;

	@FXML
	private ChoiceBox<String> cnpjForn = new ChoiceBox<String>();

	@FXML
	private DatePicker calendario = new DatePicker();

	@FXML
	private Pane background;

	@FXML
	private Pane logo;
	// private User user = new User();

	private boolean att = false;

	@FXML
	public void initialize() {

		if (SharedInfo.getCodigo() > 0) {
			Produto produto = ProdutoService.getProduto(SharedInfo.getCodigo());

			codigo.setText("" + produto.getCodigo());
			codigo.setDisable(true);
			nome.setText(produto.getNome());
			descricao.setText(produto.getDescricao());
			preco.setText("" + produto.getPreco());
			att = true;

		}

		List<String> cnpjFornecedores = FornecedorService.getList();
		cnpjForn.getItems().addAll(cnpjFornecedores);
	}

	@SuppressWarnings("deprecation")
	@FXML
	public void handlerCadastrarProduto() {

		Produto novo = new Produto();

		novo.setCodigo(Integer.parseInt(codigo.getText()));
		novo.setNome(nome.getText());
		novo.setDescricao(descricao.getText());
		novo.setD_validade(new Date(calendario.getValue().getYear(), calendario.getValue().getMonthValue(),
				calendario.getValue().getDayOfMonth()));
		novo.setPreco(Float.parseFloat(preco.getText()));
		novo.setForneCnpj(cnpjForn.getValue());

		try {
			if (att == false)
				ProdutoService.cadastrar(novo);
			else {
				ProdutoService.atualizar(novo);
				SharedInfo.setCodigo(0);
			}

		} catch (Exception e1) {
			System.out.println("impossivel cadastrar produto " + e1.getMessage());
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
