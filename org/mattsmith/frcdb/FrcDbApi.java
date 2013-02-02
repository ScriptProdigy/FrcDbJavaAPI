package org.mattsmith.frcdb;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.*;

public class FrcDbApi 
{
	private static Event[] CachedEvents_ = null;
	private static Team[] CachedTeams_ = null;
	
	public FrcDbApi()
	{
		buildCache();
	}
	
	public FrcDbApi(Boolean load_cache) 
	{ 
		if(load_cache)
			buildCache();
	}
	
	public static void buildCache()
	{
		try
		{
			String events = getHtml(FrcDbConstants.UrlBase + FrcDbConstants.UrlEvents);
			JSONArray Events = new JSONArray(events);
			CachedEvents_ = new Event[Events.length()];
			
			for (int i = 0; i < Events.length(); i++) 
			{
				final JSONObject EventObject = Events.getJSONObject(i);
				
				Event e = new Event();
				e.setName(EventObject.getString("name"));
				e.setShortName(EventObject.getString("shortName"));
				e.setVenue(EventObject.getString("venue"));
				e.setIdentifier(EventObject.getString("identifier"));
				e.setCity(EventObject.getString("city"));
				e.setState(EventObject.getString("state"));
				e.setCountry(EventObject.getString("country"));
				
				int[] years = new int[EventObject.getJSONArray("years").length()];
				for(int x = 0; x < EventObject.getJSONArray("years").length(); x++)
					years[x] = EventObject.getJSONArray("years").getInt(x);
				e.setYears(years);				
				
				CachedEvents_[i] = e;
			}
		}
		catch (Exception e) { e.printStackTrace(); }
		
		/* try
		{
			String teams = getHtml(FrcDbConstants.UrlBase + FrcDbConstants.UrlTeams);
			JSONArray Teams = new JSONArray(teams);
			CachedTeams_ = new Team[Teams.length()];
			
			for (int i = 0; i < Teams.length(); i++) 
			{
				final JSONObject TeamObject = Teams.getJSONObject(i);
				
				Team e = new Team();
				e.setName(TeamObject.getString("name"));
				e.setNumber(TeamObject.getInt("number"));
				e.setNickName(TeamObject.getString("nickname"));
				e.setRookieSeason(TeamObject.getInt("rookieSeason"));
				e.setCity(TeamObject.getString("city"));
				e.setState(TeamObject.getString("state"));
				e.setCountry(TeamObject.getString("country"));
				e.setWebSite(TeamObject.getString("website"));
				e.setMotto(TeamObject.getString("motto"));
				
				
				CachedTeams_[i] = e;
			}
		}
		catch (Exception e) { e.printStackTrace(); }*/
	}
	
	private static String getHtml(String urlToRead) 
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
	
	public static Event[] getEvents() 
	{ 
		if(CachedEvents_ == null)
			buildCache();
		
		return CachedEvents_; 
	}
	
	public static Team[] getTeams() 
	{ 
		if(CachedTeams_ == null)
			buildCache();
		
		return CachedTeams_; 
	}
	
	
}
