package Classes;

import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

import Interfaces.*;

/**
 * Simula a sess�o de servidor
 * @author Giuliano
 *
 */
@Component(id = "<http://purl.org/dcc/Classes.SessionManager>", 
		provides = { "<http://purl.org/dcc/Interfaces.ISessionManager>"},
		requires= { "<http://purl.org/dcc/Interfaces.IBusinessObject>" })
public class SessionManager extends ComponentBase implements ISessionManager, IRequires<IBusinessObject> {
	
	IBusinessObject bo;
	
	public SessionManager() {
		// vazio
	}
	
	public void connect(IBusinessObject objBO) {
		bo = objBO;
	}
	
	/**
	 * Pergunta � Database se o usu�rio especificado est� logado.
	 * @param username
	 * @return True se estiver logado, false caso contr�rio.
	 * @author Giuliano
	 */
	public boolean isUserLoggedIn(String username) {
		// Conecta � database, tenta o login, devolve o resultado, insere em tblSessions
		return false;
	}
	
	/**
	 * @author Giuliano
	 * @param username
	 * @param password
	 * @return Boolean indicating wether the login was successful.
	 */
	public User loginChallenge(String username, String password) {
		return null;
	}
	
	public void logoutUser(int userID) {
		
	}
	
	public void logoutUser(String username) {
		
	}
	
	// Faz a consulta em tempo real e retorna o n�mero de usu�rios logados.
	public int getOnlineUsers() {
		return 0;
	}
	
}
