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
import javax.servlet.annotation.WebServlet;

import com.anup.dbutils.DbConnector;
import com.anup.webapp.ComponentInfo;
import com.anup.webapp.ReleaseArtifactInfo;
import com.anup.webapp.ServerInfo;
import com.anup.webapp.queryDB;
import com.anup.webapp.releaseInfo;

/**
 * Servlet implementation class ArtifactVersion
 */
@WebServlet(description = "Displays artifact version for each environment by release", urlPatterns = { "/ArtifactVersion" })
public class ArtifactVersion extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public ArtifactVersion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			
			PrintWriter out = response.getWriter();
			
			List<ReleaseArtifactInfo> releaseartifactList = queryDB.getReleaseArtifactInfoList();
			
			for (ReleaseArtifactInfo artifact : releaseartifactList) {
				out.println(artifact.getRelease() + "::" + artifact.getComponent() + "::" + artifact.getCertifiedArtifactVersion());						
			}
			
			//request.setAttribute("releaseartifactList", releaseartifactList);		

			//Check if component is in sync
			List<ComponentInfo> componentInfoList = queryDB.getComponentSyncInfo();
			request.setAttribute("componentInfoList", componentInfoList);
			
			List<releaseInfo> releaseInfoList = queryDB.getReleaseInfo();
			String currentRelease = "";
			for (releaseInfo rinfo : releaseInfoList) {
				if (rinfo.getIsCurrentRelease().equals("Yes"))
				{
					currentRelease = rinfo.getReleaseNumber().toString();
				}
			}
			
			request.setAttribute("currentRelease", currentRelease);
		    //request.setAttribute("releaseList", releaseInfoList);
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ReleaseArtifactView.jsp");
		    dispatcher.forward(request, response);	    
			
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
