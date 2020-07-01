/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author robin
 *
 */
public class FournisseurDaoJdbc implements FournisseurDao {

	@Override
	public List<Fournisseur> extraire() {
		return null;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("INSERT INTO FOURNISSEUR(ID, NOM) VALUES (" + fournisseur.getId()
					+ ", '" + fournisseur.getNom() + "')");

			System.out.println(nb);
			maConnexion.close();
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));
		int nb = 0;
		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			Statement monStatement = maConnexion.createStatement();
			nb = monStatement.executeUpdate("UPDATE FOURNISSEUR SET NOM='"+nouveauNom+"' Where NOM='"+ancienNom+"'");

			System.out.println(nb);
			maConnexion.close();
		}
		return nb;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));

		boolean efface = false;
		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			Statement monStatement = maConnexion.createStatement();
			int nb = monStatement.executeUpdate("DELETE FROM FOURNISSEUR WHERE ID="+fournisseur.getId());

			System.out.println(nb);
			maConnexion.close();
			efface = true;
		}
		return efface;
	}
}
