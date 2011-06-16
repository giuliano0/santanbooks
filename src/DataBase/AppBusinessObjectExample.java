package DataBase;

import java.sql.Date;

import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;
import Classes.Book;

public class AppBusinessObjectExample {
	public static void main(String[] args) {

		IGlobalFactory factory = null;
		try {

			// criado uma instancia do componente de database

			factory = ComponentContextFactory.createGlobalFactory();
			IBusinessObject dataBaseComponent = factory
					.createInstance("<http://purl.org/dcc/DataBase.BusinessObject>");

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
			
			
			if (!dataBaseComponent.insertBook(b1)) {
				System.out.println("Problema na insercao: Livro com ISBN "
						+ b1.getISBN() + " já existe!");
			}
			
			if (!dataBaseComponent.insertBook(b2)) {
				System.out.println("Problema na insercao: Livro com ISBN "
						+ b1.getISBN() + " já existe!");
			}				

						
			
			// realizando a consulta
			Book result[] = dataBaseComponent.selectBooksByAuthors("Eu mesmo");			
			for (int j = 0; j < result.length; j++) {
				System.out.println("ISBN: " + result[j].getISBN() + ", Name: "
						+ result[j].getName());
			}			
			

			// faz um update
			b2.setName("CÃ¡lculo 3");
			if (dataBaseComponent.updateBook(b2))
				System.out.println("Dados de book atualizados com sucesso");

			
			// fazendo a consulta para ver se funcionou o update
			Book result2[] = dataBaseComponent.selectBooksByAuthors("Eu mesmo");			
			for (int j = 0; j < result2.length; j++) {
				System.out.println("ISBN: " + result[j].getISBN() + ", Name: "
						+ result[j].getName());
			}
			
			
			// deletando dados			
			if (dataBaseComponent.deleteBook("978-85-233-0143-2"))
				System.out.println("Dados de book deletados com sucesso");
			

			// fazendo a consulta para ver se funcionou o delete
			Book result3[] = dataBaseComponent.selectBooksByAuthors("Eu mesmo");			
			for (int j = 0; j < result3.length; j++) {
				System.out.println("ISBN: " + result[j].getISBN() + ", Name: "
						+ result[j].getName());
			}
						

		} catch (ContextException e) {
			System.err.println(e.getMessage());
		} catch (FactoryException e) {
			System.err.println(e.getMessage());
		}
	}
}
