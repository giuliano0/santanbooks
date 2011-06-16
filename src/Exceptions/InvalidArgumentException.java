package Exceptions;

public class InvalidArgumentException extends Exception {

	/**
	 * Java requires this. 
	 */
	private static final long serialVersionUID = -8090441082198389627L;

	public InvalidArgumentException() {
		super("Argumento com valor inv�lido.");
	}
	
	public InvalidArgumentException(String message) {
		super(message);
	}
	
	/* N�o � necess�rio sobrescrever getMessage() */ 
}
