package com.practice.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnectivity {
	private Properties properties;
	private String fileLocation = "src/test/java/resources/config.properties";
	private Connection connection;
	private Statement statement;
	
	public DBConnectivity() {
		super();
		try {
			FileInputStream inputStream = new FileInputStream(fileLocation);
			properties = new Properties();
			properties.load(inputStream);
			this.initiateDBConnection();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initiateDBConnection(){
		String host = properties.getProperty("server");
		String port = properties.getProperty("port");
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		String database = properties.getProperty("database");
		String driverClass = properties.getProperty("driverClass");
		
		try {
			Class.forName(driverClass);
			String connectionString = "jdbc:mysql://"+host+":"+port+
					"/"+database;
			connection = DriverManager.getConnection(connectionString, userName, password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[][] getSearchResults(String sql){
		String results[][] = null;
		try {
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.last();
			int rowCount = resultSet.getRow();
			//resultSet.beforeFirst();
			int colCount = resultSet.getMetaData().getColumnCount();
			resultSet.first();
			results = new String[rowCount][colCount];
			for (int i=0; i<rowCount; i++){
				for (int j=1; j<=colCount; j++){
					Object obj = resultSet.getObject(j);
					if (obj == null){
						results[i][j-1]="";
					}
					else{
						results[i][j-1] = resultSet.getString(j);
					}
				}
				resultSet.next();
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;		
	}
	
	public void executeUpdate(String sql){
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
