package Classes;

import java.util.Date;

import Interfaces.IBusinessObject;

/**
 * Simula a sessão de servidor
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
	 * Pergunta à Database se o usuário especificado está logado.
	 * @param username
	 * @return True se estiver logado, false caso contrário.
	 * @author Giuliano
	 */
	public static boolean isUserLoggedIn(String username) {
		// Conecta à database 
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
	
	// Placeholder de método, provavelmente inútil.
	public void logoutUser(String username) {
		
	}
	
	// Faz a consulta em tempo real e retorna o número de usuários logados.
	public int getOnlineUsers() {
		return 0;
	}
	
}
