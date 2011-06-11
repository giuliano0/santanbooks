package DataBase;

import Classes.Book;

public interface IBookDataBase {

	public boolean insert(Book book);
	
	public boolean update(Book book);
		
	public Book getByIdentifier(String identifier);
	
	public Book getByName(String name);
	
	public Book getNext();
}
