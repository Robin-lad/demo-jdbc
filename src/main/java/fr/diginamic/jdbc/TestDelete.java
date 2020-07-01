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
public class TestDelete {

	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Fournisseur f = new Fournisseur(4, "La maison du jeu");
		FournisseurDaoJdbc fdj = new FournisseurDaoJdbc();
		fdj.delete(f);
	}
}
