package GameLogic;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class Player {
    @SerializedName("id")               private int id;
    @SerializedName("first_name")       private String firstName;
    @SerializedName("second_name")      private String lastName;
    @SerializedName("element_type")     private int position;
    @SerializedName("now_cost")         private int cost;
    @SerializedName("status")           private String isAvailable;
    @SerializedName("team")             private int realLifeTeam;

    // Player's stats
    @SerializedName("total_points")     private int totalPoints;
    @SerializedName("minutes")          private int minutes;
    @SerializedName("goals")            private int goalsScored;
    @SerializedName("assists")          private int assists;
    @SerializedName("clean_sheets")     private int cleanSheets;
    @SerializedName("goals_conceded")   private int goalsConceded;
    @SerializedName("yellow_cards")     private int yellowCards;
    @SerializedName("red_cards")        private int redCards;
    @SerializedName("saves")            private int saves;

    public int getId() {
        return this.id;
    }
    public int getTotalPoints() {
        return this.totalPoints;
    }
    public int getCost() {
        return this.cost;
    }
    public String getFullName() {
        return (this.firstName + " " + this.lastName);
    }
    public int getPosition() {
        return this.position;
    }
    public boolean isInjured() {
        return isAvailable.equals("i");
    }

    public String getPositionName() {
        String position = "NaN";
        switch(this.position) {
            case 1:
                position = "GK";
                break;
            case 2:
                position = "DEF";
                break;
            case 3:
                position = "MID";
                break;
            case 4:
                position = "FOR";
                break;
        }
        return position;
    }

    public HashMap<String, Integer> getStats() {

        HashMap<String, Integer> stats = new HashMap<>();
        // Construct the map and return it
        stats.put("totalPoints", totalPoints);
        stats.put("minutes", minutes);
        stats.put("goalsScored", goalsScored);
        stats.put("assists", assists);
        stats.put("cleanSheets", cleanSheets);
        stats.put("goalConceded", goalsConceded);
        stats.put("yellowCards", yellowCards);
        stats.put("redCards", redCards);
        stats.put("saves", saves);
        return stats;
    }

}