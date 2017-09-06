package com.anup.webapp;



import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ListToJson {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//        Gson gson = new GsonBuilder().create();
//        gson.toJson("Hello", System.out);
//        gson.toJson(123, System.out);
     
        List<ServerInfo> serverList = queryDB.getServerList();
        String json = new Gson().toJson(serverList);
		System.out.println(json);
		
		
		JsonObject myobj = new JsonObject();  
		
		//System.out.println(responseDetailsJson.toString().replaceAll("\\\\", ""));
		
		
		//JsonElement jelement = new JsonElement();
		
		//responseDetailsJson.add("data", json);
		JsonArray jsonArray = new JsonArray();
		//jsonArray.add(json);
		
		
		
		//responseDetailsJson.addProperty("data", jsonArray.toString().replaceAll("\\\\", ""));
		
		
	 //System.out.println(responseDetailsJson);
	 //String jsonFormattedString = responseDetailsJson.toString().replaceAll("\\\\", "");
	 //String jsonFormattedString = responseDetailsJson.toString().replaceAll("\\\\", "");
	 //System.out.println(jsonFormattedString);

	}

}


