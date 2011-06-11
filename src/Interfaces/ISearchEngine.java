package Interfaces;

import Classes.Book;

/**
 * 
 * @author Giuliano
 *
 */
public interface ISearchEngine {
	public Book[] searchByName(String name);
	public Book[] searchByTags(String tags[]);
	public Book[] searchByAuthor(String author);
	public Book[] searchByISBN(String isbn);
}
