package GameLogic;

import java.util.ArrayList;

public class LeagueDatabase {
    ArrayList<League> leagues = new ArrayList<>();

    // This function adds a new League to the LeagueDatabase.
    public League addLeague(League league) {
        league.setId(leagues.size());
        leagues.add(league);
        return league;
    }

    // This function returns the Global League, which is always at index 0 in our Database.
    public League getGlobalLeague() { return this.leagues.get(0);}
}
