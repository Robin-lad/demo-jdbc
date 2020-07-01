/**
 * 
 */
package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author robin
 *
 */
public class TestConnexionJdbc {

	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ResourceBundle database = ResourceBundle.getBundle("database");
		Class.forName(database.getString("database.driver"));

		Connection maConnection = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));

		System.out.println("connexion : " + maConnection);

		maConnection.close();
	}
}
