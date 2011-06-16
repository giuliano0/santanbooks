package DataBase;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.Session;
import Classes.User;
import JavaDB.DataBase;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * 
 * @author Mauricio Bertanha and Rodrigo Elizeu Goncalves 
 */
@ComponentInterface("<http://purl.org/dcc/DataBase.IBusinessObject>")
public interface IBusinessObject extends ISupports {
	
	public DataBase getConnection();
		
	//Books
	//select
	public Book selectBook(String isbn);	
	public Book[] selectBooksByName(String name);	
	public Book[] selectBooksByAuthors(String authors);		
	//insert		
	public boolean insertBook(Book book);		
	//update
	public boolean updateBook(Book book);
	//delete
	public boolean deleteBook(Book book);	
	public boolean deleteBook(String isnb);
	
		
	//Users
	//select
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
	public Comment selectComment(int comment_id);
	public Comment[] selectCommentsBook(Book book);
	public Comment[] selectCommentsBookByIsbn(String isbn);
	public Comment[] selectCommentsBookFromUser(User user);
	public Comment[] selectCommentsBookFromUser(String username);
	//insert
	public boolean insertComment(Comment comment);	
	//update
	public boolean updateComment(Comment comment);	
	//delete
	public boolean deleteComment(int comment_id);
	public boolean deleteComment(Comment comment);
	public boolean deleteComments(Book book);
	public boolean deleteCommentsByIsbn(String isbn);	
	public boolean deleteCommentsByUser(User user);
	public boolean deleteCommentsByUser(String username);
	
	//Reviews
	//select
	public Review selectReview(int review_id);
	public Review[] selectReviews(Book book);
	public Review[] selectReviews(String isbn);
	public Review[] selectReviewsByUser(String username);
	public Review[] selectReviewsByUser(User user);
	
	//insert
	public boolean insertReview(Review review);	
	//update
	public boolean updateReview(Review review);
	
	//delete
	public boolean deleteReview(Review review);
	public boolean deleteReview(int review_id);
	public boolean deleteReviews(Book book);
	public boolean deleteReviews(String isbn);
	public boolean deleteReviewsByUser(String username);
	public boolean deleteReviewsByUser(User user);

	//Ratings
	//select
	public Rating selectRating(int rating_id);
	public Rating[] selectRatings(Book book);
	public Rating[] selectRatings(String isbn);	
	public Rating[] selectRatingsByUser(String username);
	public Rating[] selectRatingsByUser(User user);	
	public Rating[] selectRatingsForReview(Review review);
	public Rating[] selectRatingsForReview(int review_id);
	
	//insert
	public boolean insertRating(Rating rating);	
	//update
	public boolean updateRating(Rating rating);
	
	//delete
	public boolean deleteRating(Rating rating);
	public boolean deleteRating(int rating_id);
	public boolean deleteRatings(Book book);
	public boolean deleteRatings(String isbn);	
	public boolean deleteRatingsByUser(String username);
	public boolean deleteRatingsByUser(User user);	
	public boolean deleteRatingsForReview(Review review);
	public boolean deleteRatingsForReview(int review_id);
	
	
	//Session
	//select
	public Session selectSession(String username);
	
	//insert
	public boolean insertSession(Session session);
	
	//update
	public boolean updateSession(Session session);
	
	//delete
	public boolean deleteSession(Session session);
	public boolean deleteSession(String username);
	
}
