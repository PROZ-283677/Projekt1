package proz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**************************
 * Baza danych, PROZ
 * @author Kacper Klimczuk 
 * @version v5
 **************************/
public class DataBase {
	// imitacja bazy danych uzytkownikow
	private ObservableList<String> produkcyjne = FXCollections.observableArrayList("adam.nowak","ewa.jakas","ktos.inny");
	private ObservableList<String> testowe = FXCollections.observableArrayList("jan.janek","alan.wake","nico.gajtan");
	private ObservableList<String> deweloperskie = FXCollections.observableArrayList("pawel.pablito","em.ce","tolek.banan");
	private ObservableList<String> h_produkcyjne = FXCollections.observableArrayList("123","admin","ja");
	private ObservableList<String> h_testowe = FXCollections.observableArrayList("haslo","rower","stopro");
	private ObservableList<String> h_deweloperskie = FXCollections.observableArrayList("jola2","adminadmin","tolus");
	
	/**
	 * Metoda sprawdza czy srodowisko, nazwa uzytkownika i haslo zgadzaja sie ze
	 * soba
	 * 
	 * @param srodowisko
	 *            - jedna z wartosci: Produkcyjne, Testowe, Deweloperskie
	 * @param uzytkownik
	 *            - w zaleznosci od srodowiska wybrany uzytkownik, lub wpisany
	 *            recznie
	 * @param haslo
	 *            - zawiera haslo wpisane do pola password
	 * @return boolean - jesli srodowisko, nazwa uzytkownika i haslo zgadzaja sie ze
	 *         soba, zwroci true
	 */
	public boolean isPassCorrect(String srodowisko, String uzytkownik, String haslo) {
		if (srodowisko == "Produkcyjne") {
			for (int i = 0; i < produkcyjne.size(); ++i) {
				if (produkcyjne.get(i).equals(uzytkownik) && h_produkcyjne.get(i).equals(haslo))
					return true;
			}
		} else if (srodowisko == "Testowe") {
			for (int i = 0; i < testowe.size(); ++i) {
				if (testowe.get(i).equals(uzytkownik) && h_testowe.get(i).equals(haslo))
					return true;
			}
		} else if (srodowisko == "Deweloperskie") {
			for (int i = 0; i < deweloperskie.size(); ++i) {
				if (deweloperskie.get(i).equals(uzytkownik) && h_deweloperskie.get(i).equals(haslo))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * @return nazwy uzytkownikow srodowiska: produkcyjne
	 */
	public ObservableList<String> getProdukcyjne() {
		return produkcyjne;
	}
	
	/**
	 * @return nazwy uzytkownikow srodowiska: testowe
	 */
	public ObservableList<String> getTestowe() {
		return testowe;
	}
	
	/**
	 * @return nazwy uzytkownikow srodowiska: developerskie
	 */
	public ObservableList<String> getDeweloperskie() {
		return deweloperskie;
	}
}
