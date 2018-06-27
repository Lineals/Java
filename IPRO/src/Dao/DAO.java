package Dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connection = null;
	
	public DAO (Connection con){
		this.connection = con;
	}
	
	public abstract boolean create(T objet);
	
	public abstract boolean delete(T objet);
	
	public abstract boolean update(T objet);
	
	public abstract ArrayList<T> findAll();
}
