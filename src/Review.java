import java.util.Date;

// TODO: implements ICommentable, IRateable
public class Review {
	// Fields
	int ID;
	String author;
	String bookISBN;
	String bookName;
	String content;
	Date publishingDate;
	String title;
	
	// TODO: review access scope, data types and checkings inside these methods.
	
	public int getID() {
		return 0;
	}
	
	public String getAuthor() {
		return author;
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
	
	public void setAuthor(String value) {
		author = value;
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
}
