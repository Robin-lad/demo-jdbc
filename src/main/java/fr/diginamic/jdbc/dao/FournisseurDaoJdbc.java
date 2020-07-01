/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author robin
 *
 */
public class FournisseurDaoJdbc implements FournisseurDao {

	@Override
	public List<Fournisseur> extraire() throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));
		List<Fournisseur> list = null;

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			maConnexion.setAutoCommit(false);
			
			String sql = "SELECT * FROM FOURNISSEUR";

			try (Statement monStatement = maConnexion.createStatement();
					ResultSet curseur = monStatement.executeQuery(sql);) {

				list = new ArrayList<>();

				while (curseur.next()) {
					list.add(new Fournisseur(curseur.getInt(1), curseur.getString(2)));
				}

				System.out.println(list);

				maConnexion.commit();
			} catch (SQLException e) {
				maConnexion.rollback();
			}
		}
		
		return list;
	}

	@Override
	public void insert(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		Class.forName(database.getString("database.driver"));

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			
			String sql = "INSERT INTO FOURNISSEUR(ID, NOM) VALUES (?, ?)";
			PreparedStatement pstatement = maConnexion.prepareStatement(sql);
			pstatement.setInt(1, fournisseur.getId());
			pstatement.setString(2, fournisseur.getNom());
			int nb = pstatement.executeUpdate();

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
			
			String sql = "UPDATE FOURNISSEUR SET NOM=? Where NOM=?";
			PreparedStatement pstatement = maConnexion.prepareStatement(sql);
			pstatement.setString(1, nouveauNom);
			pstatement.setString(2, ancienNom);
			nb = pstatement.executeUpdate();
			
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

			String sql = "DELETE FROM FOURNISSEUR WHERE ID=?";
			PreparedStatement pstatement = maConnexion.prepareStatement(sql);
			pstatement.setInt(1, fournisseur.getId());
			int nb = pstatement.executeUpdate();
			
			System.out.println(nb);
			maConnexion.close();
			efface = true;
		}
		return efface;
	}
}
