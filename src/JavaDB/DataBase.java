package JavaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Classes.Book;
import Classes.Informations;

/**
 * class handling database
 * @author Fernando Costa e João Scalett
 */
public class DataBase implements IDataBase{
	private String driver;
	private String bd;
	private SQLStatements stt; 
	
	public DataBase()
	{
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		bd = "jdbc:derby:db;create=true";
		stt = new SQLStatements();
	}
	
	public void connectDataBase() throws SQLException {
		try {
            Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();
            
            // Creates table book
            command.executeUpdate(
                    "CREATE TABLE book " +
                    "(isbn VARCHAR(100) NOT NULL, " +
                    "name VARCHAR(45) NOT NULL, " +
                    "authors VARCHAR(45) DEFAULT NULL, " +
                    "description VARCHAR(100), " +
                    "edition VARCHAR(100), " +
                    "imagePath VARCHAR(100), " +
                    "publisher VARCHAR(100), " +
                    "publishingDate DATE NOT NULL, PRIMARY KEY(isbn))"
            );
            
            // Creates table informations
            command.executeUpdate(
                    "CREATE TABLE informations " +
                    "(id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY " +
                    "(START WITH 1, INCREMENT BY 1), " +
                    "isbn VARCHAR(100) NOT NULL, " +
                    "title VARCHAR(45) NOT NULL, " +
                    "authorInfo VARCHAR(45), " +
                    "comment VARCHAR(1000), " +
                    "review VARCHAR(10000), " +
                    "dateInfo DATE NOT NULL)"
            );

            command.close();
            conexion.close();
            
            System.out.println("Tabelas criadas com sucesso!");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
        } catch (SQLException erro) {
        	throw new SQLException("Erro na criacao das tabelas: " + erro.getMessage());
        }
	}

	@SuppressWarnings("unchecked")
	public boolean insertData(Book data) {
		boolean sucesso = true;
		Vector v = getVectorBook(data);
		String values = stt.mountValuesStatement(v);

		try {
            Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();

            // insert data
            command.executeUpdate("INSERT INTO book VALUES (" + values + ")");
            
            System.out.println("Dados do book inseridos com sucesso");

            command.close();
            conexion.close();

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no Insert Book: " + erro.getMessage());
            sucesso = false;
        }
		return sucesso;
	}


	@SuppressWarnings("unchecked")
	public boolean insertData(Informations data) {
		boolean sucesso = true;
		Vector v = getVectorInformations(data);
		String values = stt.mountValuesStatement(v);
		
		try {
            Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();

            // insert data
            command.executeUpdate("INSERT INTO informations (isbn, title, authorInfo, " +
            		"comment, review, dateInfo) VALUES (" + values + ")");
            
            System.out.println("Dados de informations inseridos com sucesso");

            command.close();
            conexion.close();

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no Insert Informations: " + erro.getMessage());
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

	public Informations[] queryInformations(Vector<String> select,
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
			ResultSet r = command.executeQuery(s + " FROM informations " + w + o);
			
			int numbRows = 0, cont = 0;
			while(r.next()){
				numbRows++;
			}
			//System.out.println(numbRows);
	
			// creates an array of objects informations to return
			Informations[] i = new Informations[numbRows];		
			ResultSet result = command.executeQuery(s + " FROM informations " + w + o);

			while (result.next())
			{
				Informations temp = new Informations();

				for(int j = 0; j < select.size(); j++){
					if(select.get(j).equalsIgnoreCase("isbn")) 
						temp.setIsbn(result.getString("isbn"));
					if(select.get(j).equalsIgnoreCase("title"))
						temp.setTitle(result.getString("title"));
					if(select.get(j).equalsIgnoreCase("authorInfo"))
						temp.setAuthorInfo(result.getString("authorInfo"));
					if(select.get(j).equalsIgnoreCase("comment"))
						temp.setComment(result.getString("comment"));
					if(select.get(j).equalsIgnoreCase("review"))
						temp.setReview(result.getString("review"));
					if(select.get(j).equalsIgnoreCase("dateInfo"))
						try {
							temp.setDateInfo(result.getDate("dateInfo"));
						} catch (Exception e) {
							System.out.println("Erro na data: " +e.getMessage());
						}
				}

				i[cont] = temp;
				cont++;
			}
			command.close();
			conexion.close();
			return i;

		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
			return null;
		} catch (SQLException erro) {
			System.out.println("Erro na consulta Informations: " + erro.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Book data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetBook(data);
		String set = stt.mountSetStatement(v);
		
		String w = stt.mountWhereStatement(where);
		
		//System.out.println("UPDATE book SET " + set + w);
		
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

            // makes the updating of data
			command.executeUpdate("UPDATE book SET " + set + w);

			command.close();
			conexion.close();

            System.out.println("Dados de book atualizados com sucesso");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no Update Book: " + erro.getMessage());
            sucesso = false;
        }
		return sucesso;
	}

	@SuppressWarnings("unchecked")
	public boolean updateData(Informations data, Vector<String> where) {
		boolean sucesso = true;
		Vector v = getVectorSetInformations(data);
		String set = stt.mountSetStatement(v);
		
		String w = stt.mountWhereStatement(where);
		
		//System.out.println("UPDATE informations SET " + set + w);
		
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

            // makes the updating of data
			command.executeUpdate("UPDATE informations SET " + set + w);

			command.close();
			conexion.close();

            System.out.println("Dados de informations atualizados com sucesso");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no Update Informations: " + erro.getMessage());
            sucesso = false;
        }
		return sucesso;
	}
	
	public boolean deleteDataBook(Vector<String> where){
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		
		//System.out.println("DELETE FROM book " + w);
		
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

            // makes the deletion of data
			command.executeUpdate("DELETE FROM book " + w);

			command.close();
			conexion.close();

            System.out.println("Dados de book deletados com sucesso");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no delete Book: " + erro.getMessage());
            sucesso = false;
        }
		return sucesso;
	}

	public boolean deleteDataInformations(Vector<String> where) {
		boolean sucesso = true;
		String w = stt.mountWhereStatement(where);
		
		//System.out.println("DELETE FROM informations " + w);
		
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

            // makes the deletion of data
			command.executeUpdate("DELETE FROM informations " + w);

			command.close();
			conexion.close();

            System.out.println("Dados de informations deletados com sucesso");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
            sucesso = false;
        } catch (SQLException erro) {
            System.out.println("Erro no delete Informations: " + erro.getMessage());
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
	
	@SuppressWarnings("unchecked")
	public Vector getVectorInformations(Informations i){
		Vector v = new Vector();
		
		v.add(i.getIsbn());
		v.add(i.getTitle());
		v.add(i.getAuthorInfo());
		v.add(i.getComment());
		v.add(i.getReview());
		v.add(i.getDateInfo());
		return v;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getVectorSetInformations(Informations i){
		Vector v = new Vector();
		
		v.add("isbn = '" + i.getIsbn() + "'");
		v.add("title = '" + i.getTitle() + "'");
		v.add("authorInfo = '" + i.getAuthorInfo() + "'");
		v.add("comment = '" + i.getComment() + "'");
		v.add("review = '" + i.getReview() + "'");
		v.add("dateInfo = '" + i.getDateInfo() + "'");
		return v;
	}
}
