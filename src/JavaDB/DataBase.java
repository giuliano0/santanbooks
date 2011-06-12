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
                    "authors VARCHAR(45), " +
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

	public Book[] queryBook(String select, String w, String o) {	
		try {
			Class.forName(driver);
            Connection conexion = DriverManager.getConnection(bd);
            Statement command = conexion.createStatement();

            // makes the query
            String where = "", order = "";
            
	        if(w != null && w != " ")
	        {
	        	where = "WHERE " + w;
	        }
	        if(o != null && o != " ")
	        {
	        	order = "Order by " + o;
	        }
	        
	        ResultSet result = command.executeQuery("SELECT " + select + " FROM book " + where + order);
	        //System.out.println("SELECT " + select + " FROM book " + where + order);
	        
	        // creates an array of objects book to return
			Book[] b = new Book[result.getFetchSize()];
			boolean contentHas = result.next();
		
			int cont = 0;
			
			while (contentHas)
			{
				try {
					Book temp = new Book();
					
					temp.setISBN(result.getString("isbn"));
					temp.setName(result.getString("name"));
					temp.setAuthor(result.getString("author"));
					temp.setDescription(result.getString("description"));
					temp.setEdition(result.getString("edition"));
					temp.setImagePath(result.getString("imagePath"));
					temp.setPublisher(result.getString("publisher"));
					temp.setPublishingDate(result.getDate("publishingDate"));
					
					b[cont] = temp;
					contentHas = result.next();
					cont++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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

	@Override
	public Informations[] queryInformations(String select, String Where, String Order) {
		Informations[] i = new Informations[1];
		return i;
		
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
		v.add(b.getAuthor());
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
