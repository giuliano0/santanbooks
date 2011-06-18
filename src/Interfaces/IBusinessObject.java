package Interfaces;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.SessionData;
import Classes.User;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * 
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves 
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.IBusinessObject>")
public interface IBusinessObject extends ISupports, INotifyU {
		
	//Books
	//select
	public Book[] selectAllBooks();
	public Book selectBook(String isbn);	
	public Book[] selectBooksByName(String name);	
	public Book[] selectBooksByAuthors(String authors);		
	//insert		
	public boolean insertBook(Book book);		
	//update
	public boolean updateBook(Book book);
	//delete
	public boolean deleteBook(Book book);	
	public boolean deleteBook(String isbn);
	
		
	//Users
	//select
	public User[] selectAllUsers();
	public User selectUser(String username);	
	//insert
	public boolean insertUser(User user);	
	//update
	public boolean updateUser(User user);	
	//delete
	public boolean deleteUser(String username);
	public boolean deleteUser(User user);		
	
	
	//Comments
	//select
	public Comment[] selectAllComments();
	public Comment selectComment(int commentID);
	public Comment[] selectCommentsBook(Book book);
	public Comment[] selectCommentsBookByIsbn(String isbn);
	public Comment[] selectCommentsBookFromUser(User user);
	public Comment[] selectCommentsBookFromUser(String username);
	//insert
	public boolean insertComment(Comment comment);
	public boolean insertComment(String isbn, String content, String username);
	//update
	public boolean updateComment(Comment comment);	
	//delete
	public boolean deleteComment(int commentID);
	public boolean deleteComment(Comment comment);
	public boolean deleteComments(Book book);
	public boolean deleteCommentsByIsbn(String isbn);	
	public boolean deleteCommentsByUser(User user);
	public boolean deleteCommentsByUser(String username);
	
	//Reviews
	//select
	public Review[] selectAllReviews();
	public Review selectReview(int reviewID);
	public Review[] selectReviews(Book book);
	public Review[] selectReviews(String isbn);
	public Review[] selectReviewsByUser(String username);
	public Review[] selectReviewsByUser(User user);
	
	//insert
	public boolean insertReview(Review review);
	public boolean insertReview(String isbn, String title, String content, String username);
	//update
	public boolean updateReview(Review review);
	
	//delete
	public boolean deleteReview(Review review);
	public boolean deleteReview(int reviewID);
	public boolean deleteReviews(Book book);
	public boolean deleteReviews(String isbn);
	public boolean deleteReviewsByUser(String username);
	public boolean deleteReviewsByUser(User user);

	//Ratings
	//select
	public Rating[] selectAllRatings();
	public Rating selectRating(int ratingID);
	public Rating[] selectRatings(Book book);
	public Rating[] selectRatings(String isbn);	
	public Rating[] selectRatingsByUser(String username);
	public Rating[] selectRatingsByUser(User user);	
	public Rating[] selectRatingsForReview(Review reviewID);
	public Rating[] selectRatingsForReview(int reviewID);
	
	//this methods calculates the rating
	public float selectRatingCalculed(Book book);
	public float selectRatingCalculed(String isbn);	
	public float selectRatingCalculed(Review review);
	public float selectRatingCalculed(int reviewID);
	
	//insert
	public boolean insertRating(Rating rating);
	public boolean insertRating(String isbn, int value, String username);
	public boolean insertRating(int reviewID, int value, String username);
	//update
	public boolean updateRating(Rating rating);
	
	//delete
	public boolean deleteRating(Rating rating);
	public boolean deleteRating(int ratingID);
	public boolean deleteRatings(Book book);
	public boolean deleteRatings(String isbn);	
	public boolean deleteRatingsByUser(String username);
	public boolean deleteRatingsByUser(User user);	
	public boolean deleteRatingsForReview(Review review);
	public boolean deleteRatingsForReview(int reviewID);
	
	
	//Session
	//select
	public SessionData selectSession(String user);
	
	public int sessionCountRows();
	
	//insert
	public boolean insertSession(SessionData session);
	
	//update
	public boolean updateSession(SessionData session);
	
	//delete
	public boolean deleteSession(SessionData session);
	public boolean deleteSession(String username);
	
}
