package JavaDB;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;

/**
 * manipulation interface database
 * @author Fernando Costa e João Scalett
 */
public interface IDataBase {
	public void connectDataBase() throws SQLException;
	
	public boolean insertData(Book data);
	public boolean updateData(Book data, Vector<String> where);
	public boolean deleteDataBook(Vector<String> where);
	public Book[] queryBook(Vector<String> select, 
			Vector<String> where, Vector<String> order);
}
