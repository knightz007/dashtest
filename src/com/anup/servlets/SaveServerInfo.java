package com.anup.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import com.anup.dbutils.DbConnector;
import com.anup.webapp.*;

/**
 * Servlet implementation class SaveServerInfo
 */
@WebServlet(description = "Saves the server info entered by user", urlPatterns = { "/SaveServerInfo" })
public class SaveServerInfo extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public SaveServerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		
		try 
		{			
		String hostName = request.getParameter("hostName").toString();
		String environment = request.getParameter("environment").toString();
		
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setHostname(hostName);
		serverInfo.setEnvironment(environment);
		
		List<ServerInfo> serverInfoList = new ArrayList<ServerInfo>();
		serverInfoList.add(serverInfo);
		
			
			Connection conn = DbConnector.getConnection();		
			queryDB.InsertServerInfo(conn, serverInfoList);
			System.out.println(hostName  + " was added to database");		
		
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/hello");
		    dispatcher.forward(request, response);	    
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	
		
	}

}
