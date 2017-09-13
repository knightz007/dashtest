package com.anup.webapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anup.dbutils.*;

public class CheckComponent {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DbConnector.getConnection();
		System.out.println("Connection Successful");
		PreparedStatement pstm = null;
		ResultSet rs_env = null;
		ResultSet rs_comp = null;
		ResultSet rs_serverlist = null;
		
		String envSql = "SELECT environment FROM testdb.environmentlist";
		String compSql = "SELECT component FROM testdb.componentlist";
		
		 pstm = conn.prepareStatement(envSql);
		 rs_env = pstm.executeQuery();
		 
		 pstm = conn.prepareStatement(compSql);
		 rs_comp = pstm.executeQuery();
		 
		 String serverlist_sql = "";
	     
	     while(rs_env.next()) {	    	 
	    	 System.out.println(rs_env.getString("environment"));
	    	 while(rs_comp.next())
	    	 {	    
	    		 System.out.println(rs_comp.getString("component"));
	    		 serverlist_sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='" + rs_env.getString("environment") + 
	    			      "' and components_version like '%" + rs_comp.getString("component") + "%'";
	    		 pstm = conn.prepareStatement(serverlist_sql);
	    		 rs_serverlist = pstm.executeQuery();
	    		 
			      while(rs_serverlist.next()) {
			    	  System.out.println(rs_serverlist.getString("components_version"));			    	  
			      }
	    		 
	    	 }
	     }	     
		
		//String sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='qa' and components_version like '%astra%'";

	}

}
