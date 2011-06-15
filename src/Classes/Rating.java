package Classes;

import DataBase.Entity;

/**
 * 
 * @author Elizeu, Scalett
 *
 */

public class Rating extends Entity{
	int ID;
	String username;
	String bookISBN;
	int bookReview;
	int value;
	boolean type;
	
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		this.ID = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public int getBookReview() {
		return bookReview;
	}
	public void setBookReview(int bookReview) {
		this.bookReview = bookReview;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}	
}
