package com.smb.common;

import java.sql.Connection;

public class TestDBConnection {

	public static void main(String[] args) {
		
		
		Connection dbConnection = DBConnection.getDBConnection();
		System.out.println(dbConnection);
		

	}

}
