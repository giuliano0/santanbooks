package Visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;
import javax.swing.text.html.HTMLEditorKit;

import Classes.Comment;
import DataBase.BusinessObject;
import Exceptions.InvalidArgumentException;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import JavaDB.DataBase;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;

/**
 * Exibe um comentario
 * @author Jose Americo Nabuco Leva Ferreira de Freitas RA 105153
 *
 */
public class GUIComment extends JPanel{
	private static final long serialVersionUID = 1L;

	private JEditorPane text;
	private JScrollPane scrollText;
	private JTextArea user;
	private JTextArea date;
	private JButton edit;
	private JToggleButton visible;
	private SpringLayout layout;
	private JPanel me;
	
	private JButton cancelar, salvar;
	
	private Comment comment;
	
	/**
	 * Configura o painel
	 */
	public GUIComment(){
		layout = new SpringLayout();
		setLayout(layout);
		me = this;

		setUser();
		layout.putConstraint(SpringLayout.WEST, user, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);

		setDate();
		layout.putConstraint(SpringLayout.WEST, date, 5, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.SOUTH, date, 0, SpringLayout.SOUTH, user);	
		setEditButton();
		layout.putConstraint(SpringLayout.EAST, edit, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, edit, -10, SpringLayout.SOUTH, this);
		
		//if(usuario == comment.getUsername()){
			setVisibleButton();
			layout.putConstraint(SpringLayout.EAST, visible, -10, SpringLayout.WEST, edit);
			layout.putConstraint(SpringLayout.SOUTH, visible, -10, SpringLayout.SOUTH, this);		
		//}
		setText();
		layout.putConstraint(SpringLayout.WEST, scrollText, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, scrollText, -10, SpringLayout.NORTH, edit);
		layout.putConstraint(SpringLayout.EAST, scrollText, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, scrollText, 10, SpringLayout.SOUTH, user);
		
		setSize(500, 250);
	}

	/**
	 * Configura o painel de acordo com os dados da review.
	 * @param arg0
	 */
	public void setComment(Comment arg0) {
		comment=arg0;
		user.setText("by "+arg0.getUsername());
		date.setText(arg0.getPublishingDate().toString());
		text.setText(arg0.getContent());
		visible.setText(arg0.getVisibility()?"Visivel":"Invisivel");
	}
	
	private void setText(){		
		text = new JEditorPane();
		text.setEditable(false);
		text.setEditorKit(new HTMLEditorKit());
		text.setContentType("text/html");
		scrollText = new JScrollPane(text);
		add(scrollText);
	}
	
	private void setUser(){
		user = new JTextArea();
		user.setFont(new Font(null, Font.BOLD, 16));
		user.setBackground(new Color(238,238,238));
		user.setEditable(false);
		add(user);
	}
	
	private void setDate(){
		date = new JTextArea();
		date.setFont(new Font(null, Font.ITALIC, 12));
		date.setBackground(new Color(238,238,238));
		date.setEditable(false);
		add(date);
	}
	
	private void setEditButton(){
		edit = new JButton("Editar");
		edit.setSize(20, 10);
		add(edit);
		edit.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				text.setEditable(true);
				me.setVisible(false);
				setEditionButtons();

				layout.putConstraint(SpringLayout.WEST, cancelar, 10, SpringLayout.WEST, me);
				layout.putConstraint(SpringLayout.SOUTH, cancelar, 0, SpringLayout.SOUTH, visible);
		
				layout.putConstraint(SpringLayout.WEST, salvar, 10, SpringLayout.EAST, cancelar);
				layout.putConstraint(SpringLayout.SOUTH, salvar, 0, SpringLayout.SOUTH, visible);
				
				visible.setVisible(false);
				edit.setVisible(false);
				me.setVisible(true);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	public void setEditionButtons(){
		salvar = new JButton("Salvar");
		salvar.setSize(20, 10);
		add(salvar);
		salvar.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				text.setEditable(false);
				comment.setContent(text.getText());
				text.setText(comment.getContent());
				
				try {
					IGlobalFactory factory = ComponentContextFactory.createGlobalFactory();

					 factory.registerPrototype(IBusinessObject.class);
					 IBusinessObject bo = factory.createInstance(
							 "<http://purl.org/dcc/DataBase.BusinessObject>");
					 
					 factory.registerPrototype(ISQLStatements.class);
					 ISQLStatements sqlst = factory.createInstance(
							 "<http://purl.org/dcc/JavaDB.SQLStatements>");
					 
					 factory.registerPrototype(IDataBase.class);
					 IDataBase db = factory.createInstance(
							 "<http://purl.org/dcc/JavaDB.DataBase>");
					 
					 ((DataBase)db).connect(sqlst);
					 ((BusinessObject)bo).connect(db);
					 
					 bo.insertComment(comment);
				} catch (ContextException e) {
					e.printStackTrace();
				} catch (FactoryException e) {
					e.printStackTrace();
				}
				me.setVisible(false);
				
				visible.setVisible(true);
				edit.setVisible(true);
				
				remove(salvar);
				remove(cancelar);
				me.setVisible(true);			
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		cancelar = new JButton("Cancelar");
		cancelar.setSize(20, 10);
		add(cancelar);
		cancelar.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				text.setEditable(false);
				text.setText(comment.getContent());
				me.setVisible(false);
				remove(salvar);
				remove(cancelar);

				visible.setVisible(true);
				edit.setVisible(true);
				
				me.setVisible(true);
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	private void setVisibleButton(){
		visible = new JToggleButton();
		visible.setSize(20, 10);
		add(visible);
		visible.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				if(comment.getVisibility()){
					comment.setVisibility(false);
					visible.setText("Invisivel");
				}
				else{
					comment.setVisibility(true);
					visible.setText("Visivel");
				}
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}

	
	/**
	 * Main (para testes).
	 */
	public static void main(String[] args) {
		Comment rev = new Comment();
		
		rev.setUsername("username");
		try {
			rev.setPublishingDate(new java.util.Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rev.setRating(3);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		rev.setVisibility(true);
		rev.setContent("O Umamão é uma base de perguntas e respostas expandida e melhorada constantemente por acadêmicos e profissionais. Temos orgulho de produzir conteúdo de qualidade.");
		
		GUIComment guirev = new GUIComment();
		guirev.setComment(rev);
		
		JFrame frame = new JFrame();
		frame.add(guirev);
		frame.setVisible(true);
		frame.setSize(500, 250);

	}

}
