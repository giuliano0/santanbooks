package Interfaces;
import Classes.Review;

/**
 * 
 * @author Giuliano
 *
 */
public interface IReviewable {
	//void addReview();
	Review[] getAllReviews();
	Review getReview(int id);
}
