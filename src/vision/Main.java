package vision;

import dao.LojaJDBC;
import model.Loja;

public class Main {
	public static void main(String[] args) throws Exception {
		
		String a = "";
		Loja loja = new Loja(a,"loja","loja21@gmail.com", null);
		LojaJDBC empDao = new LojaJDBC("jdbc:mysql://localhost/Estoque","aluno","aluno");
		
		empDao.remover(loja);
		empDao.commit();
		
		
	}
}
