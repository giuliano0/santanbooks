package Interfaces;
import Classes.Comment;
import Exceptions.InvalidArgumentException;

/**
 * 
 * @author Giuliano
 *
 */
public interface ICommentable {
	//void addComment(Comment comment);	// O commentID é ignorado
	//void editComment();					// O commentID é conferido
	Comment[] getAllComments();
	Comment getComment(int commentID) throws InvalidArgumentException, NullPointerException;
}
