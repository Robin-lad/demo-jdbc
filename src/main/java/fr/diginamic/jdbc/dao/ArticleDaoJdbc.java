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

import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author robin
 *
 */
public class ArticleDaoJdbc implements ArticleDao {

	private ResourceBundle database = ResourceBundle.getBundle("database");

	@Override
	public List<Article> extraire() throws ClassNotFoundException, SQLException {

		List<Article> list = null;

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			maConnexion.setAutoCommit(false);

			String sql = "SELECT * FROM ARTICLE a, FOURNISSEUR f WHERE a.ID_FOU = f.ID";

			try (Statement monStatement = maConnexion.createStatement();
					ResultSet curseur = monStatement.executeQuery(sql);) {

				list = new ArrayList<>();

				while (curseur.next()) {
					list.add(new Article(curseur.getInt(1), curseur.getString(2), curseur.getString(3),
							curseur.getDouble(4), new Fournisseur(curseur.getInt(5), curseur.getString(7))));
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
	public void insert(Article article) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			String sql = "INSERT INTO ARTICLE(ID, REF, DESIGNATION, PRIX, ID_FOU) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstatement = maConnexion.prepareStatement(sql);
			pstatement.setInt(1, article.getId());
			pstatement.setString(2, article.getRef());
			pstatement.setString(3, article.getDesignation());
			pstatement.setDouble(4, article.getPrix());
			pstatement.setInt(5, article.getFournisseur().getId());
			int nb = pstatement.executeUpdate();

			System.out.println(nb);
			maConnexion.close();
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		int nb = 0;
		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			String sql = "UPDATE ARTICLE SET DESIGNATION=? Where DESIGNATION=?";
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
	public boolean delete(Article article) throws ClassNotFoundException, SQLException {
		ResourceBundle database = ResourceBundle.getBundle("database");

		boolean efface = false;
		try (Connection maConnexion = DriverManager.getConnection(database.getString("database.url"),
				database.getString("database.user"), database.getString("database.pass"));) {

			String sql = "DELETE FROM ARTICLE WHERE ID=?";
			PreparedStatement pstatement = maConnexion.prepareStatement(sql);
			pstatement.setInt(1, article.getId());
			int nb = pstatement.executeUpdate();

			System.out.println(nb);
			maConnexion.close();
			efface = true;
		}
		return efface;
	}

	/**
	 * Getter
	 * 
	 * @return the database
	 */
	public ResourceBundle getDatabase() {
		return database;
	}

	/**
	 * Setter
	 * 
	 * @param database the database to set
	 */
	public void setDatabase(ResourceBundle database) {
		this.database = database;
	}

}
