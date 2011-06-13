package SearchEngine;

import java.util.ArrayList;

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

	// M�todos auxiliares
	
	// TODO: TROCAR ESPECIFICADOR DE ESCOPO PARA PRIVATE!
	
	/**
	 * <p>Normaliza a chave de pesquisa, retirando acentos e sinais, <br />
	 * deixando a  string em min�sculas e tirando os espa�os.<br /></p>
	 * <p>Mant�m, por�m, caracteres de pontua��o, s�mbolos e caraceres especiais.</p> 
	 * @author Giuliano
	 */
	public String keyNormalize(String value) {
		String ret = "";
		
		value.trim();
		value = value.toLowerCase();
		
		// � � � � �
		// � � � �
		// � � � �
		// � � � � �
		// � � � �
		// � �
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
		
		return ret;
	}
	
	public String[] extractTags(String value) {
		// normaliza a frase
		// quebra a frase em palavras
		// loopa pelas palavras procurando n�o-tags
		// remove os matches
		// retira pontua��o do resto
		
		ArrayList<String> tagArray = new ArrayList<String>();
		
		// Preposi��es, artigos, conjun��es, e tudo mais (pontua��o, sinais gr�ficos). 42!
		// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
		String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", 
							"das", "para", "como", "com", "em", "no", "nos", 
							"na", "nas", "uns", "umas", "um", "algum", "algu�m", 
							"nenhum", "ningu�m", "todo", "todos", "toda", "todas", 
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
