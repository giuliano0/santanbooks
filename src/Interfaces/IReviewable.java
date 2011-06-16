package Interfaces;
import Classes.Review;
import Exceptions.InvalidArgumentException;

/**
 * 
 * @author Giuliano
 *
 */
public interface IReviewable {
	//void addReview();
	Review[] getAllReviews();
	Review getReview(int id) throws InvalidArgumentException, NullPointerException;
	//void setReviews(Review[] reviews);
	//void setReview(Review review);
}
