package DataBase;

import Classes.Comment;

public interface ICommentDataBase {

	public boolean insert(Comment comment);
	
	public boolean update(Comment comment);
		
	public Comment getByIdentifier(String identifier);
	
	public Comment getNext();
}
