package DataBase;

import Classes.Book;

public class BookDataBase extends DataBase implements IBookDataBase{
	
	BookDataBase(){
		super();
		this.folder = "src/data/book/";
		this.uniqueKey = true;
	}

	public Book getByIdentifier(String identifier) {
		return (Book)super.get(identifier);
	}
	
	public Book getNext(){
		if (this.search.equalsIgnoreCase("identifier"))
			return (Book)super.getNext();
		else
			return null;
	}

	public boolean insert(Book book) {
		return super.insert(book);
	}

	public boolean update(Book book) {
		return super.update(book);
	}

	public Book getByName(String name) {
		this.search = "name";
		return null;
	}
}
