package Visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JComboBox toRate;
	private JLabel avaliacao;
	private JLabel rating;
	private JButton rateButton;
	private JButton edit;
	private JToggleButton visible;
	
	private JButton cancelar, salvar;
	
	private Comment comment;
	
	/**
	 * Configura o painel
	 */
	public GUIComment(){
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		setUser();
		layout.putConstraint(SpringLayout.WEST, user, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);

		setDate();
		layout.putConstraint(SpringLayout.WEST, date, 5, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.SOUTH, date, 0, SpringLayout.SOUTH, user);
		
		setRateLabel();
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, rating, 0, SpringLayout.VERTICAL_CENTER, user);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, avaliacao, 0, SpringLayout.VERTICAL_CENTER, rating);

		setRateButton();
		layout.putConstraint(SpringLayout.EAST, rateButton, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, rateButton, 10, SpringLayout.SOUTH, avaliacao);
		
		setRateBox();
		layout.putConstraint(SpringLayout.EAST, toRate, -10, SpringLayout.WEST, rateButton);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, toRate, 0, SpringLayout.VERTICAL_CENTER, rateButton);
		
		/*rateLabel - eixo X*/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rating, 0, SpringLayout.HORIZONTAL_CENTER, rateButton);
		layout.putConstraint(SpringLayout.EAST, avaliacao, 0, SpringLayout.WEST, rating);
		
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
		layout.putConstraint(SpringLayout.NORTH, scrollText, 10, SpringLayout.SOUTH, rateButton);
		
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
		Integer x = new Integer(arg0.getRating());
		rating.setText(x.toString());
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
	
	private void setRateLabel(){
		avaliacao = new JLabel("Avaliacao: ");
		add(avaliacao);
		
		rating = new JLabel();
		rating.setBackground(new Color(238, 238, 238));
		rating.setFont(new Font(null, Font.BOLD, 28));
		add(rating);
	}
	
	private void setRateButton(){
		rateButton = new JButton("Avaliar");
		rateButton.setSize(20, 10);
		rateButton.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				IGlobalFactory factory;
				try {
					factory = ComponentContextFactory.createGlobalFactory();
				
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
				 
				 /*Refreshes the rating in the database and in the screen*/
				try {
					// TODO  consertar isto => bo.insertRating(review.getID(), toRate.getSelectedItem(), username);
					comment.setRating(bo.selectRatingCalculed(comment.getID()));
					Integer x = comment.getRating();
					rating.setText(x.toString());
				} catch (InvalidArgumentException e) {
					e.printStackTrace();
				}
				} catch (ContextException e) {
					e.printStackTrace();
				} catch (FactoryException e) {
					e.printStackTrace();
				}
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}});
		
		add(rateButton);
	}
	
	private void setRateBox(){
		toRate = new JComboBox(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		toRate.setSize(20, 10);
		add(toRate);
	}
	
	private void setEditButton(){
		edit = new JButton("Editar");
		edit.setSize(20, 10);
		add(edit);
		edit.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				remove(visible);
				remove(edit);
				setEditButtons();
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	public void setEditButtons(){
		
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
