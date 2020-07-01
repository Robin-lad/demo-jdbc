/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.entites.Article;

/**
 * @author robin
 *
 */
public interface ArticleDao {
	List<Article> extraire() throws ClassNotFoundException, SQLException;
	void insert(Article article) throws ClassNotFoundException, SQLException;
	int update(String ancienNom, String nouveauNom) throws ClassNotFoundException, SQLException;
	boolean delete(Article article) throws ClassNotFoundException, SQLException;
}
