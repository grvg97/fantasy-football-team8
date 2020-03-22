package GameLogic;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        if (this.leagueDatabase == null)
            this.leagueDatabase = new Database();

        League globalLeague = new League("Global League", "System");
        this.leagueDatabase.add(globalLeague);
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


}
