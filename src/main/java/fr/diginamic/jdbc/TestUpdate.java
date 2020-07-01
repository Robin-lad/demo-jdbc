/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.SQLException;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;

/**
 * @author robin
 *
 */
public class TestUpdate {

	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		Class.forName(fdj.getDatabase().getString("database.driver"));
		fdj.update("L'espace création", "Le jeu");
	}
}
