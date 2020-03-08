import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/** This Program implements feature 2
 *  F2: CREATE TEAMS: Users shall be able to one or more teams choosing players from different teams and/or leagues.
 **/
public class Game {
    private Scanner scanner = new Scanner(System.in);
    private UserDatabase userDatabase;
    private LeagueDatabase leagueDatabase;
    private MarketPlace marketPlace;

    Game() throws IOException {
        this.marketPlace = new Gson().fromJson(this.getResponseBody("https://fantasy.premierleague.com/api/bootstrap-static/"), MarketPlace.class);
        String userFileName = "DatabaseUser.json";
        String leagueFileName = "DatabasueLeague.json";
        try {
            userDatabase = new Gson().fromJson(new FileReader(userFileName), UserDatabase.class);
            leagueDatabase = new Gson().fromJson(new FileReader(leagueFileName), LeagueDatabase.class);
        } catch (Exception e) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFileName));
            writer.close();
            writer = new BufferedWriter(new FileWriter(leagueFileName));
            writer.close();

            this.userDatabase = new UserDatabase();
            this.leagueDatabase = new LeagueDatabase();
            League globalLeague = new League("GlobalLeague");
            this.leagueDatabase.addLeague(globalLeague);
        }
    }


    // Implemented for bonus
    private String getResponseBody(String apiURL) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseBody = new StringBuffer();

        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while((line = reader.readLine()) != null) {
            responseBody.append(line);
        }
        reader.close();
        return responseBody.toString();
    }

    private void choosePlayers(User user, int amount, int position) {
        this.marketPlace.showPlayersWithPosition(position);
        System.out.println("Choose the players at position " + position + ". Choose exactly " + amount + " players.");
        for(int i = 0; i < amount; i++) {
            user.buyPlayer(this.marketPlace.getPlayer(this.scanner.nextInt()));
        }
    }

    public void constructTeam(User user) {
        int DEF = 4; int MID = 3; int FOR = 3; // Formation: 4-3-3
        int BENCH = 4; int GK = 1;

        // Select 1 goalkeeper
        choosePlayers(user, GK, 1);

        // Select 4 defenders
        choosePlayers(user, DEF, 2);

        // Select 4 midfielders
        choosePlayers(user, MID, 3);

        // Select 3 Forwards
        choosePlayers(user, FOR, 4);

        this.marketPlace.showPlayers();
        System.out.println("Next up are the bench. Choose exactly 4 players from any position.");
        for(int i = 0; i < BENCH; i++) {
            user.buyPlayer(this.marketPlace.getPlayer(this.scanner.nextInt()));
        }
    }

    public User authenticateUser() throws IOException {
        System.out.println("Welcome to Fantasy Football!");
        System.out.println("Please enter a username: ");
        String username = this.scanner.next();
        System.out.println("Please enter a password: ");
        String password = this.scanner.next();

        User user = this.userDatabase.authUser(username, password);
        if(user != null) {
            System.out.println("You're logged in!");
        } else {
            user = this.userDatabase.addUser(new User(username, password));
            System.out.print("Registration complete!\n");
        }

        return user;
    }

    public void beginTutorial(User user) {
        // Creation of team start of the game
        System.out.println("Please give your team a name: ");
        user.createTeam(this.scanner.next());

        // Select players and add them to the team
        constructTeam(user);

        League globalLeague = this.leagueDatabase.getGlobalLeague();
        globalLeague.addUser(user);
    }

    public void start(User user) throws IOException, InterruptedException {
        if(!user.hasTeam())
            beginTutorial(user);

        // Final Team
        user.displayTeam();
        System.out.println("\n");

        League globalLeague = this.leagueDatabase.getGlobalLeague();
        globalLeague.showRanking();
        end(user);
    }

    public void end(User user) throws IOException {
        // At exit save userDB
        BufferedWriter writer = new BufferedWriter(new FileWriter("DatabaseUser.json"));
        writer.write(new Gson().toJson(this.userDatabase));
        writer.close();

        writer = new BufferedWriter(new FileWriter("DatabaseLeague.json"));
        writer.write(new Gson().toJson(this.leagueDatabase));
        writer.close();
    }
}