package Interfaces;
import Classes.Comment;

/**
 * 
 * @author Giuliano
 *
 */
public interface ICommentable {
	//void addComment(Comment comment);	// O commentID é ignorado
	//void editComment();					// O commentID é conferido
	Comment[] getAllComments();
	Comment getComment(int id);
	void setComments(Comment[] commments);
	void setComment(Comment commment);
}
