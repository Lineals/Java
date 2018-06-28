package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Controleur.Controleur;
import Metier.Article;
import Metier.Lot;
import Metier.Sellable;

public class LotDAO extends DAO<Sellable> {

	public LotDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Sellable objet) {
		boolean result = false;
		String query = "INSERT INTO lot VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, objet.getRef());
			preparedStatement.setInt(2, ((Lot) objet).getNum());
			preparedStatement.setDouble(3, ((Lot) objet).getTaux());
			preparedStatement.setString(4, ((Lot) objet).getArticle().getRef());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Sellable objet) {
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
	public boolean update(Sellable objet) {
		boolean result = false;
		result = delete(objet);
		result = create(objet);
		return result;
	}

	@Override
	public ArrayList<Sellable> findAll() {
		ArrayList<Sellable> arrayList = new ArrayList<>();
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
