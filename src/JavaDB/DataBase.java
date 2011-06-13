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
	
	public DataBase()
	{
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		bd = "jdbc:derby:db;create=true";
	}
	
	public void connectDataBase() {
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
            
            System.out.println("Tabela criada com sucesso!");

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
        } catch (SQLException erro) {
        	if(!erro.getMessage().equalsIgnoreCase("Table/View 'BOOK' already exists in Schema 'APP'."))
        		System.out.println("Erro na criacao da tabela: " + erro.getMessage());
        }
	}

	public void deleteData(Book data) {
		
	}

	public void deleteData(Informations data) {
		
	}

	@SuppressWarnings("unchecked")
	public void insertData(Book data) {
		Vector v = getVectorBook(data);
		String values = new String();
		
		for(int i = 0; i < v.size(); i++){
			if(i != v.size() - 1)
				if(v.get(i) != null)
					values += "'" + v.get(i) + "', ";
				else
					values += "'', ";
			else
				if(v.get(i) != null)
					values += "'" + v.get(i) + "'";
				else
					values += "''";
		}
		
		try {
            Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();

            // insert data
            command.executeUpdate("INSERT INTO book VALUES (" + values + ")");

            command.close();
            conexion.close();

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
        } catch (SQLException erro) {
            System.out.println("Erro no Insert: " + erro.getMessage());
        }
	}

	@SuppressWarnings("unchecked")
	public void insertData(Informations data) {
		Vector v = getVectorInformations(data);
		String values = new String();
		
		for(int i = 0; i < v.size(); i++){
			if(i != v.size() - 1)
				if(v.get(i) != null)
					values += "'" + v.get(i) + "', ";
				else
					values += "'', ";
			else
				if(v.get(i) != null)
					values += "'" + v.get(i) + "'";
				else
					values += "''";
		}
		
		try {
            Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();

            // insert data
            command.executeUpdate("INSERT INTO informations (isbn, title, authorInfo, " +
            		"comment, review, dateInfo) VALUES (" + values + ")");

            command.close();
            conexion.close();

        } catch (ClassNotFoundException erro) {
            System.out.println(erro.getMessage());
        } catch (SQLException erro) {
            System.out.println("Erro no Insert: " + erro.getMessage());
        }		
	}

	public Book[] queryBook(Vector<String> select,
			Vector<String> where, Vector<String> order) {	
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			// mounting string select
			String s = "SELECT ";
			if(select != null)
			{
				for(int i = 0; i < select.size(); i++){
					if(i != select.size() - 1)
						s += select.get(i) + ", ";	
					else
						s += select.get(i) + " ";
				}
			}
			else
			{
				// select can not be null
				return null;
			}

			// mounting string where
			String w = "";
			if(where != null)
			{
				w = "WHERE ";
				for(int i = 0; i < where.size(); i++){
					if(i != where.size() - 1)
						w += where.get(i) + " AND ";	
					else
						w += where.get(i) + " ";
				}
			}

			// mounting string order
			String o = "";
			if(order != null)
			{
				o = "ORDER BY ";
				for(int i = 0; i < order.size(); i++){
					if(i != order.size() - 1)
						o += order.get(i) + ", ";	
					else
						o += order.get(i) + " ";
				}
			}

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
			System.out.println("Erro na consulta: " + erro.getMessage());
			return null;
		}
	}

	public Informations[] queryInformations(Vector<String> select,
			Vector<String> where, Vector<String> order) {
		try {
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			// mounting string select
			String s = "SELECT ";
			if(select != null)
			{
				for(int i = 0; i < select.size(); i++){
					if(i != select.size() - 1)
						s += select.get(i) + ", ";	
					else
						s += select.get(i) + " ";
				}
			}
			else
			{
				// select can not be null
				return null;
			}

			// mounting string where
			String w = "";
			if(where != null)
			{
				w = "WHERE ";
				for(int i = 0; i < where.size(); i++){
					if(i != where.size() - 1)
						w += where.get(i) + " AND ";	
					else
						w += where.get(i) + " ";
				}
			}

			// mounting string order
			String o = "";
			if(order != null)
			{
				o = "ORDER BY ";
				for(int i = 0; i < order.size(); i++){
					if(i != order.size() - 1)
						o += order.get(i) + ", ";	
					else
						o += order.get(i) + " ";
				}
			}

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
			System.out.println("Erro na consulta: " + erro.getMessage());
			return null;
		}
	}

	@Override
	public void updateData(Book data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateData(Informations data) {
		// TODO Auto-generated method stub
		
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
}
