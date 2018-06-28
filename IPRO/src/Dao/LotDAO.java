package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controleur.Controleur;
import Metier.Article;
import Metier.Lot;

public class LotDAO extends DAO<Lot> {

	public LotDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Lot objet) {
		boolean result = false;
		String query = "INSERT INTO lot VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, objet.getRef());
			preparedStatement.setInt(2, objet.getNum());
			preparedStatement.setDouble(3, objet.getTaux());
			preparedStatement.setString(4, objet.getArticle().getRef());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Lot objet) {
		boolean result = false;
		String query = "DELETE FROM lot WHERE redlot = ?";
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
	public boolean update(Lot objet) {
		boolean result = false;
		result = delete(objet);
		result = create(objet);
		return result;
	}

	@Override
	public ArrayList<Lot> findAll() {
		ArrayList<Lot> arrayList = new ArrayList<>();
		String query = "SELECT * FROM lot";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			ResultSet rSet = preparedStatement.executeQuery();
			while(rSet.next()) {
				arrayList.add(new Lot(rSet.getString("reflot"), 
									rSet.getInt("qte"), 
									rSet.getDouble("tauxreduc"), 
									(Article) Controleur.getArticleByReference(rSet.getString("refarticle"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

}
