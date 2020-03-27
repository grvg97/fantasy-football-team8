package GameLogic;

import UserInterface.PlayerWindow;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// DESIGN PATTERN: SINGLETON: Prevents this class to be instantiated twice
public class PlayerMarket implements Iterable {
    @SerializedName("elements") private List<Player> players;

    private PlayerMarket() {}

    @Override
    public Iterator iterator() { return players.iterator(); }

    private static class PlayerMarketHolder {
        private static final PlayerMarket INSTANCE = new PlayerMarket();
    }

    public static PlayerMarket getInstance() {
        return PlayerMarketHolder.INSTANCE;
    }

    public List<Player> getPlayers() { return new ArrayList<Player>(this.players); }

}
