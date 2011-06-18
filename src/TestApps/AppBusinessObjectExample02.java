package TestApps;

import java.sql.Date;

import Classes.User;
import Interfaces.IBusinessObject;
import Interfaces.IDataBase;
import Interfaces.ISQLStatements;
import anima.component.IRequires;
import anima.component.InterfaceType;
import anima.context.exception.ContextException;
import anima.factory.IGlobalFactory;
import anima.factory.context.componentContext.ComponentContextFactory;
import anima.factory.exception.FactoryException;

public class AppBusinessObjectExample02 {
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

			/* BusinessObject requer DataBase */
			IRequires<IDataBase> connectDataBase = businessObjectComponent
					.queryInterface(
							"<http://purl.org/dcc/Interfaces.IDataBase>",
							InterfaceType.REQUIRED);

			/* Conecta o Database no BusinessObject */
			connectDataBase.connect(db);
			
			// Exemplos de utilizacao
			exemploUsers(businessObjectComponent);
			//exemploSessions(businessObjectComponent);
			db.dropAllTables();

		} catch (ContextException e) {
			System.err.println(e.getMessage());
		} catch (FactoryException e) {
			System.err.println(e.getMessage());
		}
		
		System.exit(0);
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
		
		if(businessObjectComponent.insertUser(u))
			System.out.println("Usuario " + u.getUsername() + " inserido com sucesso!");
		else
			System.out.println("Username " + u.getUsername() + " invalido ou ja existente!");
		
		u.setUsername("my user name");
		if(businessObjectComponent.insertUser(u))
			System.out.println("Usuario " + u.getUsername() + " inserido com sucesso!");
		else
			System.out.println("Username " + u.getUsername() + " invalido ou ja existente!");
		
		System.out.println("USUARIOS:");
		for(User usuario : businessObjectComponent.selectAllUsers()){
			System.out.println(usuario.getUsername());
		}
		
		u.setCollege("Unip");
		u.setCourse("Moda");
		u.setPassword("432432");
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
		for(User usuario : businessObjectComponent.selectAllUsers()){
			System.out.println(usuario.getUsername());
		}
	}

	public static void exemploSessions(IBusinessObject businessObjectComponent) {
		// TODO
	}

}


