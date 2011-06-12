package JavaDB;

import java.sql.Date;

import Classes.Book;
import Classes.Informations;

public class AppDataBase {
	public static void main(String[] args) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Book b = new Book();
		
		b.setISBN("978-85-772-2156-1");
		b.setName("Cálculo 1");
		
		try {
			b.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		b.setAuthors("Eu mesmo");
		b.setDescription("Apesar do livro ser bom, cálculo é uma merda");
		
		DataBase db = new DataBase();
		db.connectDataBase();
		db.insertData(b);
				 
		Book[] result = db.queryBook("*", "isbn = '978-85-772-2156-1'", " ");
		System.out.println("ISBN: " + result[0].getISBN() + ", Name: " + result[0].getName());
	}
}
