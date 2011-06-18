package TestApps;

import java.sql.Date;

import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;
import Classes.Book;
import Classes.Comment;
import Classes.Rating;
import Classes.Review;
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
		businessObjectComponent
				.insertRating("978-85-233-0143-2", 3, "testuser");
		businessObjectComponent.insertRating("978-85-233-0143-2", 1,
				"testuser1");
		businessObjectComponent.insertRating("978-85-233-0143-2", 3,
				"testuser3");
		businessObjectComponent.insertRating("978-85-233-0143-2", 4,
				"testuser5");

		Rating[] ratings = businessObjectComponent.selectAllRatings();
		System.out.println("Rating");
		for (int j = 0; j < ratings.length; j++) {
			System.out.println("ISBN: " + ratings[j].getBookISBN()
					+ ", value: " + ratings[j].getValue());
		}

		ratings[0].setValue(5);
		businessObjectComponent.updateRating(ratings[0]);

		ratings = businessObjectComponent.selectAllRatings();
		System.out.println("Rating atualizado");
		for (int j = 0; j < ratings.length; j++) {
			System.out.println("ISBN: " + ratings[j].getBookISBN()
					+ ", value: " + ratings[j].getValue());
		}

		businessObjectComponent.deleteRating(ratings[0]);
	}

	public static void exemploComents(IBusinessObject businessObjectComponent) {
		businessObjectComponent.insertComment("978-85-233-0143-2",
				"livro bomm", "testuser");
		businessObjectComponent.insertComment("978-85-233-0143-2",
				"livro bomm2342", "testuser");
		businessObjectComponent.insertComment("978-85-233-0143-2",
				"livro bomm2342234", "testuser");

		Comment[] result = businessObjectComponent
				.selectCommentsBookByIsbn("978-85-233-0143-2");
		System.out.println("Comments");

		for (int j = 0; j < result.length; j++) {
			System.out.println("usename: " + result[j].getUsername()
					+ ", content: " + result[j].getContent());
		}

		businessObjectComponent.deleteCommentsByUser("testuser");
		result = businessObjectComponent
				.selectCommentsBookByIsbn("978-85-233-0143-2");
		System.out.println("Comments deletado");
		for (int j = 0; j < result.length; j++) {
			System.out.println("usename: " + result[j].getUsername()
					+ ", content: " + result[j].getContent());
		}

	}

	public static void exemploUsers(IBusinessObject businessObjectComponent) {
		Date birth = new Date(System.currentTimeMillis());

		User u = new User();
		u.setUsername("jombler");
		u.setAccessLevel(false);

		try {
			u.setBirthday(birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		u.setCollege("Unicamp");
		u.setCourse("CC");
		u.setEmail("jombler@students.ic.unicamp.br");
		u.setGender(true);
		u.setName("Joao Paulo");
		u.setPassowrd("123456");
		u.setSelfDescription("Aluno de ciencia da computacao, unicamp");
		u.setIngressYear(birth);

		if (businessObjectComponent.insertUser(u))
			System.out.println("Usuario " + u.getUsername()
					+ " inserido com sucesso!");
		else
			System.out.println("Username " + u.getUsername()
					+ " invalido ou ja existente!");

		u.setUsername("my user name");
		if (businessObjectComponent.insertUser(u))
			System.out.println("Usuario " + u.getUsername()
					+ " inserido com sucesso!");
		else
			System.out.println("Username " + u.getUsername()
					+ " invalido ou ja existente!");

		System.out.println("USUARIOS:");
		for (User usuario : businessObjectComponent.selectAllUsers()) {
			System.out.println(usuario.getUsername());
		}

		u.setCollege("Unip");
		u.setCourse("Moda");
		u.setPassowrd("432432");
		businessObjectComponent.updateUser(u);

		System.out.println("ATUALIZACAO");
		u = businessObjectComponent.selectUser(u.getUsername());
		System.out.println(u.getUsername());
		System.out.println(u.getCollege());
		System.out.println(u.getCourse());
		System.out.println(u.getPassword());

		System.out.println("DELECAO");
		businessObjectComponent.deleteUser(u.getUsername());
		System.out.println("USUARIOS:");
		for (User usuario : businessObjectComponent.selectAllUsers()) {
			System.out.println(usuario.getUsername());
		}
	}

	public static void exemploReviews(IBusinessObject businessObjectComponent) {
		businessObjectComponent.insertReview("978-85-233-0143-2", "title1",
				"content1", "testuser");
		businessObjectComponent.insertReview("978-85-233-0143-2", "title2",
				"content2", "testuser1");
		businessObjectComponent.insertReview("978-85-233-0143-2", "title3",
				"content3", "testuser2");
		businessObjectComponent.insertReview("978-85-233-0143-2", "title4",
				"content4", "testuser3");
		businessObjectComponent.insertReview("978-85-233-0143-2", "title5",
				"content5", "testuser5");

		Review[] review = businessObjectComponent.selectAllReviews();
		System.out.println("Review");
		for (int j = 0; j < review.length; j++) {
			System.out.println("ISBN: " + review[j].getBookISBN() + ", value: "
					+ review[j].getContent());
		}

		review[0].setContent("Review atualizado");
		businessObjectComponent.updateReview(review[0]);

		review = businessObjectComponent.selectAllReviews();
		System.out.println("Review atualizado");
		for (int j = 0; j < review.length; j++) {
			System.out.println("ISBN: " + review[j].getBookISBN()
					+ ", content: " + review[j].getContent());
		}

		businessObjectComponent.deleteReview(review[0]);

		review = businessObjectComponent.selectAllReviews();
		System.out.println("Review deletado");
		for (int j = 0; j < review.length; j++) {
			System.out.println("ISBN: " + review[j].getBookISBN()
					+ ", content: " + review[j].getContent());
		}
	}

	public static void exemploSessions(IBusinessObject businessObjectComponent) {
		// TODO
	}

}
