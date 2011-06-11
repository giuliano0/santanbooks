package DataBase;

import Classes.Comment;

public class CommentDataBase extends DataBase implements ICommentDataBase{
	
	CommentDataBase(){
		super();
		this.folder = "src/data/comment/";
		this.uniqueKey = false;
	}

	public Comment getByIdentifier(String identifier) {
		if (this.search.equalsIgnoreCase("identifier"))
			return (Comment)super.getNext();
		else
			return null;
	}
	
	public Comment getNext(){
		return (Comment)super.getNext();
	}

	public boolean insert(Comment Comment) {
		return super.insert(Comment);
	}

	public boolean update(Comment Comment) {
		return super.update(Comment);
	}
}
