package TestApps;

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
	}

}
