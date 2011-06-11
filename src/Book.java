import java.util.Date;

// TODO: implements ICommentable, IRateable, IReviewable
public class Book {
	String authors[];
	String description;
	String edition;
	String imagePath;
	String isbn;
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
	
	public String Publisher() {
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
		edition = value;
	}
	
	public void setImagePath(String value) {
		imagePath = value;
	}
	
	public void setISBN(String value) {
		isbn = value;
	}
	
	public void setName(String value) {
		name = value;
	}
	
	public void setPublisher(String value) {
		publisher = value;
	}
	
	public void setPublishingDate(Date value) {
		publishingDate = value;
	}
}
