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


    // Constructor
    public League(String name, String manager) {
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