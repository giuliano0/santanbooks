package JavaDB;

import java.util.Vector;
import Classes.Book;
import Classes.Informations;

/**
 * manipulation interface database
 * @author Fernando Costa e João Scalett
 */
public interface IDataBase {
	public void connectDataBase();
	public void insertData(Book data);
	public void insertData(Informations data);
	public void updateData(Book data);
	public void updateData(Informations data);
	public void deleteData(Book data);
	public void deleteData(Informations data);
	public Book[] queryBook(Vector<String> select, 
			Vector<String> where, Vector<String> order);
	public Informations[] queryInformations(Vector<String> select, 
			Vector<String> where, Vector<String> order);
}
