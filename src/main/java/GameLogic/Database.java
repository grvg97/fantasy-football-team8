package GameLogic;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Database {
    private ArrayList<League> leagues = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    private Database userDatabase;
    private Database leagueDatabase;

    UserIterator userIterator = new UserIterator(users);
    LeagueIterator leagueIterator = new LeagueIterator(leagues);

    /* SINGLETON IMPLEMENTATION */
    private Database()  {

    }

    private static class DatabaseHolder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Database.DatabaseHolder.INSTANCE;
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
            this.userDatabase = Database.getInstance();
        if (this.leagueDatabase == null)
            this.leagueDatabase = Database.getInstance();

        League globalLeague = new League("Global League", "System");
        this.leagueDatabase.add(globalLeague);
        this.leagues.add(globalLeague);
    }


    public void save(User user) throws IOException {
        // At exit save userDB
        BufferedWriter writer = new BufferedWriter(new FileWriter("DatabaseUser.json"));
        writer.write(new Gson().toJson(this.userDatabase));
        writer.close();

        writer = new BufferedWriter(new FileWriter("DatabaseLeague.json"));
        writer.write(new Gson().toJson(this.leagueDatabase));
        writer.close();
    }


    /*
      This function returns the Global League,
      which is always at index 0 in our Database.
    */
    public League getGlobalLeague() {
        return this.leagueIterator.getFirst();
    }

    public ArrayList<League> getLeagues() {
        return new ArrayList<>(this.leagues);
    }

    public void add(League league) {
        league.setId(leagues.size());
        leagues.add(league);
    }

    public void add(User user) {
        user.setId(users.size());
        users.add(user);
    }


    /* Return the user from database and return it if found, else return null */
    public User authUser(String username, String password) {
        userIterator.first();
        while (!userIterator.isDone()) {
            User user = userIterator.currentValue();
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
            userIterator.next();
        }
        return null;
    }

    /* ITERATOR DESIGN PATTERN for the user database*/
    private class UserIterator {

        private ArrayList<User> database;
        private java.util.Iterator iterator;
        private User value;

        public UserIterator(ArrayList<User> userData) {
            this.database = userData;
        }

        public void next() {
            try {
                value = (User)iterator.next();
            } catch (NoSuchElementException ex) {
                value =  null;
            }
        }

        public void first() {
            iterator = database.iterator();
            next();
        }

        public boolean isDone() {
            return value == null;
        }

        public User currentValue() {
            return value;
        }
    }


    /* ITERATOR DESIGN For the league database */
    private class LeagueIterator {

        private ArrayList<League> database;
        private java.util.Iterator iterator;
        private League value;

        public LeagueIterator(ArrayList<League> leagueData) {
            this.database = leagueData;
        }

        public void next() {
            try {
                value = (League)iterator.next();
            } catch (NoSuchElementException ex) {
                value = null;
            }
        }

        public void first() {
            iterator = database.iterator();
            next();
        }

        public League getFirst() {
            return database.get(0);
        }

        public boolean isDone() {
            return value == null;
        }

        public League currentValue() {
            return value;
        }

    }


}
