package Trabalho.data;

import Trabalho.Entity.Book;

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
		return (Book)super.getNext();
	}

	public boolean insert(Book book) {
		return super.insert(book);
	}

	public boolean update(Book book) {
		return super.update(book);
	}
}
