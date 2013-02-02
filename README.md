FrcDbJavaAPI
============

Basic Usage:
```java

public class main {
	
	public static void main(String[] args)
	{
		FrcDbApi.buildCache();
		System.out.print("Event Count: ");
		System.out.println(FrcDbApi.getEvents().length);
		
		for(Event e : FrcDbApi.getEvents())
		{
			System.out.println("=========================================");
			System.out.println(e.getName());
			System.out.println(e.getGames());
			for(Game g : e.getGames())
			{
				if(!g.hasChildren())
				{
					for(TeamStanding ts : g.getTeamStandings())
					{
						System.out.print(g.getGameYear());
						System.out.print(" ");
						System.out.print(ts.getNumber());
						System.out.print(" ");
						System.out.println(ts.getRank());
					}
				}
			}
		}
	}
}

```