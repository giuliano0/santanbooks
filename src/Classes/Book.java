package Classes;

import java.sql.Date;
import java.util.Calendar;
import DataBase.Entity;
import Interfaces.*;

/**
 * 
 * @author Giuliano
 *
 */
public class Book extends Entity implements ICommentable, IRateable, IReviewable {
	String authors;			// "James 'I'm awesome' Stewart"
	String description;		// "Blah blah blah"
	String edition;			// "7th ed."
	String imagePath;		// "/images/books/anything.ext"
	String isbn;			// 9780538497817 ISBN-13 format, unique key. Any ISBN-10 will be truncated to ISBN-13.
	String name;			// "Calculus - Early Transcedentals"
	String publisher;		// "Brooks Cole"
	Date publishingDate;	// January 1, 2011 (doesn't matter)
	
	// TODO: review access scope, data types and checkings inside these methods.
	
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
	
	public void setImagePath(String value) {
		// DEVE checar pela existência do arquivo!
		imagePath = value;
	}
	
	/* 
	 * De acordo com a Agência Brasileira do ISBN (http://www.isbn.bn.br/), o ISBN agora terá 
	 * SEMPRE 13 dígitosm no formato (ex.) 978-85-333-0398-X
	 * Todo ISBN anterior à norma, de 10 dígitos, receberá o prefixo 978 e manterá os outros 10 
	 * dígitos. Novos ISBNs virão com 13 dígitos, os 3 primeiros SEMPRE diferentes de 978.
	 * O X no final pode existir nos ISBN-10, mas nunca nos ISBN-13. Ele significa que o dígito 
	 * de verificação é 10 (no intervalo 0-10).
	 * No ISBN-13, o dígito está entre 0 e 9.
	 */
	// Admite que o isbn mandado é do tipo ISBN-10 ou ISBN-13, com ou sem hífens de separação.
	public void setISBN(String value) throws IllegalArgumentException, NumberFormatException {
		// Tira os espaços recebidos
		value = value.trim();
		
		// Checa se o ISBN é válido (|"8523301432"| = 10, |"85-233-0143-2"| = |"9788523301432"| = 13, |"978-85-233-0143-2"| = 17) 
		if (value.length() != 10 && value.length() != 13 && value.length() != 17) {
			throw new IllegalArgumentException("O ISBN digitado não está num dos formatos permitidos de entrada.");
		}
		
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
		
		// Verifica se os campos dos separadores são mesmo números
		//978-85-233-0143-2       
		try {
			Integer.parseInt(isbn.substring(0, 3));
			Integer.parseInt(isbn.substring(4, 6));
			Integer.parseInt(isbn.substring(7, 10));
			Integer.parseInt(isbn.substring(11, 15));
			Integer.parseInt(isbn.substring(15, 17));
		}
		catch (NumberFormatException ex) {
			NumberFormatException new_ex = new NumberFormatException("O ISBN digitado contém caracteres inválidos.");
			
			throw new_ex;
		}
		
		/* [GIU] VERIFICAÇÃO DE DÍGITO DESABILITADA! */
		
		// Finalmente, confere o dígito de verificação do ISBN-13, easy job ;)
		/*int digit;
		// 012-45-789-1234-5
		digit = (int)(isbn.charAt(0) - '0') + (3 * (int)(isbn.charAt(1) - '0')) + 
				(int)(isbn.charAt(2) - '0') + (3 * (int)(isbn.charAt(4) - '0')) + 
				(int)(isbn.charAt(5) - '0') + (3 * (int)(isbn.charAt(7) - '0')) + 
				(int)(isbn.charAt(8) - '0') + (3 * (int)(isbn.charAt(9) - '0')) + 
				(int)(isbn.charAt(11) - '0') + (3 * (int)(isbn.charAt(12) - '0')) + 
				(int)(isbn.charAt(13) - '0') + (3 * (int)(isbn.charAt(14) - '0'));
		
		digit %= 10;
		digit  = 10 - digit;
		digit %= 10;
		
		if ((int)(isbn.charAt(16) - '0') != digit) {
			isbn = null;
			throw new IllegalArgumentException("O ISBN não é válido! falha no dígito de verificação!");
		}*/
	}
	
	public void setName(String value) {
		name = value.trim();
	}
	
	public void setPublisher(String value) {
		publisher = value.trim();
	}
	
	public void setPublishingDate(Date value) throws Exception {
		if (value.after(Calendar.getInstance().getTime()))
			throw new Exception("A publicação é inválida. O sistema só aceita títulos já lançados.");
		
		publishingDate = value;
	}
	
	/*
	 * Esses fields estão no lugar errado. São "placeholders".
	 */
	Comment[] comments;
	Review[] reviews;
	float rating;			// Retornado direto de consulta à DB

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment[] getAllComments() {
		if (comments == null); // dbGetComments(isbn);
		
		return comments;
	}

	@Override
	public Comment getComment(int id) {
		if (comments == null) return null; // return dbGetComment(isbn, commentID);
		else return comments[id];
	}

	@Override
	public int getRating() {
		return (int)Math.floor((double)rating);
	}

	@Override
	public void updateRating(int rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReview() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Review[] getAllReviews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review getReview(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
