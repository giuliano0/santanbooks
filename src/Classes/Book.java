package Classes;

import java.io.File;
import java.sql.Date;
import Exceptions.InvalidArgumentException;
import Interfaces.*;

/**
 * Classe b�sica que drscreve o objeto livro.
 * @author Giuliano
 */
public class Book implements ICommentable, IRateable, IReviewable {
	String authors;			// "James 'I'm awesome' Stewart"
	String description;		// "Blah blah blah"
	String edition;			// "7th ed."
	String imagePath;		// "/images/books/anything.ext"
	String isbn;			// 9780538497817 ISBN-13 format, unique key. Any ISBN-10 will be truncated to ISBN-13.
	String name;			// "Calculus - Early Transcedentals"
	String publisher;		// "Brooks Cole"
	Date publishingDate;	// January 1, 2011 (doesn't matter)
	
	// Setados a parte da consulta � tabela Users no banco de dados
	Comment[] comments;
	Review[] reviews;
	float rating;
	
	public String getAuthors() {
		return authors;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public Date getPublishingDate() {
		return publishingDate;
	}
	
	public void setAuthors(String value) {
		authors = value.trim();
	}
	
	public void setDescription(String value) {
		description = value.trim();
	}
	
	public void setEdition(String value) {
		edition = value.trim();
	}
	
	// ESPERA um caminho v�lido, sen�o joga o lixo no... lixo, e seta como null
	public void setImagePath(String value) {
		imagePath = value;
	}
	
	/* 
	 * De acordo com a Ag�ncia Brasileira do ISBN (http://www.isbn.bn.br/), o ISBN agora ter� 
	 * SEMPRE 13 d�gitos no formato (ex.) 978-85-333-0398-X
	 * Todo ISBN anterior � norma, de 10 d�gitos, receber� o prefixo 978 e manter� os outros 10 
	 * d�gitos. Novos ISBNs vir�o com 13 d�gitos, os 3 primeiros SEMPRE diferentes de 978.
	 * O X no final pode existir nos ISBN-10, mas nunca nos ISBN-13. Ele significa que o d�gito 
	 * de verifica��o � 10 (no intervalo 0-10).
	 * No ISBN-13, o d�gito est� entre 0 e 9.
	 */
	// Admite que o isbn mandado � do tipo ISBN-10 ou ISBN-13, com ou sem h�fens de separa��o.
	/**
	 * <p>De acordo com a Ag�ncia Brasileira do ISBN (<i><u>http://www.isbn.bn.br/</u></i>), o ISBN agora ter� <br /> 
	 * <b>sempre</b> 13 d�gitos no formato (ex.) <i>978-85-333-0398-X</i>.</p>
	 * <p>Todo ISBN anterior � norma, de 10 d�gitos, receber� o prefixo 978 e manter� os outros 10 <br /> 
	 * d�gitos. Novos ISBNs vir�o com 13 d�gitos, os 3 primeiros <b>sempre</b> diferentes de 978.</p>
	 * <p>O X no final pode existir nos ISBN-10, mas nunca nos ISBN-13. Ele significa que o d�gito 
	 * de verifica��o � 10 (no intervalo 0-10).
	 * No ISBN-13, o d�gito est� entre 0 e 9.</p>
	 * @author Giuliano
	 * @param value o ISBN a ser validado e setado. 
	 * @remarks Admite que o isbn mandado � do tipo ISBN-10 ou ISBN-13, com ou sem h�fens de separa��o.
	 */
	public void setISBN(String value) throws IllegalArgumentException, NumberFormatException {
		// Tira os espa�os recebidos
		value = value.trim();
		
		// Checa se o ISBN � v�lido (|"8523301432"| = 10, |"85-233-0143-2"| = |"9788523301432"| = 13, |"978-85-233-0143-2"| = 17) 
		if (value.length() != 10 && value.length() != 13 && value.length() != 17)
			throw new IllegalArgumentException("O ISBN digitado n�o est� num dos formatos permitidos de entrada.");
		
		switch(value.length()) {
		case 10: //8523301432
			isbn = "978-" + value.substring(0, 2) + "-" + value.substring(2, 5) + "-" + value.substring(5, 9) + "-" + value.substring(9);
			
			break;
		case 13:
			if (value.charAt(2) == '-') // ISBN-10  //85-233-0143-2
				isbn = "978-" + value;
			else // ISBN-13  //9788523301432
				isbn = value.substring(0, 3) + "-" + value.substring(3, 5) + "-" + value.substring(5, 8) + "-" + value.substring(8, 12) + "-" + value.substring(12);
			
			break;
		case 17:
			isbn = value;
			break;
		}
		
		// Verifica se os campos dos separadores s�o mesmo n�meros
		//978-85-233-0143-2       
		try {
			Integer.parseInt(isbn.substring(0, 3));
			Integer.parseInt(isbn.substring(4, 6));
			Integer.parseInt(isbn.substring(7, 10));
			Integer.parseInt(isbn.substring(11, 15));
			Integer.parseInt(isbn.substring(15, 17));
		}
		catch (NumberFormatException ex) {
			NumberFormatException new_ex = new NumberFormatException("O ISBN digitado cont�m caracteres inv�lidos.");
			
			throw new_ex;
		}
		
		/* [GIU] A VERIFICA��O DE D�GITO DESABILITADA! */
		// A verifica��o foge ao escopo do projeto.
	}
	
	// Admite nome N�O NULO
	public void setName(String value) {
		name = value.trim();
	}
	
	public void setPublisher(String value) {
		publisher = value.trim();
	}
	
	// Admite data V�LIDA (no m�nimo, anterior � data atual)
	public void setPublishingDate(Date value) {
		publishingDate = value;
	}
	
	public Comment[] getAllComments() {
		return comments;
	}

	/**
	 * @author Davi
	 * @author Giuliano
	 */
	public Comment getComment(int commentID) throws InvalidArgumentException, NullPointerException {
		if (commentID == 0)
			throw new InvalidArgumentException();

		/*if (reviews == null)
			throw new NullPointerException("O liro n�o cont�m coment�rios.");*/
		if (reviews == null) return null;
		
		for(int i = 0; i < reviews.length; i++)
			if(reviews[i].ID == commentID)
				return comments[i];
		
		return null;
	}
	
	public int getRating() {
		return (int)Math.round(rating);
	}

	public Review[] getAllReviews() {
		return reviews;
	}

	/**
	 * @author Davi
	 * @author Giuliano
	 * @throws InvalidArgumentException, NullPointerException 
	 */
	public Review getReview(int id) throws InvalidArgumentException, NullPointerException {
		//if (reviews == null) throw new NullPointerException("Nenhuma review encontrada.");
		if (reviews == null) return null;
		
		for(int i = 0; i < reviews.length; i++) {
			if(reviews[i].ID == id)
				return reviews[i];
		}
		
		return null;
	}

	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}

	public void setComments(Comment[] commments) {
		this.comments = commments;
	}
	
	/**
	 * @author Davi
	 */
	public void setRating(float value) {
		if (value < 0) rating = 0; 
		else if (value > 5) rating = 5;
		else rating = value;
	}

}