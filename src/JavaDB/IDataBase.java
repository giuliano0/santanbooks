package JavaDB;

import java.sql.SQLException;
import java.util.Vector;
import Classes.Book;
import Classes.Informations;

/**
 * manipulation interface database
 * @author Fernando Costa e João Scalett
 */
public interface IDataBase {
	public void connectDataBase() throws SQLException;
	public boolean insertData(Book data);
	public boolean insertData(Informations data);
	public boolean updateData(Book data, Vector<String> where);
	public boolean updateData(Informations data, Vector<String> where);
	public boolean deleteDataBook(Vector<String> where);
	public boolean deleteDataInformations(Vector<String> where);
	public Book[] queryBook(Vector<String> select, 
			Vector<String> where, Vector<String> order);
	public Informations[] queryInformations(Vector<String> select, 
			Vector<String> where, Vector<String> order);
}
