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
 * @author Fernando Costa e João Scalett
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.IDataBase>")
public interface IDataBase extends ISupports{
	// método para conexão do banco de dados
	public void connectDataBase() throws SQLException;
	
	// método para deleção de todas as tabelas do banco de dados
	public void dropAllTables(); 

	/* User Operations */
	// método para inserção de dados na tabela usuários
	public boolean insertData(User data);
	
	// método para consulta de dados na tabela usuários
	public User[] queryUser(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela usuários
	public boolean updateData(User data, Vector<String> where);
	
	// método para deleção de dados na tabela usuários
	public boolean deleteDataUser(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela usuários
	public Vector<Object> getVectorUser(User u);
	
	// método usado para tratamanto da inserção de dados na tabela usuários
	public Vector<Object> getVectorSetUser(User u);

	/* Book Operations */
	// método para inserção de dados na tabela book
	public boolean insertData(Book data);
	
	// método para consulta de dados na tabela book
	public Book[] queryBook(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela book
	public boolean updateData(Book data, Vector<String> where);
	
	// método para deleção de dados na tabela book
	public boolean deleteDataBook(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela book
	public Vector<Object> getVectorBook(Book b);
	
	// método usado para tratamanto da inserção de dados na tabela book
	public Vector<Object> getVectorSetBook(Book b);

	/* Comment Operations */
	// método para inserção de dados na tabela comment
	public boolean insertData(Comment data);
	
	// método para consulta de dados na tabela comment
	public Comment[] queryComment(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela comment
	public boolean updateData(Comment data, Vector<String> where);
	
	// método para deleção de dados na tabela comment
	public boolean deleteDataComment(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela comment
	public Vector<Object> getVectorComment(Comment c);
	
	// método usado para tratamanto da inserção de dados na tabela comment
	public Vector<Object> getVectorSetComment(Comment c);

	/* Review Operations */
	// método para inserção de dados na tabela review
	public boolean insertData(Review data);
	
	// método para consulta de dados na tabela review
	public Review[] queryReview(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela review
	public boolean updateData(Review data, Vector<String> where);
	
	// método para deleção de dados na tabela review
	public boolean deleteDataReview(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela review
	public Vector<Object> getVectorReview(Review r);
	
	// método usado para tratamanto da inserção de dados na tabela review
	public Vector<Object> getVectorSetReview(Review r);

	/* Rating Operations */
	// método para inserção de dados na tabela rating
	public boolean insertData(Rating data);
	
	// método para consulta de dados na tabela rating
	public Rating[] queryRating(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela rating
	public boolean updateData(Rating data, Vector<String> where);
	
	// método para deleção de dados na tabela rating
	public boolean deleteDataRating(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela rating
	public Vector<Object> getVectorRating(Rating r);
	
	// método usado para tratamanto da inserção de dados na tabela rating
	public Vector<Object> getVectorSetRating(Rating r);

	/* SessionData Operations */
	// método para inserção de dados na tabela sessionData
	public boolean insertData(SessionData data);
	
	// método para consulta de dados na tabela sessionData
	public SessionData[] querySessionData(Vector<String> select, Vector<String> where,
			Vector<String> order);
	
	// método para atualização de dados na tabela sessionData
	public boolean updateData(SessionData data, Vector<String> where);
	
	// método para deleção de dados na tabela sessionData
	public boolean deleteDataSessionData(Vector<String> where);
	
	// método usado para tratamanto do retorno de dados da consulta na tabela sessionData
	public Vector<Object> getVectorSessionData(SessionData s);
	
	// método usado para tratamanto da inserção de dados na tabela sessionData
	public Vector<Object> getVectorSetSessionData(SessionData s);
}
