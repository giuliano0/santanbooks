package Classes;
import java.util.Date;

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
	
	public void setPublishingDate(Date value) {
		publishingDate = value;
	}
	
	public void setTitle(String value) {
		title = value;
	}

	@Override
	public Comment[] getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRating() {
		return (int)Math.floor((double)rating);
	}

	@Override
	public void setComments(Comment[] commments) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setComment(Comment commment) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setRating(float rating) {
		// TODO Auto-generated method stub
	}

}
