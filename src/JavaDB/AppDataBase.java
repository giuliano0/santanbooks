package JavaDB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import Classes.Book;
import Classes.Informations;

public class AppDataBase {
	public static void main(String[] args) {
		
		Date dataAtual = new Date(System.currentTimeMillis());
		
		// montando dados de book para inserir
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
		
		// montando outros dados de book para inserir
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
		
		// montando dados de informations para inserir
		Informations i = new Informations();
		i.setIsbn("978-85-333-0398-X");
		i.setTitle("Comentário sobre o livro de Cálculo 1");
		i.setAuthorInfo("Eu mesmo");
		i.setComment("Apesar do livro ser bom, cálculo é uma merda");
		i.setDateInfo(dataAtual);
		
		// conectando o banco e inserindo os dados
		DataBase db = new DataBase();
		try {
			db.connectDataBase(); //deve sempre ser feito com try/catch
		} catch (SQLException e) {
			//na verdade nao deve fazer nada, pois essa excecao acontecera se o banco ja existir
			System.out.println(e.getMessage());
		}
		if(!db.insertData(b1))
			System.out.println("Problema na insercao: Livro com ISBN " + b1.getISBN() + " j� existe!" );
		if(!db.insertData(b2))
			System.out.println("Problema na insercao: Livro com ISBN " + b2.getISBN() + " j� existe!" );
		
		db.insertData(i);
		
		// criando select
		Vector<String> select = new Vector<String>();
		select.add("isbn");
		select.add("name");
		select.add("authors");
		
		// criando where
		Vector<String> where = new Vector<String>();
		//where.add("isbn = '978-85-772-2156-1'");
		//where.add("name = 'Cálculo 1'");
		where.add("authors = 'Eu mesmo'");

		// criando order
		Vector<String> order = new Vector<String>();
		order.add("isbn");
		order.add("name");
		
		// realizando a consulta
		Book result[] = db.queryBook(select, where, order);
		for(int j = 0; j < result.length; j++){
			System.out.println("ISBN: " + result[j].getISBN() + ", Name: " + result[j].getName());
		}
		
		// faz um update
		b2.setName("Cálculo 3");
		where.clear();
		where.add("isbn = '978-85-233-0143-2'");
		db.updateData(b2, where);
		
		// fazendo a consulta para ver se funcionou o update
		where.clear();
		where.add("authors = 'Eu mesmo'");
		Book result2[] = db.queryBook(select, where, order);
		for(int j = 0; j < result2.length; j++){
			System.out.println("ISBN: " + result2[j].getISBN() + ", Name: " + result2[j].getName());
		}
		
		// deletando dados
		where.clear();
		where.add("isbn = '978-85-233-0143-2'");
		db.deleteDataBook(where);
		
		// fazendo a consulta para ver se funcionou o delete
		where.clear();
		where.add("authors = 'Eu mesmo'");
		Book result3[] = db.queryBook(select, where, order);
		for(int j = 0; j < result3.length; j++){
			System.out.println("ISBN: " + result3[j].getISBN() + ", Name: " + result3[j].getName());
		}
		
	}
}
