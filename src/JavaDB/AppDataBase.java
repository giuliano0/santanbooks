package JavaDB;

import java.sql.Date;
import java.util.Vector;
import Classes.Book;
import Classes.Informations;

public class AppDataBase {
	public static void main(String[] args) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Book b1 = new Book();
		
		b1.setISBN("978-85-772-2156-1");
		b1.setName("Cálculo 1");
		b1.setAuthors("Eu mesmo");
		b1.setDescription("Apesar do livro ser bom, cálculo é uma merda");
		
		try {
			b1.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Book b2 = new Book();
		
		b2.setISBN("978-85-233-0143-2");
		b2.setName("Cálculo 2");
		b2.setAuthors("Eu mesmo");
		b2.setDescription("Apesar deste outro livro ser bom, cálculo é uma merda");
		
		try {
			b2.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Informations i = new Informations();
		i.setIsbn("978-85-333-0398-X");
		i.setTitle("Comentário sobre o livro de Cálculo 1");
		i.setAuthorInfo("Eu mesmo");
		i.setComment("Apesar do livro ser bom, cálculo é uma merda");
		i.setDateInfo(dataAtual);
		
		DataBase db = new DataBase();
		db.connectDataBase();
		db.insertData(b1);
		db.insertData(b2);
		db.insertData(i);
		
			 
		Vector<String> select = new Vector<String>();
		select.add("isbn");
		select.add("name");
		select.add("authors");
		
		Vector<String> where = new Vector<String>();
		//where.add("isbn = '978-85-772-2156-1'");
		//where.add("name = 'Cálculo 1'");
		where.add("authors = 'Eu mesmo'");

		Vector<String> order = new Vector<String>();
		order.add("isbn");
		order.add("name");
		
		Book result[] = db.queryBook(select, where, order);
		for(int j = 0; j < result.length; j++){
			System.out.println("ISBN: " + result[j].getISBN() + ", Name: " + result[j].getName());
		}
		
	}
}
