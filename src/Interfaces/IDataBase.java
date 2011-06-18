package Interfaces;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.SessionData;
import Classes.User;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * Interface prove metodos de manipulacao de banco de dados, para o sistema Santanbooks
 * @author Fernando Costa e João Scalett
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.IDataBase>")
public interface IDataBase extends ISupports{
	public void connectDataBase() throws SQLException;
	public void dropAllTables(); //TODO método temporário. Deverá ser removido da interface

	/* User Operations */
	public boolean insertData(User data);
	public User[] queryUser(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(User data, Vector<String> where);

	public boolean deleteDataUser(Vector<String> where);
	public Vector<Object> getVectorUser(User u);
	public Vector<Object> getVectorSetUser(User u);

	/* Book Operations */
	public boolean insertData(Book data);
	public Book[] queryBook(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Book data, Vector<String> where);
	public boolean deleteDataBook(Vector<String> where);
	public Vector<Object> getVectorBook(Book b);
	public Vector<Object> getVectorSetBook(Book b);

	/* Comment Operations */
	public boolean insertData(Comment data);
	public Comment[] queryComment(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Comment data, Vector<String> where);
	public boolean deleteDataComment(Vector<String> where);
	public Vector<Object> getVectorComment(Comment c);
	public Vector<Object> getVectorSetComment(Comment c);

	/* Review Operations */
	public boolean insertData(Review data);
	public Review[] queryReview(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Review data, Vector<String> where);
	public boolean deleteDataReview(Vector<String> where);
	public Vector<Object> getVectorReview(Review r);
	public Vector<Object> getVectorSetReview(Review r);

	/* Rating Operations */
	public boolean insertData(Rating data);
	public Rating[] queryRating(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Rating data, Vector<String> where);
	public boolean deleteDataRating(Vector<String> where);
	public Vector<Object> getVectorRating(Rating r);
	public Vector<Object> getVectorSetRating(Rating r);

	/* SessionData Operations */
	public boolean insertData(SessionData data);
	public SessionData[] querySessionData(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(SessionData data, Vector<String> where);
	public boolean deleteDataSessionData(Vector<String> where);
	public Vector<Object> getVectorSessionData(SessionData s);
	public Vector<Object> getVectorSetSessionData(SessionData s);
}
