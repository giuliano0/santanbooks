package Interfaces;

import Classes.*;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * Gerencia a(s) sess�o(�es) de usu�rio.
 * @author Giuliano
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.ISessionManager>")
public interface ISessionManager extends ISupports {

	public User loginChallenge(String username, String password);
	public void logoutUser(String username);
	
	public boolean isUserLoggedIn(String username);
	public int getOnlineUsers();
	
}
