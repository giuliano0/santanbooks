package JavaDB;

import java.sql.Date;

import Classes.Book;
import Classes.Informations;

public class AppDataBase {
	public static void main(String[] args) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Book b = new Book();
		b.setISBN("0002112451");
		b.setName("Cálculo 1");
		
		try {
			b.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Informations i = new Informations();
		i.setIsbn("0002112451");
		i.setTitle("Comentário sobre o livro de Cálculo 1");
		i.setAuthorInfo("Eu mesmo");
		i.setComment("Apesar do livro ser bom, cálculo é uma merda");
		i.setDateInfo(dataAtual);
		
		DataBase db = new DataBase();
		db.connectDataBase();
		db.insertData(b);
		db.insertData(i);
		
		Book[] result = db.queryBook("*", "isbn = '0002112451'", " ");
		System.out.println("ISBN: " + result[0].getISBN() + ", Name: " + result[0].getName());
	}
}
