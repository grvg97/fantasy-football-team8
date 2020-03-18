package GameLogic;

import java.util.ArrayList;

public class Database {
    private static ArrayList<League> leagues = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static Database userDatabase;
    private static Database leagueDatabase;


    // This function returns the Global League, which is always at index 0 in our Database.
    public static League getGlobalLeague() {
        return leagues.get(0);
    }

    public static void add(User user, League league) {
        user.setId(users.size());
        league.setId(leagues.size());
        users.add(user);
        leagues.add(league);
    }

    public static void add(League league) {
        league.setId(leagues.size());
        leagues.add(league);
    }

    public static void add(User user) {
        user.setId(users.size());
        users.add(user);
    }

    public static User authUser(String username, String password) {
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
