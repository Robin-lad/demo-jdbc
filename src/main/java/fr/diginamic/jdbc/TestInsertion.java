/**
 * 
 */
package fr.diginamic.jdbc;

import java.sql.SQLException;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

/**
 * @author robin
 *
 */
public class TestInsertion {

	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// il faut dédoubler la quote pour que ça marche et avec les preparedStatement il ne faut plus dédoubler la quote
		Fournisseur f = new Fournisseur(4, "L'espace création");
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		Class.forName(fdj.getDatabase().getString("database.driver"));
		fdj.insert(f);
	}
}
