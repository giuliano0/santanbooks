package Visual;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.text.html.HTMLEditorKit;

import Classes.Review;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.ISupports;
import anima.component.InterfaceType;
import anima.component.base.*;

/**
 * JPanel que exibe um review (o formato tamb�m � bom para coment�rios).
 * @author Jos� Am�rico Nabuco Leva Ferreira de Freitas
 *
 */
@Component(id = "")
public class GUIReview extends JPanel implements ISupports{//, IRequires<Review>{
	private static final long serialVersionUID = 1L;
	
	private JEditorPane text;
	private JTextArea user;
	private JTextArea date;
	private ComponentBase component;
	private Review review;
	

	public GUIReview(Review rev){//Assim que Review virar um componente, esse par�metro some
		review = rev;
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		setUser();
		layout.putConstraint(SpringLayout.WEST, user, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);

		setDate();
		layout.putConstraint(SpringLayout.WEST, date, 5, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.SOUTH, date, 0, SpringLayout.SOUTH, user);

		setText();
		layout.putConstraint(SpringLayout.WEST, text, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, text, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, text, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, text, 10, SpringLayout.SOUTH, user);
		
		setSize(400, 500);
		this.component = new ComponentBase();
	}
	
	private void setText(){
		text = new JEditorPane();
		text.setEditable(true);
		text.setEditorKit(new HTMLEditorKit());
		text.setContentType("text/html");
		text.setText("<h1>"+review.getTitle()+"</h1><br>"+review.getContent());// TIRAR DEPOIS
		text.setSize(300, 200);
		text.setBackground(new Color(238,238,238));
		text.setEditable(false);
		add(text);
	}
	
	private void setUser(){
		user = new JTextArea(review.getUsername());//TIRAR DEPOIS
		user.setFont(new Font(null, Font.BOLD, 16));
		user.setBackground(new Color(238,238,238));
		user.setEditable(false);
		add(user);
	}
	
	private void setDate(){
		date = new JTextArea(review.getPublishingDate().toString());//TIRAR DEPOIS
		date.setFont(new Font(null, Font.ITALIC, 12));
		date.setBackground(new Color(238,238,238));
		date.setEditable(false);
		add(date);
	}
	
	public void show(){
		setVisible(true);
	}
	
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
	/**
	 * Main (para testes).
	 */
	public static void main(String[] args) {
		Review rev = new Review();
		
		rev.setUsername("username");
		rev.setPublishingDate(new java.util.Date());
		rev.setTitle("Quimdou");
		rev.setContent("<p align=\"LEFT\">Este texto � um exemplo de livro a ser lido no QuimDou.</p><p align=\"right\">O dispositivo QuimDou revolucionar� a maneira como as pessoas l�em livros. A sua interface arrojada, dotada de comandos intuitivos e uma tela de 16 x 2 caracteres tornar� a leitura muito mais divertida e desafiante.</p> <p align \"center\"><i>O inteligente modo de exibi��o autom�tica do QuimDou permite que o usu�rio leia o livro inteiro sem ter que apertar bot�es para troca de linhas ou p�ginas. <u>Al�m disso, com seu modo autom�tico de leitura reversa, o QuimDou � o dispositivo certo para aqueles que gostam, ou s� sabem ler de tr�s para frente.</u></i></p><img src=\"../Programa/Santanbooks/src/Santanbooks.png\" alt=\"\" />");
		
		GUIReview guirev = new GUIReview(rev);
		//guirev.setBackground(new Color(0, 0, 0));
		
		JFrame frame = new JFrame();
		frame.add(guirev);
		frame.setVisible(true);
		frame.setSize(500, 500);

	}	
	
	/*M�todos relativos � estrutura DCC*/
	@Override
	public int addRef() {
		return component.addRef();
	}


	@Override
	public String getInstanceId() {
		return component.getInstanceId();
	}


	@Override
	public <T extends ISupports> T queryInterface(String arg0) {
		return component.queryInterface(arg0);
	}


	@Override
	public <T extends ISupports> T queryInterface(String arg0,
			InterfaceType arg1) {
		return component.queryInterface(arg0, arg1);
	}


	@SuppressWarnings("deprecation")
	@Override
	public <T extends ISupports> IRequires<T> queryReceptacle(String arg0) {
		return component.queryReceptacle(arg0);
	}


	@Override
	public int release() {
		return component.release();
	}


/*	@Override
	public void connect(Review arg0) {
		review=arg0;
		user.setText(arg0.getUsername());
		date.setText(arg0.getPublishingDate().toString());
		text.setText("<h1>"+arg0.getTitle()+"</h1><br>"+arg0.getContent());
	}
*/
}
