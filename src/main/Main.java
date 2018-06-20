package main;

import java.io.UnsupportedEncodingException;

import javafx.application.Application;
import javafx.stage.Stage;
import util.DaoInfo;
import util.SceneBuilder;
import util.ScreenConstants;

public class Main extends Application{
	public static void main(String[] args)  throws Exception {
		DaoInfo.set("jdbc:mysql://localhost/Estoque", "root", "samuca1996");
		launch(args);
		
	}
	
	@Override
	public void start(Stage primarystage) throws UnsupportedEncodingException {
		SceneBuilder.setPrimaryStage(primarystage);
		SceneBuilder.LoadScreen(ScreenConstants.IDLOGIN);
	} 
}


