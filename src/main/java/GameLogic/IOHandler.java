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
    private static class Database {
        private ArrayList<League> leagues = new ArrayList<>();
        private ArrayList<User> users = new ArrayList<>();

        /* This function returns the Global League, which is always at index 0 in our Database. */
        public League getGlobalLeague() {
            return leagues.get(0);
        }

        public void add(League league) {
            league.setId(this.leagues.size());
            this.leagues.add(league);
        }

        public void add(User user) {
            user.setId(this.users.size());
            this.users.add(user);
        }

        /* Return the user from database and return it if found, else return null */
        public User authenticateUser(String username, String password) {
            for (User user: this.users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password))
                    return user;
            }
            return null;
        }

        /* Fetching user again from database to refresh the page*/
        public User getUser(int id) {
            for (User user: this.users) {
                if (user.getId() == id)
                    return user;
            }
            return null;
        }

        public List<League> getLeagues() {
            return new ArrayList<>(this.leagues);
        }

    }

}
