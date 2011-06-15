package JavaDB;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.User;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * 
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves
 */
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
		where.add("isbn = '" + isnb + "'");

		return db.deleteDataBook(where);
	}

	@Override
	public boolean deleteComment(Comment comment) {		
		return deleteComment(comment.getIdentifier());
	}

	@Override
	public boolean deleteComment(String comment_id) {
		Vector<String> where = new Vector<String>();
		where.add("comment_id = '" + comment_id + "'");

		return db.deleteDataComment(where);
	}

	@Override
	public boolean deleteComments(Book book) {		
		return deleteCommentsByIsbn(book.getISBN());
	}

	@Override
	public boolean deleteCommentsByIsbn(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("isnb = '" + isbn + "'");

		return db.deleteDataComment(where);
	}

	@Override
	public boolean deleteCommentsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataComment(where);
	}

	@Override
	public boolean deleteCommentsByUser(User user) {
		return deleteCommentsByUser(user.getUsername());
	}

	@Override
	public boolean deleteRating(Rating rating) {
		return deleteRating(rating.getIdentifier());
	}

	@Override
	public boolean deleteRating(String rating_id) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + rating_id + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteRatings(Book book) {
		return deleteRatings(book.getISBN());
	}

	@Override
	public boolean deleteRatings(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("isbn = '" + isbn + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteRatingsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteRatingsByUser(User user) {
		return deleteRatingsByUser(user.getUsername());
	}

	@Override
	public boolean deleteRatingsForReview(Review review) {
		return deleteRatingsForReview(review.getIdentifier());
	}

	@Override
	public boolean deleteRatingsForReview(String review_id) {
		Vector<String> where = new Vector<String>();
		where.add("review_id = '" + review_id + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteReview(Review review) {
		return deleteReview(review.getIdentifier());
	}

	@Override
	public boolean deleteReview(String review_id) {
		Vector<String> where = new Vector<String>();
		where.add("review_id = '" + review_id + "'");

		return db.deleteDataReview(where);
	}

	@Override
	public boolean deleteReviews(Book book) {
		return deleteReviews(book.getISBN());
	}

	@Override
	public boolean deleteReviews(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("review_id = '" + isbn + "'");

		return db.deleteDataReview(where);
	}

	@Override
	public boolean deleteReviewsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataReview(where);
	}

	@Override
	public boolean deleteReviewsByUser(User user) {
		return deleteReviewsByUser(user.getUsername());
	}

	@Override
	public boolean deleteUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataUser(where);
	}

	@Override
	public boolean deleteUser(User user) {
		return deleteUser(user.getUsername());
	}

	@Override
	public DataBase getConnection() {
		return db;
	}

	@Override
	public boolean insertBook(Book book) {
		return db.insertData(book);
	}

	@Override
	public boolean insertComment(Comment comment) {
		return db.insertData(comment);
	}

	@Override
	public boolean insertRating(Rating rating) {
		return db.insertData(rating);
	}

	@Override
	public boolean insertReview(Review review) {
		return db.insertData(review);
	}

	@Override
	public boolean insertUser(User user) {
		return db.insertData(user);
	}

	@Override
	public Book selectBook(String isbn) {
		Book books[] = selectBooks("isbn", isbn, "isbn");
		return books[0];
	}

	private Book[] selectBooks(String colunnName, String value, String orderBy) {	
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
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
	public Comment selectComment(String comment_id) {
		Comment comments[] = selectComments("comment_id", comment_id, "comment_id");
		return comments[0];
	}
	
	private Comment[] selectComments(String colunnName, String value, String orderBy) {	
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("content");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add(colunnName + " = '" + value + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add(orderBy);

		// realizando a consulta
		Comment result[] = db.queryComment(select, where, order);

		return result;
	}

	@Override
	public Comment[] selectCommentsBook(Book book) {
		return selectCommentsBookByIsbn(book.getISBN());
	}

	@Override
	public Comment[] selectCommentsBookByIsbn(String isbn) {
		return selectComments("isbn", isbn, "isbn");
	}

	@Override
	public Comment[] selectCommentsBookFromUser(String username) {
		return selectComments("username", username, "username");
	}

	@Override
	public Comment[] selectCommentsBookFromUser(User user) {
		return selectCommentsBookFromUser(user.getUsername());
	}

	@Override
	public Rating selectRating(String rating_id) {
		 Rating ratings[] = selectRatings("rating_id", rating_id, "rating_id");
		 return ratings[0];
	}

	@Override
	public Rating[] selectRatings(Book book) {
		return selectRatings(book.getISBN());
	}

	@Override
	public Rating[] selectRatings(String isbn) {
		return selectRatings("isbn", isbn, "isbn");
	}

	private Rating[] selectRatings(String colunnName, String value, String orderBy) {	
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("value");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add(colunnName + " = '" + value + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add(orderBy);

		// realizando a consulta
		Rating[] result = db.queryRating(select, where, order);

		return result;
	}

	@Override
	public Rating[] selectRatingsByUser(String username) {
		return selectRatings("username", username, "username");
	}

	@Override
	public Rating[] selectRatingsByUser(User user) {
		return selectRatingsByUser(user.getUsername());
	}

	@Override
	public Rating[] selectRatingsForReview(Review review) {
		return selectRatingsForReview(review.getIdentifier());
	}

	@Override
	public Rating[] selectRatingsForReview(String review_id) {
		return selectRatings("review_id", review_id, "review_id");
	}

	@Override
	public Review selectReview(String review_id) {
		Review reviews[] = selectReviews("review_id", review_id, "review_id");
		return reviews[0];
	}

	@Override
	public Review[] selectReviews(Book book) {
		return selectReviews(book.getISBN());
	}

	@Override
	public Review[] selectReviews(String isbn) {
		return selectReviews("isbn", isbn, "isbn");
	}

	private Review[] selectReviews(String colunnName, String value, String orderBy) {	
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("comment");
		select.add("name");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add(colunnName + " = '" + value + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add(orderBy);

		// realizando a consulta
		Review result[] = db.queryReview(select, where, order);

		return result;
	}

	@Override
	public Review[] selectReviewsByUser(String username) {
		return selectReviews("username", username, "username");
	}

	@Override
	public Review[] selectReviewsByUser(User user) {
		return selectReviewsByUser(user.getUsername());
	}

	@Override
	public User selectUser(String username) {
		User users[] = selectUsers("username", username, "username");
		return users[0];
	}

	private User[] selectUsers(String colunnName, String value, String orderBy) {	
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("username");
		select.add("name");
		select.add("gender");
		select.add("email");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add(colunnName + " = '" + value + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add(orderBy);

		// realizando a consulta
		User result[] = db.queryUser(select, where, order);

		return result;
	}

	
	@Override
	public boolean updateBook(Book book) {
		Vector<String> where = new Vector<String>();
		where.add("isbn = '" + book.getISBN() + "'");

		return db.updateData(book, where);
	}

	@Override
	public boolean updateComment(Comment comment) {
		Vector<String> where = new Vector<String>();
		where.add("comment_id = '" + comment.getIdentifier() + "'");

		return db.updateData(comment, where);
	}

	@Override
	public boolean updateRating(Rating rating) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + rating.getIdentifier() + "'");

		return db.updateData(rating, where);
	}

	@Override
	public boolean updateReview(Review review) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + review.getIdentifier() + "'");

		return db.updateData(review, where);
	}

	@Override
	public boolean updateUser(User user) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + user.getIdentifier() + "'");

		return db.updateData(user, where);
	}

}
