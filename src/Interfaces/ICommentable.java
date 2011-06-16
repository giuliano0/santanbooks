package Interfaces;
import Classes.Comment;
import Exceptions.InvalidArgumentException;

/**
 * 
 * @author Giuliano
 *
 */
public interface ICommentable {
	//void addComment(Comment comment);	// O commentID � ignorado
	//void editComment();					// O commentID � conferido
	Comment[] getAllComments();
	Comment getComment(String username) throws InvalidArgumentException, NullPointerException;
	//void setComments(Comment[] commments);
	//void setComment(Comment commment);
}
