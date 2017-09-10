package com.anup.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.anup.dbutils.*;
import com.anup.webapp.*;

import com.google.gson.*;
import com.google.gson.GsonBuilder;

public class HelloServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Welcome to servlets</h1>");
		out.println("</body>");
		out.println("</html>");
		
		try {
			Connection conn = DbConnector.getConnection();
			out.println("Connection Successful");
			
//			String env = queryDB.findServer(conn, "myserver1");
//			out.println(env);
//			
//			List<ServerInfo> serverList = queryDB.getServerList();
//			
//			for (ServerInfo server : serverList) {
//				out.println(server.getHostname() + "::" + server.getEnvironment());
//			}
//			
//			out.println("hello");
			
			/*request.setAttribute("serverList", serverList);			
			request.setAttribute("htmlTagData", "<br/> creates a new line.");
			request.setAttribute("url", "http://www.testjstl.com");		
			*/
			
			String sql = "SELECT hostname, components_version FROM testdb.serverlist where environment='stage' and components_version like '%astra%'";
			
			   PreparedStatement pstm = conn.prepareStatement(sql);
			      ResultSet rs = pstm.executeQuery();
			      
			      StringBuilder componentsVersionStr = new StringBuilder();
			      
			      while(rs.next()) {
			    	  componentsVersionStr.append(rs.getString("components_version"));
			    	  componentsVersionStr.append(" ");
			      }
			      
			      //out.print("String is:" + componentsVersionStr.toString());			      
			      
			      
			      String[] users = componentsVersionStr.toString().split(" ");
			      List<String> componentVersionList = new LinkedList<String>();
			      List<String> components = new LinkedList<String>();
			      //StringBuilder components = new StringBuilder();
			      String[] comp;
			      for (int i = 0; i < users.length; i++)
			      {
			    	 componentVersionList.add(users[i].toString());
			    	 comp = users[i].toString().split(":");
			    	 components.add(comp[0].toString());		    	 
			      }
			      
			      out.println("Number of non unique components:" + components.size());
			      
			      Set<String> uniqueComponents = new HashSet<String>(components);
			      out.println("Number of unique components:" + uniqueComponents.size());
			      
			      Set<String> uniqueComponentVersion = new HashSet<String>(componentVersionList);
			      out.println("Number of unique component versions:" + uniqueComponentVersion.size());
			      
			      
//			      Set<String> uniqueComponents = new HashSet<String>();
//
//			        for (int i = 0; i < users.length; i++) {
//			            if (!uniqueComponents.add(users[i]))
//			            	
//			        }
			      
			      
			      //String[] users = "User1,User2,User1,User,User".split(",");

			        Set<String> uniquUsers = new HashSet<String>();

			        for (int i = 0; i < users.length; i++) {
			            if (!uniquUsers.add(users[i]))
			                users[i] = "Duplicate"; // here I am assigning Duplicate instead if find duplicate
			                                        // you can assign as null or whatever you want to do with duplicates.
			        }
			        out.println(Arrays.toString(users));
			      
			      			      
			      /*for(String s: componentVersions)
			      {
			    	 
			      }
			      
			      String componentVersion = componentVersions[0]; */
			      //out.println(componentVersion);
			      
			      
			      
			     //Check Component for version sync
/*			      List<String> comp_version_list =   new LinkedList<String>(); ;
			      while(rs.next()) {
			    	 comp_version_list.add(rs.getString("components_version"));
			      }	*/		      
			      
			      //out.println("Size is:" + comp_version_list.size());
			
			
		} catch (ClassNotFoundException e) {
			out.println(e.getMessage());
			//e.printStackTrace();
		} catch (SQLException e) {
			out.println(e.getMessage());			
			//e.printStackTrace();
		}

	}

}
