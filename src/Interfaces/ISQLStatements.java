package Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import anima.annotation.ComponentInterface;
import anima.component.ISupports;

/**
 * Prove metodos auxiliares e de sintaxe de comandos SQL.
 * @author Fernando Costa e Jo�o Scalett
 */
@ComponentInterface("<http://purl.org/dcc/Interfaces.ISQLStatements>")
public interface ISQLStatements extends ISupports{

	public String getDriver();

	public void setDriver(String driver);

	public String getBd();

	public void setBd(String bd);

	public void executeStatement(String statement) throws SQLException;

	public ResultSet querryStatement(String statement) throws SQLException;

	public void insert(String table, String values) throws SQLException;

	public void delete(String table, String where) throws SQLException;

	public void update(String table, String set, String where)
			throws SQLException;

	public String mountValuesStatement(Vector<Object> v);

	public String mountSelectStatement(Vector<String> select);

	public String mountWhereStatement(Vector<String> where);

	public String mountOrderStatement(Vector<String> order);

	public String mountSetStatement(Vector<Object> v);

}