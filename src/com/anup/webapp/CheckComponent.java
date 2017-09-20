package com.anup.webapp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
		 
		 //pstm.close();
		 
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
		 
		 String[] users;
		 List<String> componentVersionList = new LinkedList<String>();;
		 List<String> components =  new LinkedList<String>();
		 Set<String> uniqueComponents = null;
		 Set<String> uniqueComponentVersion  = null;
		 String updateSQL = "";
		 String componentlistUpdateSQL = "";
		 		 
	     for(int i=0; i<env_list.size();i++)
	     {	    	 
	    	System.out.println("Environment:" + env_list.get(i).toString());
	    	//int uniqueCount = 0;
	    	int envOutOfSync = 0;
	    	 for (int j=0; j< comp_list.size();j++)
	    	 {	    
	    		 System.out.println("Component:" + comp_list.get(j).toString());
	    		 
	    		 serverlist_sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='" + env_list.get(i).toString() + 
	    			      "' and components_version like '%" + comp_list.get(j).toString() + "%'";
	    		 pstm = conn.prepareStatement(serverlist_sql);
	    		 rs_serverlist = pstm.executeQuery();
	    		 
	    		 //System.out.println("Servers:Versions");
			      while(rs_serverlist.next()) {
			    	  //System.out.println(rs_serverlist.getString("hostname") + " : " + rs_serverlist.getString("components_version"));  		    	  
			    	  users = rs_serverlist.getString("components_version").toString().split(" ");
			    	  //System.out.println("Users length:" + users.length);
				      
				      String[] comp;
				      for (int k = 0; k < users.length; k++)
				      {
				    	  //System.out.println("comp name:" + comp_list.get(j).toString());
				    	 if(users[k].toString().toLowerCase().contains(comp_list.get(j).toString()))
				    	 {
				    		 //System.out.println("Match");
					    	 componentVersionList.add(users[k].toString());			    	 
					    	 
					    	 comp = users[k].toString().split(":");	
					    	 //System.out.println("component from split:" + comp[0].toString().toLowerCase());
					    	 //System.out.println("component from list:" + comp_list.get(j).toString());
					    	 //System.out.println("Number of non unique components:" + components.size());
					    	 if(comp[0].toString().toLowerCase().equals(comp_list.get(j).toString().toLowerCase()))
					    	 {
					    		 //System.out.println("Component Added:" + comp[0].toString());
					    		 components.add(comp[0].toString());						        
					   	      	 					      
					    	 }	
					    	 uniqueComponents = new HashSet<String>(components);	
					    	 uniqueComponentVersion = new HashSet<String>(componentVersionList);
						      
				    	 }			    	 
				    	 
				    	 
				      }
				     
				      //uniqueComponentVersion.clear();
			      }
			         //uniqueComponents.clear();

			    	 System.out.println("Number of unique component versions:" + uniqueComponentVersion.size());	
				     System.out.println("Number of unique components:" + uniqueComponents.size());  
				     
				     if ( uniqueComponentVersion.size() != uniqueComponents.size())
				     {	
				    	 envOutOfSync = envOutOfSync + 1;
				    	 componentlistUpdateSQL = "UPDATE testdb.componentlist SET " + env_list.get(i).toString() + "ComponentsInSync='No' WHERE component='" 
				    		+ comp_list.get(j).toString() + "'";
				    	 //updateSQL = "UPDATE testdb.environmentlist SET IsEnvInSync='No' WHERE environment='" + env_list.get(i).toString() + "'"; 

						//System.out.println(env_list.get(i).toString() + " env NOT in sync. Table will be updated!!");				    	 
				     }
				     else
				     {
				    	 componentlistUpdateSQL = "UPDATE testdb.componentlist SET " + env_list.get(i).toString() + "ComponentsInSync='Yes' WHERE component='" 
				    		+ comp_list.get(j).toString() + "'";
				    	 //updateSQL = "UPDATE testdb.environmentlist SET IsEnvInSync='Yes' WHERE environment='" + env_list.get(i).toString() + "'";
				    	// System.out.println(env_list.get(i).toString() + " env in sync. Table will be updated!!");
				     }
				     
							     
				     componentVersionList.clear();
			    	 components.clear();
			    	  uniqueComponents.clear();
			    	  uniqueComponentVersion.clear();
			    	  
			    	  pstm = conn.prepareStatement(componentlistUpdateSQL);
					  pstm.executeUpdate();
					  pstm.close();
			    	  
	    		 
	    	 }
	    	 
	    	 //Update the environmentlist table
		     if ( envOutOfSync != 0)
		     {
		    	 updateSQL = "UPDATE testdb.environmentlist SET IsEnvInSync='No' WHERE environment='" + env_list.get(i).toString() + "'"; 
				 System.out.println(env_list.get(i).toString() + " env NOT in sync. Table will be updated!!");				    	 
		     }
		     else
		     {
		    	 updateSQL = "UPDATE testdb.environmentlist SET IsEnvInSync='Yes' WHERE environment='" + env_list.get(i).toString() + "'";
		    	 System.out.println(env_list.get(i).toString() + " env in sync. Table will be updated!!");
		     }
		     
		     pstm = conn.prepareStatement(updateSQL);
			 pstm.executeUpdate();
			 pstm.close();
	    	 
		  
	     }
	     
	     
	     
	  /*   System.out.println("Number of non unique components:" + components.size());
	     
	      Set<String> uniqueComponents = new HashSet<String>(components);
	      System.out.println("Number of unique components:" + uniqueComponents.size());
	      
	      Set<String> uniqueComponentVersion = new HashSet<String>(componentVersionList);
	      System.out.println("Number of unique component versions:" + uniqueComponentVersion);*/
	      
	      /*Iterator<String> itr = uniqueComponentVersion.iterator();

	      
	      while(itr.hasNext()){
	    	  System.out.println(itr.next());
	    	}*/
		
		//String sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='qa' and components_version like '%astra%'";

	}

}
