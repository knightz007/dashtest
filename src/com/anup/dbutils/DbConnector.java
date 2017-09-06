package com.anup.dbutils;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnector {
	
	 public static Connection getConnection()
             throws ClassNotFoundException, SQLException {
      
       // Here I using Oracle Database.
       //return OracleConnUtils.getOracleConnection();
       // Using MySql 
       return MySqlConnector.getMySQLConnection();
       // return SQLServerConnUtils_JTDS.getSQLServerConnection_JTDS();
       // return SQLServerConnUtils_SQLJDBC.getSQLServerConnection_SQLJDBC();
   }
	 
	 public static void closeQuietly(Connection conn) {
	       try {
	           conn.close();
	       } catch (Exception e) {
	       }
	   }
	 
	   public static void rollbackQuietly(Connection conn) {
	       try {
	           conn.rollback();
	       } catch (Exception e) {
	       }
	   }	 
}
