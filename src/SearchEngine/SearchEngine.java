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
 * @remarks Pessoal, lembrem-se de colocar um coment�rio como este, de Javadoc <br />
 * no c�digo que voc�s escreverem, e salvem numa planilha o que vcs fizeram, precisaremos <br />
 * dessa informa��o mais tarde! Thanks ;)
 */
@Component(id="<http://purl.org/dcc/santanbooks.SearchEngine.SearchEngine>",
        provides={"<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>"})
public class SearchEngine implements ISearchEngine, IRequires<IDataBase> { // TODO: colocar o IRequires<IDataBase>, ajuda aqui please =)

	// Preposi��es, artigos, conjun��es, e tudo mais (pontua��o, sinais gr�ficos). 42!
	// Bithces, ajudem a popular o array abaixo, kkthxbye ;)
	private static String[] notTags = {"a", "as", "o", "os", "e", "de", "do", "dos", "da", "das", 
						"para", "como", "com", "em", "no", "nos", "na", "nas", "uns", "umas", 
						"um", "algum", "alguem", "nenhum", "ninguem", "todo", "todos", "toda", "todas", "tudo"};

	// Verificar documenta��o do java para express�es regulares a respeito dos caracteres comentados abaixo.
	// Eles s�o usados no Regex, e na verdade esse array inteiro pode ser substitu�do por um Regex Pattern...
	// Metacaracteres do Regex: ([{\^-$|]})?*+.
	private static final String[] punctuation = {",", "\\.", ":", "\\-", ";", "\\?", "!", "\\(", "\\)", "\\n"};
	
	private IDataBase dataBase;
	
	// TODO: Declarar a database e a factory, e instanci�-la no construtor.

	public SearchEngine() {
		Arrays.sort(notTags);
	}

	@Override
	public Book[] search(String key) {
		Vector<String> select = new Vector<String>();
		Vector<String> where = new Vector<String>();
		Vector<String> order = new Vector<String>();
		select.add("name"); // Falta ver um jeito de selecionar todos os campo
		select.add("isbn");
		select.add("authors");
		select.add("description");
		select.add("edition");
		select.add("imagePath");
		select.add("publisher");
		select.add("publishingDate");
		
		// 1� - Procura pela express�o exata
		where.add("name = '" + key + "'");
		order.add(""); // colocar aqui o jeito pra ordernar
		Book[] book = dataBase.queryBook(select, where, order);

		// 2� - Procura por nomes semelhantes as tags procuradas
		String[] nKey = extractTags(key);
		where.clear();
		where.add("name LIKE '%" + nKey[0] + "%'");
		if (nKey.length > 1)
			for (int i = 1; i < nKey.length - 1; i++)
				where.add("name LIKE '%" + nKey[i] + "%'");
		Book[] books = dataBase.queryBook(select, where, order);
		
		Book[] livros = new Book[books.length + book.length];
		
		for (int i=0; i< livros.length; i++)
			if (i < book.length)
				livros[i] = book[i];
			else
				livros[i] = books[i - book.length];
		
		Arrays.sort(livros, 
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
		
		/*
			Depois disso, juntar os dois resultados "book" e "books", com o primeiro com maior prioridade que o segundo.
			Por enquanto ele busca apenas pelo nome, mas uma id�ia seria buscar pelos outros campos tamb�m,
		com uma prioridade entre eles.
			No final, juntar os dois resultados em um j� ordenado na ordemd e mostrar e retornar isso.
		*/
		
		return livros;
	}
	
	public Book[] mergeBooks(Book[] b1, Book[] b2) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (Book b : b1) books.add(b);
		for (Book b : b2) if (!books.contains(b)) books.add(b);
		return books.toArray(new Book[books.size()]);
	}
	
	@Override
	public Book[] searchByAuthor(String key) {
		//String nKey = keyNormalize(key);
		//Book[] books = mgrBusinessObject.selectBooksByAuthors(authors);

		return null;
	}

	@Override
	public Book searchByISBN(String key) {
		//String nKey = keyNormalize(key);
		
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
		long start = System.currentTimeMillis();
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
		
		for (int i = 0; i < punctuation.length; i++)
			ret = ret.replaceAll(punctuation[i], ""); 
		
		System.out.println("Running time (keyNormalize): " + (System.currentTimeMillis() - start) + "ms.");
		return ret;
	}
	
	/**
	 * Extrai potenciais tags relevantes de uma string, que pode ser o t�tulo do livro.
	 * @author Giuliano
	 * @param value � a string a sofrer extra��o
	 * @return Retorna um array de strings com as tags encontradas.
	 */
	public String[] extractTags(String value) {
		long start = System.currentTimeMillis();
		String tags[];
		
		// remove, al�m de acentos, pontua��o
		value = keyNormalize(value);
		
		// adiciona um espa�o no come�o e no final da string para o replaceAll()
		value = " " + value + " ";
		
		// Remove cada match de n�o-tag da string
		for (int i = 0; i < notTags.length; i++)
			value = value.replaceAll(" " + notTags[i] + " ", " ");
		
		// trim dos espa�os extras
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
