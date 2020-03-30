package GameLogic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

public class IOHandler {

    private Database<User> userDatabase;
    private Database<League> leagueDatabase;

    private IOHandler() {}

    // holds reference to a single instance  of IOHandler
    private static class IOHandlerHolder {
        private static final IOHandler INSTANCE = new IOHandler();
    }

    public static IOHandler getInstance() {
        return IOHandler.IOHandlerHolder.INSTANCE;
    }

    /* Method run upon every startup of the game:
     *  Creates two Databases in JSON format to store all Users that have
     *  signed up to the game and all leagues created by all Users, initialise a global league.
     *  Loads from the saved state of the Databases if they already exist at startup.
     */
    public void init() throws IOException {
        String userFileName = "DatabaseUser.json";
        String leagueFileName = "DatabaseLeague.json";

        try {
            Type userType = new TypeToken<Database<User>>(){}.getType();
            this.userDatabase = new Gson().fromJson(new FileReader(userFileName), userType);

            Type leagueType = new TypeToken<Database<League>>(){}.getType();
            this.leagueDatabase = new Gson().fromJson(new FileReader(leagueFileName), leagueType);
        }
        catch (Exception e) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFileName));
            writer.close();
            writer = new BufferedWriter(new FileWriter(leagueFileName));
            writer.close();
        }

        if (this.userDatabase == null)
            this.userDatabase = new Database<>();
        if (this.leagueDatabase == null) {
            this.leagueDatabase = new Database<>();
            League globalLeague = new League("Global League", "System", 0);
            this.leagueDatabase.add(globalLeague);
        }
    }

    // Writes the User and created League into the Databases when game exits
    // this saves the state of the Databases
    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("DatabaseUser.json"));
        writer.write(new Gson().toJson(this.userDatabase));
        writer.close();

        writer = new BufferedWriter(new FileWriter("DatabaseLeague.json"));
        writer.write(new Gson().toJson(this.leagueDatabase));
        writer.close();
    }

    /* User Authorisation by iterating through the User database
     * to check for equality between all username, password and the parameters entered
     * Using Iterator to iterate throught he User database */
    public User authUser(String username, String password) {
        Iterator<User> it = userDatabase.iterator();
        while(it.hasNext()) {
            User user = it.next();
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public void add(League league) {
        league.setId(this.leagueDatabase.getSize());
        this.leagueDatabase.add(league);
    }

    public void remove(League league) {
        this.leagueDatabase.remove(league);
    }

    public void add(User user) {
        user.setId(userDatabase.getSize() + 1);
        this.userDatabase.add(user);
    }

    public void remove(User user) {
        this.userDatabase.remove(user);
    }

    // Getters for IOHandler :

    public List<League> getLeagues() {
        return this.leagueDatabase.db;
    }

    // This function returns the Global League which is always at index 0 in our Database.
    public League getGlobalLeague() {
        return this.leagueDatabase.db.get(0);
    }

    // Fetching the same user again from database to refresh the page
    // Using Iterator to iterate through database
    public User getUser(int id) {
        Iterator<User> it = userDatabase.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getId() == id)
                return user;
        }
        return null;
    }


    /* Private  Generic class to handle the storing for the IOHandler for User and League.
     * This class implements the Iterable Interface to create an Iterator
     * for itself.
     */
    private static class Database<T> implements Iterable<T> {
        private ArrayList<T> db = new ArrayList<>();

        @Override
        public Iterator<T> iterator() {
            return this.db.iterator();
        }

        private void add(T element) {
            this.db.add(element);
        }
        private void remove(T element) {
            this.db.remove(element);
        }

        private int getSize() {
            return this.db.size();
        }
    }
}
