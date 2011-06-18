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
 * @author José Américo Nabuco Leva Ferreira de Freitas
 *
 */
public class GUIReview extends JPanel{
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
	
	private Review review;
	
	/**
	 * Configura o painel
	 */
	public GUIReview(){
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

		setText();
		layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, title, 60, SpringLayout.SOUTH, rateButton);
		layout.putConstraint(SpringLayout.EAST, title, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, title, -50, SpringLayout.SOUTH, title);		
		
		layout.putConstraint(SpringLayout.WEST, scrollText, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, scrollText, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, scrollText, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, scrollText, 10, SpringLayout.SOUTH, title);
		
		setSize(650, 500);
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
		add(title);
		
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

				toRate.getSelectedItem();
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
	
	/**
	 * Mostra o painel
	 */
	public void show(){
		setVisible(true);
	}
	
	/**
	 * Oculta o painel
	 */
	public void hide(){
		setVisible(false);
	}
	
	public JTextArea getUsernameTextArea(){
		return user;
	}

	public JTextArea getPublishingDateTextArea(){
		return date;
	}

	public JEditorPane getTextEditorPane(){
		return text;
	}
	
	public Review getReview(){
		return review;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rev.setTitle("Quimdou");
		rev.setContent("<p align=\"LEFT\">Este texto é um <a href=\"gmail.com\">exemplo</a> de livro a ser lido no QuimDou.</p><p align=\"right\">O dispositivo QuimDou revolucionará a maneira como as pessoas lêem livros. A sua interface arrojada, dotada de comandos intuitivos e uma tela de 16 x 2 caracteres tornará a leitura muito mais divertida e desafiante.</p> <p align \"center\"><i>O inteligente modo de exibição automática do QuimDou permite que o usuário leia o livro inteiro sem ter que apertar botões para troca de linhas ou páginas. <u>Além disso, com seu modo automático de leitura reversa, o QuimDou é o dispositivo certo para aqueles que gostam, ou só sabem ler de trás para frente.</u></i></p><img src=\"../Programa/Santanbooks/src/Santanbooks.png\" alt=\"\" />");
		
		GUIReview guirev = new GUIReview();
		guirev.setReview(rev);
		
		JFrame frame = new JFrame();
		frame.add(guirev);
		frame.setVisible(true);
		frame.setSize(500, 500);

	}	
}
