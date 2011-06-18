package SearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import Classes.Book;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISearchEngine;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * Componente de motor de buscas.
 * @author Giuliano, Lucas, Oscar, Wesley
 */
@Component(id="<http://purl.org/dcc/SearchEngine.SearchEngine>",
        provides={"<http://purl.org/dcc/Interfaces.ISearchEngine>"}, 
        requires= { "<http://purl.org/dcc/Interfaces.IBusinessObject>",
					"<http://purl.org/dcc/Interfaces.IDataBase>"})
public class SearchEngine extends ComponentBase implements ISearchEngine, IReceptacleDataBase, IReceptacleBussinessObject {

	// Preposições, artigos, conjunções, e tudo mais (pontuação, sinais gráficos)
	private static String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", "das", 
						"para", "como", "com", "em", "no", "nos", "na", "nas", "uns", "umas", 
						"um", "algum", "alguem", "nenhum", "ninguem", "todo", "todos", "toda", "todas", "tudo"};

	private static final String[] punctuation = {",", "\\.", ":", "\\-", ";", "\\?", "!", "\\(", "\\)", "\\n"};
	
	private IDataBase dataBase;
	private IBusinessObject bO;
	private String[] tags;
	
	public SearchEngine() {
		Arrays.sort(notTags);
	}

	/**
	 * Função principal de busca. Busca os livros seguindo a seguinte prioridade (maior para menor):
	 * Name, Authors, Description, Publisher, ISBN.
	 * @author Lucas, Oscar, Wesley
	 * @param String key - expressão digitada que será procurada.
	 * @return Retorna um vetor de Books (sem repetição) ordenado por relevância de várias colunas do Book.
	 */
	@Override
	public Book[] search(String key) {
		Book[] booksByName = searchByParameter(key, "name");
		Book[] booksByAuthors = searchByParameter(key, "authors");
		Book[] booksByDescription = searchByParameter(key, "description");
		Book[] booksByPublisher = searchByParameter(key, "publisher");
		Book[] booksByISBN = searchByParameter(key, "isbn");
		Book[] resultadoFinal = mergeByBooks(booksByName, mergeByBooks(booksByAuthors, mergeByBooks(booksByDescription, mergeByBooks(booksByPublisher, booksByISBN))));
		return resultadoFinal;
	}

	/**
	 * Junta vetores de Books, eliminando os repetidos.
	 * @author Lucas, Oscar, Wesley
	 * @param Book[] b1 - Vetor de Books 1 a ser mergeado.
	 * @param Book[] b2 - Vetor de Books 2 a ser mergeado.
	 * @return Retorna um vetor de Books (sem repetição).
	 */
	private Book[] mergeByBooks(Book[] b1, Book[] b2) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (Book b : b1) 
			books.add(b);
		
		boolean existe = false;
		for (Book b : b2){
			for (Book b0 : books)
				if (b.getISBN().equalsIgnoreCase(b0.getISBN())) {
					existe = true;
					break;
				}
			if (!existe) books.add(b);
			existe = false;
		}
		return books.toArray(new Book[books.size()]);
	}

	/**
	 * Junta vetores de String, eliminando os repetidos.
	 * @author Lucas, Oscar, Wesley
	 * @param String[] s1 - Vetor de Strings 1 a ser mergeado.
	 * @param String[] s2 - Vetor de Strings 2 a ser mergeado.
	 * @return Retorna um vetor de Strings (sem repetição).
	 */
	private String[] mergeByString(String[] s1, String[] s2) {
		ArrayList<String> tags = new ArrayList<String>();
		for (String s : s1) tags.add(s);
		for (String s : s2) if (!tags.contains(s)) tags.add(s);
		return tags.toArray(new String[tags.size()]);
	}

	/**
	 * Faz a busca dos livros ordenando pelas chances de ser o livro procurado
	 * @author Lucas, Oscar, Wesley
	 * @param String key - expressão digitada que será procurada.
	 * @param String campo - coluna do banco de dados usada na pesquisa.
	 * @return Retorna um vetor de Books (sem repetição) ordenado por relevância.
	 */
	private Book[] searchByParameter(String key, String campo) {
		Vector<String> select = new Vector<String>();
		Vector<String> where = new Vector<String>();
		select = returnSelect();
		key = key.toLowerCase();
		Book[] myBooks, books2;
	
		// 1º - Procura pela nome exato do autor
		where.add(campo + " LIKE '%" + key + "%'");
		myBooks = dataBase.queryBook(select, where, null);
		
		// 2º - Procura por cada um dos termos no nome do autor passado
		String words1[] = key.split(" ");
		String words2[] = extractTags(key);
		String words[] = mergeByString(words1, words2);
		
		for(int i =0; i < words.length ; i++){
			books2 = null;
			where.clear();
			where.add(campo + " LIKE '%" + keyNormalize(words[i]) + "%'");
			//pega todos os livros contendo o trecho do nome do autor nessa iteracao
			books2 = dataBase.queryBook(select, where, null);
			myBooks = this.mergeByBooks(myBooks, books2);
		}
	
		// Se encontrou mais de um livro, ordenar por quantidade de tags encontradas
		if (myBooks.length > 1){
			String[][] booksQtdsTags = new String[myBooks.length][2];
			for (int i = 0; i < myBooks.length; i++){
				ArrayList<String> tagLivro = new ArrayList<String>();
				String[] tagAux = extractTags(myBooks[i].getName());
				for (int q = 0; q < tagAux.length; q++)
					tagLivro.add(tagAux[q]);
				Integer qtd = 0;
				for (int j = 0; j < words.length; j++)
					if (tagLivro.contains(words[j]))
						qtd++;
				booksQtdsTags[i][0] = myBooks[i].getName();
				booksQtdsTags[i][1] = qtd.toString();
			}
			String[][] temp = new String[2][2];
		    for (int z = 0; z < booksQtdsTags.length; z++){
	            temp[0] = booksQtdsTags[z];
	            for (int y = 0; y <= z; y++){
                    if(Integer.parseInt(temp[0][1]) > Integer.parseInt(booksQtdsTags[y][1])){
                    	temp[1] = booksQtdsTags[y];
                    	booksQtdsTags[y] = temp[0];
	                    y++;
	                    for(; y <=z; y++){
	                    	temp[0] = booksQtdsTags[y];
	                    	booksQtdsTags[y] = temp[1];
	                    	temp[1] = temp[0];
	                    }
                    }
	            }
		    }
		}
		
/*		Arrays.sort(myBooks, 
			new Comparator<Book>() {  
				public int compare(Book b1, Book b2) { 
					Vector<String> select = new Vector<String>();
					select.add("value");
	
					Vector<String> where = new Vector<String>();
					where.add("bookISBN = '" + b1.getISBN() + "'");
	
					Vector<String> order = new Vector<String>();
					order.add("value");
					float bb1 = b1.getRating()*b1.getRating()*dataBase.queryRating(select, where, order).length;
					
					where.clear();
					where.add("bookISBN = '" + b2.getISBN() + "'");
					float bb2 = b2.getRating()*b2.getRating()*dataBase.queryRating(select, where, order).length;
				    return bb1 < bb2 ? 1 : (bb1 > bb2 ? -1 : 0);
				}
			} 
		);
*/
		return myBooks;
	}

	/**
	 * Coloca todos os campos do livro para busca
	 * @author Lucas, Oscar, Wesley
	 * @return Retorna um Vector "select" com todos os campos do livro.
	 */
	private Vector<String> returnSelect() {
		Vector<String> select = new Vector<String>();
		select.add("name"); // Falta ver um jeito de selecionar todos os campo
		select.add("isbn");
		select.add("authors");
		select.add("description");
		select.add("edition");
		select.add("imagePath");
		select.add("publisher");
		select.add("publishingDate");
		return select;
	}

	/**
	 * <p>Normaliza a chave de pesquisa, retirando acentos e sinais, <br />
	 * deixando a  string em minúsculas e tirando os espaços.<br /></p>
	 * <p>Mantém, porém, caracteres de pontuação, símbolos e caraceres especiais.</p> 
	 * @author Giuliano
	 */
	private String keyNormalize(String value) {
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
		
		for (int i = 0; i < punctuation.length; i++)
			ret = ret.replaceAll(punctuation[i], ""); 
		
		return ret;
	}
	
	/**
	 * Extrai potenciais tags relevantes de uma string, que pode ser o título do livro.
	 * @author Giuliano
	 * @param value é a string a sofrer extração
	 * @return Retorna um array de strings com as tags encontradas.
	 */
	private String[] extractTags(String value) {
		String tags[];
		
		// remove, além de acentos, pontuação
		value = keyNormalize(value);
		
		// adiciona um espaço no começo e no final da string para o replaceAll()
		value = " " + value + " ";
		
		// Remove cada match de não-tag da string
		for (int i = 0; i < notTags.length; i++)
			value = value.replaceAll(" " + notTags[i] + " ", " ");
		
		// trim dos espaços extras
		value = value.trim();
		
		tags = value.split(" ");
		Arrays.sort(tags);
		
		return tags;
	}

	@Override
	public void connect(IDataBase arg0) {
		this.dataBase = arg0;	
	}

	public String[] autoComplete(String arg) {
		ArrayList<String> cands = new ArrayList<String>();
		for (String s : extractTags(arg)) {
			ArrayList<String> temp = new ArrayList<String>();
			for (String str : tags)
				if (str.toLowerCase().contains(s.toLowerCase()))
					temp.add(str);
			cands = mergeString(cands, temp);
		}
		String[] res = cands.toArray(new String[cands.size()]);
		Arrays.sort(res,
			new Comparator<String>() {
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			}
		);
		return res;
	}
	
	private ArrayList<String> mergeString(ArrayList<String> s1, ArrayList<String> s2) {
		ArrayList<String> strings = new ArrayList<String>();
		if (s1.size() == 0) for (String s : s2) strings.add(s);
		for (String s : s1) if (s2.contains(s)) strings.add(s);
		return strings;
	}

	@Override
	public void connect(IBusinessObject bussinessobject) {
		this.bO = bussinessobject;
	}
	
	public void update() {
		ArrayList<String> temp = new ArrayList<String>();
		for (Book b : bO.selectAllBooks()) {
			temp.add(keyNormalize(b.getName()));
			temp.add(keyNormalize(b.getAuthors()));
			temp.add(keyNormalize(b.getDescription()));
			temp.add(keyNormalize(b.getPublisher()));
		
		}
		this.tags = temp.toArray(new String[temp.size()]);
	}

}
