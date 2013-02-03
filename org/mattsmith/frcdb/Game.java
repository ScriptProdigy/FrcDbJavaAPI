package org.mattsmith.frcdb;

import java.util.ArrayList;

import org.json.JSONObject;

public class Game {
	private String GameName_;
	private String EventShortName_;
	private int GameYear_;
	private int StartDate_;
	private int EndDate_;
	private TeamStanding[] TeamStandings_;
	private Match[] Matches_;
	private String[] Parent_;
	private ArrayList<String[]> Children_;
	private Boolean loadedCache = false;
	
	public void loadCache()
	{
		String Url = FrcDbConstants.UrlBase;
		Url += String.format(FrcDbConstants.UrlGame, EventShortName_);
		JSONObject g = null;
		
		try {
			g = new JSONObject(FrcDbConstants.getHtml(Url));
		} catch (Exception e) { e.printStackTrace(); }
		
		// Load Parent 
		try
		{
			if(!g.isNull("parent"))
			{
				JSONObject parent = g.getJSONObject("parent");
					
				Parent_ = new String[2];
				Parent_[0] = parent.getString("name");
				Parent_[1] = String.valueOf(parent.getInt("year"));
				
			}
		} catch (Exception ex) { ex.printStackTrace(); }
		
		// Load Children
		try
		{	
			if(g.getJSONArray("children").length() > 0)
			{
				for(int x = 0; x < g.getJSONArray("children").length(); x++)
				{
					JSONObject child = g.getJSONArray("children").getJSONObject(x);
					
					String[] c = new String[2];
					c[0] = child.getString("name");
					c[1] = String.valueOf(child.getInt("year"));
					
					Children_.add(c);
				}
			}
		} catch (Exception ex) { ex.printStackTrace(); }
		
		
		
		// Load TeamStandings
		try
		{
			TeamStandings_ = new TeamStanding[g.getJSONArray("teams").length()];
			for(int x = 0; x<g.getJSONArray("teams").length(); x++)
			{
				JSONObject team = g.getJSONArray("teams").getJSONObject(x);
				
				TeamStanding t = new TeamStanding();
				t.setNumber(team.getInt("number"));
				t.setRank(team.getInt("rank"));
				t.setMatchesPlayed(team.getInt("matchesPlayed"));
				t.setGameYear(team.getInt("gameYear"));
				t.setWins(team.getInt("wins"));
				t.setLosses(team.getInt("losses"));
				t.setTies(team.getInt("ties"));
				if(team.has("finalMatchLevel"))
					t.setFinalMatchLevel(team.getString("finalMatchLevel"));
				t.setOPR(team.getDouble("opr"));
				t.setDPR(team.getDouble("dpr"));
				
				TeamStandings_[x] = t;
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		
		// Load Matches
		try
		{
			Matches_ = new Match[g.getJSONArray("matches").length()];
			for(int x = 0; x<g.getJSONArray("matches").length(); x++)
			{
				JSONObject match = g.getJSONArray("matches").getJSONObject(x);
				
				Match m = new Match();
				m.setNumber(match.getInt("number"));
				m.setType(match.getString("type"));
				m.setTime(match.getString("time"));
				m.setRedScore(match.getInt("redScore"));
				
				int[] redAlliance = new int[match.getJSONArray("redTeams").length()];
				for(int i = 0; i < match.getJSONArray("redTeams").length(); i++)
					redAlliance[i] = Integer.parseInt(String.valueOf(match.getJSONArray("redTeams").get(i)));
				
				m.setRedAlliance(redAlliance);
				m.setBlueScore(match.getInt("blueScore"));
				
				int[] blueAlliance = new int[match.getJSONArray("blueTeams").length()];
				for(int i = 0; i < match.getJSONArray("blueTeams").length(); i++)
					blueAlliance[i] = Integer.parseInt(String.valueOf(match.getJSONArray("blueTeams").get(i)));
				
				m.setBlueAlliance(blueAlliance);
				m.setWinner(match.getString("winner"));
				
				Matches_[x] = m;
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		loadedCache = true;
	}
	
	public Boolean hasParent()
	{
		if(!loadedCache)
			loadCache();
		
		if(Parent_ == null)
			return false;
		return true;
	}	
	public Boolean hasChildren()
	{
		if(!loadedCache)
			loadCache();
		
		if(Children_ == null)
			return false;
		return true;
	}
	
	public String getGameName() {
		return GameName_;
	}
	public String getEventShortName() {
		return EventShortName_;
	}
	public int getGameYear() {
		return GameYear_;
	}
	public int getStartDate() {
		return StartDate_;
	}
	public int getEndDate() {
		return EndDate_;
	}
	public TeamStanding[] getTeamStandings() {
		return TeamStandings_;
	}
	public Match[] getMatches() {
		return Matches_;
	}
	public ArrayList<String[]> getChildren() {
		return Children_;
	}
	public String[] getParent() {
		return Parent_;
	}
	
	public void setParent(String[] parent_) {
		Parent_ = parent_;
	}
	public void setChildren(ArrayList<String[]> children_) {
		Children_ = children_;
	}
	public void setEventShortName(String eventShortName_) {
		EventShortName_ = eventShortName_;
	}
	public void setGameYear(int gameYear_) {
		GameYear_ = gameYear_;
	}
	public void setStartDate(int startDate_) {
		StartDate_ = startDate_;
	}
	public void setEndDate(int endDate_) {
		EndDate_ = endDate_;
	}
	public void setTeamStandings(TeamStanding[] TeamStandings) {
		TeamStandings_ = TeamStandings;
	}
	public void setGameName(String gameName_) {
		GameName_ = gameName_;
	}
	public void setMatches(Match[] matches_) {
		Matches_ = matches_;
	}
	
	
}
