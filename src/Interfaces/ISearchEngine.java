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
	public Book[] searchByName(String key);		// Busca a database por match exata de nome.
	public Book[] searchByTags(String tags[]);		// Busca por tags, mais complicadinha.
	public Book[] searchByAuthor(String key);	// Busca por nome do autor, match exato, de preferência.
	public Book[] searchByISBN(String key);		// Busca por ISBN. Deve redirecionar direto ao livro encontrado, caso haja.
}
