package Interfaces;

import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.SessionData;
import Classes.User;
import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * Interface prove metodos de manipulacao de banco de dados, para o sistema Santanbooks
 * @author Fernando Costa e Jo�o Scalett
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.IDataBase>")
public interface IDataBase extends ISupports{
	// m�todo para conex�o do banco de dados
	public void connectDataBase() throws SQLException;
	
	// m�todo para dele��o de todas as tabelas do banco de dados
	public void dropAllTables(); 

	/* User Operations */
	// m�todo para inser��o de dados na tabela usu�rios
	public boolean insertData(User data);
	
	// m�todo para consulta de dados na tabela usu�rios
	public User[] queryUser(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela usu�rios
	public boolean updateData(User data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela usu�rios
	public boolean deleteDataUser(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela usu�rios
	public Vector<Object> getVectorUser(User u);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela usu�rios
	public Vector<Object> getVectorSetUser(User u);

	/* Book Operations */
	// m�todo para inser��o de dados na tabela book
	public boolean insertData(Book data);
	
	// m�todo para consulta de dados na tabela book
	public Book[] queryBook(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela book
	public boolean updateData(Book data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela book
	public boolean deleteDataBook(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela book
	public Vector<Object> getVectorBook(Book b);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela book
	public Vector<Object> getVectorSetBook(Book b);

	/* Comment Operations */
	// m�todo para inser��o de dados na tabela comment
	public boolean insertData(Comment data);
	
	// m�todo para consulta de dados na tabela comment
	public Comment[] queryComment(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela comment
	public boolean updateData(Comment data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela comment
	public boolean deleteDataComment(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela comment
	public Vector<Object> getVectorComment(Comment c);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela comment
	public Vector<Object> getVectorSetComment(Comment c);

	/* Review Operations */
	// m�todo para inser��o de dados na tabela review
	public boolean insertData(Review data);
	
	// m�todo para consulta de dados na tabela review
	public Review[] queryReview(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela review
	public boolean updateData(Review data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela review
	public boolean deleteDataReview(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela review
	public Vector<Object> getVectorReview(Review r);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela review
	public Vector<Object> getVectorSetReview(Review r);

	/* Rating Operations */
	// m�todo para inser��o de dados na tabela rating
	public boolean insertData(Rating data);
	
	// m�todo para consulta de dados na tabela rating
	public Rating[] queryRating(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela rating
	public boolean updateData(Rating data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela rating
	public boolean deleteDataRating(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela rating
	public Vector<Object> getVectorRating(Rating r);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela rating
	public Vector<Object> getVectorSetRating(Rating r);

	/* SessionData Operations */
	// m�todo para inser��o de dados na tabela sessionData
	public boolean insertData(SessionData data);
	
	// m�todo para consulta de dados na tabela sessionData
	public SessionData[] querySessionData(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// m�todo para atualiza��o de dados na tabela sessionData
	public boolean updateData(SessionData data, Vector<String> where);
	
	// m�todo para dele��o de dados na tabela sessionData
	public boolean deleteDataSessionData(Vector<String> where);
	
	// m�todo usado para tratamanto do retorno de dados da consulta na tabela sessionData
	public Vector<Object> getVectorSessionData(SessionData s);
	
	// m�todo usado para tratamanto da inser��o de dados na tabela sessionData
	public Vector<Object> getVectorSetSessionData(SessionData s);
}
