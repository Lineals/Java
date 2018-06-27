package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Metier.Client;

public class ClientDAO extends DAO<Client>{

	public ClientDAO(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Client objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Client objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Client objet) {
		// TODO Auto-generated method stub
		return false;
	}

	public Client find(int id) {
		Client client= null;
		
		String query = "SELECT * FROM CLIENT WHERE idclient = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setInt(0, id);
			ResultSet rSet = preparedStatement.executeQuery();
			rSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
}
