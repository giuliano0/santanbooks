package SearchEngine;

import anima.annotation.Component;
import Classes.Book;
import Interfaces.ISearchEngine;

/**
 * Componente de motor de buscas.
 * @author Giuliano
 * @remarks Pessoal, lembrem-se de colocar um coment�rio como este, de Javadoc <br />
 * no c�digo que voc�s escreverem, e salvem numa planilha o que vcs fizeram, precisaremos <br />
 * dessa informa��o mais tarde! Thanks ;)
 */
@Component(id="<http://purl.org/dcc/santanbooks.SearchEngine.SearchEngine>",
        provides={"<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>"})
public class SearchEngine implements ISearchEngine { // TODO: colocar o IRequires<IDataBase>, ajuda aqui please =)

	// Preposi��es, artigos, conjun��es, e tudo mais (pontua��o, sinais gr�ficos). 42!
	// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
	private static String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", "das", 
						"para", "como", "com", "em", "no", "nos", "na", "nas", "uns", "umas", 
						"um", "algum", "algu�m", "nenhum", "ningu�m", "todo", "todos", "toda", "todas", "tudo"};

	// Verificar documenta��o do java para express�es regulares a respeito dos caracteres comentados abaixo.
	// Eles s�o usados no Regex, e na verdade esse array inteiro pode ser substitu�do por um Regex Pattern...
	private static final String[] punctuation = {",", ".", ":", "-", ";", /*"?",*/ "!", /*"(", ")"*/};
	
	// TODO: Declarar a database e a factory, e instanci�-la no construtor.
	
	public SearchEngine() {
		//Arrays.sort(notTags);
	}
	
	@Override
	public Book[] searchByAuthor(String key) {
		//String nKey = keyNormalize(key);

		return null;
	}

	@Override
	public Book[] searchByISBN(String key) {
		//String nKey = keyNormalize(key);
		
		return null;
	}

	@Override
	public Book[] searchByName(String key) {
		//String nKey = keyNormalize(key);
		
		return null;
	}

	@Override
	public Book[] searchByTags(String[] tags) {
		//String[] nKeys; // Declara��o errada!
		//for (int i = 0; i < tags.length; i++) nKeys[i] = keyNormalize(tags[i]);
		
		return null;
	}

	// M�todos auxiliares
	
	// TODO: TROCAR ESPECIFICADOR DE ESCOPO PARA PRIVATE!
	
	/**
	 * <p>Normaliza a chave de pesquisa, retirando acentos e sinais, <br />
	 * deixando a  string em min�sculas e tirando os espa�os.<br /></p>
	 * <p>Mant�m, por�m, caracteres de pontua��o, s�mbolos e caraceres especiais.</p> 
	 * @author Giuliano
	 */
	public String keyNormalize(String value, boolean removePunctuation /*OPTIONAL*/) {
		String ret = "";
		
		value.trim();
		value = value.toLowerCase();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			
			if (c == '�' || c == '�' || c == '�' || c == '�' || c == '�') c = 'a';
			if (c == '�' || c == '�' || c == '�' || c == '�') c = 'e';
			if (c == '�' || c == '�' || c == '�' || c == '�') c = 'i';
			if (c == '�' || c == '�' || c == '�' || c == '�' || c == '�') c = 'o';
			if (c == '�' || c == '�' || c == '�' || c == 'u') c = 'u';
			if (c == '�') c = 'c';
			if (c == '�') c = 'n';
			
			ret += c;
		}
		
		if (removePunctuation) {
			// O(n�), sucks
			for (int i = 0; i < punctuation.length; i++) {
				System.out.println("retirando pontua��o[: " + i + "]" + punctuation[i]);
				ret.replaceAll(punctuation[i], ""); 
			}
		}
		
		return ret;
	}
	
	/**
	 * Extrai potenciais tags relevantes de uma string, que pode ser o t�tulo do livro.
	 * @author Giuliano
	 * @param value � a string a sofrer extra��o
	 * @return Retorna um array de strings com as tags encontradas.
	 */
	public String[] extractTags(String value) {
		String tags[];
		
		// remove, al�m de acentos, pontua��o
		value = keyNormalize(value, true);
		
		// Remove cada match de n�o-tag da string
		for (int i = 0; i < notTags.length; i++) {
			System.out.println("Retirando ocorr�ncia de n�o-tag[" + i + "]: " + notTags[i]);
			value = value.replaceAll(" " + notTags[i] + " ", " ");
		}
		
		tags = value.split(" ");
		//Arrays.sort(words);
		
		return tags;
	}
	
}
