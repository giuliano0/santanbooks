package Classes;

import java.util.Date;

/**
 * Simula a sessão de servidor
 * @author Giuliano
 *
 */
public class SessionData {
        String username;
        boolean status;
        Date lastLogin;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public boolean getStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public Date getLastLogin() {
			return lastLogin;
		}
		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}

        

}
