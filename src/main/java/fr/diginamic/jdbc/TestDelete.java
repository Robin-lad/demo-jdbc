/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author robin
 *
 */
public class TestDelete {

	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("DELETE FROM FOURNISSEUR where ID=4");

			System.out.println(nb);
			maConnexion.close();
		}
	}
}
