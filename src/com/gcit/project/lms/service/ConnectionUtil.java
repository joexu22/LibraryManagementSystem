package com.gcit.project.lms.service;

/*
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "xujoe123";
	public static String url = "jdbc:mysql://localhost/library?serverTimezone=UTC";
	
	public static void main(String[] args) {
		try {
			Class.forName(driver).newInstance();
			Connection conn = DriverManager.getConnection(url, user, password);
			//Statement stmt = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("Select * from tbl_author where authorName = ?");
			String sql = "SELECT * FROM tbl_author";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("Author ID: " + rs.getInt("authorId"));
				System.out.println("Author Name: " + rs.getString("authorName"));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String user = "root";
	public static String password = "xujoe123";
	public static String url = "jdbc:mysql://localhost/library?serverTimezone=UTC";
	
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url, user, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}