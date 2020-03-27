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
            Type userType = new TypeToken<Database<User>>(){}.getType();
            this.userDatabase = new Gson().fromJson(new FileReader(userFileName), userType);

            Type leagueType = new TypeToken<Database<League>>(){}.getType();
            this.leagueDatabase = new Gson().fromJson(new FileReader(leagueFileName), leagueType);
        }
        catch (Exception e) {
            System.out.println(e.toString());
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
            globalLeague.setId(leagueDatabase.getSize());
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
        Iterator<User> it = userDatabase.iterator();
        while(it.hasNext()) {
            User user = it.next();
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    /* Fetching user again from database to refresh the page*/
    public User getUser(int id) {
        Iterator<User> it = userDatabase.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getId() == id)
                return user;
        }
        return null;
    }
    public void add(League league) {
        this.leagueDatabase.add(league);
    }
    public void add(User user) {
        this.userDatabase.add(user);
    }
    public List<League> getLeagues() {
        return this.leagueDatabase.db;
    }

    /* This function returns the Global League, which is always at index 0 in our Database. */
    public League getGlobalLeague() {
        return this.leagueDatabase.db.get(0);
    }

    /* Private class to handle the storing for the IOHandler */
    private static class Database<T> implements Iterable<T> {
        private ArrayList<T> db = new ArrayList<T>();

        @Override
        public Iterator<T> iterator() {
            return this.db.iterator();
        }

        public void add(T element) {
            this.db.add(element);
        }

        public int getSize() {
            return this.db.size();
        }
    }
}
