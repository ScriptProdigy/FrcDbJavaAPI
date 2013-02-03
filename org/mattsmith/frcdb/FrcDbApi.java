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
		
		try
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
		catch (Exception e) { e.printStackTrace(); }
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
		return getEvents(FrcDbConstants.DefaultYear);
	}
	
	public static Event[] getEvents(int year) 
	{ 
		if(CachedEvents_ == null)
			buildCache();
		
		int Count = 0;
		for(Event e : CachedEvents_)
		{
			for(int y : e.getYears())
			{
				if(y == year)
				{
					Count += 1;
					break;
				}
			}
		}
		

		Event[] retEvent = new Event[Count];
		Count = 0;
		for(Event e : CachedEvents_)
		{
			for(int y : e.getYears())
			{
				if(y == year)
				{
					Count += 1;
					retEvent[Count] = e;
					break;
				}
			}
		}
		
		return CachedEvents_; 
	}
	
	public static Team[] getTeams() 
	{ 
		if(CachedTeams_ == null)
			buildCache();
		
		return CachedTeams_; 
	}
	
	public static Team[] getTeamsAtGame(Game g)
	{
		return getTeamsAtEvent(getEventByShortname(g.getEventShortName()), g.getGameYear());
	}
	
	public static Team[] getTeamsAtEvent(Event e, int year)
	{
		Team[] ret = new Team[e.getGame(year).getTeamStandings().length];
		
		int x = 0;
		for(TeamStanding ts : e.getGame(year).getTeamStandings())
		{
			ret[x] = getTeamFromTeamStanding(ts);
			x += 1;
		}
		
		return ret;
	}
	
	public static Event getEventByShortname(String shortname)
	{
		for(Event e : CachedEvents_)
		{
			if(e.getShortName().contains(shortname))
				return e;
		}
		return null;
	}
	
	public static Team getTeamFromTeamStanding(TeamStanding ts)
	{
		for(Team t : CachedTeams_)
		{
			if(t.getNumber() == ts.getNumber())
				return t;
		}
		return null;
	}
	
	
	public static Double getVersion()
	{
		return FrcDbConstants.Version;
	}
}
