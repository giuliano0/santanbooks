package Classes;

import java.util.Date;

/**
 * @author Giuliano
 */
public class User {
	boolean accessLevel;		// Feature não implementada. True indica usuário moderador.
	Date birthday;
	String college;
	String course;
	String email;
	boolean gender;
	String name;
	String password;			// É "ignorado", mas faz parte do sistema e qualquer implementação alternativa.
	String selfDescription;		// Big String, baby.
	String username;
	Date ingressYear;
	
	public User() {
		// vazio
	}
	
	public boolean getAccessLevel() {
		return accessLevel;
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
	
	public void setPassword(String value) {
		this.password = value;
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
	
}