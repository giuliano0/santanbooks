package dataBase1;

import Classes.User;

public class UserDataBase extends DataBase implements IUserDataBase{
	
	UserDataBase(){
		super();
		this.folder = "src/data/user/";
		this.uniqueKey = true;
	}

	public User getByIdentifier(String identifier) {
		return (User)super.get(identifier);
	}
	
	public User getNext(){
		return (User)super.getNext();
	}

	public boolean insert(User user) {
		return super.insert(user);
	}

	public boolean update(User user) {
		return super.update(user);
	}
}
