package com.anir.hbm.mapping.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl1 = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?allowPublicKeyRetrieval=true&useSSL=false";
		String jdbcUrl2 = "jdbc:mysql://localhost:3306/hb-02-one-to-many?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "hbstudent";
		String password = "hbstudent";
		
		try {
			System.out.println("Connecting to DB for one-to-one : " + jdbcUrl1);
			Connection conn1 = DriverManager.getConnection(jdbcUrl1, user, password);
			
			System.out.println("connection to DB successfull ");
			
			System.out.println("Connecting to DB for one-to-many: " + jdbcUrl2);
			Connection conn2 = DriverManager.getConnection(jdbcUrl2, user, password);
			
			System.out.println("connection to DB successfull");
		}catch(Exception exception) {
			exception.printStackTrace();
		}

	}

}
