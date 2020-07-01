/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.SQLException;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;

/**
 * @author robin
 *
 */
public class TestJdbcArticles {

	/**
	 * 
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ArticleDaoJdbc adj = new ArticleDaoJdbc();
		Class.forName(adj.getDatabase().getString("database.driver"));
		adj.extraire();
	}
}
