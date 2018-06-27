package Controleur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
	private static final String url = "jdbc:postgresql://mydatabase.cwro6zzonnpg.eu-west-3.rds.amazonaws.com:5432/boutique";
	private static final String user = "dbAdmin";
	private static final String pwd = "admin8char";
	private static Connection con = null;
	
	protected DbConnector() {
		try {
			DbConnector.con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getDbConnector() {
		if(DbConnector.con == null) {
			new DbConnector();
		}
		return DbConnector.con;
	}
}
