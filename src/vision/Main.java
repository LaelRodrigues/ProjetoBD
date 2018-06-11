package vision;

import dao.LojaJDBC;
import model.Loja;

public class Main {
	public static void main(String[] args) throws Exception {
		
		Loja loja = new Loja(2,"loja","loja21@gmail.com", 1);
		LojaJDBC empDao = new LojaJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		
		empDao.inserir(loja);
		empDao.commit();
		
		
	}
}
