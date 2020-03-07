import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarketPlace {
    @SerializedName("elements") private List<Player> players;

    // Constructor
    public MarketPlace(List<Player> players) {
        this.players = players;
    }

    public void showPlayers() {
        for(Player player : players) {
            System.out.println(player.getId() + " " + player.getFullName() + " " + player.getCost());
        }
    }

    // For bench players
    public Player getPlayer(int id) {
        for (Player player : players)
            if (player.getId() == id)
                return player;

        return null;
    }

    public void showPlayersWithPosition(int position) {
        for(Player player : players) {
            printPlayer(player, position);
        }
    }

    private void printPlayer(Player player, int position) {
        String playerInfo = player.getId() + " " + player.getFullName() + " " + player.getTotalPoints();
        if (player.getPosition() == position) {
            System.out.println(playerInfo);
        }
    }

}
