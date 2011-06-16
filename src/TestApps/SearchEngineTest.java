package TestApps;

import Classes.Book;
import Exceptions.BookNotFoundException;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import SearchEngine.SearchEngine;
import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;

public class SearchEngineTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IGlobalFactory factory = ComponentContextFactory.createGlobalFactory();
			/* Instancia objeto do tipo DataBase */
			IDataBase db = factory.createInstance(
					"<http://purl.org/dcc/JavaDB.DataBase>",
					"<http://purl.org/dcc/Interfaces.IDataBase>");
			/* Instancia objeto do tipo SQLStatements */
			ISQLStatements stt = factory.createInstance(
					"<http://purl.org/dcc/JavaDB.SQLStatements>",
					"<http://purl.org/dcc/Interfaces.ISQLStatements>");
			/* DataBase requer SQLStatements */
			IRequires<ISQLStatements> connectStatements = db.queryInterface(
					"<http://purl.org/dcc/Interfaces.ISQLStatements>",
					InterfaceType.REQUIRED);
			/* Conecta o SQLStatements no DataBase */
			connectStatements.connect(stt);

			// Teste de normaliza��o de chave
			String testKey = "No espanhol, existe tanto o ene comum como 'la enhe', �.\n" + 
			"� jardin�ro � G�z��s, � �s �rvrezes ��m�s n�z�s.\n" + 
			"N�o sei onde diabos usar �, nem �, nem �, nem �, nem �, nem �, muito menos � e �, mas e da�? Foda-se!";
			
			SearchEngine se = new SearchEngine();
			testKey = se.keyNormalize(testKey);
			
			System.out.println(testKey);
			// Fim do teste de normaliza��o de chave
			
			// Teste de Exception throwing
			// Simula um not found da p�gina de livro, como por exemplo, qdo eh acessada com um ISBN inexistente.
			try {
				throw new BookNotFoundException();
			} catch (BookNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println();
				System.out.println("Peguei uma exception, chefe, saca s�:");
				System.out.println(e.getMessage());
			}
			// Fim do teste de Exception throwing
			
			// Teste da extra��o de tags
			String phrase = "Equa��es Diferenciais Elementares e, Problemas. de Valores de Contorno!? ? Algu�m";
			String tags[] = se.extractTags(phrase);
			Book[] books = se.search(phrase);
			System.out.print("{");
			for (int i = 0; i < tags.length; i++)
				if (i == tags.length - 1) System.out.print(tags[i]);
				else System.out.print(tags[i] + ", ");
			System.out.println("}");
			// Fim do teste de extra��o de tags
			} catch (ContextException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
