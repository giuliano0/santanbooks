package Classes;
import java.util.Date;

import DataBase.Entity;
import Exceptions.InvalidISBNException;

// TODO: implements ICommentable, IRateable, IReviewable
public class Book extends Entity {
	String authors[];
	String description;
	String edition;
	String imagePath;
	String isbn;			// ISBN-10 format, unieuq key
	String name;
	String publisher;
	Date publishingDate;
	
	// TODO: review access scope, data types and checkings inside these methods.
	
	public String[] getAuthors() {
		return authors;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public Date getPublishingDate() {
		return publishingDate;
	}
	
	public void setAuthors(String[] value) {
		authors = value;
	}
	
	public void setDescription(String value) {
		description = value;
	}
	
	public void setEdition(String value) {
		edition = value.trim();
	}
	
	public void setImagePath(String value) {
		// must CHECK the file's existance
		imagePath = value;
	}
	
	public void setISBN(String value) throws Exception {
		// Trims the string to get it normalized
		value = value.trim();
		
		// Checks if the ISBN number is a valid one
		if (value.length() != 10 || value.length() != 13) {
			throw new Exception();
		}
		
		// Converts ISBN-13 to ISBN-10 format
		if (value.length() == 13) {
			value = value.substring(3);
		}
		
		isbn = value;
	}
	
	public void setName(String value) {
		name = value.trim();
	}
	
	public void setPublisher(String value) {
		publisher = value.trim();
	}
	
	public void setPublishingDate(Date value) {
		publishingDate = value;
	}
}
