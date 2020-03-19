package GameLogic;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class League {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private ArrayList<User> competingUsers = new ArrayList<>();
    private HashMap<String, Integer> teamPoints = new HashMap<>();
    private String manager;

    // Constructor: For Global league
    // NOTE: No manager because this league belongs to the game
    public League(String name) {
        this.name = name;
    }

    // Second constructor
    public League(String name, Date start, String manager) {
        this.name = name;
        this.startDate = start;
        this.manager = manager;
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
            System.out.println("Specified user does not compete in this league");
        }
    }

    public String getManager() {
        return this.manager;
    }

    public String getName() {
        return this.name;
    }
    public int getId() { return this.id; }
    public void setId(int id) {this.id = id;}
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    private void computeRanking() {
        // Compute ranking based on the points that the teams have
    }
}