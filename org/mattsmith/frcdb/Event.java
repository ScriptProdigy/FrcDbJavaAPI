package org.mattsmith.frcdb;

import org.json.JSONObject;

public class Event {
	private String Name_;
	private String ShortName_;
	private String Venue_;
	private String City_;
	private String State_;
	private String Country_;
	private String Identifier_;
	private int[] Years_;
	private Game[] Games_;

	public Event() {}
	
	
	public Game getGame(int year) 
	{ 
		if(Games_ == null)
		{
			try
			{
				String html = FrcDbConstants.getHtml(FrcDbConstants.UrlBase + String.format(FrcDbConstants.UrlGameWithYear, ShortName_, year));
				JSONObject game = new JSONObject(html);
				
				Game g = new Game();
				g.setGameYear(game.getInt("gameYear"));
				g.setGameName(game.getString("gameName"));
				g.setEventShortName(game.getString("eventShortName"));
				g.setStartDate(game.getInt("startDate"));
				g.setEndDate(game.getInt("endDate"));
				g.loadCache();
				
				return g;
			} catch(Exception ex) { ex.printStackTrace(); }
		} else {
			for(Game g : Games_)
			{
				if(g.getGameYear() == year)
					return g;
			}
		}
		return null;
		
	}	
	
	public String getName() { return Name_; }
	public String getShortName() { return ShortName_; }
	public String getVenue() { return Venue_; }
	public String getCity() { return City_; }
	public String getState() { return State_; }
	public String getCountry() { return Country_; }
	public String getIdentifier() { return Identifier_; }
	public int[] getYears() { return Years_; }
	public Game[] getGames() 
	{ 
		if(Games_ == null)
		{
			Game[] games = new Game[Years_.length];
			for(int x = 0; x < Years_.length; x++)
				games[x] = getGame(Years_[x]);
			Games_ = games;
		}
		
		return Games_; 
	}

	public void setName(String Name) { 
		Name_ = Name;
	}
	public void setShortName(String ShortName) { 
		ShortName_ = ShortName;
	}
	public void setVenue(String Venue) { 
		Venue_ = Venue;
	}
	public void setCity(String City) { 
		City_ = City;
	}
	public void setState(String State) {
		State_ = State;
	}
	public void setCountry(String Country) { 
		Country_ = Country;
	}
	public void setIdentifier(String Identifier) { 
		Identifier_ = Identifier;
	}
	public void setYears(int[] Years) { 
		Years_ = Years;
	}
	public void setGames(Game[] Games) { 
		Games_ = Games;
	}
}
