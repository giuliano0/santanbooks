package TestApps;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import Classes.Book;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;

/**
 * Pequena aplicação exemplificando o uso do componente DataBase
 * @author Fernando Costa, João Scalett
 *
 */
public class AppDataBase {

	public final static int MILI_SECONDS_PER_MONTH = 24*60*60*100;
	public final static int MILI_SECONDS_PER_YEAR = 365*MILI_SECONDS_PER_MONTH;
	public static void main(String[] args) {
		try {
			IGlobalFactory factory = ComponentContextFactory.createGlobalFactory();
			/* Instancia objeto do tipo DataBase */
			IDataBase db = factory.createInstance(
					"<http://purl.org/dcc/JavaDB.DataBase>",
					"<http://purl.org/dcc/Interfaces.IDataBase>");
			/* Instancia objeto do tipo SQLStatements */
			ISQLStatements stt = factory.createInstance(
					"<http://purl.org/dcc/JavaDB.SQLStatements>",
					"<http://purl.org/dcc/Interfaces.ISQLStatements>");
			/* DataBase requer SQLStatements */
			IRequires<ISQLStatements> connectStatements = db.queryInterface(
					"<http://purl.org/dcc/Interfaces.ISQLStatements>",
					InterfaceType.REQUIRED);
			/* Conecta o SQLStatements no DataBase */
			connectStatements.connect(stt);

			/* montando dados de books para inserir */
			Book b1 = createBook1(),
				b2 = createBook2(), //método estático desta própria classe, 
				b3 = createBook3(); //apenas para deixar o Main mais limpo

			/* conectando o banco e inserindo os dados */
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

			// selectBook: contém os campos a serem selecionados na consulta
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

			/* realiza a consulta no banco. Result é um vetor de Books com todos os
			 * books que atendem à consulta.
			 * where pode ser nulo; ou seja, retorna todos os campos da tabela.
			 * order pode ser nulo; ou seja, retorna na ordem que estiver na tabela */
			Book result[] = db.queryBook(selectBook, where, order);
			printQuerryResults(result); //Método estático deste aplicativo de teste, apenas para deixar o código limpo
			
			/* Mudar alguns dados de b3 para atualizar */
			b3.setName(b3.getName() + " e PVCs");
			b3.setAuthors("Boyce");
			b3.setPublisher("Moderna");

			where.clear();
			where.add("isbn = '" + b3.getISBN() +"'");
			if(db.updateData(b3, where))
				System.out.println("Dados de book atualizados com sucesso (" + b3.getISBN() + ")");
			else
				System.out.println("Problema na atulizacao." + b3.getISBN() + ")");
			/* Consulta para verificar se o update ocorreu com sucesso */
			result = db.queryBook(selectBook, where, null);
			printQuerryResults(result);

			/* Delecao de livros */
			where.clear();
			where.add("edition = '3'");
			if(db.deleteDataBook(where))
				System.out.println("Dados de book deletados com sucesso");
			else
				System.out.println("Erro na delecao!");
			/* Consulta para verificar se o delete ocorreu com sucesso */
			result = db.queryBook(selectBook, null, null); //where null busca todos os dados. order null traz na ordem da tabela
			printQuerryResults(result);

			/* REMOVE TODAS AS TABELAS. SÓ PARA O EXEMPLO! */
			db.dropAllTables();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printQuerryResults(Book[] result) {
		if(result == null || result.length == 0)
			System.out.println("Nenhum resultado");
		else{
			System.out.println("\nRESULTADOS DA CONSULTA (total de " + result.length+ "):");
			for(int j = 0; j < result.length; j++){
				System.out.println(
						"ISBN: " + result[j].getISBN() +
						"\nNome: " + result[j].getName() +
						"\nAutor(es): " + result[j].getAuthors() +
						"\nDescricao: " + result[j].getDescription() +
						"\nEdicao: " + result[j].getEdition() +
						"\nCaminho da imagem: " + result[j].getImagePath() +
						"\nEditora: " + result[j].getPublisher() +
						"\nData de publicacao: " + result[j].getPublishingDate() + "\n"
				);
			}
			System.out.println();
		}
	}

	private static Book createBook3() {
		Date dataEdicao;
		Book b3 = new Book();
		b3.setISBN("978-86-234-0398-4");
		b3.setName("Equacoes diferenciais");
		b3.setAuthors("Stewart");
		b3.setDescription("Equacoes diferencias ordinarias, transformadas, séries, sistemas, PVC");
		b3.setEdition("5");
		try {
			b3.setImagePath("//images/books/ed-boyce.jpeg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b3.setPublisher("atica");
		dataEdicao = new Date((2002-1970)* MILI_SECONDS_PER_YEAR + 7 * MILI_SECONDS_PER_MONTH);
		try {
			b3.setPublishingDate(dataEdicao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return b3;
	}

	private static Book createBook2() {
		Date dataEdicao;
		/* montando dados de book para inserir */
		Book b2 = new Book();
		b2.setISBN("978-85-333-0398-4");
		b2.setName("Calculo 2");
		b2.setAuthors("Stewart");
		b2.setDescription("Derivadas parcias, gradiente, Integrais múltiplas");
		b2.setEdition("7");
		try {
			b2.setImagePath("//images/books/calc2-stewart.jpeg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b2.setPublisher("Thompson");
		dataEdicao = new Date((2004-1970)* MILI_SECONDS_PER_YEAR + 7 * MILI_SECONDS_PER_MONTH);
		try {
			b2.setPublishingDate(dataEdicao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return b2;
	}

	private static Book createBook1() {
		Book b1 = new Book();
		b1.setISBN("978-85-772-2156-1");
		b1.setName("Calculo 1");
		b1.setAuthors("Stewart");
		b1.setDescription("Limite, derivada, integral");
		b1.setEdition("3");
		try {
			b1.setImagePath("//images/books/calc1-stewart.jpeg");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b1.setPublisher("Thompson");
		Date dataEdicao = new Date((1997-1970)* MILI_SECONDS_PER_YEAR + 5 * MILI_SECONDS_PER_MONTH);
		try {
			b1.setPublishingDate(dataEdicao);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return b1;
	}
}
