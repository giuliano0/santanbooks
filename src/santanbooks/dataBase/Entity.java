package santanbooks.dataBase;

public class Entity {
	private String identificador = null;
	private String arquivo = null;
	
	public String getIdentificador(){
		return this.identificador;
	}
	
	public void setIdentificador(String identificador){
		this.identificador = identificador;
	}
	
	public String getArquivo(){
		return this.arquivo;
	}
	
	public void setArquivo(String arquivo){
		this.arquivo = arquivo;
	}
}
