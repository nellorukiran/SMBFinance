package com.smb.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
		private DBConnection(){
		}
		/*public static Connection getDBConnection(){
			Connection con = null;
			if(con == null){
				try{
					String url = "jdbc:mysql://localhost:3306/mysql";
					//String url = "us-cdbr-iron-east-04.cleardb.net:3306/ad_aeca8e9e7e78f64";
					Class.forName("com.mysql.jdbc.Driver"); 
					con=DriverManager.getConnection(url,"root","root"); 
					//con=DriverManager.getConnection(url,"b80197998e7f7d","c986e885"); 
				}catch (Exception e) {System.out.println(e);}
			}
			return con;
			
		}
		/*
		public static Connection getDBConnection(){
			Connection con = null;
			try {
				System.out.println("DBConnectvity getConnection()");
		         Class.forName("org.hsqldb.jdbc.JDBCDriver");
		         con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/SMB_DEV", "SA", "");
		         
		         
			 } catch (Exception e) {
		         System.out.println("Exception -->"+e);
		      }
			return con;
		}
		
		*/
		public static Connection getDBConnection(){
			Connection con = null;
			String dbName = "u530425252_tL6ss";
			  String userName = "u530425252_63xSN";
			  String password = "T/knqS0z";
			  
			  //Updated on 02-Mar-2020
			  //String hostname = "smbfinance1.cxfrg4mycpoz.ap-south-1.rds.amazonaws.com";
			  //Updated on 23-Dec-2020
			  //String hostname = "smbfinance.cvdm9quflan9.ap-south-1.rds.amazonaws.com";
			 //Updated on 09-Dec-2021
			 // String hostname = "smbfinance.cggz4shjrmjy.ap-south-1.rds.amazonaws.com";
			  //Update on 28-Feb-2023
			  String hostname = "sql356.main-hosting.eu";
			  String port = "3306";
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
			    port + "/" + dbName + "?user=" + userName + "&password=" + password;
			   try {
				    System.out.println("Loading driver...");
				    Class.forName("com.mysql.cj.jdbc.Driver");
				    System.out.println("Driver loaded!");
				    con = DriverManager.getConnection(jdbcUrl);
				  } catch (ClassNotFoundException e) {
				    throw new RuntimeException("Cannot find the driver in the classpath!", e);
				 }catch (SQLException ex) {
					 	System.out.println("SQLException: " + ex.getMessage());
					    System.out.println("SQLState: " + ex.getSQLState());
					    System.out.println("VendorError: " + ex.getErrorCode());
				}catch (Exception e) {
					System.out.println("Exception: " + e);
				}
			 return con;
		}
	
}
