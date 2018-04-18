package proz;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

/**************************
 * Klasa Main, PROZ
 * 
 * @author Kacper Klimczuk
 * @version v4
 **************************/
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pair<String, String> result = (new LogonDialog()).showAndWait();

		if (result == null)
			System.out.println("Logowanie nieudane, haslo, nazwa uzytkownika lub srodowisko nie poprawne");
		else
			System.out.println("Srodowisko=" + result.getKey() + ", Uzytkownik=" + result.getValue());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
