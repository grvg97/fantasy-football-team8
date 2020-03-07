import java.util.ArrayList;

public class LeagueDatabase {
    ArrayList<League> leagues = new ArrayList<>();

    public League addLeague(League league) {
        league.setId(leagues.size());
        leagues.add(league);
        return league;
    }

    public League getGlobalLeague() { return this.leagues.get(0);}
}
