package TestApps;

import java.sql.Date;

import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;
import Classes.Book;
import Classes.User;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;

public class AppBusinessObjectExample {
	public static void main(String[] args) {

		IGlobalFactory factory = null;
		try {

			factory = ComponentContextFactory.createGlobalFactory();

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

			/* Instancia objeto do tipo BusinessObject */
			factory = ComponentContextFactory.createGlobalFactory();
			IBusinessObject businessObjectComponent = factory.createInstance(
					"<http://purl.org/dcc/DataBase.BusinessObject>",
					"<http://purl.org/dcc/Interfaces.IBusinessObject>");

			/* DataBase requer SQLStatements */
			IRequires<IDataBase> connectStatements2 = businessObjectComponent
					.queryInterface(
							"<http://purl.org/dcc/Interfaces.IDataBase>",
							InterfaceType.REQUIRED);

			/* Conecta o Database no BusinessObject */
			connectStatements2.connect(db);

			// Exemplos de utilizacao
			exemploBooks(businessObjectComponent);
			exemploRatings(businessObjectComponent);
			exemploComents(businessObjectComponent);
			exemploUsers(businessObjectComponent);
			exemploReviews(businessObjectComponent);
			exemploSessions(businessObjectComponent);

		} catch (ContextException e) {
			System.err.println(e.getMessage());
		} catch (FactoryException e) {
			System.err.println(e.getMessage());
		}
		
		System.exit(0);
	}

	public static void exemploBooks(IBusinessObject businessObjectComponent) {

		Date dataAtual = new Date(System.currentTimeMillis());

		// montando dados de book para inserir
		Book b1 = new Book();
		b1.setISBN("978-85-772-2156-1");
		b1.setName("Calculo 1");
		b1.setAuthors("Eu mesmo");
		b1.setDescription("Apesar do livro ser bom, calculo eh uma merda");
		try {
			b1.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// montando outros dados de book para inserir
		Book b2 = new Book();
		b2.setISBN("978-85-233-0143-2");
		b2.setName("Calculo 2");
		b2.setAuthors("Eu mesmo");
		b2.setDescription("Apesar deste outro livro ser bom, calculo eh uma merda");
		try {
			b2.setPublishingDate(dataAtual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!businessObjectComponent.insertBook(b1)) {
			System.out.println("Problema na insercao: Livro com ISBN "
					+ b1.getISBN() + " já existe!");
		}

		if (!businessObjectComponent.insertBook(b2)) {
			System.out.println("Problema na insercao: Livro com ISBN "
					+ b1.getISBN() + " já existe!");
		}

		// realizando a consulta por autor
		Book result[] = businessObjectComponent
				.selectBooksByAuthors("Eu mesmo");
		for (int j = 0; j < result.length; j++) {
			System.out.println("ISBN: " + result[j].getISBN() + ", Name: "
					+ result[j].getName());
		}

		// faz um update
		b2.setName("Calculo 3");
		if (businessObjectComponent.updateBook(b2))
			System.out.println("Dados de book atualizados com sucesso");

		// fazendo a consulta para ver se funcionou o update
		Book result2[] = businessObjectComponent
				.selectBooksByAuthors("Eu mesmo");
		for (int j = 0; j < result2.length; j++) {
			System.out.println("ISBN: " + result2[j].getISBN() + ", Name: "
					+ result2[j].getName());
		}

		// deletando dados
		if (businessObjectComponent.deleteBook("978-85-233-0143-2"))
			System.out.println("Dados de book deletados com sucesso");

		// fazendo a consulta para ver se funcionou o delete
		Book result3[] = businessObjectComponent
				.selectBooksByAuthors("Eu mesmo");
		for (int j = 0; j < result3.length; j++) {
			System.out.println("ISBN: " + result3[j].getISBN() + ", Name: "
					+ result3[j].getName());
		}

		// obtendo a lista de todos os books
		Book result4[] = businessObjectComponent.selectAllBooks();
		System.out.println("todos os livros");
		for (int j = 0; j < result4.length; j++) {
			
			System.out.println("ISBN: " + result4[j].getISBN() + ", Name: "
					+ result4[j].getName());
		}

	}

	public static void exemploRatings(IBusinessObject businessObjectComponent) {
		// TODO
	}

	public static void exemploComents(IBusinessObject businessObjectComponent) {
		// TODO
	}

	public static void exemploUsers(IBusinessObject businessObjectComponent) {
//		Date dataAtual = new Date(System.currentTimeMillis());
//		
//		User user = new User();
//		user.setAccessLevel(false);
//		
//		try {
//			user.setBirthday(dataAtual);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		user.setCollege("Unicamp");
//		user.setCourse("CC");				
		
	}

	public static void exemploReviews(IBusinessObject businessObjectComponent) {
		// TODO
	}

	public static void exemploSessions(IBusinessObject businessObjectComponent) {
		// TODO
	}

}
