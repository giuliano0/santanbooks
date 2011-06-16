package Interfaces;

import Exceptions.InvalidArgumentException;

/**
 * 
 * @author Giuliano
 *
 */
public interface IRateable {
	int getRating();
	void setRating(float rating) throws InvalidArgumentException;
	//void updateRating(int rating);
}
