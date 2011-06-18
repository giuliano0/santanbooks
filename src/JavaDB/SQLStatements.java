package JavaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Interfaces.ISQLStatements;
import anima.annotation.Component;
import anima.component.base.ComponentBase;

/**
 * Metodos auxiliares e de sintaxe de comandos SQL.
 * @author Fernando Costa e João Scalett
 */
@Component(id = "<http://purl.org/dcc/JavaDB.SQLStatements>", 
		provides = { "<http://purl.org/dcc/Interfaces.ISQLStatements>" })
public class SQLStatements extends ComponentBase implements ISQLStatements {
	public String driver;
	public String bd;

	public SQLStatements() {
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		bd = "jdbc:derby:db;create=true";
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public void executeStatement(String statement) throws SQLException{
		try{
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			command.executeUpdate(statement);
			
			command.close();
			conexion.close();
		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
		} catch (SQLException erro) {
			throw new SQLException(erro.getMessage());
		}
	}
	
	public ResultSet querryStatement(String statement) throws SQLException{
		ResultSet r = null;
		try{
			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(bd);
			Statement command = conexion.createStatement();

			r = command.executeQuery(statement);
			
			command.close();
			conexion.close();
		} catch (ClassNotFoundException erro) {
			System.out.println(erro.getMessage());
		} catch (SQLException erro) {
			throw new SQLException(erro.getMessage());
		}
		return r;
	}

	public void insert(String table, String values) throws SQLException{
		try {
			String columns = getColumns(table);
			//executeStatement("INSERT INTO " + table + " VALUES (" + values + ")");
			executeStatement("INSERT INTO " + table + columns + " VALUES (" + values + ")");
		} catch (SQLException erro) {
			throw new SQLException("Erro na insercao em " + table + ": " + erro.getMessage());
		}
	}
	
	private String getColumns(String table){
		String s = "";
		if(table.equals("comment"))
			s = " (username, bookISBN, content, publishingDate) ";
		else if(table.equals("review"))
			s = " (username, bookISBN, bookName, content, publishingDate, title) ";
		else if(table.equals("rating"))
			s = " (username, bookISBN, bookReview, value, type) ";
		return s;
	}
	
	public void delete(String table, String where) throws SQLException{
		try {
			executeStatement("DELETE FROM " + table + " " + where);
		} catch (SQLException erro) {
			throw new SQLException("Erro na delecao em " + table + ": " + erro.getMessage());
		}
	}
	
	public void update(String table, String set, String where) throws SQLException{
		try {
			executeStatement("UPDATE " + table + " SET " + set + where);
		} catch (SQLException erro) {
			throw new SQLException("Erro na atualizacao em " + table + ": " + erro.getMessage());
		}
	}

	public String mountValuesStatement(Vector<Object> v) {
		String values = new String();
		for(int i = 0; i < v.size(); i++){
			if(i != v.size() - 1)
				if(v.get(i) != null){
					if(v.get(i) instanceof Integer)
						values +=  v.get(i) + ", ";
					else
						values += "'" + v.get(i) + "', ";
				}
				else
					values += "'', ";
			else
				if(v.get(i) != null){
					if(v.get(i) instanceof Integer)
						values +=  v.get(i);
					else
						values += "'" + v.get(i) + "'";
				}
				else
					values += "''";
		}
		return values;
	}

	public String mountSelectStatement(Vector<String> select) {
		String s = "SELECT ";
		if(select != null){
			for(int i = 0; i < select.size(); i++){
				if(i != select.size() - 1)
					s += select.get(i) + ", ";	
				else
					s += select.get(i) + " ";
			}
		}
		else{
			s = null; // select cannot be null
		}
		return s;
	}

	public String mountWhereStatement(Vector<String> where) {
		String w = "";
		if(where != null){
			w = " WHERE ";
			for(int i = 0; i < where.size(); i++){
				if(i != where.size() - 1)
					w += where.get(i) + " AND ";	
				else
					w += where.get(i) + " ";
			}
		}
		return w;
	}

	public String mountOrderStatement(Vector<String> order) {
		String o = "";
		if(order != null){
			o = "ORDER BY ";
			for(int i = 0; i < order.size(); i++){
				if(i != order.size() - 1)
					o += order.get(i) + ", ";	
				else
					o += order.get(i) + " ";
			}
		}
		return o;
	}

	public String mountSetStatement(Vector<Object> v) {
		String set = new String();
		for(int i = 0; i < v.size(); i++){
			if(i != v.size() - 1)
				if(v.get(i) != null)
					set += "" + v.get(i) + ", ";
				else
					set += ", ";
			else
				if(v.get(i) != null)
					set += "" + v.get(i) + "";
				else
					set += "";
		}
		return set;
	}
}