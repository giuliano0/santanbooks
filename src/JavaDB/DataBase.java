package JavaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Classes.Book;
import Classes.Comment;
import Classes.Review;
import Classes.User;

/**
 * class handling database
 * @author Fernando Costa e JoÃ£o Scalett
 */
public class DataBase implements IDataBase{
	private SQLStatements stt; 
	private String bd, driver;

	public DataBase(){
		bd = "jdbc:derby:db;create=true";
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		stt = new SQLStatements(); //nao devera ser instanciado aqui, e sim recebido no metodo connect (componente)
		stt.setBd("jdbc:derby:db;create=true");
		stt.setDriver("org.apache.derby.jdbc.EmbeddedDriver");
	}

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#connectDataBase()
	 */
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
					"isbn VARCHAR(100) NOT NULL, " +
					"content VARCHAR(255) NOT NULL, " +
					"publishingDate DATE NOT NULL)"
					/*"FOREIGN KEY(username, isbn))"*/
			);

			/* Creates table review */
			stt.executeStatement("CREATE TABLE review " +
					"(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
					"(START WITH 1, INCREMENT BY 1), " +
					"username VARCHAR(100) NOT NULL, " +
					"isbn VARCHAR(100) NOT NULL, " +
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
					"isbn VARCHAR(100), " +
					"review INT, " +
					"value INT NOT NULL," +
					"type INT NOT NULL)"
					/*"FOREIGN KEY(username, isbn, review))"*/
			);
			
			/* Creates table session */
			stt.executeStatement(
					"CREATE TABLE session " +
					"(username VARCHAR(100) NOT NULL, " +
					"status INT NOT NULL, " +
					"lastDate DATE NOT NULL, PRIMARY KEY(username))"
					/*"FOREIGN KEY(username, isbn, review))"*/
			);
			
		} catch (SQLException erro) {
			throw new SQLException("Erro na criacao das tabelas: " + erro.getMessage());
		}
	}
	
	/* Método temporário. Até pq não é vantagem poder sair excluindo tudo */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#queryUser(java.util.Vector, java.util.Vector, java.util.Vector)
	 */
	public Book[] queryUser(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#updateData(Classes.User, java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#deleteDataUser(java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorUser(Classes.User)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorUser(User u){
		Vector v = new Vector();
		v.add(u.getUsername());
		v.add((u.getAccesLevel() ? 1 : 0)); //como o banco não suporta boolean, deve ser 0 ou 1
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorSetUser(Classes.User)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorSetUser(User u){
		Vector v = new Vector();
		v.add("username = '" + u.getUsername() + "'");
		v.add("accessLevel = '" + (u.getAccesLevel() ? 1 : 0) + "'"); //como o banco não suporta boolean, deve ser 0 ou 1
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
	
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#insertData(Classes.Book)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#queryBook(java.util.Vector, java.util.Vector, java.util.Vector)
	 */
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

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#updateData(Classes.Book, java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#deleteDataBook(java.util.Vector)
	 */
	public boolean deleteDataBook(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		try {
			stt.delete("book", w);
		} catch (SQLException erro) {
			sucesso = false;
		}
		return sucesso;
	}
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorBook(Classes.Book)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorSetBook(Classes.Book)
	 */
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
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#insertData(Classes.Comment)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#queryComment(java.util.Vector, java.util.Vector, java.util.Vector)
	 */
	public Book[] queryComment(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#updateData(Classes.Comment, java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#deleteDataComment(java.util.Vector)
	 */
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
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorComment(Classes.Comment)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorComment(Comment c){
		Vector v = new Vector();
		v.add(c.getID());
		v.add(c.getAuthor());
		//v.add(c.getISBN()); //FALTA ESTE MÉTODO NA CLASSE COMMENT 
		v.add(c.getContent());
		v.add(c.getPublishingDate());
		return v;
	}

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorSetComment(Classes.Comment)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorSetComment(Comment c){
		Vector v = new Vector();
		v.add("id = '" + c.getID() + "'");
		v.add("username = '" + c.getAuthor() + "'");
		//v.add("isbn = '" + c.getISBN() + "'"); //FALTA ESTE MÉTODO NA CLASSE COMMENT 
		v.add("content = '" + c.getContent() + "'");
		v.add("publishingDate = '" + c.getPublishingDate() + "'");
		return v;
	}
	
	////////////////////////////////////////////////////////////////////
	/*
	 * Operações com Review
	 */
	////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#insertData(Classes.Review)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#queryReview(java.util.Vector, java.util.Vector, java.util.Vector)
	 */
	public Book[] queryReview(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM review " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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
			System.out.println("Erro na consulta Review: " + erro.getMessage());
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#updateData(Classes.Review, java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#deleteDataReview(java.util.Vector)
	 */
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
	
	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorReview(Classes.Review)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorReview(Review r){
		Vector v = new Vector();
		v.add(r.getID());
		v.add(r.getAuthor());
		v.add(r.getBookISBN());
		v.add(r.getBookName());
		v.add(r.getContent());
		v.add(r.getPublishingDate());
		v.add(r.getTitle());
		return v;
	}

	/* (non-Javadoc)
	 * @see JavaDB.SDdasdasdasd#getVectorSetReview(Classes.Review)
	 */
	@SuppressWarnings("unchecked")
	public Vector getVectorSetReview(Review r){
		Vector v = new Vector();
		v.add("id = '" + r.getID() + "'");
		v.add("username = '" + r.getAuthor() + "'");
		v.add("isbn = '" + r.getBookISBN() + "'");
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
	
	/* RATING AINDA NAO IMPLEMENTADO */
	/*@SuppressWarnings("unchecked")
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

	public Book[] queryRating(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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
		v.add(r.getID());
		v.add(r.getAuthor());
		v.add(r.getBookISBN());
		v.add(r.getReview());
		v.add(r.getValue());
		v.add(r.getType());		
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetRating(Rating r){
		Vector v = new Vector();
		v.add("id = '" + r.getID() + "'");
		v.add("username = '" + r.getAuthor() + "'");
		v.add("isbn = '" + r.getBookISBN() + "'");
		v.add("review = '" + r.getReview() + "'");
		v.add("value = '" + r.getValue() + "'");
		v.add("type = '" + r.getType() + "'");		
		return v;
	}*/
	
	///////////////////////////////////////////////////////////////////
	/*
	 * Operações com Session
	 */
	////////////////////////////////////////////////////////////////////
	
	/*SESSION AINDA NAO IMPLEMENTADO*/
	/*@SuppressWarnings("unchecked")
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

	public Book[] querySession(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			String s = stt.mountSelectStatement(select);
			if(s == null) return null; //select cannot be null!

			String w = stt.mountWhereStatement(where);
			String o = stt.mountOrderStatement(order);

			// making the query
			//System.out.println(s + " FROM book " + w + o);
			ResultSet r = command.executeQuery(s + " FROM book " + w + o);

			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);

			// creates an array of objects book to return
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
						temp.setImagePath(result.getString("imagePath"));
					if(select.get(i).equalsIgnoreCase("publisher"))
						temp.setPublisher(result.getString("publisher"));
					if(select.get(i).equalsIgnoreCase("publishingDate"))
						try {
							temp.setPublishingDate(result.getDate("publishingDate"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}
				//System.out.println("isbn: " +temp.getISBN() + ", cont: " + cont);

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
	public Vector getVectorSession(Rating r){
		Vector v = new Vector();
		v.add(r.getUser());
		v.add((r.getStatus() ? 1 : 0));
		v.add(r.getLastDate());
		return v;
	}

	@SuppressWarnings("unchecked")
	public Vector getVectorSetSession(Session s){
		Vector v = new Vector();
		v.add("username = '" + r.getUser() + "'");
		v.add("status = '" + (r.getStatus() ? 1 : 0) + "'");
		v.add("lastDate = '" + r.getLastDate() + "'");
		return v;
	}*/
	
}
