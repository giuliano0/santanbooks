package santanbooks.dataBase.example;

import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;
import santanbooks.dataBase.IDataBaseEntity;
import santanbooks.dataBase.entities.Pessoa;

public class Teste {

	public static void main(String[] args) {
		// Primeiro exemplo, classe que terá chave unica
		// Ex: Livro
		// Constructor necessita de 2 parametros: O nome da Entidade e se ela é
		// chave unica
		IDataBaseEntity bd;

		IGlobalFactory factory = null;
		try {
			factory = ComponentContextFactory.createGlobalFactory();
		} catch (ContextException e) {
			System.err.println(e.getMessage());
		} catch (FactoryException e) {
			System.err.println(e.getMessage());
		}

		bd = factory
				.createInstance("<http://purl.org/dcc/santanbooks.dataBase.DataBaseEntity>");

		bd.setEntidade("Pessoa");
		bd.isChaveUnica(true);
		Pessoa pessoa = bd.buildEntityClass();

		pessoa.setIdentificador("Mauricio"); // Toda classe terá que ter um
												// identificador, no caso do
												// livro será o ISBN
		pessoa.setNome("Mauricio");
		pessoa.setIdade(20);

		if (bd.salvarEntidade(pessoa))
			System.out.println("Salvo");
		else
			System.out.println("Erro");

		// Pronto, aqui a gente já salvou, se você checar em
		// \src\Trabalho\Pessoa verá o xml ^^
		// Agora vamos alterá-lo

		Pessoa pessoa2 = bd.obterEntidade("Mauricio"); // Aqui a gente passa
														// aquele identificador
														// lá de cima
		pessoa2.setIdade(21);

		if (bd.salvarEntidade(pessoa))
			System.out.println("Salvo");
		else
			System.out.println("Erro");

		// E está salvo! =D

		// Segundo exemplo, classe que não terá chave unica
		// Ex: Comentario - Varios comentarios para um livro
		// Constructor necessita de 2 parametros: O nome da Entidade e se ela é
		// chave unica
		IDataBaseEntity bd2;
		bd2 = factory
				.createInstance("<http://purl.org/dcc/santanbooks.dataBase.DataBaseEntity>");

		bd2.setEntidade("Pessoa");
		bd2.isChaveUnica(false);

		Pessoa pessoa3 = bd.buildEntityClass();

		pessoa3.setIdentificador("Mauricio"); // Toda classe terá que ter um
												// identificador, no caso do
												// livro será o ISBN
		pessoa3.setNome("Mauricio");
		pessoa3.setIdade(20);

		if (bd2.salvarEntidade(pessoa3))
			System.out.println("Salvo");
		else
			System.out.println("Erro");

		// Vamos salvar outra?
		Pessoa pessoa4 = bd.buildEntityClass();

		pessoa4.setIdentificador("Mauricio"); // Toda classe terá que ter um
												// identificador, no caso do
												// livro será o ISBN
		pessoa4.setNome("Mauricio2");
		pessoa4.setIdade(21);

		if (bd2.salvarEntidade(pessoa4))
			System.out.println("Salvo");
		else
			System.out.println("Erro");

		// Agora vamos obter as 2 =D
		Pessoa pessoa5 = bd2.obterEntidade("Mauricio");
		while (pessoa5 != null) {
			System.out.println(pessoa5.getNome() + pessoa5.getIdade());

			// Obtemos a proxima
			pessoa5 = bd2.obterProximo();
		}

		// Para alterar é igual do primeiro jeito
		Pessoa pessoa6 = bd2.obterEntidade("Mauricio");
		while (pessoa6 != null) {
			// Só que precisamos criar um novo BD, porque o primeiro está
			// iterando
			IDataBaseEntity bd3;
			bd3 = factory
					.createInstance("<http://purl.org/dcc/santanbooks.dataBase.DataBaseEntity>");

			bd3.setEntidade("Pessoa");
			bd3.isChaveUnica(false);

			pessoa6.setIdade(30);
			bd3.salvarEntidade(pessoa6);
			pessoa6 = bd.obterProximo();
		}

		// E está salvo
	}
}