package com.boun.nine.service.interfaces;
import java.sql.*;
/**
 * Resource classes are inherited from this abstract class to provide themselves SQL connection capability.
 * All the SQL related operations are managed in this class.
 * @author ffguven
 * @version 1.0
 * @since 15-05-2017
 */
public abstract class ConnectedService {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/api_project";
	
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static Connection conn;
	private static Statement stmt;
	private static ResultSet rs;
	/**
	 * Stands for managing {@code SELECT} jobs on database. SQL query should be prepared well before sending to this function.
	 * 
	 * @param query An ordinary working SQL query 
	 * @return ResultSet The response that is obtained from SQL database.
	 * @throws SQLException
	 */
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
	/**
	 * Stands for managing {@code INSERT} jobs on database or the jobs that is change the state of database
	 * @param query An ordinary working SQL query
	 * @return int If this is equal to a value other than 0, then everything is OK, else there are some problems on query.
	 * @throws SQLException
	 */
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
	/**
	 * Can be used to close SQL database connections.
	 */
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
