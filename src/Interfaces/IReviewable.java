package Interfaces;
import Classes.Review;


public interface IReviewable {
	void addReview();
	Review[] getAllReviews();
	Review getReview(int id);
}
