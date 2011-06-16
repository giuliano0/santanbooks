package Classes;
//import java.util.Calendar;
import java.util.Date;

import Exceptions.InvalidArgumentException;
import Interfaces.IRateable;

/**
 * 
 * @author Giuliano
 *
 */
public class Comment implements IRateable {
	// Fields
	int ID;
	String username;
	String bookISBN;
	String content;
	Date publishingDate;
	boolean visible;
	
	float rating;
	
	public int getID() {
		return ID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getPublishingDate() {
		return publishingDate;
	}
	
	public boolean getVisibility() {
		return visible;
	}

	public void setID(int value) throws IllegalArgumentException {
		//if (value == 0) throw new IllegalArgumentException("Identificadores de comentário não podem ser nulos.");
		
		ID = value;
	}
	
	public void setUsername(String value) {
		username = value.trim();
	}
	
	public void setContent(String value) {
		content = value.trim();
	}
	
	public void setPublishingDate(Date value) throws Exception {
		//if (value.after(Calendar.getInstance().getTime()))
			//throw new Exception("A publicação é inválida. O sistema só aceita títulos já lançados.");
		
		publishingDate = value;
	}
	
	public void setVisibility(boolean value) {
		visible = value;
	}

	public int getRating() {
		return (int)Math.round(rating);
	}

	public String getBookISBN() {
		return bookISBN;
	}
	
	public void setBookISBN(String value) {
		bookISBN = value;
	}

	/**
	 * @author Davi
	 */
	public void setRating(float value) throws InvalidArgumentException {
		if (value < 0) rating = 0;
		else if(value > 5) rating = 5;
		else rating = value;
	}
	
}