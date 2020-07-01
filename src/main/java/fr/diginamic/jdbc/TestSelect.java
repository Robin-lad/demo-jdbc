/**
 * 
 */
package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author robin
 *
 */
public class TestSelect {

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

			maConnexion.setAutoCommit(false);

			try (Statement monStatement = maConnexion.createStatement();
					ResultSet curseur = monStatement.executeQuery("SELECT * FROM FOURNISSEUR");) {

				List<Fournisseur> list = new ArrayList<>();

				while (curseur.next()) {
					list.add(new Fournisseur(curseur.getInt(1), curseur.getString(2)));
				}

				System.out.println(list);

				maConnexion.commit();
			} catch (SQLException e) {
				maConnexion.rollback();
			}
		}
	}
}
