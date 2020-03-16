package GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class User {
    private int id = 1;
    private String username;
    private String password;
    private Team team;
    private int credits = 1000;
    private Boolean hasTransferred = false;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password; }
    public String getTeamName() { return this.team.getName(); }

    public void createTeam(String name)   {
        this.team = new Team(name, this.id);
    }
    public boolean hasTeam() { return this.team != null; }

    public void deleteTeam() {
        // after this, if there is no reference to the object,
        // it will be deleted by the garbage collector
        this.team = null;
    }

    // This function shows a list of all players currently in the user's team.
    public void displayTeam() {
        System.out.println("Starting line up 4-3-3: ");
        for(Player player : this.team.players)
            System.out.println(player.getPositionName() + " - " + player.getFullName());
        System.out.println("Bench: ");
        for (Player player: this.team.bench) {
            System.out.println(player.getPositionName() + " - " + player.getFullName());
        }
    }

    public int getTotalTeamPoints() {
        return this.team.getTotalPoints();
    }

    // This function handles the buying of players off the market. Credits may not go below 0.
    public void buyPlayer(Player player) {
        if ((this.credits - player.getCost()) >= 0) {
            this.team.addPlayer(player);
            this.credits -= player.getCost();
        }
        else {
            System.out.println("Not enough credits to buy player");
        }
        System.out.println("Credits: " + this.credits);
    }

    // This function handles the selling of players and removes it from the User's team.
    public void sellPlayer(Player player) {
        this.team.removePlayer(player);
        this.credits += player.getCost();
    }

    public void pickCaptains(Player captain, Player viceCaptain) {
        this.team.assignCaptains(captain, viceCaptain);
    }

    public void setId(int id) { this.id = id;}

    // The following functions are not implemented (yet) but are here to be in line with our Class diagrams.
    // User can set the end date of any league as long as that user is
    // the manager of the specified league
    public void setEndOfLeague(Date endDate, League league) { }

    // User enters the specified league and,
    // Also adds that to it's own competed leagues
    public void joinLeague(League league) { }

    // User exits the specified league and,
    // Also removes that league from it's own competed leagues
    public void exitLeague(League league) { }

    // Create a league and assign the user who created as the manager
    // Add created league to the leagues that the user competes
    public League createLeague(String name, User manager, Date start) { return null; }

    public int getTeamSize() {
        return this.team.players.size();
    }
    // League
    public void deleteLeague(League league) { }

    private class Team {
        private int id;
        private List<Player> players = new ArrayList<>( 11); // Starters
        private List<Player> bench = new ArrayList<>(4);
        private int totalPoints = 0;
        private String name;
        private Player captain;
        private Player viceCaptain;

        Team (String name, int id) {
            this.name = name;
            this.id = id;
        }
        public String getName() {
            return this.name;
        }

        // Get all the 15 players total points
        public int getTotalPoints() {
            for (Player player: this.players) {
                this.totalPoints += player.getTotalPoints();
            }
            for (Player benchPlayer: this.bench) {
                this.totalPoints += benchPlayer.getTotalPoints();
            }
            return this.totalPoints;
        }

        public void assignCaptains(Player captain, Player viceCaptain) {
            this.captain = captain;
            this.viceCaptain = viceCaptain;
        }

        // Add to bench if starting lineup (11) already chosen
        public void addPlayer(Player player) {
            if (this.players.size() == 11)
                this.bench.add(player);
            else
                this.players.add(player);
        }

        public void removePlayer(Player player) {
            players.remove(player);
        }

        public void changeStartingLineup() {
            // TODO: Switch players from the starting lineup
        }

        public boolean isComplete() {
            return players.size() + bench.size() >= 11;
        }
    }
}