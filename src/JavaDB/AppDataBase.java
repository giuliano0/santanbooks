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
		b.setAuthors("Eu mesmo");
		b.setDescription("Apesar do livro ser bom, cálculo é uma merda");
		
		try {
			b.setPublishingDate(dataAtual);
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
		db.insertData(b);
		db.insertData(i);
		
			 
		//Book[] result = db.queryBook("*", "isbn = '978-85-772-2156-1'", " ");
		//System.out.println("ISBN: " + result[0].getISBN() + ", Name: " + result[0].getName());
	}
}
