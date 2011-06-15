package JavaDB;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;

public class AppDataBase {

	public final static int MILI_SECONDS_PER_MONTH = 24*60*60*100;
	public final static int MILI_SECONDS_PER_YEAR = 365*MILI_SECONDS_PER_MONTH;
	public static void main(String[] args) {

		/* montando dados de book para inserir */
		Book b1 = new Book();
		b1.setISBN("978-85-772-2156-1");
		b1.setName("Calculo 1");
		b1.setAuthors("Stewart");
		b1.setDescription("Limite, derivada, integral");
		b1.setEdition("3");
		b1.setImagePath("//images/books/calc1-stewart.jpeg");
		b1.setPublisher("Thompson");
		Date dataEdicao = new Date((1997-1970)* MILI_SECONDS_PER_YEAR + 5 * MILI_SECONDS_PER_MONTH);
		try {
			b1.setPublishingDate(dataEdicao);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		/* montando dados de book para inserir */
		Book b2 = new Book();
		b2.setISBN("978-85-333-0398-4");
		b2.setName("Calculo 2");
		b2.setAuthors("Stewart");
		b2.setDescription("Derivadas parcias, gradiente, Integrais múltiplas");
		b2.setEdition("7");
		b2.setImagePath("//images/books/calc2-stewart.jpeg");
		b2.setPublisher("Thompson");
		dataEdicao = new Date((2004-1970)* MILI_SECONDS_PER_YEAR + 7 * MILI_SECONDS_PER_MONTH);
		try {
			b2.setPublishingDate(dataEdicao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		/* montando dados de book para inserir */
		Book b3 = new Book();
		b3.setISBN("978-86-234-0398-4");
		b3.setName("Equacoes diferenciais");
		b3.setAuthors("Boyce");
		b3.setDescription("Equacoes diferencias ordinarias, transformadas, séries, sistemas, PVC");
		b3.setEdition("5");
		b3.setImagePath("//images/books/ed-boyce.jpeg");
		b3.setPublisher("atica");
		dataEdicao = new Date((2002-1970)* MILI_SECONDS_PER_YEAR + 7 * MILI_SECONDS_PER_MONTH);
		try {
			b3.setPublishingDate(dataEdicao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		/* conectando o banco e inserindo os dados */
		DataBase db = new DataBase();
		try {
			db.connectDataBase(); //deve sempre ser feito com try/catch
		} catch (SQLException e) {
			//acontece se houver problema na criação de alguma tabela ou se já existir
			System.out.println(e.getMessage());
		}
		if(db.insertData(b1))
			System.out.println("Livro com ISBN " + b1.getISBN() + " inserido com sucesso!");
		else
			System.out.println("Problema na insercao: Livro com ISBN " + b1.getISBN() + " já existe!" );
		if(db.insertData(b2))
			System.out.println("Livro com ISBN " + b2.getISBN() + " inserido com sucesso!");
		else
			System.out.println("Problema na insercao: Livro com ISBN " + b2.getISBN() + " já existe!" );
		if(db.insertData(b3))
			System.out.println("Livro com ISBN " + b3.getISBN() + " inserido com sucesso!");
		else
			System.out.println("Problema na insercao: Livro com ISBN " + b3.getISBN() + " já existe!" );
		/*  tenta inserir b1 novamente. Deve exibir a mensagem de erro*/
		if(db.insertData(b1))
			System.out.println("Livro com ISBN " + b1.getISBN() + " inserido com sucesso!");
		else
			System.out.println("Problema na insercao: Livro com ISBN " + b1.getISBN() + " já existe!" );
		
		// selectBook: contém os campos selecionados na consulta
		Vector<String> selectBook = new Vector<String>();
		selectBook.add("isbn");
		selectBook.add("name");
		selectBook.add("authors");
		selectBook.add("description");
		selectBook.add("edition");
		selectBook.add("imagePath");
		selectBook.add("imagePath");
		selectBook.add("publisher");
		selectBook.add("publisher");
		selectBook.add("publishingDate");

		// where: cada elemento é uma condição da consulta
		Vector<String> where = new Vector<String>();
		where.add("authors = 'Stewart'");

		// order: ordem de retorno da consulta
		Vector<String> order = new Vector<String>();
		order.add("name");
		order.add("isbn");

		// realiza a consulta no banco
		Book result[] = db.queryBook(selectBook, where, order);
		for(int j = 0; j < result.length; j++){
			System.out.println(
					"\nISBN: " + result[j].getISBN() +
					"\nNome: " + result[j].getName() +
					"\nAutor(es): " + result[j].getAuthors() +
					"\nDescricao: " + result[j].getDescription() +
					"\nEdicao: " + result[j].getEdition() +
					"\nCaminho da imagem: " + result[j].getImagePath() +
					"\nEditora: " + result[j].getPublisher() +
					"\nData de publicacao: " + result[j].getPublishingDate()
			);
		}
		///CONTINUAR A PARTIR DAQUI
		// faz um update
		b3.setName("CÃ¡lculo 3");
		where.clear();
		where.add("isbn = '978-85-233-0143-2'");
		if(db.updateData(b3, where))
			System.out.println("Dados de book atualizados com sucesso");


		// fazendo a consulta para ver se funcionou o update
		where.clear();
		where.add("authors = 'Eu mesmo'");
		Book result2[] = db.queryBook(selectBook, where, order);
		for(int j = 0; j < result2.length; j++){
			System.out.println("ISBN: " + result2[j].getISBN() + ", Name: " + result2[j].getName());
		}

		// deletando dados
		where.clear();
		where.add("isbn = '978-85-233-0143-2'");
		if(db.deleteDataBook(where))
			System.out.println("Dados de book deletados com sucesso");;

			// fazendo a consulta para ver se funcionou o delete
			where.clear();
			where.add("authors = 'Eu mesmo'");
			Book result3[] = db.queryBook(selectBook, where, order);
			for(int j = 0; j < result3.length; j++){
				System.out.println("ISBN: " + result3[j].getISBN() + ", Name: " + result3[j].getName());
			}
			/* REMOVE TODAS AS TABELAS. SÓ PARA O EXEMPLO! */
			db.dropAllTables();
	}
}
