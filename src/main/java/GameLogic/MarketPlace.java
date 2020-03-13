package GameLogic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarketPlace {
    @SerializedName("elements") private List<Player> players;

    public List<Player> getPlayers() {
        return this.players;
    }
    // For bench players
    public Player getPlayer(int id) {
        for (Player player : players)
            if (player.getId() == id)
                return player;

        return null;
    }

}
