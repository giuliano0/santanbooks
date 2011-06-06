package Trabalho;

public interface IBancoDeDados {
	
	public boolean salvarEntidade(Entidade entidade);
	
	public boolean alterarEntidade(Entidade entidade);
		
	public Object obterEntidade(String id);
	
	public Object obterProximo();
}
