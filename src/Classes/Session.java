package Classes;

import java.util.Date;

import Interfaces.IBusinessObject;

/**
 * Simula a sess�o de servidor
 * @author Giuliano
 *
 */
public class Session {
	IBusinessObject db;
	String username;
	boolean status;
	Date lastLogin;
	//Date lastActivity;
	
	/**
	 * Pergunta � Database se o usu�rio especificado est� logado.
	 * @param username
	 * @return True se estiver logado, false caso contr�rio.
	 * @author Giuliano
	 */
	public static boolean isUserLoggedIn(String username) {
		// Conecta � database 
		return true;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return True if the user is loggen in.
	 */
	public boolean getStatus() {
		return status;
	}
	
	/**
	 * @param status online = true
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Date getLastLogin() {
		return lastLogin;
	}
	
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	/**
	 * @author Giuliano
	 * @param username
	 * @param password
	 * @return Boolean indicating wether the login was successful.
	 */
	public boolean loginChallenge(String username, String password) {
		return true;
	}
	
	// Placeholder de m�todo, provavelmente in�til.
	public void logoutUser(String username) {
		
	}
	
	// Faz a consulta em tempo real e retorna o n�mero de usu�rios logados.
	public int getOnlineUsers() {
		return 0;
	}
	
}
