package JavaDB;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
import Classes.Session;
import Classes.User;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import anima.annotation.Component;
import anima.component.IRequires;
import anima.component.base.ComponentBase;

/**
 * Componente para manipulação de dados
 * @author Fernando Costa e João Scalett
 */
@Component(id = "<http://purl.org/dcc/JavaDB.DataBase>", 
		provides = { "<http://purl.org/dcc/Interfaces.IDataBase>" },
		requires= { "<http://purl.org/dcc/Interfaces.ISQLStatements>" })
public class DataBase extends ComponentBase implements IDataBase, IRequires<ISQLStatements>{
	private ISQLStatements stt; 
	private String bd, driver;

	public DataBase(){
		bd = "jdbc:derby:db;create=true";
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
	}

	@Override
	public void connect(ISQLStatements stt) {
		this.stt = stt;
		stt.setBd("jdbc:derby:db;create=true");
		stt.setDriver("org.apache.derby.jdbc.EmbeddedDriver");
	}
	
	public void connectDataBase() throws SQLException {
		try {
			/* Creates table users */
			stt.executeStatement(
					"CREATE TABLE users " +
					"(username VARCHAR(100) NOT NULL, " +
					"accesLevel INT NOT NULL, " +
					"birthday DATE NOT NULL, " +
					"college VARCHAR(100), " +
					"course VARCHAR(100), " +
					"email VARCHAR(100) NOT NULL, " +
					"gender INT NOT NULL, " +
					"name VARCHAR(100) NOT NULL, " +
					"password VARCHAR(100) NOT NULL, " +
					"selfDescription VARCHAR(100), " +
					"ingressYear DATE NOT NULL, PRIMARY KEY(username))"
			);

			/* Creates table book */
			stt.executeStatement(
					"CREATE TABLE book " +
					"(isbn VARCHAR(100) NOT NULL, " +
					"name VARCHAR(100) NOT NULL, " +
					"authors VARCHAR(100) DEFAULT NULL, " +
					"description VARCHAR(100), " +
					"edition VARCHAR(100), " +
					"imagePath VARCHAR(100), " +
					"publisher VARCHAR(100), " +
					"publishingDate DATE NOT NULL, PRIMARY KEY(isbn))"
			);

			/* Creates table comment */
			stt.executeStatement("CREATE TABLE comment "+
					"(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
					"(START WITH 1, INCREMENT BY 1), " +
					"username VARCHAR(100) NOT NULL, " +
					"bookISBN VARCHAR(100) NOT NULL, " +
					"content VARCHAR(255) NOT NULL, " +
					"publishingDate DATE NOT NULL)"
					/*"FOREIGN KEY(username, isbn))"*/
			);

			/* Creates table review */
			stt.executeStatement("CREATE TABLE review " +
					"(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
					"(START WITH 1, INCREMENT BY 1), " +
					"username VARCHAR(100) NOT NULL, " +
					"bookISBN VARCHAR(100) NOT NULL, " +
					"bookName VARCHAR(100) NOT NULL, " +
					"content VARCHAR(10000) NOT NULL, " +
					"publishingDate DATE NOT NULL, " +
					"title VARCHAR(100) NOT NULL)"
					/*"FOREIGN KEY(username, isbn))"*/
			);

			/* Creates table rating */
			stt.executeStatement("CREATE TABLE rating " +
					"(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
					"(START WITH 1, INCREMENT BY 1), " +
					"username VARCHAR(100) NOT NULL, " +
					"bookISBN VARCHAR(100), " +
					"bookReview INT, " +
					"value INT NOT NULL," +
					"type INT NOT NULL)"
					/*"FOREIGN KEY(username, isbn, review))"*/
			);
			
			/* Creates table session */
			stt.executeStatement(
					"CREATE TABLE session " +
					"(username VARCHAR(100) NOT NULL, " +
					"status INT NOT NULL, " +
					"lastLogin DATE NOT NULL, PRIMARY KEY(username))"
					/*"FOREIGN KEY(username, isbn, review))"*/
			);
			
		} catch (SQLException erro) {
			throw new SQLException("Erro na criacao das tabelas: " + erro.getMessage());
		}
	}
	
	/* Método temporário. Até pq não é vantagem poder sair excluindo tudo */
	/* (non-Javadoc)
	 * @see JavaDB.Agfgdfgf#dropAllTables()
	 */
	public void dropAllTables(){
		try{
			stt.executeStatement("DROP TABLE users");
			stt.executeStatement("DROP TABLE book");
			stt.executeStatement("DROP TABLE comment");
			stt.executeStatement("DROP TABLE review");
			stt.executeStatement("DROP TABLE rating");
			stt.executeStatement("DROP TABLE session");
		}
		catch(SQLException erro){
			System.out.println(erro.getMessage());
		}
	}

	////////////////////////////////////////////////////////////////////
	/*
	 * Operações com User
	 */
	////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#insertData(Classes.User)
	 */
	/* (non-Javadoc)
	 * @see JavaDB.Agfgdfgf#insertData(Classes.User)
	 */
	@SuppressWarnings("unchecked")
	public boolean insertData(User data) {
		boolean sucesso = true;
		Vector v = getVectorUser(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("users", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public User[] queryUser(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM users " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			
			User[] u = new User[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM users " + w + o);

			while (result.next())
			{
				User temp = new User();

				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("username")) 
						temp.setUsername(result.getString("username"));
					if(select.get(i).equalsIgnoreCase("accessLevel"))
						temp.setAccessLevel(result.getInt("accessLevel") == 1 ? true : false);
					if(select.get(i).equalsIgnoreCase("birthday"))
						temp.setBirthday(result.getDate("birthday"));
					if(select.get(i).equalsIgnoreCase("college"))
						temp.setCollege(result.getString("college"));
					if(select.get(i).equalsIgnoreCase("course"))
						temp.setCourse(result.getString("course"));
					if(select.get(i).equalsIgnoreCase("email"))
						temp.setEmail(result.getString("email"));
					if(select.get(i).equalsIgnoreCase("gender"))
						temp.setGender(result.getInt("gender") == 1 ? true : false);
					if(select.get(i).equalsIgnoreCase("name"))
						temp.setName(result.getString("name"));
					if(select.get(i).equalsIgnoreCase("password"))
						temp.setPassword(result.getString("password"));
					if(select.get(i).equalsIgnoreCase("selfDescription"))
						temp.setSelfDescription(result.getString("selfDescription"));
					if(select.get(i).equalsIgnoreCase("ingressYear"))
						temp.setIngressYear(result.getDate("ingressYear"));
				}
				u[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return u;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Users: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(User data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetUser(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("users", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataUser(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("users", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorUser(User u){
		Vector v = new Vector();
		v.add(u.getUsername());
		v.add((u.getAccessLevel() ? 1 : 0)); //como o banco não suporta boolean, deve ser 0 ou 1
		v.add(u.getBirthday());
		v.add(u.getCollege());
		v.add(u.getCourse());
		v.add(u.getEmail());
		v.add((u.getGender() ? 1 : 0)); //como o banco não suporta boolean, deve ser 0 ou 1
		v.add(u.getName());
		v.add(u.getPassword());
		v.add(u.getSelfDescription());
		v.add(u.getIngressYear());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetUser(User u){
		Vector v = new Vector();
		v.add("username = '" + u.getUsername() + "'");
		v.add("accessLevel = '" + (u.getAccessLevel() ? 1 : 0) + "'"); //como o banco não suporta boolean, deve ser 0 ou 1
		v.add("birthday = '" + u.getBirthday() + "'");
		v.add("college = '" + u.getCollege() + "'");
		v.add("course = '" + u.getCourse() + "'");
		v.add("email = '" + u.getEmail() + "'");
		v.add("gender = '" + (u.getGender() ? 1 : 0)+ "'"); //como o banco não suporta boolean, deve ser 0 ou 1
		v.add("name = '" + u.getName() + "'");
		v.add("password = '" + u.getPassword() + "'");
		v.add("selfDescription = '" + u.getSelfDescription() + "'");
		v.add("ingressYear = '" + u.getIngressYear() + "'");
		return v;
	}

	////////////////////////////////////////////////////////////////////
	/*
	 * Operações com BOOK
	 */
	////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public boolean insertData(Book data) {
		boolean sucesso = true;
		Vector v = getVectorBook(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("book", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public Book[] queryBook(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			
			Book[] b = new Book[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM book " + w + o);

			while (result.next())
			{
				Book temp = new Book();

				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("isbn")) 
						temp.setISBN(result.getString("isbn"));
					if(select.get(i).equalsIgnoreCase("name"))
						temp.setName(result.getString("name"));
					if(select.get(i).equalsIgnoreCase("authors"))
						temp.setAuthors(result.getString("authors"));
					if(select.get(i).equalsIgnoreCase("description"))
						temp.setDescription(result.getString("description"));
					if(select.get(i).equalsIgnoreCase("edition"))
						temp.setEdition(result.getString("edition"));
					if(select.get(i).equalsIgnoreCase("imagePath"))
						try {
							temp.setImagePath(result.getString("imagePath"));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				b[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return b;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Book: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Book data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetBook(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("book", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataBook(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("book", w);
		} catch (SQLException erro) {
			System.out.println(erro.getMessage()); 
			sucesso = false;
		}
		return sucesso;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorBook(Book b){
		Vector v = new Vector();
		v.add(b.getISBN());
		v.add(b.getName());
		v.add(b.getAuthors());
		v.add(b.getDescription());
		v.add(b.getEdition());
		v.add(b.getImagePath());
		v.add(b.getPublisher());
		v.add(b.getPublishingDate());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetBook(Book b){
		Vector v = new Vector();
		v.add("isbn = '" + b.getISBN() + "'");
		v.add("name = '" + b.getName() + "'");
		v.add("authors = '" + b.getAuthors() + "'");
		v.add("description = '" + b.getDescription() + "'");
		v.add("edition = '" + b.getEdition() + "'");
		v.add("imagePath = '" + b.getImagePath() + "'");
		v.add("publisher = '" + b.getPublisher() + "'");
		v.add("publishingDate = '" + b.getPublishingDate() + "'");
		return v;
	}
	
	////////////////////////////////////////////////////////////////////
	/*
	 * Operações com Comment
	 */
	////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public boolean insertData(Comment data) {
		boolean sucesso = true;
		Vector v = getVectorComment(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("comment", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public Comment[] queryComment(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM comment " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}

			Comment[] c = new Comment[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM comment " + w + o);

			while (result.next())
			{
				Comment temp = new Comment();

				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("id")) 
						temp.setID(result.getInt("id"));
					if(select.get(i).equalsIgnoreCase("bookISBN")) 
						temp.setBookISBN(result.getString("bookISBN"));
					if(select.get(i).equalsIgnoreCase("username"))
						temp.setUsername(result.getString("username"));
					if(select.get(i).equalsIgnoreCase("content"))
						temp.setContent(result.getString("content"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				c[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return c;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Comment: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Comment data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetComment(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("comment", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataComment(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("comment", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorComment(Comment c){
		Vector v = new Vector();
		v.add(c.getID());
		v.add(c.getUsername());
		v.add(c.getBookISBN());  
		v.add(c.getContent());
		v.add(c.getPublishingDate());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetComment(Comment c){
		Vector v = new Vector();
		v.add("id = '" + c.getID() + "'");
		v.add("username = '" + c.getUsername() + "'");
		v.add("bookISBN = '" + c.getBookISBN() + "'"); 
		v.add("content = '" + c.getContent() + "'");
		v.add("publishingDate = '" + c.getPublishingDate() + "'");
		return v;
	}
	
	////////////////////////////////////////////////////////////////////
	/*
	 * Operações com Review
	 */
	////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public boolean insertData(Review data) {
		boolean sucesso = true;
		Vector v = getVectorReview(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("comment", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public Review[] queryReview(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM review " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}

			Review[] rv = new Review[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM review " + w + o);

			while (result.next())
			{
				Review temp = new Review();
				
				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("id")) 
						temp.setID(result.getInt("id"));
					if(select.get(i).equalsIgnoreCase("username"))
						temp.setUsername(result.getString("username"));
					if(select.get(i).equalsIgnoreCase("bookIsbn"))
						temp.setBookISBN(result.getString("bookIsbn"));
					if(select.get(i).equalsIgnoreCase("bookName"))
						temp.setBookName(result.getString("bookName"));
					if(select.get(i).equalsIgnoreCase("content"))
						temp.setContent(result.getString("content"));
					if(select.get(i).equalsIgnoreCase("title"))
						temp.setTitle(result.getString("title"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}

				rv[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return rv;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Review: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Review data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetReview(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("review", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataReview(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("review", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorReview(Review r){
		Vector v = new Vector();
		v.add(r.getID());
		v.add(r.getUsername());
		v.add(r.getBookISBN());
		v.add(r.getBookName());
		v.add(r.getContent());
		v.add(r.getPublishingDate());
		v.add(r.getTitle());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetReview(Review r){
		Vector v = new Vector();
		v.add("id = '" + r.getID() + "'");
		v.add("username = '" + r.getUsername() + "'");
		v.add("bookISBN = '" + r.getBookISBN() + "'");
		v.add("bookName = '" + r.getBookName() + "'");
		v.add("content = '" + r.getContent() + "'");
		v.add("publishingDate = '" + r.getPublishingDate() + "'");
		v.add("title = '" + r.getTitle() + "'");
		return v;
	}
	
	///////////////////////////////////////////////////////////////////
	/*
	 * Operações com Rating
	 */
	////////////////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	public boolean insertData(Rating data) {
		boolean sucesso = true;
		Vector v = getVectorRating(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("rating", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public Rating[] queryRating(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM rating " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			
			Rating[] rt = new Rating[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM rating " + w + o);

			while (result.next())
			{
				Rating temp = new Rating();
				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("id")) 
						temp.setID(result.getInt("id"));
					if(select.get(i).equalsIgnoreCase("username"))
						temp.setUsername(result.getString("username"));
					if(select.get(i).equalsIgnoreCase("bookISBN"))
						temp.setBookISBN(result.getString("bookISBN"));
					if(select.get(i).equalsIgnoreCase("bookReview"))
						temp.setBookReview(result.getInt("bookReview"));
					if(select.get(i).equalsIgnoreCase("value"))
						temp.setValue(result.getInt("value"));
					if(select.get(i).equalsIgnoreCase("type"))
						temp.setType(result.getInt("type") == 1);
				}
				rt[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return rt;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Rating: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Rating data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetRating(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("rating", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataRating(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("rating", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorRating(Rating r){
		Vector v = new Vector();
		v.add(r.getID() + "'");
		v.add(r.getUsername());
		v.add(r.getBookISBN());
		v.add(r.getBookReview());
		v.add(r.getValue());
		v.add((r.getType()? 1 : 0 ));				
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetRating(Rating r){
		Vector v = new Vector();
		v.add("id = '" + r.getID() + "'");
		v.add("username = '" + r.getUsername() + "'");
		v.add("bookISBN = '" + r.getBookISBN() + "'");
		v.add("bookReview = '" + r.getBookReview() + "'");
		v.add("value = '" + r.getValue() + "'");
		v.add("type = '" + (r.getType()? 1 : 0 )+ "'");		
		return v;
	}
	
	///////////////////////////////////////////////////////////////////
	/*
	 * Operações com Session
	 */
	////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unchecked")
	public boolean insertData(Session data) {
		boolean sucesso = true;
		Vector v = getVectorSession(data);
		String values = stt.mountValuesStatement(v);
		try {
			stt.insert("session", values);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public Session[] querySession(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			ResultSet r = command.executeQuery(s + " FROM session " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}

			Session[] sn = new Session[numbRows];
			ResultSet result = command.executeQuery(s + " FROM session " + w + o);

			while (result.next())
			{
				Session temp = new Session();
				
				for(int i = 0; i < select.size(); i++){
					if(select.get(i).equalsIgnoreCase("username")) 
						temp.setUsername(result.getString("username"));
					if(select.get(i).equalsIgnoreCase("status"))
						temp.setStatus(result.getInt("status") == 1? true : false);
					if(select.get(i).equalsIgnoreCase("lastLogin"))
						temp.setLastLogin(result.getDate("lastLogin"));
				}
				sn[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return sn;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Session: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Session data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetSession(data);
		String set = stt.mountSetStatement(v);
		String w = stt.mountWhereStatement(where);		
		try {
			stt.update("session", set, w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}

	public boolean deleteDataSession(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("session", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorSession(Session s){
		Vector v = new Vector();
		v.add(s.getUsername());
		v.add((s.getStatus() ? 1 : 0));
		v.add(s.getLastLogin());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetSession(Session s){
		Vector v = new Vector();
		v.add("username = '" + s.getUsername() + "'");
		v.add("status = '" + (s.getStatus() ? 1 : 0) + "'");
		v.add("lastLogin = '" + s.getLastLogin() + "'");
		return v;
	}	
}
