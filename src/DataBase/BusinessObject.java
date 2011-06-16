package DataBase;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.Session;
import Classes.User;
import JavaDB.DataBase;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * 
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves
 */
@Component(id = "<http://purl.org/dcc/DataBase.BusinessObject>", provides = { "<http://purl.org/dcc/DataBase.IBusinessObject>" })
public class BusinessObject extends ComponentBase implements
		IBusinessObject {
	DataBase db;

	public BusinessObject() {
		// TODO fazer com que esse componente não instancie uma Classe DataBase
		// dentro ele, já que ela tb vai ser um componente

		// conectando o banco e inserindo os dados
		
		//TODO fazer conexao com o componente de JavaDB
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
		return deleteComment(comment.getID());
	}

	@Override
	public boolean deleteComment(int comment_id) {
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
	public boolean deleteRating(int rating_id) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + rating_id + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteRating(Rating rating) {
		return deleteRating(rating.getID());
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
	public boolean deleteRatingsForReview(int review_id) {
		Vector<String> where = new Vector<String>();
		where.add("review_id = '" + review_id + "'");

		return db.deleteDataRating(where);
	}

	@Override
	public boolean deleteRatingsForReview(Review review) {
		return deleteRatingsForReview(review.getID());
	}

	@Override
	public boolean deleteReview(int review_id) {
		Vector<String> where = new Vector<String>();
		where.add("review_id = '" + review_id + "'");

		return db.deleteDataReview(where);
	}

	@Override
	public boolean deleteReview(Review review) {
		return deleteReview(review.getID());
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
	public boolean deleteSession(Session session) {
		return deleteSession(session.getUsername());
	}

	@Override
	public boolean deleteSession(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataSession(where);
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
	public boolean insertSession(Session session) {
		return db.insertData(session);
	}
	
	@Override
	public boolean insertUser(User user) {
		return db.insertData(user);
	}
	
	@Override
	public Book selectBook(String isbn) {
		Book[] books = selectBooks("isbn", isbn, "isbn");
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
		Book[] result = db.queryBook(select, where, order);
		
		//coloca os valores no book correspondente a outras tabelas
		for (int i=0;i<result.length;i++) {
			result[i].setRating(selectRatingCalculed(result[i]));
			result[i].setComments(selectCommentsBook(result[i]));
			result[i].setReviews(selectReviews(result[i]));
		}

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
	public Comment selectComment(int comment_id) {
		Comment[] comments = selectComments("comment_id", comment_id, "comment_id");
		return comments[0];
	}

	private Comment[] selectComments(String colunnName, Object value, String orderBy) {	
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
		Comment[] result = db.queryComment(select, where, order);

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
	public Rating selectRating(int rating_id) {
		 Rating[] ratings = selectRatings("rating_id", rating_id, "rating_id");
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

	private Rating[] selectRatings(String colunnName, Object value, String orderBy) {	
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
	public Rating[] selectRatingsForReview(int review_id) {
		return selectRatings("review_id", review_id, "review_id");
	}

	@Override
	public Rating[] selectRatingsForReview(Review review) {
		return selectRatingsForReview(review.getID());
	}

	@Override
	public Review selectReview(int review_id) {
		Review[] reviews = selectReviews("review_id", review_id, "review_id");
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

	private Review[] selectReviews(String colunnName, Object value, String orderBy) {	
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
		Review[] result = db.queryReview(select, where, order);

		//coloca os valores relacionados a reviews que estao em outras tabelas
		for (int i=0;i<result.length;i++) {
			result[i].setRating(selectRatingCalculed(result[i]));			
			
			//por enquanto o banco de dados nao permite um review ser comentado
			//result[i].setComments(selectCommentsBook(result[i]));
		}
		
		
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
	public Session selectSession(String username) {
		// TODO por enquanto está fixo as colunas que retornam, era melhor retornar
		// todas as colunas da tabela
		Vector<String> select = new Vector<String>();
		select.add("username");
		select.add("status");
		select.add("lastLogin");

		// criando where
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add("username");

		// realizando a consulta
		Session[] result = db.querySession(select, where, order);

		return result[0];
	}

	@Override
	public User selectUser(String username) {
		User[] users = selectUsers("username", username, "username");
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
		User[] result = db.queryUser(select, where, order);

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
		where.add("comment_id = '" + comment.getID() + "'");

		return db.updateData(comment, where);
	}

	@Override
	public boolean updateRating(Rating rating) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + rating.getID() + "'");

		return db.updateData(rating, where);
	}

	@Override
	public boolean updateReview(Review review) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + review.getID() + "'");

		return db.updateData(review, where);
	}

	@Override
	public boolean updateSession(Session session) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + session.getUsername() + "'");

		return db.updateData(session, where);
	}

	@Override
	public boolean updateUser(User user) {
		Vector<String> where = new Vector<String>();
		where.add("rating_id = '" + user.getUsername() + "'");

		return db.updateData(user, where);
	}

	@Override
	public float selectRatingCalculed(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float selectRatingCalculed(String isbn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float selectRatingCalculed(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float selectRatingCalculed(int review_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
