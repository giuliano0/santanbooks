package Classes;

import java.util.Calendar;

import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

import Interfaces.*;

/**
 * Simula a sessão de servidor
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
	 * Pergunta à Database se o usuário especificado está logado.
	 * @param username
	 * @return True se estiver logado, false caso contrário.
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
	 * @return objeto User com as informações do usuário, o qual a interface <br />
	 * gráfica pode tratar como desejar.
	 * @remarks O método implementado no nosso exemplo <b>ignora a senha</b>. Numa aplicação real, <br />
	 * obviamente, o login retornaria um ID único de usuário baseando-se em um <i>par de autenticação</i>.
	 */
	public User loginChallenge(String username, String password) {
		SessionData sd = bo.selectSession(username);
		
		// Se sd não é nulo, o usuário está logado e retornamos uma instância do User referido.
		if (sd != null) return bo.selectUser(username);
		else { // Caso contrário, é criada uma sessão para o usuário.
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
	
	// Faz a consulta em tempo real e retorna o número de usuários logados.
	public int getOnlineUsers() {
		return 0;
	}
	
}
