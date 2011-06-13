package TestApps;

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
	}

}
