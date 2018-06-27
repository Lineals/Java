package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controleur.Controleur;
import Metier.Commande;

public class CommandeDAO extends DAO<Commande>{

	public CommandeDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Commande objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Commande objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Commande objet) {
		// TODO Auto-generated method stub
		return false;
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
