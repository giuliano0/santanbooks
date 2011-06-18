package Visual;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.text.html.HTMLEditorKit;

import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;
import Interfaces.*;
import JavaDB.*;
import DataBase.*;

import Classes.Review;
import Exceptions.InvalidArgumentException;

/**
 * JPanel que exibe um review (o formato também é bom para comentários).
 * @author José Américo Nabuco Leva Ferreira de Freitas RA 105153
 *
 */
public class GUIReview extends JFrame{
	private static final long serialVersionUID = 1L;

	private JEditorPane title;
	private JEditorPane text;
	private JScrollPane scrollText;
	private JTextArea user;
	private JTextArea date;
	private JComboBox toRate;
	private JLabel avaliacao;
	private JLabel rating;
	private JButton rateButton;
	private JButton edit;
	int commentIndex;
	private JButton back;
	private Container contentPane;
	private GUIReview me;
	
	private Review review;
	
	/**
	 * Configura o painel
	 */
	public GUIReview(){
		me = this;
		SpringLayout layout = new SpringLayout();
		contentPane = getContentPane();
		contentPane.setLayout(layout);

		setUser();
		layout.putConstraint(SpringLayout.WEST, user, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, contentPane);

		setDate();
		layout.putConstraint(SpringLayout.WEST, date, 5, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.SOUTH, date, 0, SpringLayout.SOUTH, user);
		
		setRateLabel();
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, rating, 0, SpringLayout.VERTICAL_CENTER, user);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, avaliacao, 0, SpringLayout.VERTICAL_CENTER, rating);

		setRateButton();
		layout.putConstraint(SpringLayout.EAST, rateButton, -10, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, rateButton, 10, SpringLayout.SOUTH, avaliacao);
		
		setRateBox();
		layout.putConstraint(SpringLayout.EAST, toRate, -10, SpringLayout.WEST, rateButton);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, toRate, 0, SpringLayout.VERTICAL_CENTER, rateButton);
		
		/*rateLabel - eixo X*/
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, rating, 0, SpringLayout.HORIZONTAL_CENTER, rateButton);
		layout.putConstraint(SpringLayout.EAST, avaliacao, 0, SpringLayout.WEST, rating);
		
		setEditButton();
		layout.putConstraint(SpringLayout.EAST, edit, -10, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, edit, -10, SpringLayout.SOUTH, contentPane);

		setText();
		layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, title, 60, SpringLayout.SOUTH, rateButton);
		layout.putConstraint(SpringLayout.EAST, title, -10, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, title, -50, SpringLayout.SOUTH, title);		
		
		layout.putConstraint(SpringLayout.WEST, scrollText, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, scrollText, -10, SpringLayout.NORTH, edit);
		layout.putConstraint(SpringLayout.EAST, scrollText, -10, SpringLayout.EAST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, scrollText, 10, SpringLayout.SOUTH, title);
		
		setBack();
		layout.putConstraint(SpringLayout.WEST, back, 10, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.SOUTH, back, 0, SpringLayout.SOUTH, edit);

		
		setSize(650, 500);
		setResizable(false);
	}

	/**
	 * Configura o painel de acordo com os dados da review.
	 * @param arg0
	 */
	public void setReview(Review arg0) {
		review=arg0;
		user.setText("by "+arg0.getUsername());
		date.setText(arg0.getPublishingDate().toString());
		title.setText("<h1>"+arg0.getTitle()+"</h1>");
		text.setText(arg0.getContent());
		Integer x = new Integer(arg0.getRating());
		rating.setText(x.toString());
	}
	
	private void setText(){
		title = new JEditorPane();
		title.setEditable(false);
		title.setEditorKit(new HTMLEditorKit());
		title.setContentType("text/html");
		contentPane.add(title);
		
		text = new JEditorPane();
		text.setEditable(false);
		text.setEditorKit(new HTMLEditorKit());
		text.setContentType("text/html");
		scrollText = new JScrollPane(text);
		contentPane.add(scrollText);
	}
	
	private void setUser(){
		user = new JTextArea();
		user.setFont(new Font(null, Font.BOLD, 16));
		user.setBackground(new Color(238,238,238));
		user.setEditable(false);
		contentPane.add(user);
	}
	
	private void setDate(){
		date = new JTextArea();
		date.setFont(new Font(null, Font.ITALIC, 12));
		date.setBackground(new Color(238,238,238));
		date.setEditable(false);
		contentPane.add(date);
	}
	
	private void setRateLabel(){
		avaliacao = new JLabel("Avaliacao: ");
		contentPane.add(avaliacao);
		
		rating = new JLabel();
		rating.setBackground(new Color(238, 238, 238));
		rating.setFont(new Font(null, Font.BOLD, 28));
		contentPane.add(rating);
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
					// TODO bo.insertRating(review.getID(), toRate.getSelectedItem(), username);
					review.setRating(bo.selectRatingCalculed(review.getID()));
					Integer x = review.getRating();
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
		
		contentPane.add(rateButton);
	}
	
	private void setRateBox(){
		toRate = new JComboBox(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
		toRate.setSize(20, 10);
		contentPane.add(toRate);
	}
	
	private void setEditButton(){
		edit = new JButton("Editar");
		edit.setSize(20, 10);
		add(edit);
		edit.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				IGlobalFactory factory;
				try {
					factory = ComponentContextFactory.createGlobalFactory();
					
					factory.registerPrototype(GUIReviewEditor.class);
					IGUIReviewEditor editor = factory.createInstance(
							 "<http://purl.org/dcc/Visual.GUIReviewEditor>");
					editor.setReview(review.getContent());
					editor.setPai(me);
					editor.setVisible(true);
					
				} catch (ContextException e) {
					e.printStackTrace();
				} catch (FactoryException e) {
					e.printStackTrace();
				}
				
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	
	private void setBack(){
		back = new JButton("Voltar");
		back.setSize(20, 10);
		add(back);
		back.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				dispose();				
			}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		
	}
	
	/**
	 * Refreshes the text (after edition)
	 */
	public void refresh(String newContent){
		text.setText(newContent);
		review.setContent(newContent);
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
		
		  	 bo.insertReview(review);
			
		} catch (ContextException e) {
			e.printStackTrace();
		} catch (FactoryException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Main (para testes).
	 */
	public static void main(String[] args) {
		Review rev = new Review();
		
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
		rev.setTitle("Quimdou");
		rev.setContent("<p align=\"LEFT\">Este texto é um <a href=\"gmail.com\">exemplo</a> de livro a ser lido no QuimDou.</p><p align=\"right\">O dispositivo QuimDou revolucionará a maneira como as pessoas lêem livros. A sua interface arrojada, dotada de comandos intuitivos e uma tela de 16 x 2 caracteres tornará a leitura muito mais divertida e desafiante.</p> <p align \"center\"><i>O inteligente modo de exibição automática do QuimDou permite que o usuário leia o livro inteiro sem ter que apertar botões para troca de linhas ou páginas. <u>Além disso, com seu modo automático de leitura reversa, o QuimDou é o dispositivo certo para aqueles que gostam, ou só sabem ler de trás para frente.</u></i></p><img src=\"../Programa/Santanbooks/src/Santanbooks.png\" alt=\"\" />");
		
		GUIReview guirev = new GUIReview();
		guirev.setReview(rev);
		guirev.setVisible(true);

	}
}
