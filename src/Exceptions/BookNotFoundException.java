package Exceptions;

public class BookNotFoundException extends Exception {

	/**
	 * Java requires this. 
	 */
	private static final long serialVersionUID = -8090441082198389627L;

	public BookNotFoundException() {
		super("Livro não encontrado.");
	}
	
	public BookNotFoundException(String message) {
		super(message);
	}
	
}
