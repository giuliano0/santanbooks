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
	public void updateData(Book data, Vector<String> where);
	public void updateData(Informations data, Vector<String> where);
	public void deleteDataBook(Vector<String> where);
	public void deleteDataInformations(Vector<String> where);
	public Book[] queryBook(Vector<String> select, 
			Vector<String> where, Vector<String> order);
	public Informations[] queryInformations(Vector<String> select, 
			Vector<String> where, Vector<String> order);
}
