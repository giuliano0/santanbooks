
public interface ICommentable {
	void addComment();
	void editComment();
	Comment[] getAllComments();
	Comment getComment(int id);
}
