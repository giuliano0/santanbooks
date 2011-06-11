package Interfaces;

public interface ILoggable {
	boolean login(String username, String password);
	void logout(int userID);
}
