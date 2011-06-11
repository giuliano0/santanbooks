package Interfaces;
import Classes.Comment;

/**
 * 
 * @author Giuliano
 *
 */
public interface ICommentable {
	void addComment(Comment comment);	// The comment ID is ignored.
	void editComment();					// The comment ID is 
	Comment[] getAllComments();
	Comment getComment(int id);
}
