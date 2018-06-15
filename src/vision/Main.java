package vision;

import java.io.UnsupportedEncodingException;

import dao.EnderecoJDBC;
import dao.LojaJDBC;
import javafx.stage.Stage;
import javafx.application.Application;
import model.Endereco;
import model.Loja;
import util.SceneBuilder;
import util.ScreenConstants;

public class Main {
	public static void main(String[] args)  throws Exception {
		
		Loja loja = new Loja("1","loja","loja21@gmail.com", "2");
		Endereco endereco = new Endereco("2","RN", "natal", "coophab","A");
		LojaJDBC lojaDao = new LojaJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		EnderecoJDBC enderecoDao = new EnderecoJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		
		enderecoDao.inserir(endereco);
		enderecoDao.commit();
		
	}
}
