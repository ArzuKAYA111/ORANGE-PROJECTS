package com.syntax.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DButils {

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static List<Map<String, String>> listData;

	/**
	 * this method will eatablish a connection with Db/DataBase
	 */
	
	//1 Create connection
	public static void getConnection() {

		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dabUrl"),
					ConfigsReader.getProperty("dbUsername"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	
	/**
	 * This method will execute the query and store the data inside the list of maps 
	 * by the given sql query
	 * 
	 * @param sqlQuery
	 * @retur   <....List<Map<String,String>> listData  / retuns us data list
	 */
	public static List<Map<String, String>> storeDataFromDB(String sqlQuery) {
		//1 Create connection
		getConnection();// called connection method only with method name because method is a static
						// method
		try {
			//2-Create Statement
			st = conn.createStatement();
			// Execute Query
			rs = st.executeQuery(sqlQuery);
		// 4- Proccess result
			ResultSetMetaData rsMetaData = rs.getMetaData();//Gets data from DB
			listData = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> MapData = new LinkedHashMap<>();
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {//lopps all DB Datas
					MapData.put(rsMetaData.getColumnName(i), rs.getObject(i).toString());//Stores all datas into the Map
				}
				listData.add(MapData);//adds Map into the List
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listData;
	}

//5- Close Connection
	/**
	 * Second way this method will close the connection with Db/DataBase
	 */
	public static void closeCon() {
		if (rs != null || st != null || conn != null) {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}
	
	/**
	 * this method will close the connection with Db/DataBase
	 */
	public static void closeConnection() {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
