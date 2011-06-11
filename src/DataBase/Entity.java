package DataBase;

public class Entity {
	private String identifier = null;
	private String fileName = null;
	
	public String getIdentifier(){
		return this.identifier;
	}
	
	public void setIdentifier(String identifier){
		this.identifier = identifier;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
}
