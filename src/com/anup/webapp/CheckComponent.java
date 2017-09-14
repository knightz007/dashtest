package com.anup.webapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		 
		 List<String> env_list = new ArrayList<String>();
		 List<String> comp_list = new ArrayList<String>();
		 List<String> server_list = new ArrayList<String>();
		 
		 while(rs_env.next()) {	   
			 env_list.add(rs_env.getString("environment"));
		 }
		 while(rs_comp.next())
		 {
			 comp_list.add(rs_comp.getString("component"));
		 }
		 
		 
		 
		 
		 
	     for(int i=0; i<env_list.size();i++)
	     {	    	 
	    	System.out.println("Environment:" + env_list.get(i).toString());
	    	 for (int j=0; j< comp_list.size();j++)
	    	 {	    
	    		 System.out.println("Component:" + comp_list.get(j).toString());
	    		 serverlist_sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='" + env_list.get(i).toString() + 
	    			      "' and components_version like '%" + comp_list.get(j).toString() + "%'";
	    		 pstm = conn.prepareStatement(serverlist_sql);
	    		 rs_serverlist = pstm.executeQuery();
	    		 
	    		 System.out.println("Servers:Versions");
			      while(rs_serverlist.next()) {
			    	  System.out.println(rs_serverlist.getString("hostname") + " : " + rs_serverlist.getString("components_version"));			    	  
			      }
	    		 
	    	 }
	     }	     
		
		//String sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='qa' and components_version like '%astra%'";

	}

}
