package com.Veri.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
	
	static Connection con;
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return con;
	}

}
