package com.anup.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
			
			String env = queryDB.findServer(conn, "myserver1");
			out.println(env);
			
			List<ServerInfo> serverList = queryDB.getServerList();
			
			for (ServerInfo server : serverList) {
				out.println(server.getHostname() + "::" + server.getEnvironment());
			}
			
			request.setAttribute("serverList", serverList);			
			request.setAttribute("htmlTagData", "<br/> creates a new line.");
			request.setAttribute("url", "http://www.testjstl.com");		
			
			
			String serverJson = getJson.getServerListJSON();
			response.getWriter().write(serverJson);
			//System.out.println(serverJson);
			 // convert your list to json
			 //String jsonServerList = gson.toJson(serverList);
			 // print your generated json
			 //System.out.println("jsonCartList: " + jsonServerList);
			
			//Gson gson = new GsonBuilder().create();
	        //gson.toJson("Hello", System.out);
	        //gson.toJson(123, System.out);
			
			//String json = new Gson().toJson(serverList);
			//out.println(json);
			//RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/ServerListView.jsp");
		   // dispatcher.forward(request, response);	    
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
