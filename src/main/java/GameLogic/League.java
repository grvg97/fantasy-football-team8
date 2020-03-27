package GameLogic;

import UserInterface.HandleError;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class League {
    private int id;
    private String name;
    private Date startDate;
    private ArrayList<User> competingUsers = new ArrayList<>();
    private HashMap<String, Integer> teamPoints = new HashMap<>();
    private int manager;


    // Constructor
    public League(String name, int manager) {
        this.name = name;
        this.manager = manager;
    }

    public ArrayList<User> getCompetingUsers() {
        return new ArrayList<>(this.competingUsers);
    }

    public HashMap<String, Integer> getTeamPoints() {
        return new HashMap<>(this.teamPoints);
    }

    // When a new user enters the league, it will always start with 0 points
    public void addUser(User user) {
        competingUsers.add(user);
        teamPoints.put(user.getTeamName(),0);
    }

    // Removes user from the league and removes its team from the teamPoints map
    public void removeUser(User user) {
        if (competingUsers.contains(user)) {
            this.competingUsers.remove(user);
            this.teamPoints.remove(user.getTeamName());
        }
        else {
            HandleError.userDoesNotExist();
        }
    }

    //private List<User> showLeaderboards() {
        // Compute ranking based on the points that the teams have
    //}

    public int getManager() {return this.manager;}
    public String getName() {return this.name;}
    public void setId(int id) {this.id = id;}
    public int getId() {return this.id;}

}