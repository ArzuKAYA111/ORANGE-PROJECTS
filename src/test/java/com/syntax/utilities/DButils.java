package com.syntax.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DButils {
	
private static Connection conn;
private static Statement st;
private ResultSet rs;
private List <Map<String ,String>> listData;
	
	
public static void getConnection() {
	
	try {
		conn=DriverManager.getConnection(ConfigsReader.getProperty("dabUrl"),
				ConfigsReader.getProperty("dbUsername"),
				ConfigsReader.getProperty("dbPassword"));
	} catch (SQLException e) {

		e.printStackTrace();
	}
	
}
	
	
	
	
	
	
	
}
