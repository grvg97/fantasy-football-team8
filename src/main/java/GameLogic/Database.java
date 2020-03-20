package GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private ArrayList<League> leagues = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private Database userDatabase;
    private Database leagueDatabase;


    private Database() {}

    private static class DatabaseHolder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Database.DatabaseHolder.INSTANCE;
    }


    // This function returns the Global League, which is always at index 0 in our Database.
    public League getGlobalLeague() {
        return this.leagues.get(0);
    }

    public ArrayList<League> getLeagues() {
        return new ArrayList<>(this.leagues);
    }


    public void add(User user, League league) {
        user.setId(this.users.size());
        league.setId(this.leagues.size());
        users.add(user);
        leagues.add(league);
    }

    public void add(League league) {
        league.setId(leagues.size());
        leagues.add(league);
    }

    public void add(User user) {
        user.setId(users.size());
        users.add(user);
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
