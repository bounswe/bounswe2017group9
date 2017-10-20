package boun.group9.webservice.helper;
import java.sql.*;

import boun.group9.webservice.app.Application;
import boun.group9.webservice.exception.NotSavedException;
public class Database {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://concerter-db.csosuob9ic2u.eu-central-1.rds.amazonaws.com:3306/concerter_db";
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static int updateResult;
	public static ResultSet connect(String query,int mode) throws SQLException,NotSavedException{
		try {
			//Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,Application.username,Application.password);
			stmt = conn.createStatement();
			switch(mode) {
			case Application.MODE_GET:
				rs = stmt.executeQuery(query);
				break;
			case Application.MODE_UPDATE:
				updateResult = stmt.executeUpdate(query);
				if(updateResult == 0) {
					throw new NotSavedException();
				}
				break;
			}
			
			
		}catch(SQLException ex) {
			System.out.println("SQL Exception occured.");
			ex.printStackTrace();
			throw new SQLException();
		}
		return rs;
	}
	public static void closeConnection(){
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}
