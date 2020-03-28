package GameLogic;

import UserInterface.PlayerWindow;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


// DESIGN PATTERN: Iterator, creates a uniform way to iterate through list of players
public class PlayerMarket implements Iterable {
    @SerializedName("elements") private List<Player> players;

    @Override
    public Iterator iterator() {return players.iterator();}

    public List<Player> getPlayers() { return new ArrayList<>(this.players); }

}
