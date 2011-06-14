package TestApps;

import Exceptions.BookNotFoundException;
import SearchEngine.SearchEngine;

public class SearchEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// por enquanto, s� testado o m�todo keyNormalize
		String testKey = "No espanhol, existe tanto o ene comum como 'la enhe', �.\n" + 
		"� jardin�ro � G�z��s, � �s �rvrezes ��m�s n�z�s.\n" + 
		"N�o sei onde diabos usar �, nem �, nem �, nem �, nem �, nem �, muito menos � e �, mas e da�? Foda-se!";
		
		SearchEngine se = new SearchEngine();
		testKey = se.keyNormalize(testKey);
		
		System.out.println(testKey);
		
		// Simula um not found da p�gina de livro, como por exemplo, qdo eh acessada com um ISBN inexistente.
		try {
			throw new BookNotFoundException();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.out.println("Peguei uma exception, chefe, saca s�:");
			System.out.println(e.getMessage());
		}
	}

}
