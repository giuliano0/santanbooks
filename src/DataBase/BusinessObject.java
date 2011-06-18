package DataBase;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.Session;
import Classes.User;
import Exceptions.InvalidArgumentException;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

/**
 * Esse componente disponibilza metodos principais para consulta no banco de dados,
 * fazendo com que a consulta, insercao, delecao, e atualizacao seja mais simples de se fazer
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves
 */
@Component(id = "<http://purl.org/dcc/DataBase.BusinessObject>", 
		provides = { "<http://purl.org/dcc/Interfaces.IBusinessObject>"},
		requires= { "<http://purl.org/dcc/Interfaces.IDataBase>" })
public class BusinessObject extends ComponentBase implements IBusinessObject, IRequires<IDataBase> {
	private IDataBase db;

	// TODO quando fazer um insert update tb tem que atualizar os ratings, comments e reviews associados
	

	@Override
	public void connect(IDataBase dataBase) {
		db = dataBase;
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
	public boolean deleteBook(String isbn) {
		
		deleteCommentsByIsbn(isbn);
		deleteRatings(isbn);
		deleteReviews(isbn);
				
		Vector<String> where = new Vector<String>();
		where.add("isbn = '" + isbn + "'");

		return db.deleteDataBook(where);
	}

	@Override
	public boolean deleteComment(Comment comment) {
		return deleteComment(comment.getID());
	}

	@Override
	public boolean deleteComment(int comment_id) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + comment_id);

		return db.deleteDataComment(where);
	}

	@Override
	public boolean deleteComments(Book book) {
		return deleteCommentsByIsbn(book.getISBN());
	}

	@Override
	public boolean deleteCommentsByIsbn(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("bookISBN = '" + isbn + "'");

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
		where.add("id = " + rating_id);

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
		where.add("bookISBN = '" + isbn + "'");

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
		where.add("bookReview = " + review_id);

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
		where.add("bookISBN = '" + isbn + "'");

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
	public boolean insertBook(Book book) {
		Comment[] comments = book.getAllComments();
		if (comments != null) {
			for (Comment comment:comments) {
				insertComment(comment);
			}
		}
				
		Review[] reviews = book.getAllReviews();
		if (reviews != null) {
			for (Review review:reviews) {
				insertReview(review);
			}
		}
		
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
	public Book[] selectAllBooks() {
		return selectBooks(null, null, null);
	}

	@Override
	public Comment[] selectAllComments() {
		return selectComments(null, null, null);
	}

	@Override
	public Rating[] selectAllRatings() {
		return selectRatings(null, null, null);
	}

	@Override
	public Review[] selectAllReviews() {
		return selectReviews(null, null, null);
	}

	@Override
	public User[] selectAllUsers() {
		return selectUsers(null, null, null);
	}

	@Override
	public Book selectBook(String isbn) {
		Book[] books = selectBooks("isbn", isbn, "isbn");

		if (books != null) {
			return books[0];
		}

		return null;
	}

	private Book[] selectBooks(String colunnName, String value, String orderBy) {
		Vector<String> select = new Vector<String>();
		select.add("isbn");
		select.add("name");
		select.add("authors");
		select.add("description");
		select.add("edition");
		select.add("imagepath");
		select.add("publisher");
		select.add("publishingDate");

		Vector<String> where = null;
		Vector<String> order = null;
		// criando where
		if (colunnName != null && value != null) {
			where = new Vector<String>();
			where.add(colunnName + " = '" + value + "'");
		}

		// criando order
		if (orderBy != null) {
			order = new Vector<String>();
			order.add(orderBy);
		}

		// realizando a consulta
		Book[] result = db.queryBook(select, where, order);

		// coloca os valores no book correspondente a outras tabelas
		if (result != null) {
			for (int i = 0; i < result.length; i++) {
				try {
					result[i].setRating(selectRatingCalculed(result[i]));
				} catch (InvalidArgumentException e) {
					e.printStackTrace();
				}
				result[i].setComments(selectCommentsBook(result[i]));
				result[i].setReviews(selectReviews(result[i]));
			}
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
		Comment[] comments = selectComments("id", comment_id, "id");

		if (comments != null) {
			return comments[0];
		}

		return null;
	}

	private Comment[] selectComments(String colunnName, Object value,
			String orderBy) {
		Vector<String> select = new Vector<String>();
		select.add("id");
		select.add("username");
		select.add("bookISBN");
		select.add("content");
		select.add("publishingDate");

		Vector<String> where = null;
		Vector<String> order = null;
		// criando where
		if (colunnName != null && value != null) {
			where = new Vector<String>();
			where.add(colunnName + " = '" + value + "'");
		}

		// criando order
		if (orderBy != null) {
			order = new Vector<String>();
			order.add(orderBy);
		}

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
		return selectComments("bookISBN", isbn, "bookISBN");
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
		Rating[] ratings = selectRatings("id", rating_id, "id");

		if (ratings != null) {
			return ratings[0];
		}

		return null;
	}

	@Override
	public float selectRatingCalculed(Book book) {
		return selectRatingCalculed(book.getISBN());
	}

	@Override
	public float selectRatingCalculed(int review_id) {

		if (selectRatingsForReview(review_id).length > 0) {
			Vector<String> select = new Vector<String>();
			select.add("value/count(value) as value");

			// criando where
			Vector<String> where = new Vector<String>();
			where.add("bookReview = " + review_id);

			// criando order
			Vector<String> order = new Vector<String>();
			order.add("value");

			// realizando a consulta
			Rating[] result = db.queryRating(select, where, order);

			if (result != null) {
				return result[0].getValue();
			}
		}

		return 0;
	}

	@Override
	public float selectRatingCalculed(Review review) {
		return selectRatingCalculed(review.getID());
	}

	@Override
	public float selectRatingCalculed(String isbn) {

		if (selectRatings(isbn).length > 0) {
			System.out.println("test");
			Vector<String> select = new Vector<String>();
			select.add("value/count(value) as value");

			// criando where
			Vector<String> where = new Vector<String>();
			where.add("bookISBN = '" + isbn + "'");

			// criando order
			Vector<String> order = new Vector<String>();
			order.add("value");

			// realizando a consulta
			Rating[] result = db.queryRating(select, where, order);

			if (result != null) {
				return result[0].getValue();
			}
		}

		return 0;
	}

	@Override
	public Rating[] selectRatings(Book book) {
		return selectRatings(book.getISBN());
	}

	@Override
	public Rating[] selectRatings(String isbn) {
		return selectRatings("bookISBN", isbn, "bookISBN");
	}

	private Rating[] selectRatings(String colunnName, Object value,
			String orderBy) {
		Vector<String> select = new Vector<String>();
		select.add("id");
		select.add("username");
		select.add("bookISBN");
		select.add("bookReview");
		select.add("value");
		select.add("type");

		Vector<String> where = null;
		Vector<String> order = null;
		// criando where
		if (colunnName != null && value != null) {
			where = new Vector<String>();
			where.add(colunnName + " = '" + value + "'");
		}

		// criando order
		if (orderBy != null) {
			order = new Vector<String>();
			order.add(orderBy);
		}

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
	public Rating[] selectRatingsForReview(int rating_id) {
		return selectRatings("id", rating_id, "id");
	}

	@Override
	public Rating[] selectRatingsForReview(Review review) {
		return selectRatingsForReview(review.getID());
	}

	@Override
	public Review selectReview(int review_id) {
		Review[] reviews = selectReviews("id", review_id, "id");

		if (reviews != null) {
			return reviews[0];
		}

		return null;
	}

	@Override
	public Review[] selectReviews(Book book) {
		return selectReviews(book.getISBN());
	}

	@Override
	public Review[] selectReviews(String isbn) {
		return selectReviews("bookISBN", isbn, "bookISBN");
	}

	private Review[] selectReviews(String colunnName, Object value,
			String orderBy) {
		Vector<String> select = new Vector<String>();
		select.add("id");
		select.add("username");
		select.add("bookISBN");
		select.add("content");
		select.add("publishingDate");
		select.add("title");

		Vector<String> where = null;
		Vector<String> order = null;
		// criando where
		if (colunnName != null && value != null) {
			where = new Vector<String>();
			where.add(colunnName + " = '" + value + "'");
		}

		// criando order
		if (orderBy != null) {
			order = new Vector<String>();
			order.add(orderBy);
		}

		// realizando a consulta
		Review[] result = db.queryReview(select, where, order);

		// coloca os valores relacionados a reviews que estao em outras tabelas
		if (result != null) {
			for (int i = 0; i < result.length; i++) {
				try {
					result[i].setRating(selectRatingCalculed(result[i]));
				} catch (InvalidArgumentException e) {
					e.printStackTrace();
				}

				// por enquanto o banco de dados nao permite um review ser
				// comentado
				// result[i].setComments(selectCommentsBook(result[i]));
			}
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

		if (result != null) {
			return result[0];
		}

		return null;
	}

	@Override
	public User selectUser(String username) {
		User[] users = selectUsers("username", username, "username");

		if (users != null) {
			return users[0];
		}

		return null;
	}

	private User[] selectUsers(String colunnName, String value, String orderBy) {
		Vector<String> select = new Vector<String>();
		select.add("username");
		select.add("accessLevel");
		select.add("birthday");
		select.add("college");
		select.add("course");
		select.add("email");
		select.add("gender");
		select.add("name");
		select.add("password");
		select.add("selfDescription");
		select.add("ingressYear");

		Vector<String> where = null;
		Vector<String> order = null;
		// criando where
		if (colunnName != null && value != null) {
			where = new Vector<String>();
			where.add(colunnName + " = '" + value + "'");
		}

		// criando order
		if (orderBy != null) {
			order = new Vector<String>();
			order.add(orderBy);
		}

		// realizando a consulta
		User[] result = db.queryUser(select, where, order);

		return result;
	}

	@Override
	public boolean updateBook(Book book) {
						
		Comment[] comments = book.getAllComments();
		if (comments != null) {
			for (Comment comment: comments){
				updateComment(comment);
			}		
		}
		
		Review[] reviews = book.getAllReviews();
		if (reviews != null) {
			for(Review review: reviews){
				updateReview(review);
			}
		}
		
		
		Vector<String> where = new Vector<String>();
		where.add("isbn = '" + book.getISBN() + "'");

		return db.updateData(book, where);
	}

	@Override
	public boolean updateComment(Comment comment) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + comment.getID());

		return db.updateData(comment, where);
	}

	@Override
	public boolean updateRating(Rating rating) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + rating.getID());

		return db.updateData(rating, where);
	}

	@Override
	public boolean updateReview(Review review) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + review.getID());

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
		where.add("id = '" + user.getUsername());

		return db.updateData(user, where);
	}

}
