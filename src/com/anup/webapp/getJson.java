package com.anup.webapp;


import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class getJson {
	
	
	public static String getServerListJSON() throws SQLException {
		 
//        Gson gson = new GsonBuilder().create();
//        gson.toJson("Hello", System.out);
//        gson.toJson(123, System.out);
     
        List<ServerInfo> serverList = queryDB.getServerList();
         String json = new Gson().toJson(serverList);
        
		System.out.println(json);
	    return json;
	  }

}
