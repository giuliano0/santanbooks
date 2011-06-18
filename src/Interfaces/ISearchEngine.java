package Interfaces;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;
import Classes.Book;

/**
 * Interface de especificação de componente de motor de buscas do site. 
 * @author Giuliano
 *
 */
@ComponentInterface("<http://purl.org/dcc/santanbooks.Interfaces.ISearchEngine>")
public interface ISearchEngine extends ISupports, ISeeU {
	public Book[] search(String key);
	public String[] autoComplete(String arg);
}
