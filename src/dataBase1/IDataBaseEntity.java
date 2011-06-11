package dataBase1;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

@ComponentInterface("<http://purl.org/dcc/santanbooks.dataBase.IDataBaseEntity>")
public interface IDataBaseEntity extends ISupports{
	
	public boolean salvarEntidade(Entity entidade);
	
	public boolean alterarEntidade(Entity entidade);
		
	public <T extends Entity> T obterEntidade(String id);
	
	public <T extends Entity> T obterProximo();
	
	public void isChaveUnica(boolean chaveUnica);
	
	public void setEntidade(String entidade);
	
	public <T extends Entity> T buildEntityClass(); 
}
