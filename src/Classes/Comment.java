package Classes;
import java.util.Calendar;
import java.util.Date;

import DataBase.Entity;
import Interfaces.IRateable;

/**
 * 
 * @author Giuliano
 *
 */
public class Comment extends Entity implements IRateable {
	// Fields
	int ID;
	String author;
	String content;
	Date publishingDate;
	boolean visible;
	
	// TODO: review access scope, data types and checkings inside these methods.
	
	public int getID() {
		return 0;
	}
	
	public String getAuthor() {
		return author;
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
		if (value == 0) throw new IllegalArgumentException("Identificadores de coment�rio n�o podem ser nulos.");
		
		ID = value;
	}
	
	public void setAuthor(String value) {
		author = value.trim();
	}
	
	public void setContent(String value) {
		content = value.trim();
	}
	
	public void setPublishingDate(Date value) throws Exception {
		if (value.after(Calendar.getInstance().getTime()))
			throw new Exception("A publica��o � inv�lida. O sistema s� aceita t�tulos j� lan�ados.");
		
		publishingDate = value;
	}
	
	public void setVisibility(boolean value) {
		visible = value;
	}

	/*
	 * Esse field est� no lugar errado. � um "placeholder".
	 */
	float rating;			// Retornado direto de consulta � DB
	
	@Override
	public int getRating() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateRating(int rating) {
		// TODO Auto-generated method stub
		
	}
}
