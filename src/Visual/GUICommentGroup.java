package Visual;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

import Classes.Comment;

public class GUICommentGroup extends JScrollPane{
	private static final long serialVersionUID = 1L;
	Comment[] comments;
	
	public GUICommentGroup(Classes.Book l){
		Comment[] c = l.getAllComments();
		comments = c;
		GUIComment[] guiC = new GUIComment[c.length];
		GridLayout layout = new GridLayout(c.length, 1, 0, 5);
		setLayout(layout);

		for(int i = 0; i< c.length; i++){
			guiC[i].setComment(c[i], l.getISBN());
			add(guiC[i]);
		}
	}
}
