package com.syntax.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

	public static Properties prop;  // properties class extends Hashtable<Object,Object> has getProperties methods etc.
	
	
	/**
	 * This method reads properties file 
	 * @param filePath
	 */
	public static void readProperties(String filePath) {// create a method to read properties file
		                                                // Write logic part to read properties file and add try catch blocks for exceptions	
	
	
	try {
		FileInputStream fis= new FileInputStream(filePath);
		
	prop=new Properties();// object of the properties class 
	
	prop.load(fis);// brings the properties 
	//  fis.close();
	} catch (FileNotFoundException e) {
	
		e.printStackTrace();
	} catch (IOException e) {
	
		e.printStackTrace();
	}
}
	// Create a method to get values 
	/**
	 *  this methods returns value of the specified key from configs file 
	 *  this method retrieves properties prom the property file
	 * @param key
	 * @return String value
	 */
	public static String getProperty(String key) {
		
		return prop.getProperty(key);
		
	}
	
	
	
	
	
}
