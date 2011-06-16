package JavaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import anima.annotation.Component;
import anima.component.base.ComponentBase;

import Interfaces.ISQLStatements;

@Component(id = "<http://purl.org/dcc/JavaDB.SQLStatements>", 
		provides = { "<http://purl.org/dcc/Interfaces.ISQLStatements>" })
public class SQLStatements extends ComponentBase implements ISQLStatements {
	public String driver;
	public String bd;

	public SQLStatements() {
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		bd = "jdbc:derby:db;create=true";
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#getDriver()
	 */
	public String getDriver() {
		return driver;
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#setDriver(java.lang.String)
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#getBd()
	 */
	public String getBd() {
		return bd;
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#setBd(java.lang.String)
	 */
	public void setBd(String bd) {
		this.bd = bd;
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#executeStatement(java.lang.String)
	 */
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
	
	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#querryStatement(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#insert(java.lang.String, java.lang.String)
	 */
	public void insert(String table, String values) throws SQLException{
		try {
			executeStatement("INSERT INTO " + table + " VALUES (" + values + ")");
		} catch (SQLException erro) {
			throw new SQLException("Erro na insercao em " + table + ": " + erro.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#delete(java.lang.String, java.lang.String)
	 */
	public void delete(String table, String where) throws SQLException{
		try {
			executeStatement("DELETE FROM " + table + " " + where);
		} catch (SQLException erro) {
			throw new SQLException("Erro na delecao em " + table + ": " + erro.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#update(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void update(String table, String set, String where) throws SQLException{
		try {
			executeStatement("UPDATE " + table + " SET " + set + where);
		} catch (SQLException erro) {
			throw new SQLException("Erro na atualizacao em " + table + ": " + erro.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#mountValuesStatement(java.util.Vector)
	 */
	@SuppressWarnings("unchecked")
	public String mountValuesStatement(Vector v) {
		String values = new String();
		for(int i = 0; i < v.size(); i++){
			if(i != v.size() - 1)
				if(v.get(i) != null)
					values += "'" + v.get(i) + "', ";
				else
					values += "'', ";
			else
				if(v.get(i) != null)
					values += "'" + v.get(i) + "'";
				else
					values += "''";
		}
		return values;
	}

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#mountSelectStatement(java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#mountWhereStatement(java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#mountOrderStatement(java.util.Vector)
	 */
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

	/* (non-Javadoc)
	 * @see JavaDB.ISQLStatements#mountSetStatement(java.util.Vector)
	 */
	@SuppressWarnings("unchecked")
	public String mountSetStatement(Vector v) {
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