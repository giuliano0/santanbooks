import java.util.Date;

// TODO: implements IRateable
public class Comment {
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
	
	public void setID(int value) {
		ID = value;
	}
	
	public void setAuthor(String value) {
		author = value;
	}
	
	public void setContent(String value) {
		content = value;
	}
	
	public void setPublishingDate(Date value) {
		publishingDate = value;
	}
	
	public void setVisibility(boolean value) {
		visible = value;
	}
}
