package SearchEngine;

import java.util.Arrays;

import anima.annotation.Component;
import Classes.Book;
import Interfaces.ISearchEngine;

/**
 * Componente de motor de buscas.
 * @author Giuliano
 * @remarks Pessoal, lembrem-se de colocar um comentário como este, de Javadoc <br />
 * no código que vocês escreverem, e salvem numa planilha o que vcs fizeram, precisaremos <br />
 * dessa informação mais tarde! Thanks ;)
 */
@Component(id="<http://purl.org/dcc/santanbooks.SearchEngine.SearchEngine>",
        provides={"<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>"})
public class SearchEngine implements ISearchEngine { // TODO: colocar o IRequires<IDataBase>, ajuda aqui please =)

	// Preposições, artigos, conjunções, e tudo mais (pontuação, sinais gráficos). 42!
	// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
	private static String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", "das", 
						"para", "como", "com", "em", "no", "nos", "na", "nas", "uns", "umas", 
						"um", "algum", "alguém", "nenhum", "ninguém", "todo", "todos", "toda", "todas", "tudo"};

	// Verificar documentação do java para expressões regulares a respeito dos caracteres comentados abaixo.
	// Eles são usados no Regex, e na verdade esse array inteiro pode ser substituído por um Regex Pattern...
	// Metacaracteres do Regex: ([{\^-$|]})?*+.
	private static final String[] punctuation = {",", "\\.", ":", "\\-", ";", "\\?", "!", "\\(", "\\)", "\\n"};
	
	// TODO: Declarar a database e a factory, e instanciá-la no construtor.
	
	public SearchEngine() {
		Arrays.sort(notTags);
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
		//String[] nKeys; // Declaração errada!
		//for (int i = 0; i < tags.length; i++) nKeys[i] = keyNormalize(tags[i]);
		
		return null;
	}

	// Métodos auxiliares
	
	// TODO: TROCAR ESPECIFICADOR DE ESCOPO PARA PRIVATE!
	
	/**
	 * <p>Normaliza a chave de pesquisa, retirando acentos e sinais, <br />
	 * deixando a  string em minúsculas e tirando os espaços.<br /></p>
	 * <p>Mantém, porém, caracteres de pontuação, símbolos e caraceres especiais.</p> 
	 * @author Giuliano
	 */
	public String keyNormalize(String value, boolean removePunctuation /*OPTIONAL*/) {
		long start = System.currentTimeMillis();
		String ret = "";
		
		value.trim();
		value = value.toLowerCase();

		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			
			if (c == 'á' || c == 'à' || c == 'ã' || c == 'â' || c == 'ä') c = 'a';
			if (c == 'é' || c == 'è' || c == 'ê' || c == 'ë') c = 'e';
			if (c == 'í' || c == 'ì' || c == 'î' || c == 'ï') c = 'i';
			if (c == 'ó' || c == 'ò' || c == 'õ' || c == 'ô' || c == 'ö') c = 'o';
			if (c == 'ú' || c == 'ù' || c == 'û' || c == 'u') c = 'u';
			if (c == 'ç') c = 'c';
			if (c == 'ñ') c = 'n';
			
			ret += c;
		}
		
		if (removePunctuation) {
			for (int i = 0; i < punctuation.length; i++)
				ret = ret.replaceAll(punctuation[i], ""); 
		}
		
		System.out.println("Running time (keyNormalize): " + (System.currentTimeMillis() - start) + "ms.");
		return ret;
	}
	
	/**
	 * Extrai potenciais tags relevantes de uma string, que pode ser o título do livro.
	 * @author Giuliano
	 * @param value é a string a sofrer extração
	 * @return Retorna um array de strings com as tags encontradas.
	 */
	public String[] extractTags(String value) {
		long start = System.currentTimeMillis();
		String tags[];
		
		// remove, além de acentos, pontuação
		value = keyNormalize(value, true);
		
		// Remove cada match de não-tag da string
		for (int i = 0; i < notTags.length; i++)
			value = value.replaceAll(" " + notTags[i] + " ", " ");
		
		tags = value.split(" ");
		Arrays.sort(tags);
		
		System.out.println("Running time (extractTags): " + (System.currentTimeMillis() - start) + "ms.");
		
		return tags;
	}
	
}
