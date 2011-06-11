package Interfaces;

/**
 * 
 * @author Giuliano
 *
 */
public interface ILoggable {
	boolean login(String username, String password);
	void logout(int userID);
}
