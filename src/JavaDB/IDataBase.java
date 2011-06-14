package JavaDB;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Review;
import Classes.User;

/**
 * manipulation interface database
 * @author Fernando Costa e João Scalett
 */
public interface IDataBase {
	public void connectDataBase() throws SQLException;
	/* User Operations */
	public boolean insertData(User data);
	public Book[] queryUser(Vector<String> select, Vector<String> where,
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
	public Book[] queryComment(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Comment data, Vector<String> where);
	public boolean deleteDataComment(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorComment(Comment c);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetComment(Comment c);
	
	/* Review Operations */
	public boolean insertData(Review data);
	public Book[] queryReview(Vector<String> select, Vector<String> where,
			Vector<String> order);
	public boolean updateData(Review data, Vector<String> where);
	public boolean deleteDataReview(Vector<String> where);
	@SuppressWarnings("unchecked")
	public Vector getVectorReview(Review r);
	@SuppressWarnings("unchecked")
	public Vector getVectorSetReview(Review r);
}
