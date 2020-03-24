package GameLogic;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

public class IOHandler {

    private Database userDatabase;
    private Database leagueDatabase;

    private IOHandler() {}

    private static class IOHandlerHolder {
        private static final IOHandler INSTANCE = new IOHandler();
    }

    public static IOHandler getInstance() {
        return IOHandler.IOHandlerHolder.INSTANCE;
    }

    public Database getUserDB() {
        return userDatabase;
    }
    public Database getLeagueDB () {
        return leagueDatabase;
    }

    /*
     * This function is called when the game is initialized
     */
    public void init() throws IOException {
        String userFileName = "DatabaseUser.json";
        String leagueFileName = "DatabaseLeague.json";

        try {
            userDatabase = new Gson().fromJson(new FileReader(userFileName), Database.class);
            leagueDatabase = new Gson().fromJson(new FileReader(leagueFileName), Database.class);
        }
        catch (Exception e) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFileName));
            writer.close();
            writer = new BufferedWriter(new FileWriter(leagueFileName));
            writer.close();
        }

        if (this.userDatabase == null)
            this.userDatabase = new Database();
        if (this.leagueDatabase == null) {
            this.leagueDatabase = new Database();
            League globalLeague = new League("Global League", "System");
            this.leagueDatabase.add(globalLeague);
        }
    }


    public void save() throws IOException {
        // At exit save userDB
        BufferedWriter writer = new BufferedWriter(new FileWriter("DatabaseUser.json"));
        writer.write(new Gson().toJson(this.userDatabase));
        writer.close();

        writer = new BufferedWriter(new FileWriter("DatabaseLeague.json"));
        writer.write(new Gson().toJson(this.leagueDatabase));
        writer.close();
    }

    /* Authenticates the user. Returns the user with the username and password,
     * Else, returns null
     */

    public User authUser(String username, String password) {
        return userDatabase.authenticateUser(username, password);
    }
    public User getUser(int id) {
        return userDatabase.getUser(id);
    }
    public void add(League league) {
        leagueDatabase.add(league);
    }
    public void add(User user) {
        userDatabase.add(user);
    }
    public List<League> getLeagues() {
        return leagueDatabase.getLeagues();
    }
    public League getGlobalLeague() {
        return leagueDatabase.getGlobalLeague();
    }

    /* Private class to handle the storing for the IOHandler */
    private class Database {
        private League[] leagues;
        private User[] users;

        /*
          This function returns the Global League,I
          which is always at index 0 in our Database.
        */
        public League getGlobalLeague() {
            return LeagueIterator.getInstance(this.leagues).getFirst();
        }

        public List<League> getLeagues() {
            return new ArrayList<>(Arrays.asList(this.leagues));
        }

        public void add(League league) {
            league.setId(leagues.length);
            LeagueIterator.getInstance(this.leagues).add(league);
        }

        public void add(User user) {
            user.setId(users.length);
            UserIterator.getInstance(this.users).add(user);
        }

        /* Return the user from database and return it if found, else return null */
        public User authenticateUser(String username, String password) {
            UserIterator userIterator = UserIterator.getInstance(this.users);

            while (userIterator.hasNext()) {
                User user = userIterator.next();
                if (user.getUsername().equals(username) && user.getPassword().equals(password))
                    return user;
            }
            return null;
        }

        /* Fetching user again from database to refresh the page*/
        public User getUser(int id) {
            for (User user: this.users) {
                if (user.getId() == id) {
                    return user;
                }
            }
            return null;
        }

    }

    /* Private inner league iterator class to implement the ITERATOR DESIGN PATTERN */
    private static class LeagueIterator implements Iterator<League> {

        private static League[] database;
        private int index = 0;

        private LeagueIterator() {
        }

        private static class LeagueIteratorHolder {
            private static final LeagueIterator INSTANCE = new LeagueIterator();
        }

        public static LeagueIterator getInstance(League[] mydatabase) {
            database = mydatabase;
            return LeagueIterator.LeagueIteratorHolder.INSTANCE;
        }


        @Override
        public boolean hasNext() {
            if (database[index] == null) {
                return false;
            }
            return true;
        }

        @Override
        public League next() {
            return database[index ++];
        }

        public League getFirst() {
            return database[0];
        }

        public void add(League league) {
            database[index] = league;
        }

    }

    /* Private inner league iterator class to implement the ITERATOR DESIGN PATTERN */
    private static class UserIterator implements Iterator<User> {

        private static User[] database;
        private int index = 0;

        private UserIterator() {
        }

        private static class UserIteratorHolder {
            private static final UserIterator INSTANCE = new UserIterator();
        }

        public static UserIterator getInstance(User[] mydatabase) {
            database = mydatabase;
            return UserIterator.UserIteratorHolder.INSTANCE;
        }

        @Override
        public boolean hasNext() {
            if (database[index] == null) {
                return false;
            }
            return true;
        }

        @Override
        public User next() {
            return database[index ++];
        }

        public void add(User user) {
            database[index] = user;
        }
    }

}
