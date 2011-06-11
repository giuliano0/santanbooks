package Trabalho.data;

import Trabalho.Entity.Review;

public interface IReviewDataBase {
	
	public boolean insert(Review review);
	
	public boolean update(Review review);
		
	public Review getByIdentifier(String identifier);
	
	public Review getNext();
}
