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

//    UserIterator userIterator = new UserIterator(users);
//    LeagueIterator leagueIterator = new LeagueIterator(leagues);


    /*
      This function returns the Global League,I
      which is always at index 0 in our Database.
    */
    public League getGlobalLeague() {
        return this.leagues.get(0);
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
        for (User user: this.users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /* Fetching user from database to refresh the page*/
    public User getUser(int id) {
        for (User user: this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
