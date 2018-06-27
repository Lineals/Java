package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Metier.Article;
import Metier.Papier;
import Metier.Stylo;
import Metier.Stylo.Couleur;

public class ArticleDAO extends DAO<Article>{

	public ArticleDAO(Connection con) {
		super(con);
	}

	@Override
	public boolean create(Article objet) {
		return false;
	}

	@Override
	public boolean delete(Article objet) {
		return false;
	}

	@Override
	public boolean update(Article objet) {
		return false;
	}

	public Article find(String ref) {
		Article article = null;
		
		String query = "SELECT * FROM ARTICLE WHERE refarticle = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, ref);
			ResultSet rSet = preparedStatement.executeQuery();
			rSet.next();
			if (rSet.getString("weigth") == null) {
				Couleur color = Couleur.valueOf(rSet.getString("color"));
				article = new Stylo(rSet.getString("nom"), 
									  rSet.getString("marque"), 
									  ref,
									  rSet.getFloat("cout"),
									  rSet.getFloat("prix"),
									  color);
			}
			else {
				article = new Papier(rSet.getString("nom"), 
									  rSet.getString("marque"), 
									  rSet.getString(ref), 
									  rSet.getFloat("cout"),
									  rSet.getFloat("prix"),
									  rSet.getFloat("weigth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public ArrayList<Article> findAll() {
		ArrayList<Article> listArticle = new ArrayList<Article>();
		
		String query = "SELECT * FROM ARTICLE";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet rSet = preparedStatement.executeQuery();
			
			while (rSet.next()) {
				Article article;
				if (rSet.getString("weigth") == null) {
					Couleur color = Couleur.valueOf(rSet.getString("color"));
					article = new Stylo(rSet.getString("nom"), 
										  rSet.getString("marque"), 
										  rSet.getString("refarticle"), 
										  rSet.getFloat("cout"),
										  rSet.getFloat("prix"),
										  color);
				}
				else {
					article = new Papier(rSet.getString("nom"), 
										  rSet.getString("marque"), 
										  rSet.getString("refarticle"), 
										  rSet.getFloat("cout"),
										  rSet.getFloat("prix"),
										  rSet.getFloat("weigth"));
				}
				listArticle.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticle;
	}
	

}
