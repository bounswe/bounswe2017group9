package com.boun.nine.service.interfaces;
import java.sql.*;
public abstract class ConnectedService {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/api_project";
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	public ResultSet executeQuery(String query) throws SQLException{
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			return rs;
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	public int executeUpdate(String query) throws SQLException{
		int result;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			stmt = conn.createStatement();
			 result = stmt.executeUpdate(query);
			return result;
		}catch(SQLException ex){
			ex.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}
	public static void closeConnections(){
		try{
			if(conn != null){
				conn.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(rs != null){
				rs.close();
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
