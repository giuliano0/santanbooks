package santanbooks.dataBase;

public interface IDataBaseEntity {
	
	public boolean salvarEntidade(Entity entidade);
	
	public boolean alterarEntidade(Entity entidade);
		
	public Entity obterEntidade(String id);
	
	public Entity obterProximo();
}
