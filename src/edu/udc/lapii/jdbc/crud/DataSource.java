package edu.udc.lapii.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DataSource {
	
	private static final Logger LOGGER = Logger.getLogger(DataSource.class);
	static{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.error( e,e) ;
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/ventas", "postgres", "postgres");
		} catch (SQLException e) {
			LOGGER.error(e, e);
			return null;
		}
		
	}
	
	public static void closeConnection(Connection cnn){
		try {
			cnn.close();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
}
