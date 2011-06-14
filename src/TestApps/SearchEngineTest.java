package TestApps;

import Exceptions.BookNotFoundException;
import SearchEngine.SearchEngine;

public class SearchEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// por enquanto, só testado o método keyNormalize
		String testKey = "No espanhol, existe tanto o ene comum como 'la enhe', ñ.\n" + 
		"Ö jardinêro é Gëzúïs, è às árvrezes çômõs nózès.\n" + 
		"Não sei onde diabos usar ò, nem ù, nem â, nem ä, nem ì, nem î, muito menos ù e û, mas e daí? Foda-se!";
		
		SearchEngine se = new SearchEngine();
		testKey = se.keyNormalize(testKey);
		
		System.out.println(testKey);
		
		// Simula um not found da página de livro, como por exemplo, qdo eh acessada com um ISBN inexistente.
		try {
			throw new BookNotFoundException();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.out.println("Peguei uma exception, chefe, saca só:");
			System.out.println(e.getMessage());
		}
	}

}
