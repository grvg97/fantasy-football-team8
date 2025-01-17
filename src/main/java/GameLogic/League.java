package GameLogic;

import UserInterface.HandleError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class League {
    private int id;
    private String name;
    private ArrayList<User> competingUsers = new ArrayList<>();
    private HashMap<String, Integer> teamPoints = new HashMap<>();
    private String manager;


    // Constructor: League shares the same id as the manager of that league
    public League(String name, String manager, int id) {
        this.name = name;
        this.manager = manager;
        this.id = id;
    }


    // Update the round (weekly) points using this function
    public void updateRoundPoints() {
        Iterator<User> it = competingUsers.iterator();
        while (it.hasNext()) {
            User user = it.next();
            user.getTeamRoundPoints();
            teamPoints.replace(user.getTeamName(), this.teamPoints.get(user.getTeamName()) + user.getTeamRoundPoints());
        }
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
            HandleError.errorMessage("User Does Not Exists!",
                    user.getUsername() + " doesn't exist in the league");
        }
    }

    // GETTERS and setters for LEAGUE :
    public String getName() {return this.name;}
    public String getManager() {return this.manager;}
    public void setId(int id) {this.id = id;}
    public int getId() {return this.id;}

    public ArrayList<User> getCompetingUsers() {
        return new ArrayList<>(this.competingUsers);
    }

    public HashMap<String, Integer> getTeamPoints() {
        return this.teamPoints;
    }



}