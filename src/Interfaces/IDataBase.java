package Interfaces;

import java.sql.SQLException;
import java.util.Vector;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.Session;
import Classes.User;

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
	@SuppressWarnings("unchecked")
	public Vector getVectorUser(User u);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetUser(User u);

	/* Book Operations */
	public boolean insertData(Book data);
	public Book[] queryBook(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Book data, Vector<String> where);
	public boolean deleteDataBook(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorBook(Book b);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetBook(Book b);

	/* Comment Operations */
	public boolean insertData(Comment data);
	public Comment[] queryComment(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Comment data, Vector<String> where);
	public boolean deleteDataComment(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorComment(Comment c);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetComment(Comment c);

	/* Review Operations */
	public boolean insertData(Review data);
	public Review[] queryReview(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Review data, Vector<String> where);
	public boolean deleteDataReview(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorReview(Review r);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetReview(Review r);

	/* Rating Operations */
	public boolean insertData(Rating data);
	public Rating[] queryRating(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Rating data, Vector<String> where);
	public boolean deleteDataRating(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorRating(Rating r);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetRating(Rating r);

	/* Session Operations */
	public boolean insertData(Session data);
	public Session[] querySession(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Session data, Vector<String> where);
	public boolean deleteDataSession(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorSession(Session s);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetSession(Session s);
}
