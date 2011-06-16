package Classes;
//import java.util.Calendar;
import java.util.Date;

import Exceptions.InvalidArgumentException;
import Interfaces.*;

/**
 * 
 * @author Giuliano
 *
 */
public class Review implements ICommentable, IRateable {
	// Fields
	int ID;
	String username;
	String bookISBN;
	String bookName;		// Essa propriedade deve ser setada pelo banco, consultando tblBooks via ISBN
	String content;
	Date publishingDate;
	String title;
	
	Comment[] comments;
	float rating;
	
	// TODO: review access scope, data types and checkings inside these methods.
	
	public int getID() {
		return ID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getBookISBN() {
		return bookISBN;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getPublishingDate() {
		return publishingDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setID(int value) {
		ID = value;
	}
	
	public void setUsername(String value) {
		username = value;
	}
	
	public void setBookISBN(String value) {
		bookISBN = value;
	}
	
	public void setBookName(String value) {
		bookName = value;
	}
	
	public void setContent(String value) {
		content = value;
	}
	
	public void setPublishingDate(Date value) throws Exception {
		//if (value.after(Calendar.getInstance().getTime()))
			//throw new Exception("A publicação é inválida. O sistema só aceita títulos já lançados.");
		
		publishingDate = value;
	}
	
	public void setTitle(String value) {
		title = value.trim();
	}

	public Comment[] getAllComments() {
		return comments;
	}

	public Comment getComment(String username) throws InvalidArgumentException, NullPointerException {
		try {
			if(username.length() == 0) throw new InvalidArgumentException();
			if(comments == null) throw new NullPointerException("Nenhuma review encontrada.");
		}	
		catch (InvalidArgumentException iaEx) {
			throw iaEx;
		}
		catch (NullPointerException npEx) {
			throw npEx;
		}
		
		for(int i = 0; i < comments.length; i++) {
			if(comments[i].getUsername().equalsIgnoreCase(username))
				return comments[i];
		}
		
		return null;
	}

	public int getRating() {
		return (int)Math.round(rating);
	}

	public void setRating(float value) throws InvalidArgumentException {
		if (value < 0) rating = 0;
		else if (value > 5) rating = 5; 
		else rating = value;
	}

}