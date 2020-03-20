package GameLogic;

import java.util.Arrays;
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
    private Database() {}

    private static class DatabaseHolder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return Database.DatabaseHolder.INSTANCE;
    }


    // This function returns the Global League, which is always at index 0 in our Database.
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


    /* ITERATOR DESIGN PATTERN */
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
                value =  null;
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
