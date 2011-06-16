package Exceptions;

public class InvalidArgumentException extends Exception {

	/**
	 * Java requires this. 
	 */
	private static final long serialVersionUID = -8090441082198389627L;

	public InvalidArgumentException() {
		super("Argumento com valor inválido.");
	}
	
	public InvalidArgumentException(String message) {
		super(message);
	}
	
	/* Não é necessário sobrescrever getMessage() */ 
}
