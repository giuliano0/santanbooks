package SearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import Classes.Book;
import Interfaces.IDataBase;
import Interfaces.ISearchEngine;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;

/**
 * Componente de motor de buscas.
 * @author Giuliano
 * @remarks Pessoal, lembrem-se de colocar um comentário como este, de Javadoc <br />
 * no código que vocês escreverem, e salvem numa planilha o que vcs fizeram, precisaremos <br />
 * dessa informação mais tarde! Thanks ;)
 */
@Component(id="<http://purl.org/dcc/SearchEngine.SearchEngine>",
        provides={"<http://purl.org/dcc/Interfaces.ISearchEngine>"}, 
        requires= { "<http://purl.org/dcc/Interfaces.IBusinessObject>" })
public class SearchEngine implements ISearchEngine, IRequires<IDataBase> { // TODO: colocar o IRequires<IDataBase>, ajuda aqui please =)

	// Preposições, artigos, conjunções, e tudo mais (pontuação, sinais gráficos). 42!
	// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
	private static String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", "das", 
						"para", "como", "com", "em", "no", "nos", "na", "nas", "uns", "umas", 
						"um", "algum", "alguem", "nenhum", "ninguem", "todo", "todos", "toda", "todas", "tudo"};

	// Verificar documentação do java para expressões regulares a respeito dos caracteres comentados abaixo.
	// Eles são usados no Regex, e na verdade esse array inteiro pode ser substituído por um Regex Pattern...
	// Metacaracteres do Regex: ([{\^-$|]})?*+.
	private static final String[] punctuation = {",", "\\.", ":", "\\-", ";", "\\?", "!", "\\(", "\\)", "\\n"};
	
	private IDataBase dataBase;
	
	// TODO: Declarar a database e a factory, e instanciá-la no construtor.

	public SearchEngine() {
		Arrays.sort(notTags);
	}

	@Override
	public Book[] search(String key) {
		Book[] booksByName = searchByParameter(key, "name");
		Book[] booksByISBN = searchByParameter(key, "isbn");
		Book[] booksByAuthors = searchByParameter(key, "authors");
		Book[] booksByDescription = searchByParameter(key, "description");
		Book[] booksByPublisher = searchByParameter(key, "publisher");
		Book[] resultadoFinal = mergeByBooks(booksByName, mergeByBooks(booksByISBN, mergeByBooks(booksByAuthors, mergeByBooks(booksByDescription, booksByPublisher))));
		return resultadoFinal;
	}

	@Override
	public Book[] searchByAuthor(String key) {
		return searchByParameter(key, "authors");
	}

	@Override
	public Book searchByISBN(String key) {
		//String nKey = keyNormalize(key);
		return null;
	}

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

	private String[] mergeByString(String[] s1, String[] s2) {
		ArrayList<String> tags = new ArrayList<String>();
		for (String s : s1) tags.add(s);
		for (String s : s2) if (!tags.contains(s)) tags.add(s);
		return tags.toArray(new String[tags.size()]);
	}

	public Book[] searchByParameter(String key, String campo) {
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
		
		/*		Arrays.sort(livros, 
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

	// Métodos auxiliares
	
	/**
	 * <p>Normaliza a chave de pesquisa, retirando acentos e sinais, <br />
	 * deixando a  string em minúsculas e tirando os espaços.<br /></p>
	 * <p>Mantém, porém, caracteres de pontuação, símbolos e caraceres especiais.</p> 
	 * @author Giuliano
	 */
	private String keyNormalize(String value) {
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
		
		for (int i = 0; i < punctuation.length; i++)
			ret = ret.replaceAll(punctuation[i], ""); 
		
		System.out.println("Running time (keyNormalize): " + (System.currentTimeMillis() - start) + "ms.");
		return ret;
	}
	
	/**
	 * Extrai potenciais tags relevantes de uma string, que pode ser o título do livro.
	 * @author Giuliano
	 * @param value é a string a sofrer extração
	 * @return Retorna um array de strings com as tags encontradas.
	 */
	private String[] extractTags(String value) {
		long start = System.currentTimeMillis();
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
		
		System.out.println("Running time (extractTags): " + (System.currentTimeMillis() - start) + "ms.");
		
		return tags;
	}

	@Override
	public void connect(IDataBase arg0) {
		this.dataBase = arg0;	
	}

	@Override
	public int addRef() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInstanceId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int release() {
		// TODO Auto-generated method stub
		return 0;
	}

}
