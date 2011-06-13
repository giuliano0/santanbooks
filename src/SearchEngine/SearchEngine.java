package SearchEngine;

import java.util.ArrayList;

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
public class SearchEngine implements ISearchEngine {

	@Override
	public Book[] searchByAuthor(String key) {
		keyNormalize(key);

		return null;
	}

	@Override
	public Book[] searchByISBN(String key) {
		keyNormalize(key);
		
		return null;
	}

	@Override
	public Book[] searchByName(String key) {
		keyNormalize(key);
		
		return null;
	}

	@Override
	public Book[] searchByTags(String[] tags) {
		for (int i = 0; i < tags.length; i++) keyNormalize(tags[i]);
		
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
	public String keyNormalize(String value) {
		String ret = "";
		
		value.trim();
		value = value.toLowerCase();
		
		// á à ã â ä
		// é è ê ë
		// í ì î ï
		// ó ò õ ô ö
		// ú ù û ü
		// ç ñ
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
		
		return ret;
	}
	
	public String[] extractTags(String value) {
		// normaliza a frase
		// quebra a frase em palavras
		// loopa pelas palavras procurando não-tags
		// remove os matches
		// retira pontuação do resto
		
		ArrayList<String> tagArray = new ArrayList<String>();
		
		// Preposições, artigos, conjunções, e tudo mais (pontuação, sinais gráficos). 42!
		// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
		String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", 
							"das", "para", "como", "com", "em", "no", "nos", 
							"na", "nas", "uns", "umas", "um", "algum", "alguém", 
							"nenhum", "ninguém", "todo", "todos", "toda", "todas", 
							"tudo", 
							",", ".", ":", "-", ";", "?", "!", "(", ")"};
		
		keyNormalize(value);
		
		String words[] = value.split(" ");
		
		for (int word = 0; word < words.length; word++) {
			for (int notag = 0; notag < notTags.length; notag++) {
				if (words[word].equals(notTags[notag]))
					// remover do array, nem sei como, mas remover anyway.
			}
		}
		
		
		
		return null;
	}
	
}
