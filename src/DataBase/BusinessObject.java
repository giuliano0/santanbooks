package DataBase;

import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.SessionData;
import Classes.User;
import Exceptions.InvalidArgumentException;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISearchEngine;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

/**
 * Esse componente disponibilza metodos principais para consulta no banco de dados,
 * fazendo com que a consulta, insercao, delecao, e atualizacao seja mais simples de se fazer
 * 
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves
 */

@Component(id = "<http://purl.org/dcc/DataBase.BusinessObject>", 
		provides = { "<http://purl.org/dcc/Interfaces.IBusinessObject>"},
		requires= { "<http://purl.org/dcc/Interfaces.IDataBase>" })
public class BusinessObject extends ComponentBase implements IBusinessObject, IRequires<IDataBase> {
	private IDataBase db;
	private ISearchEngine se;
	
	private enum RatingType {
		BOOK, REVIEW
	}

    /**
     * Conecta com o banco de dados 
     * @param dataBase IDataBase
     */
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

    /**
     * Deleta um livro pela entidade
     * @param book Book
     * @return boolean 
     */
	public boolean deleteBook(Book book) {
		return deleteBook(book.getISBN());
	}

    /**
     * Deleta um livro pelo isbn 
     * @param isbn String
     * @return boolean 
     */
	public boolean deleteBook(String isbn) {	
		deleteCommentsByIsbn(isbn);
		deleteRatings(isbn);
		deleteReviews(isbn);
				
		Vector<String> where = new Vector<String>();
		where.add("isbn = '" + isbn + "'");
		boolean success = db.deleteDataBook(where);
		if(se != null) {
			se.update();
		}
		return success;
	}

	/**
     * Deleta um comentario pela entidade 
     * @param comment Comment
     * @return boolean
     */
	public boolean deleteComment(Comment comment) {
		return deleteComment(comment.getID());
	}

	/**
     * Deleta um comentario pelo ID 
     * @param commentID int
     * @return boolean 
     */
	public boolean deleteComment(int commentID) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + commentID);

		return db.deleteDataComment(where);
	}

	/**
     * Deleta um comentario pela entidade de um livro 
     * @param book Book
     * @return boolean 
     */
	public boolean deleteComments(Book book) {
		return deleteCommentsByIsbn(book.getISBN());
	}

	/**
     * Deleta um comentario pelo isbn de um livro 
     * @param isbn String
     * @return boolean 
     */
	public boolean deleteCommentsByIsbn(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("bookISBN = '" + isbn + "'");

		return db.deleteDataComment(where);
	}

	/**
     * Deleta um comentario pelo nome do usuario 
     * @param username String
     * @return boolean 
     */
	public boolean deleteCommentsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataComment(where);
	}

	/**
     * Deleta um comentario pela entidade de um usuario
     * @param user User
     * @return boolean 
     */
	public boolean deleteCommentsByUser(User user) {
		return deleteCommentsByUser(user.getUsername());
	}

	/**
     * Deleta uma avaliacao pelo ID 
     * @param ratingID int
     * @return boolean 
     */
	public boolean deleteRating(int ratingID) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + ratingID);

		return db.deleteDataRating(where);
	}

	/**
     * Deleta uma avaliacao pela entidade 
     * @param rating Rating
     * @return boolean 
     */
	public boolean deleteRating(Rating rating) {
		return deleteRating(rating.getID());
	}

	/**
     * Deleta uma avaliacao pela entidade do livro 
     * @param book Book
     * @return boolean 
     */
	public boolean deleteRatings(Book book) {
		return deleteRatings(book.getISBN());
	}

	/**
     * Deleta uma avaliacao pelo isbn do livro
     * @param isbn String
     * @return boolean 
     */
	public boolean deleteRatings(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("bookISBN = '" + isbn + "'");

		return db.deleteDataRating(where);
	}

	/**
     * Deleta uma avaliacao pelo nome do usuario 
     * @param username String
     * @return boolean 
     */
	public boolean deleteRatingsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataRating(where);
	}

	/**
     * Deleta uma avaliacao pela entidade do usuario 
     * @param user User
     * @return boolean 
     */
	public boolean deleteRatingsByUser(User user) {
		return deleteRatingsByUser(user.getUsername());
	}

	/**
     * Deleta uma avaliacao pelo id da review 
     * @param reviewID int
     * @return boolean 
     */
	public boolean deleteRatingsForReview(int reviewID) {
		Vector<String> where = new Vector<String>();
		where.add("bookReview = " + reviewID);

		return db.deleteDataRating(where);
	}

	/**
     * Deleta uma avaliacao pela entidade da review 
     * @param review Review
     * @return boolean 
     */
	public boolean deleteRatingsForReview(Review review) {
		return deleteRatingsForReview(review.getID());
	}

	/**
     * Deleta uma review pelo ID 
     * @param reviewID int
     * @return boolean 
     */
	public boolean deleteReview(int reviewID) {
		Vector<String> where = new Vector<String>();
		where.add("reviewID = '" + reviewID + "'");

		return db.deleteDataReview(where);
	}

	/**
     * Deleta uma review pela entidade 
     * @param review Review
     * @return boolean 
     */
	public boolean deleteReview(Review review) {
		return deleteReview(review.getID());
	}

	/**
     * Deleta uma review pela entidade do livro 
     * @param book Book
     * @return boolean 
     */
	public boolean deleteReviews(Book book) {
		return deleteReviews(book.getISBN());
	}

	/**
     * Deleta uma review pelo isbn do livro 
     * @param isbn String
     * @return boolean 
     */
	public boolean deleteReviews(String isbn) {
		Vector<String> where = new Vector<String>();
		where.add("bookISBN = '" + isbn + "'");

		return db.deleteDataReview(where);
	}

	/**
     * Deleta uma review pelo nome do usuario 
     * @param username String
     * @return boolean 
     */
	public boolean deleteReviewsByUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataReview(where);
	}

	/**
     * Deleta uma review pela entidade do usuario 
     * @param user User
     * @return boolean 
     */
	public boolean deleteReviewsByUser(User user) {
		return deleteReviewsByUser(user.getUsername());
	}

	/**
     * Deleta uma sessao pela entidade 
     * @param session SessionData
     * @return boolean 
     */
	public boolean deleteSession(SessionData session) {
		return deleteSession(session.getUsername());
	}

	/**
     * Deleta uma sessao pelo nome do usuario 
     * @param userName String
     * @return boolean 
     */
	public boolean deleteSession(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataSessionData(where);
	}

	/**
     * Deleta um usuario pelo nome 
     * @param username String
     * @return boolean 
     */
	public boolean deleteUser(String username) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + username + "'");

		return db.deleteDataUser(where);
	}

	/**
     * Deleta um usuario pela entidade 
     * @param user User
     * @return boolean 
     */
	public boolean deleteUser(User user) {
		return deleteUser(user.getUsername());
	}

	/**
     * Insere um livro 
     * @param book Book
     * @return boolean 
     */
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
		boolean success = db.insertData(book);
		if(se != null) {
			se.update();
		}
		return success;
	}

	/**
     * Insere um comentario 
     * @param comment Comment
     * @return boolean 
     */
	public boolean insertComment(Comment comment) {
		return db.insertData(comment);
	}

	/**
     * Insere uma avaliacao 
     * @param rating Rating
     * @return boolean 
     */
	public boolean insertRating(Rating rating) {
		return db.insertData(rating);
	}

	/**
     * Insere uma review 
     * @param review Review
     * @return boolean 
     */
	public boolean insertReview(Review review) {
		return db.insertData(review);
	}

	/**
     * Insere uma sessao 
     * @param session SessionData
     * @return boolean 
     */
	public boolean insertSession(SessionData session) {
		return db.insertData(session);
	}

	/**
     * Insere um usuario 
     * @param user User
     * @return boolean 
     */
	public boolean insertUser(User user) {
		return db.insertData(user);
	}

	/**
     * Obtem todos os livros 
     * @return Book[]
     */
	public Book[] selectAllBooks() {
		return selectBooks(null, null, null);
	}

	/**
     * Obtem todos os comentarios 
     * @return Comment[]
     */
	public Comment[] selectAllComments() {
		return selectComments(null, null, null);
	}

	/**
     * Obtem todas avaliacoes 
     * @return Rating[]
     */
	public Rating[] selectAllRatings() {
		return selectRatings(null, null, null, null);
	}

	/**
     * Obtem todas as reviews 
     * @return Review[]
     */
	public Review[] selectAllReviews() {
		return selectReviews(null, null, null);
	}

	/**
     * Obtem todos os usuarios 
     * @return User[]
     */
	public User[] selectAllUsers() {
		return selectUsers(null, null, null);
	}

	/**
     * Obtem o livro pelo isbn
     * @param isbn String 
     * @return Book[]
     */
	public Book selectBook(String isbn) {
		Book[] books = selectBooks("isbn", isbn, "isbn");

		if (books != null) {
			return books[0];
		}

		return null;
	}

	/**
     * Obtem os livros pela coluna, pelo valor e ordenacao
     * @param colunnName String
     * @param value String
     * @param orderBy String
     * @return Book[]
     */
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
				result[i].setRating(selectRatingCalculed(result[i]));
				result[i].setComments(selectCommentsBook(result[i]));
				result[i].setReviews(selectReviews(result[i]));
			}
		}

		return result;
	}

	/**
     * Obtem os livros pelos autores
     * @param authors String
     * @return Book[]
     */
	public Book[] selectBooksByAuthors(String authors) {
		return selectBooks("authors", authors, "authors");
	}

	/**
     * Obtem os livros pelos nomes
     * @param name String
     * @return Book[]
     */
	public Book[] selectBooksByName(String name) {
		return selectBooks("name", name, "name");
	}

	/**
     * Obtem o comentario pelo ID
     * @param commentID int
     * @return Comment
     */
	public Comment selectComment(int commentID) {
		Comment[] comments = selectComments("id", commentID, "id");

		if (comments != null) {
			return comments[0];
		}

		return null;
	}

	/**
     * Obtem os comentarios pela coluna, pelo valor e ordenacao
     * @param colunnName String
     * @param value Object
     * @param orderBy String
     * @return Comment[]
     */
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
			
			if(value instanceof Integer) {
				where.add(colunnName + " = " + value);
			}
			else {
				where.add(colunnName + " = '" + value + "'");
			}
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

	/**
     * Obtem os comentarios pela entidade do livro
     * @param book Book
     * @return Comment[]
     */
	public Comment[] selectCommentsBook(Book book) {
		return selectCommentsBookByIsbn(book.getISBN());
	}

	/**
     * Obtem os comentarios pelo isbn do livro
     * @param isbn String
     * @return Comment[]
     */
	public Comment[] selectCommentsBookByIsbn(String isbn) {
		return selectComments("bookISBN", isbn, "bookISBN");
	}

	/**
     * Obtem os comentarios pelo nome do usuario
     * @param username String
     * @return Comment[]
     */
	public Comment[] selectCommentsBookFromUser(String username) {
		return selectComments("username", username, "username");
	}

	/**
     * Obtem os comentarios pela entidade do usuario
     * @param user User
     * @return Comment[]
     */
	public Comment[] selectCommentsBookFromUser(User user) {
		return selectCommentsBookFromUser(user.getUsername());
	}

	/**
     * Obtem a avaliacao pelo ID
     * @param ratingID int
     * @return Rating
     */
	public Rating selectRating(int ratingID) {
		Rating[] ratings = selectRatings("id", ratingID, "id", null);

		if (ratings != null) {
			return ratings[0];
		}

		return null;
	}

	/**
     * Obtem a avaliacao calculada pela entidade do livro
     * @param book Book
     * @return float
     */
	public float selectRatingCalculed(Book book) {
		return selectRatingCalculed(book.getISBN());
	}

	/**
     * Obtem a avaliacao calculada pelo ID da review
     * @param reviewID int
     * @return float
     */
	public float selectRatingCalculed(int reviewID) {

		if (selectRatingsForReview(reviewID).length > 0) {
			Vector<String> select = new Vector<String>();
			select.add("value/count(value) as value");

			// criando where
			Vector<String> where = new Vector<String>();
			where.add("bookReview = " + reviewID);

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

	/**
     * Obtem a avaliacao calculada pela entidade do review
     * @param review Review
     * @return float
     */
	public float selectRatingCalculed(Review review) {
		return selectRatingCalculed(review.getID());
	}

	/**
     * Obtem a avaliacao calculada pelo isbn do livro
     * @param isbn String
     * @return float
     */
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

	/**
     * Obtem todas as avaliacao
     * @return Rating[]
     */
	public Rating[] selectRatings(Book book) {
		return selectRatings(book.getISBN());
	}

	/**
     * Obtem as avaliacaos pelo isbn do livro
     * @param isbn String
     * @return Rating[]
     */
	public Rating[] selectRatings(String isbn) {
		return selectRatings("bookISBN", isbn, "bookISBN", RatingType.BOOK);
	}

	/**
     * Obtem as avaliacaos pela coluna, pelo valor, pela ordenacao e pelo tipo
     * @param colunnName String
     * @param value Object
     * @param orderBy String
     * @param type RatingType
     * @return Rating[]
     */
	private Rating[] selectRatings(String colunnName, Object value,
			String orderBy, RatingType type) {
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
			if(value instanceof Integer) {
				where.add(colunnName + " = " + value);
			}
			else {
				where.add(colunnName + " = '" + value + "'");
			}
		}
		
		if (type != null) {
			where.add("type = " + ((type == RatingType.BOOK)?1:0));				
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

	/**
     * Obtem as avaliacaos pelo nome do usuario
     * @param username String
     * @return Rating[]
     */
	public Rating[] selectRatingsByUser(String username) {
		return selectRatings("username", username, "username", null);
	}

	/**
     * Obtem as avaliacaos pela entidade do usuario
     * @param user User
     * @return Rating[]
     */
	public Rating[] selectRatingsByUser(User user) {
		return selectRatingsByUser(user.getUsername());
	}

	/**
     * Obtem as avaliacaos pelo id da review
     * @param reviewID int
     * @return Rating[]
     */
	public Rating[] selectRatingsForReview(int reviewID) {
		return selectRatings("id", reviewID, "id", RatingType.REVIEW);
	}

	/**
     * Obtem as avaliacaos pela entidade da review
     * @param review Review
     * @return Rating[]
     */
	public Rating[] selectRatingsForReview(Review review) {
		return selectRatingsForReview(review.getID());
	}

	/**
     * Obtem a review pelo ID
     * @param reviewID int
     * @return Review
     */
	public Review selectReview(int reviewID) {
		Review[] reviews = selectReviews("id", reviewID, "id");

		if (reviews != null) {
			return reviews[0];
		}

		return null;
	}

	/**
     * Obtem as reviews pela entidade do livro
     * @param book Book
     * @return Review[]
     */
	public Review[] selectReviews(Book book) {
		return selectReviews(book.getISBN());
	}

	/**
     * Obtem as reviews pelo isbn do livro
     * @param isbn String
     * @return Review[]
     */
	public Review[] selectReviews(String isbn) {
		return selectReviews("bookISBN", isbn, "bookISBN");
	}

	/**
     * Obtem as reviews pela coluna, pelo valor e ordenacao
     * @param colunnName String
     * @param value Object
     * @param orderBy String
     * @return Review[]
     */
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
			if(value instanceof Integer) {
				where.add(colunnName + " = " + value);
			}
			else {
				where.add(colunnName + " = '" + value + "'");
			}
			
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

			}
		}

		return result;
	}

	/**
     * Obtem as reviews pelo nome do usuario
     * @param username String
     * @return Review[]
     */
	public Review[] selectReviewsByUser(String username) {
		return selectReviews("username", username, "username");
	}

	/**
     * Obtem as reviews pela entidade do usuario
     * @param user User
     * @return Review[]
     */
	public Review[] selectReviewsByUser(User user) {
		return selectReviewsByUser(user.getUsername());
	}

	/**
     * Obtem a sessao do usuario
     * @param username String
     * @return SessionData
     */
	public SessionData selectSession(String username) {
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
		SessionData[] result = db.querySessionData(select, where, order);

		if (result != null && result.length > 0) {
			return result[0];
		}

		return null;
	}

	/**
     * Obtem o usuario pelo nome
     * @param username String
     * @return User
     */
	public User selectUser(String username) {
		User[] users = selectUsers("username", username, "username");

		if (users != null) {
			return users[0];
		}

		return null;
	}

	/**
     * Obtem os usuario pela coluna, pelo valor e pela ordenacao
     * @param colunnName String
     * @param value String
     * @param orderBy String
     * @return User[]
     */
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

	/**
     * Atualiza um livro
     * @param book Book
     * @return boolean
     */
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
		
		boolean success = db.updateData(book, where);
		if(se != null) {
			se.update();
		}
		return success;
	}

	/**
     * Atualiza um comentario
     * @param comment Comment
     * @return boolean
     */
	public boolean updateComment(Comment comment) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + comment.getID());

		return db.updateData(comment, where);
	}

	/**
     * Atualiza uma avaliacao
     * @param rating Rating
     * @return boolean
     */
	public boolean updateRating(Rating rating) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + rating.getID());

		return db.updateData(rating, where);
	}

	/**
     * Atualiza uma review
     * @param review Review
     * @return boolean
     */
	public boolean updateReview(Review review) {
		Vector<String> where = new Vector<String>();
		where.add("id = " + review.getID());

		return db.updateData(review, where);
	}

	/**
     * Atualiza uma sessao
     * @param session SessionData
     * @return boolean
     */
	public boolean updateSession(SessionData session) {
		Vector<String> where = new Vector<String>();
		where.add("username = '" + session.getUsername() + "'");

		return db.updateData(session, where);
	}

	/**
     * Atualiza um usuario
     * @param user User
     * @return boolean
     */
	public boolean updateUser(User user) {
		Vector<String> where = new Vector<String>();
		where.add("id = '" + user.getUsername());

		return db.updateData(user, where);
	}

	/**
     * Insere uma review pelos campos
     * @param isbn String
     * @param title String
     * @param content String
     * @param username String
     * @return boolean
     */
	public boolean insertReview(String isbn, String title, String content, String username) {
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Review review = new Review();
		review.setBookISBN(isbn);
		review.setContent(content);		
		review.setPublishingDate(dataAtual);
		review.setTitle(title);
		review.setUsername(username);
		
		return insertReview(review);
	}

	/**
     * Insere uma avaliacao do livro pelos campos
     * @param isbn String
     * @param value int
     * @param username String
     * @return boolean
     */
	public boolean insertRating(String isbn, int value, String username) {
		Rating rating = new Rating();
		rating.setType(false);
		rating.setValue(value);
		rating.setUsername(username);
		rating.setBookISBN(isbn);
		return insertRating(rating);
	}

	/**
     * Insere uma avaliacao do review pelos campos
     * @param reviewID int
     * @param value int
     * @param username String
     * @return boolean
     */
	public boolean insertRating(int reviewID, int value, String username) {
		Rating rating = new Rating();
		rating.setType(true);
		rating.setValue(value);
		rating.setUsername(username);
		rating.setBookReview(reviewID);
		return insertRating(rating);
	}

	/**
     * Insere um comentario pelos campos
     * @param isbn String
     * @param content String
     * @param username String
     * @return boolean
     */
	public boolean insertComment(String isbn, String content, String username) {

		Date dataAtual = new Date(System.currentTimeMillis());
		
		Comment comment = new Comment();
		comment.setBookISBN(isbn);
		comment.setContent(content);
		try {
			comment.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comment.setUsername(username);
		comment.setVisibility(true);
		return insertComment(comment);
	}

	/**
     * Obtem numero de sessoes
     * @return int
     */
	public int sessionCountRows() {
		Vector<String> select = new Vector<String>();
		select.add("username");
		
		Vector<String> where = null;
		Vector<String> order = null;

		// realizando a consulta
		SessionData[] result = db.querySessionData(select, where, order);

		if (result != null) {
			return result.length;
		}
				
		return 0;
	}

	/**
     * Loga
     * @param se ISearchEngine
     */
	public void sign(ISearchEngine se) {
		this.se = se;		
	}

}
