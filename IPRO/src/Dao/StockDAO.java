package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import Controleur.Controleur;
import Metier.Sellable;

	public class StockDAO{
		private Connection connection = null;
		
		public StockDAO(Connection con){
			this.connection = con;
		}
		
		public HashMap<Sellable, Integer> fetchAll() {
			HashMap<Sellable, Integer> hashMap = new HashMap<>();
			String query = "SELECT * FROM stock";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = this.connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					hashMap.put(Controleur.getArticleByReference(resultSet.getString("refart"))
							    ,resultSet.getInt("qte"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return hashMap;
			
		}
		
		public boolean create(Sellable sellable, Integer qte) {
			boolean result = false;
			String query = "INSERT INTO stock values (?, ?)";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = this.connection.prepareStatement(query);
				preparedStatement.setString(1, sellable.getRef());
				preparedStatement.setInt(2, qte);
				preparedStatement.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public boolean delete(Sellable sellable) {
			boolean result = false;
			String query = "DELETE FROM stock WHERE refart = ?";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = this.connection.prepareStatement(query);
				preparedStatement.setString(1, sellable.getRef());
				preparedStatement.executeUpdate();
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		
		public boolean update(Sellable sellable, Integer qte) {
			boolean result = false;
			result = delete(sellable);
			result = create(sellable, qte);
			return result;
		}
}
