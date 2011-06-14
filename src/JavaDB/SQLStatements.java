package JavaDB;

import java.util.Vector;

public class SQLStatements {
	public String driver;
	public String bd;

	public SQLStatements() {
	}
	
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
			s = null; // select can not be null
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