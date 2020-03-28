package GameLogic;

import UserInterface.HandleError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class User {
    private int id = 1;
    private String username;
    private String password;
    private Team team;
    private int credits = 1000;
    private List<Integer> leagues;
    private Boolean hasTransferred = false;


    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return this.username;}
    public String getPassword() {return this.password;}
    public String getTeamName() {return this.team.getName();}

    public int getId() {return this.id;}

    public void createTeam(String name) {
        this.team = new Team(name, this.id);
    }

    public boolean hasTeam() {
        return this.team != null;
    }

    public List<Integer> getLeagues() {
        return this.leagues;
    }

    public void deleteTeam() {
        // after this, if there is no reference to the object,
        // it will be deleted by the garbage collector
        this.team = null;
    }

    public int getTotalTeamPoints() {
        return this.team.getTotalPoints();
    }

    public int getTeamRoundPoints() {
        return this.team.getRoundPoints();
    }

    // This function handles the buying of players off the market. Credits may not go below 0.
    public void buyPlayer(Player player) {
        int totalSize = this.team.players.size() + this.team.bench.size();

        if (this.team.contains(player))
            HandleError.playerExists(player);

        else if (totalSize == 15)
            HandleError.maxNumPlayers();

        else if ((this.credits - player.getCost()) < 0)
            HandleError.notEnoughCredits(this.credits, player.getFullName());

        else {
            this.team.addPlayer(player);
            this.credits -= player.getCost();
        }
    }


    // This function handles the selling of players and removes it from the User's team.
    public void sellPlayer(Player player) {

        this.team.removePlayer(player);
        this.credits += player.getCost();
    }


    public void pickCaptains(Player captain, Player viceCaptain) {
        this.team.assignCaptains(captain, viceCaptain);
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getCredits() {
        return this.credits;
    }

    // User enters the specified league and,
    // Also adds that to it's own competed leagues
    public void joinLeague(League league){
        league.addUser(this);
    }

    // User exits the specified league and,
    // Also removes that league from it's own competed leagues
    public void exitLeague(League league) throws IOException {
        league.removeUser(this);
        IOHandler.getInstance().save();
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
            HandleError.actionNotAuthorized(String.valueOf(league.getManager()));
        }
    }


    public int getTeamSize() {
        return this.team.players.size();
    }


    // This function returns the number of players from that position
    public int getTeamPositionCount(Positions position) {
        int counter = 0;
        for (Player player: this.team.players) {
            if (player.getPosition() == position)
                counter++;
        }
        return counter;
    }


    public List<Player> getTeamStarters() {
        return new ArrayList<>(this.team.players);
    }

    public List<Player> getTeamBench() {
        return new ArrayList<>(this.team.bench);
    }

    public List<Player> getFullTeam() {
        List<Player> fullTeam = new ArrayList<>(this.team.players);
        fullTeam.addAll(this.team.bench);
        return fullTeam;
    }



    private class Team {
        private int id;
        private List<Player> players = new ArrayList<>(11); // Starters
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
            this.totalPoints = 0;

            for (Player player: this.players) {
                this.totalPoints += player.getTotalPoints();
            }

            for (Player benchPlayer: this.bench) {
                this.totalPoints += benchPlayer.getTotalPoints();
            }

            return this.totalPoints;
        }

        public int getRoundPoints() {
            int roundPoints = 0;
            for (Player player : this.players) {
                roundPoints += player.getRoundPoints();
            }
            for (Player player : this.bench) {
                roundPoints += player.getRoundPoints();
            }

            return roundPoints;
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


        public void removePlayer(Player selectedPlayer) {
            if (this.players.contains(selectedPlayer))
                this.players.remove(selectedPlayer);
            else
                this.bench.remove(selectedPlayer);

        }

        public boolean contains(Player player) {
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


        public void changeStartingLineup() {
            // TODO: Switch players from the starting lineup
        }

        public boolean isComplete() {
            return players.size() + bench.size() >= 11;
        }
    }
}