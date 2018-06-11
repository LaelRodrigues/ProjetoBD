package vision;

import dao.*;
import model.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Loja loja = new Loja("1","loja","loja21@gmail.com", "2");
		Endereco endereco = new Endereco("2","RN", "natal", "coophab","A");
		LojaJDBC lojaDao = new LojaJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		EnderecoJDBC enderecoDao = new EnderecoJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		
		lojaDao.remover(loja);
		lojaDao.commit();
		
		
		
		
	}
}
