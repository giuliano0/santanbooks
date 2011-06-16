package Interfaces;

import anima.annotation.ComponentInterface;
import Classes.Book;

/**
 * Interface de especificação de componente de motor de buscas do site. 
 * @author Giuliano
 *
 */
@ComponentInterface("<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>")
public interface ISearchEngine {
	public Book[] search(String key);			// Busca a database por match exata de nome e tags do nome (usa extractTags).
	public Book[] searchByAuthor(String key);	// Busca por nome do autor, match exato, de preferência.
	public Book searchByISBN(String key);		// Busca por ISBN. Deve redirecionar direto ao livro encontrado, caso haja.
}

/*
 * Ok, here's the thing: essa coisa funciona mais ou menos assim... Quando uma chave é recebida, 
 * é feito o seu pré-processamento, que SEMPRE inclui gerar uma chave normalizada, que é o que a 
 * base de dados vai entender, já que acentos e sinais gráficos podem atrapalhar a busca.
 * 
 * Uma vez normalizada a chave, é criada a estrutura do comando a ser passado para a DB, 
 * a conexão de dados é aberta e o comando, executado. Uma vez que isso aconteça sem erros, os 
 * resultados devem ser TRATADOS antes de serem retornados.
 * 
 * O tratamento é a chave do Search Engine, 
 * é o que faz dele o Google do Santanbooks. É simplesmente acertar a ordem de relevância dos 
 * resultados para que sejam exibidos nos resultados.
 * 
 * O detalhe interessante é que o formulário final PODE reordenar os dados, ele é livre para tal. 
 * Mas o engine em si define a ordem que acha mais interessante e é nessa ordem que os dados serão 
 * devolvidos.
 * 
 * Matches exatas > matches parciais > matches de tags
 * Match exata é trivial.
 * 
 * Match parcial é alguma que, numa chave de busca, considera cada tag potencial e faz a intersecção 
 * dos conjuntos de resultados dessas tags, retornando possíveis matches exatas e matches similares.
 * 
 * Tag match é quando n ou mais tags (n é um threshold, n = 1, 2, 3...) são encontradas, retornando 
 * muitos resultados e, possivelmente, nenhum relevante.
 */
