package JavaDB;

import java.sql.Date;

import Classes.Book;
import Classes.Informations;

public class AppDataBase {
	public static void main(String[] args) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		Book b = new Book();
		b.setIsbn("0002112450");
		b.setName("Cálculo 1");
		b.setPublishingDate(dataAtual);
		
		Informations i = new Informations();
		i.setIsbn("0002112450");
		i.setTitle("Comentário sobre o livro de Cálculo 1");
		i.setAuthorInfo("Eu mesmo");
		i.setComment("Apesar do livro ser bom, cálculo é uma merda");
		i.setDateInfo(dataAtual);
		
		DataBase db = new DataBase();
		db.connectDataBase();
		db.insertData(b);
		db.insertData(i);
		
		Book[] result = db.queryBook("*", "isbn = '0002112450'", " ");
		System.out.println("ISBN: " + result[0].getIsbn() + ", Name: " + result[0].getName());
	}
}
