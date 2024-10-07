package com.esed.easybridge.core.utility;

import java.sql.Connection;
import java.util.Properties;

import com.esed.easybridge.core.dao.EyBridgeDao;
import com.seda.data.dao.DAOHelper;
import com.seda.data.dao.DAOSysException;

public class EyBridgeUtility {

	private static Connection getConnection(String driver, String url,  String username, String password, String autocommit)
 	{
 		Properties dsProperties= new Properties();
 		dsProperties.put(DAOHelper.JDBC_DRIVER, driver.trim());
 		dsProperties.put(DAOHelper.JDBC_URL, url.trim());
 		dsProperties.put(DAOHelper.JDBC_USER,username.trim());
 		dsProperties.put(DAOHelper.JDBC_PASSWORD, password.trim());
 		if(autocommit.equalsIgnoreCase("false")) {
 			dsProperties.put("autocommit", false);
 		}

 		Connection connection = null;

 		try {
 			connection = DAOHelper.getConnection(dsProperties);
 		} catch (DAOSysException e) {
 			e.printStackTrace();
 			System.out.println("Cannot open connection" + e.toString());
 		} 

 		return connection;
 	}

	//inizio LP PG21XX04 Leak
	//Nota. La close della connection è affidata al chiamante 
	//fine LP PG21XX04 Leak
	public static EyBridgeDao getDao(String schema, String driver, String url,  String username, String password, String autocommit) throws Exception
	{
		String messOut = "";
		EyBridgeDao out = null;
		Connection connection = null; 

		try {
			connection = getConnection(driver, url, username, password, autocommit);
		}
		catch (Exception e){
			messOut ="Connessione al DataBase non riuscita: (" + e.getMessage() + ")";
			System.out.println(messOut);
			throw new Exception(messOut);
		}
		out = new EyBridgeDao(connection, schema);
		if(!out.testConnection()) {
			throw new Exception("Test di prova utilizzo del DataBase fallito.");
		}
		return out;
	}

}
