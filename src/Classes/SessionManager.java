package Classes;

import java.util.Calendar;

import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

import Interfaces.*;

/**
 * Simula a sess�o de servidor
 * @author Giuliano
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
		SessionData sd = bo.selectSession(username);
		
		if (sd != null) return true;
		else return false;
	}
	
	/**
	 * @author Giuliano
	 * @param username
	 * @param password
	 * @return objeto User com as informa��es do usu�rio, o qual a interface <br />
	 * gr�fica pode tratar como desejar.
	 * @remarks O m�todo implementado no nosso exemplo <b>ignora a senha</b>. Numa aplica��o real, <br />
	 * obviamente, o login retornaria um ID �nico de usu�rio baseando-se em um <i>par de autentica��o</i>.
	 */
	public User loginChallenge(String username, String password) {
		SessionData sd = bo.selectSession(username);
		
		// Se sd n�o � nulo, o usu�rio est� logado e retornamos uma inst�ncia do User referido.
		if (sd != null) return bo.selectUser(username);
		else { // Caso contr�rio, � criada uma sess�o para o usu�rio.
			User objUser = bo.selectUser(username);
			sd = new SessionData();
			
			sd.setUsername(objUser.username);
			sd.setLastLogin(Calendar.getInstance().getTime());
			
			bo.insertSession(sd);
			
			return objUser;
		}
	}
	
	public void logoutUser(String username) {
		if (isUserLoggedIn(username))
			bo.deleteSession(username);
	}
	
	// Faz a consulta em tempo real e retorna o n�mero de usu�rios logados.
	public int getOnlineUsers() {
		return 0;
	}
	
}
