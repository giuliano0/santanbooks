package JavaDB;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Review;
import Classes.User;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

@Component(id = "<http://purl.org/dcc/JavaDB.DataBaseComponent>", provides = { "<http://purl.org/dcc/JavaDB.IDataBaseComponent>" })
public class DataBaseComponent extends ComponentBase implements
		IDataBaseComponent {
	DataBase db;

	public DataBaseComponent() {
		// TODO fazer com que esse componente não instancie uma Classe DataBase
		// dentro ele, já que ela tb vai ser um componente

		// conectando o banco e inserindo os dados
		db = new DataBase();
		try {
			db.connectDataBase(); // deve sempre ser feito com try/catch
		} catch (SQLException e) {
			// na verdade nao deve fazer nada, pois essa excecao acontecera se o
			// banco ja existir
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean deleteBook(Book book) {
		return deleteBook(book.getISBN());
	}

	@Override
	public boolean deleteBook(String isnb) {
		Vector<String> where = new Vector<String>();
		where.add("isbn = '"+isnb+"'");
		
		if (db.deleteDataBook(where)) {
			return true;
		}

		return false;
	}

	@Override
	public DataBase getConnection() {
		return db;
	}

	@Override
	public boolean insertBook(Book book) {
		if (db.insertData(book)) {
			return true;
		}

		return false;
	}

	@Override
	public Book selectBook(String isbn) {
		Book books[] = selectBooks("isbn", isbn, "isbn");
		return books[0];
	}

	private Book[] selectBooks(String colunnName, String value, String orderBy) {
		// criando select

		// por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("isbn");
		select.add("name");
		select.add("authors");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add(colunnName + " = '" + value + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add(orderBy);

		// realizando a consulta
		Book result[] = db.queryBook(select, where, order);

		return result;
	}

	@Override
	public Book[] selectBooksByAuthors(String authors) {
		return selectBooks("authors", authors, "authors");
	}

	@Override
	public Book[] selectBooksByName(String name) {
		return selectBooks("name", name, "name");
	}

	@Override
	public Review[] selectReviews(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	// pessando que o usuario não vai alterar mais o isbn do livro, então
	// poderemos fazer um update do livro baseando-se no isnb
	@Override
	public boolean updateBook(Book book) {
		
		Vector<String> where = new Vector<String>();
		where.add("isbn = '"+book.getISBN()+"'");
		
		if (db.updateData(book, where)){
			return true;
		}
		
		return false;
	}

}
