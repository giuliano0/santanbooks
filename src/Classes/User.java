package Classes;
import java.util.Date;

import DataBase.Entity;
import Interfaces.ILoggable;

/**
 * 
 * @author Giuliano
 *
 */
public class User extends Entity implements ILoggable {
	boolean accessLevel;
	Date birthday;
	String college;
	String course;
	String email;
	boolean gender;
	String name;
	String password;
	String selfDescription;
	String username;
	Date ingressYear;
	
	// TODO: review access scope, data types and checkings inside these methods.
	
	public boolean getAccessLevel() {
		return accessLevel;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}
	
	public String getCollege() {
		return college;
	}
	
	public String getCourse() {
		return course;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean getGender() {
		return gender;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getSelfDescription() {
		return selfDescription;
	}
	
	public String getUsername() {
		return username;
	}
	
	public Date getIngressYear() {
		return ingressYear;
	}
	
	public void setAccessLevel(boolean value) {
		accessLevel = value;
	}
	
	public void setBirthday(Date value) {
		birthday = value;
	}
	
	public void setCollege(String value) {
		college = value;
	}
	
	public void setCourse(String value) {
		course = value;
	}
	
	public void setEmail(String value) {
		email = value;
	}
	
	public void setGender(boolean value) {
		gender = value;
	}
	
	public void setName(String value) {
		name = value;
	}
	
	public void setPassowrd(String value) {
		password = value;
	}
	
	public void setSelfDescription(String value) {
		selfDescription = value;
	}
	
	public void setUsername(String value) {
		username = value;
	}
	
	public void setIngressYear(Date value) {
		ingressYear = value;
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void logout(int userID) {
		// TODO Auto-generated method stub
		
	}
}
