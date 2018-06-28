package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import com.sun.corba.se.spi.ior.ObjectKeyTemplate;

import Controleur.Controleur;
import Metier.Commande;
import Metier.Commande.Ligne;
import Metier.Lot;

public class CommandeDAO extends DAO<Commande>{

	public CommandeDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Commande objet) {
		Boolean result = false;
		String query = "INSERT INTO commande VALUES (?, ?, ?, ?, ?, ?, ?)";
		String queryLigne = "INSERT INTO ligne VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, objet.getId());
			preparedStatement.setDate(2, Date.valueOf(objet.getDate().toLocalDate()));
			preparedStatement.setDouble(3, objet.getTauxReduc());
			preparedStatement.setDouble(4, objet.getFraisDePort());
			preparedStatement.setDouble(5, objet.getPrixTotal());
			preparedStatement.setBoolean(6, objet.getEstFinalisee());
			preparedStatement.setInt(7, objet.getClient().getId());
			preparedStatement.executeQuery();
			
			for (Ligne ligne: objet.getArticles()) {
				preparedStatement = this.connection.prepareStatement(queryLigne);
				preparedStatement.setInt(1, objet.getId());
				if(ligne.getSell() instanceof Lot) {
					preparedStatement.setString(2, ligne.getSell().getRef());
					preparedStatement.setNull(3, java.sql.Types.NULL);
				} else {
					preparedStatement.setNull(2, java.sql.Types.NULL);
					preparedStatement.setString(3, ligne.getSell().getRef());
				}
				preparedStatement.setInt(4, ligne.getQuantite());
				preparedStatement.executeQuery();
			}
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Commande objet) {
		Boolean result = false;
		String query = "DELETE FROM commande WHERE idcommande = ?";
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(1, objet.getId());
			preparedStatement.executeQuery();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean update(Commande objet) {
		Boolean result = false;
		String query = "UPDATE commande SET tauxreduc = ?, fraisport = ?, prix = ?, fini = ?, idclient = ? WHERE idcommande = ?";
		String queryDel = "DELETE FROM ligne WHERE idcommande = ?";
		String queryLigne = "INSERT INTO ligne VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setDouble(1, objet.getTauxReduc());
			preparedStatement.setDouble(2, objet.getFraisDePort());
			preparedStatement.setDouble(3, objet.getPrixTotal());
			preparedStatement.setBoolean(4, objet.getEstFinalisee());
			preparedStatement.setInt(5, objet.getClient().getId());
			preparedStatement.setInt(6, objet.getId());
			preparedStatement.executeQuery();
			
			preparedStatement = this.connection.prepareStatement(queryDel);
			preparedStatement.setInt(1, objet.getId());
			preparedStatement.executeQuery();
			
			for (Ligne ligne: objet.getArticles()) {
				preparedStatement = this.connection.prepareStatement(queryLigne);
				preparedStatement.setInt(1, objet.getId());
				if(ligne.getSell() instanceof Lot) {
					preparedStatement.setString(2, ligne.getSell().getRef());
					preparedStatement.setNull(3, java.sql.Types.NULL);
				} else {
					preparedStatement.setNull(2, java.sql.Types.NULL);
					preparedStatement.setString(3, ligne.getSell().getRef());
				}
				preparedStatement.setInt(4, ligne.getQuantite());
				preparedStatement.executeQuery();
			}
			result = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Commande> findAll() {
		ArrayList<Commande> listCommande = new ArrayList<Commande>();
		
		String query = "SELECT * FROM COMMANDE";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet rSet = preparedStatement.executeQuery();
			
			while (rSet.next()) {
				Commande commande = new Commande(Controleur.getClientById(rSet.getInt("idclient")), 
												  rSet.getInt("idcommande"),
												  rSet.getTimestamp("datecommande").toLocalDateTime(),
												  rSet.getFloat("tauxreduc"),
												  rSet.getFloat("fraisport"),
												  rSet.getBoolean("fini"));
				
				listCommande.add(feedCommandArticle(commande));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCommande;
	}
	
	private Commande feedCommandArticle(Commande commande) {
		String query = "SELECT * FROM LIGNE WHERE idcommande = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setLong(1, commande.getId());
			ResultSet rSet = preparedStatement.executeQuery();
			while (rSet.next()) {
				String refsellable;
				if(rSet.getString("refart") != null) {
					refsellable = rSet.getString("refart");
				} else {
					refsellable = rSet.getString("reflot");
				}
				commande.addSellable(Controleur.getArticleByReference(refsellable),
									 rSet.getInt("qte"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commande;
	}
}
