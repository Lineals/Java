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
		Boolean result = false;
		String query = "INSERT INTO article VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, objet.getRef());
			preparedStatement.setString(2, objet.getName());
			preparedStatement.setString(3, objet.getBrand());
			preparedStatement.setDouble(4, objet.getCoutObtention());
			preparedStatement.setDouble(5, objet.getPrice());
			if (objet instanceof Stylo) {
				System.out.println(((Stylo) objet).getColor());
				preparedStatement.setString(6, ((Stylo) objet).getColor().toString());
				preparedStatement.setNull(7, java.sql.Types.NULL);
			} else {
				preparedStatement.setNull(6, java.sql.Types.NULL);
				preparedStatement.setDouble(7, ((Papier) objet).getPoids());
			}
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Article objet) {
		Boolean result = false;
		String query = "DELETE FROM article WHERE refarticle = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, objet.getRef());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Article objet) {
		boolean result = false;
		result = delete(objet);
		result = create(objet);
		return result;
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
