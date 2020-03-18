package GameLogic;

import java.util.ArrayList;

public class Database {
    ArrayList<League> leagues = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    // This function adds a new League to the LeagueDatabase.
    public League addLeague(League league) {
        league.setId(leagues.size());
        leagues.add(league);
        return league;
    }

    // This function returns the Global League, which is always at index 0 in our Database.
    public League getGlobalLeague() {
        return this.leagues.get(0);
    }

    public User addUser(User user) {
        user.setId(users.size());
        users.add(user);
        return user;
    }

    public User authUser(String username, String password) {
        for(User user : users) {
            if(user.getUsername().equals(username))
                if(user.getPassword().equals(password))
                    return user;
                else
                    return null;
        }
        return null;
    }
}
