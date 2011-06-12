package JavaDB;

import Classes.Book;

/**
 * manipulation interface database
 * @author Fernando Costa e João Scalett
 */
public interface IDataBase {
	public void connectDataBase();
	public void insertData(Book data);
	public void updateData(Book data);
	public void deleteData(Book data);
	public Book[] queryBook(String select, String where, String order);
}
