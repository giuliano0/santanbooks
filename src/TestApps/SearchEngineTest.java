package TestApps;

import Exceptions.BookNotFoundException;
import SearchEngine.SearchEngine;

public class SearchEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Teste de normalização de chave
		String testKey = "No espanhol, existe tanto o ene comum como 'la enhe', ñ.\n" + 
		"Ö jardinêro é Gëzúïs, è às árvrezes çômõs nózès.\n" + 
		"Não sei onde diabos usar ò, nem ù, nem â, nem ä, nem ì, nem î, muito menos ù e û, mas e daí? Foda-se!";
		
		SearchEngine se = new SearchEngine();
		testKey = se.keyNormalize(testKey, false);
		
		System.out.println(testKey);
		// Fim do teste de normalização de chave
		
		// Teste de Exception throwing
		// Simula um not found da página de livro, como por exemplo, qdo eh acessada com um ISBN inexistente.
		try {
			throw new BookNotFoundException();
		} catch (BookNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println();
			System.out.println("Peguei uma exception, chefe, saca só:");
			System.out.println(e.getMessage());
		}
		// Fim do teste de Exception throwing
		
		// Teste da extração de tags
		String phrase = "Equações Diferenciais Elementares e Problemas de Valores de Contorno";
		String tags[] = se.extractTags(phrase);
		
		System.out.print("{");
		for (int i = 0; i < tags.length; i++)
			if (i == tags.length - 1) System.out.print(tags[i]);
			else System.out.print(tags[i] + ", ");
		System.out.println("}");
		// Fim do teste de extração de tags
	}

}
