package JavaDB;

import Classes.Book;
import Classes.Review;
import Classes.User;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

@ComponentInterface("<http://purl.org/dcc/JavaDB.IDataBaseComponent>")
public interface IDataBaseComponent extends ISupports {
	public DataBase getConnection();
	
	
	//Books
	
		
	public Book selectBook(String isbn);
	
	public Book[] selectBooksByName(String name);
	
	public Book[] selectBooksByAuthors(String authors);
	
	
		
	public boolean insertBook(Book book);	
	
	public boolean updateBook(Book book);
		
	public boolean deleteBook(Book book);
	
	public boolean deleteBook(String isnb);
	
	
	
	//Users
	public User selectUser(String username);		
	
	
	//Comments
	
	
	
	
	
	//Reviews
	public Review[] selectReviews(String isbn);
	
	
	
	//Rattings				
	
	
	
	
	
	
		
}
