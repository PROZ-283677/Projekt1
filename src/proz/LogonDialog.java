package proz;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**************************
 * Okienko logowania, PROZ
 * 
 * @author Kacper Klimczuk
 * @version v5
 **************************/
public class LogonDialog {

	// utworzenie dialog
	private Dialog<ButtonType> dialog = new Dialog<>();

	// utworzenie buttonow
	private ButtonType loginButtonType = new ButtonType("Logon");
	private ButtonType anulujButtonType = new ButtonType("Anuluj");

	// utworzenie Grid
	private GridPane grid = new GridPane();

	// utworzenie Password Field
	private PasswordField password = new PasswordField();

	// utworzenie Choice box
	private ChoiceBox<String> choiceBox = new ChoiceBox<>();

	// utworzenie Combo box
	private ComboBox<String> comboBox = new ComboBox<>();

	private Node loginButton;

	private DataBase db = new DataBase();

	LogonDialog() {
		initialize();
	}

	/**
	 * Procedura blokuje/odblokowuje logon button w zaleznosci od tego czy
	 * srodowisko i uzytkownik wybrany, a haslo wpisane. Zmienia takze liste
	 * uzytkownikow gdy srodowisko zmienione
	 */
	private void changed() {
		loginButton.setDisable(choiceBox.getValue() == null || comboBox.getEditor().getText().trim().isEmpty()
				|| password.getText().isEmpty());

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
			if (choiceBox.getValue() == "Produkcyjne")
				comboBox.setItems(db.getProdukcyjne());
			else if (choiceBox.getValue() == "Testowe")
				comboBox.setItems(db.getTestowe());
			else
				comboBox.setItems(db.getDeweloperskie());
		});
	}

	/**
	 * Metoda konwertuje buttontype na pair
	 * 
	 * @param buttonType
	 *            - zwracane przez okno Dialog
	 * @param loginButtonType
	 *            - sluzy do sprawdzenia ktory przycisk zostal nacisniety
	 * @return Pair<String,String> - para uzytkownik, srodowisko, jesli dane sie
	 *         zgadzaja, null gdy sie nie zgadzaja
	 */
	private Pair<String, String> resultConverter(Optional<ButtonType> buttonType, ButtonType loginButtonType) {
		if (buttonType.get() == loginButtonType) {
			if (db.isPassCorrect(choiceBox.getValue(), comboBox.getEditor().getText(), password.getText()))
				return new Pair<>(choiceBox.getValue(), comboBox.getEditor().getText());
		}
		return null;
	}

	/**
	 * Metoda reaguje na wywolanie uzytkownika
	 * 
	 * @return przekonwertowana wartosc z resultConverter
	 */
	public Pair<String, String> showAndWait() {
		return resultConverter(dialog.showAndWait(), loginButtonType);
	}

	/**
	 * Metoda inicjalizujaca
	 */
	private void initialize() {
		// ustawianie elementow dialog
		dialog.setTitle("Logowanie");
		dialog.setHeaderText("Logowanie do systemu STYLEman");
		// dodanie buttonow do dialog
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, anulujButtonType);

		// ustawianie grid
		grid.setHgap(50);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 10, 10, 50));

		password.setPromptText("Hasło");
		password.textProperty().addListener((observable, oldVal, newVal) -> changed());
		grid.add(new Label("Hasło:"), 0, 2);
		grid.add(password, 1, 2);

		choiceBox.getItems().addAll("Produkcyjne", "Testowe", "Deweloperskie");
		choiceBox.setValue("Produkcyjne");
		choiceBox.valueProperty().addListener((observable, oldVal, newVal) -> changed());
		grid.add(new Label("Środowisko:"), 0, 0);
		grid.add(choiceBox, 1, 0);

		comboBox.setItems(db.getProdukcyjne());
		comboBox.setEditable(true);
		comboBox.getEditor().textProperty().addListener((observable, oldVal, newVal) -> changed());
		comboBox.setValue("adam.nowak");
		grid.add(new Label("Użytkownik:"), 0, 1);
		grid.add(comboBox, 1, 1);

		// loginButton
		loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// dodanie zarzadcy do okienka
		dialog.getDialogPane().setContent(grid);
	}
}
