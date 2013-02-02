package org.mattsmith.frcdb;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class FrcDbConstants {
	public static String UrlBase = "http://api.frcdb.net/";
	
	public static String UrlEvents = "events.json";
	public static String UrlTeams = "teams.json";
	
	public static String UrlTeam = "team/%s/json";
	public static String UrlGame = "event/%s/json";
	public static String UrlGameWithYear = "event/%s/%s/json";
	
	public static String getHtml(String urlToRead) 
	{
		try 
		{
            HttpClient client = new DefaultHttpClient();  
            HttpGet get = new HttpGet(urlToRead);
            HttpResponse responseGet = client.execute(get);  
            HttpEntity resEntityGet = responseGet.getEntity();  
            
            if (resEntityGet != null)  
            	return EntityUtils.toString(resEntityGet);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
}
