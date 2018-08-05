package com.anir.hbm.mapping.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-02-one-to-many?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to DB: " + jdbcUrl);
			Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("connection to DB successfull");
		}catch(Exception exception) {
			exception.printStackTrace();
		}

	}

}
