package Interfaces;

import Classes.*;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * Gerencia a(s) sessão(ões) de usuário.
 * @author Giuliano
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.ISessionManager>")
public interface ISessionManager extends ISupports {

	public User loginChallenge(String username, String password);
	public void logoutUser(String username);
	
	public boolean isUserLoggedIn(String username);
	public int getOnlineUsers();
	
}
