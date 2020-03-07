import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/** This Program implements feature 2
 *  F2: CREATE TEAMS: Users shall be able to one or more teams choosing players from different teams and/or leagues.
 **/
class Game {
    static Scanner scanner = new Scanner(System.in);
    static UserDatabase userDatabase;
    static LeagueDatabase leagueDatabase;
    static MarketPlace marketPlace;


    public static MarketPlace parse(String responseBody) {
        MarketPlace marketPlace = new Gson().fromJson(responseBody, MarketPlace.class);
        return marketPlace;
    }

    // Implemented for bonus
    public static String getResponseBody(String apiURL) throws IOException {
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

    private static void choosePlayers(MarketPlace market, User user, int amount, int position, Scanner scanner) {
        market.showPlayersWithPosition(position);
        System.out.println("Choose the players at position " + position + ". Choose exactly " + amount + " players.");
        for(int i = 0; i < amount; i++) {
            user.buyPlayer(market.getPlayer(scanner.nextInt()));
        }
    }

    public static void constructTeam(MarketPlace marketPlace, User user) {
        Scanner scanner = new Scanner(System.in);
        int DEF = 4; int MID = 3; int FOR = 3; // Formation: 4-3-3
        int BENCH = 4; int GK = 1;

        // Select 1 goalkeeper
        choosePlayers(marketPlace, user, GK, 1, scanner);

        // Select 4 defenders
        choosePlayers(marketPlace, user, DEF, 2, scanner);

        // Select 4 midfielders
        choosePlayers(marketPlace, user, MID, 3, scanner);

        // Select 3 Forwards
        choosePlayers(marketPlace, user, FOR, 4, scanner);

        marketPlace.showPlayers();
        System.out.println("Next up are the bench. Choose exactly 4 players from any position.");
        for(int i = 0; i < BENCH; i++) {
            user.buyPlayer(marketPlace.getPlayer(scanner.nextInt()));
        }
    }

    public static User authenticateUser() throws IOException {
        userDatabase = new Gson().fromJson(new FileReader("src/database/DatabaseUser.json"), UserDatabase.class);
        leagueDatabase = new Gson().fromJson(new FileReader("src/database/DatabaseLeague.json"), LeagueDatabase.class);
        if(userDatabase == null)
            userDatabase = new UserDatabase();

        System.out.println("Welcome to Fantasy Football!");
        System.out.println("Please enter a username: ");
        String username = scanner.next();
        System.out.println("Please enter a password: ");
        String password = scanner.next();

        User user = userDatabase.authUser(username, password);
        if(user != null) {
            System.out.println("You're logged in!");
        } else {
            user = userDatabase.addUser(new User(username, password));
            System.out.print("Registration complete!\n");
            }

        return user;
    }

    public static void beginTutorial(User user) {
        // Creation of team start of the game
        System.out.println("Please give your team a name: ");
        user.createTeam(scanner.next());

        // Select players and add them to the team
        constructTeam(marketPlace, user);

        League globalLeague = leagueDatabase.getGlobalLeague();
        globalLeague.addUser(user);
    }

    public static void start(User user) throws IOException, InterruptedException {
        // Get data from api and parse the data to use it properly
        String responseBody = getResponseBody("https://fantasy.premierleague.com/api/bootstrap-static/");
        marketPlace = parse(responseBody);

        if(!user.hasTeam())
            beginTutorial(user);

        // Final Team
        user.displayTeam();
        System.out.println("\n");

        League globalLeague = leagueDatabase.getGlobalLeague();
        globalLeague.showRanking();
        end(user);
    }

    public static void end(User user) throws IOException {
        // At exit save userDB
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/DatabaseUser.json"));
        writer.write(new Gson().toJson(userDatabase));
        writer.close();

        writer = new BufferedWriter(new FileWriter("src/database/DatabaseLeague.json"));
        writer.write(new Gson().toJson(leagueDatabase));
        writer.close();
    }
}