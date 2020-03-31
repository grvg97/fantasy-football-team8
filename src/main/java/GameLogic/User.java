package GameLogic;

import UserInterface.HandleError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public boolean hasTeam() {
        return this.team != null;
    }

    public void createTeam(String name) {
        this.team = new Team(name, this.id);
    }


    // This function handles the buying of players off the market. Credits may not go below 0.
    public boolean buyPlayer(Player player) {
        int totalSize = this.team.players.size() + this.team.bench.size();

        if (this.team.contains(player)) {
            HandleError.errorMessage("Player Exists!",
                    player.getFullName() + " already exists in the team.");
            return false;
        }

        else if (totalSize >= 15) {
            HandleError.errorMessage("Max Number Reached!",
                    "The number of players in the team can't exceed 15");
            return false;
        }

        else if ((this.credits - player.getCost()) < 0) {
            HandleError.errorMessage("Not Enough Credits",
                    this.credits + " left, " + " not enough to buy " + player.getFullName());
            return false;
        }

        else {
            this.team.addPlayer(player);
            this.credits -= player.getCost();
            return true;
        }
    }

    // This function handles the selling of players and removes it from the User's team.
    public void sellPlayer(Player player) {
        this.team.removePlayer(player);
        this.credits += player.getCost();
    }

    public void pickCaptain(Player player) {
        this.team.assignCaptainId(player);
    }

    public void pickViceCaptain(Player player) {
        this.team.assignViceCaptainId(player);
    }

    // User enters the specified league and,
    // Also adds that to it's own competed leagues
    public void joinLeague(League league){
        league.addUser(this);
    }

    // User exits the specified league and,
    // Also removes that league from it's own competed leagues
    public void exitLeague(League league) throws IOException {
        // User can't exit global league
        if (league.getManager().equals("System"))
            HandleError.errorMessage("Can't Exit League!",
                    "User can't exit Global League");
        else {
            league.removeUser(this);
            IOHandler.getInstance().save();
        }
    }

    // Create a league and assign the user's id who created as the manager
    // Add created league to the leagues that the user competes
    public League createLeague(String name) {
        League customLeague = new League(name, this.getUsername(), this.id);
        customLeague.addUser(this);
        IOHandler.getInstance().add(customLeague);

        return customLeague;
    }

    // Delete league if user is the manager
    public void deleteLeague(League league) {
        if (league.getManager().equals(this.username)) {
            IOHandler.getInstance().remove(league);
            league = null;
        }
        else {
            HandleError.errorMessage("Action Not Authorized" ,
                    String.valueOf(league.getManager()) + " is the manager of the league");
        }
    }

    public void updatePlayers() throws IOException {
        PlayerMarket playerMarket = HandleApi.getJsonObject();
        this.team.updateTeamAndBench(playerMarket);
    }

    public void changePlayers(Player benchPlayer, Player starterPlayer) {
        this.team.changeStartingLineup(benchPlayer, starterPlayer);
    }

    // GETTERS and SETTERS for USER:
    public void setId(int id) { this.id = id; }
    public int getId() {return this.id;}
    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public int getCredits() { return this.credits; }


    // Gets TEAM related information
    public String getTeamName() { return this.team.getName(); }
    public int getTeamStarterSize() {return this.team.players.size();}
    public int getTeamBenchSize() {return this.team.bench.size(); }
    public int getTeamRoundPoints() { return this.team.getRoundPoints(); }

    /*
     * Keeps track of the number of players from the positions
     * as mentioned in the Enumerator "Positions" and "Formation"
     * (GK,DEF,MID,FWD)
     */
    public int getTeamPositionCount(Positions position) {
        int counter = 0;
        for (Player player: this.team.players) {
            if (player.getPosition() == position)
                counter++;
        }

        return counter;
    }


    // Gets Player related Information from team
    public List<Player> getTeamStarters() { return new ArrayList<>(this.team.players); }
    public List<Player> getTeamBench() { return new ArrayList<>(this.team.bench); }

    public int getCaptainId() { return this.team.captainId; }
    public int getViceCaptainId() { return this.team.viceCaptainId; }

    //TEAM PRIVATE CLASS
    private class Team{
        private int id;
        private List<Player> players = new ArrayList<>(11); // Starters
        private List<Player> bench = new ArrayList<>(4);
        private int totalPoints = 0;
        private String name;
        private int captainId;
        private int viceCaptainId;


        Team (String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int getId() { return id; }
        private String getName() {
            return this.name;
        }

        // Get all the 15 players total points
        private int getTotalPoints() { return this.totalPoints; }

        private void updateTeamAndBench(PlayerMarket playerMarket) {
            Iterator<Player> it = playerMarket.iterator();
            List<Player> updatedPlayers = new ArrayList<>();
            for (Player player : this.players) {
                updatedPlayers.add(playerMarket.getPlayer(player.getId()));
            }

            List<Player> updatedBench = new ArrayList<>();
            for (Player player : this.bench) {
                updatedBench.add(playerMarket.getPlayer(player.getId()));
            }

            this.players = updatedPlayers;
            this.bench = updatedBench;
        }

        // Bench players receive half the round points.
        private int getRoundPoints() {
            int roundPoints = 0;
            for (Player player : this.players) roundPoints += player.getRoundPoints();
            for (Player player : this.bench) roundPoints += player.getRoundPoints() / 2;

            return roundPoints;
        }

        // Assign player as captain and the rest as false for captain
        private void assignCaptainId(Player captain) {
            this.captainId = captain.getId();
        }
        private void assignViceCaptainId(Player viceCaptain) {
            this.viceCaptainId = viceCaptain.getId();
        }

        // Add to bench if starting lineup (11) already chosen
        private void addPlayer(Player player) {
            if (this.players.size() < 11)
                this.players.add(player);
            else
                this.bench.add(player);
        }

        private void removePlayer(Player selectedPlayer) {
            if (this.players.contains(selectedPlayer))
                this.players.remove(selectedPlayer);
            else
                this.bench.remove(selectedPlayer);

        }

        private boolean contains(Player player) {
            for (Player p : players) {
                if (p.equals(player))
                    return true;
            }

            for (Player p : bench) {
                if (p.equals(player))
                    return true;
            }
            return false;
        }


        private void changeStartingLineup(Player benchPlayer, Player starterPlayer) {
            this.players.remove(starterPlayer);
            this.bench.remove(benchPlayer);

            this.players.add(benchPlayer);
            this.bench.add(starterPlayer);
        }

    }
}