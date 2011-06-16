package Interfaces;

import anima.annotation.ComponentInterface;
import Classes.Book;

/**
 * Interface de especifica��o de componente de motor de buscas do site. 
 * @author Giuliano
 *
 */
@ComponentInterface("<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>")
public interface ISearchEngine {
	public Book[] search(String key);			// Busca a database por match exata de nome e tags do nome (usa extractTags).
	public Book[] searchByAuthor(String key);	// Busca por nome do autor, match exato, de prefer�ncia.
	public Book searchByISBN(String key);		// Busca por ISBN. Deve redirecionar direto ao livro encontrado, caso haja.
}

/*
 * Ok, here's the thing: essa coisa funciona mais ou menos assim... Quando uma chave � recebida, 
 * � feito o seu pr�-processamento, que SEMPRE inclui gerar uma chave normalizada, que � o que a 
 * base de dados vai entender, j� que acentos e sinais gr�ficos podem atrapalhar a busca.
 * 
 * Uma vez normalizada a chave, � criada a estrutura do comando a ser passado para a DB, 
 * a conex�o de dados � aberta e o comando, executado. Uma vez que isso aconte�a sem erros, os 
 * resultados devem ser TRATADOS antes de serem retornados.
 * 
 * O tratamento � a chave do Search Engine, 
 * � o que faz dele o Google do Santanbooks. � simplesmente acertar a ordem de relev�ncia dos 
 * resultados para que sejam exibidos nos resultados.
 * 
 * O detalhe interessante � que o formul�rio final PODE reordenar os dados, ele � livre para tal. 
 * Mas o engine em si define a ordem que acha mais interessante e � nessa ordem que os dados ser�o 
 * devolvidos.
 * 
 * Matches exatas > matches parciais > matches de tags
 * Match exata � trivial.
 * 
 * Match parcial � alguma que, numa chave de busca, considera cada tag potencial e faz a intersec��o 
 * dos conjuntos de resultados dessas tags, retornando poss�veis matches exatas e matches similares.
 * 
 * Tag match � quando n ou mais tags (n � um threshold, n = 1, 2, 3...) s�o encontradas, retornando 
 * muitos resultados e, possivelmente, nenhum relevante.
 */
