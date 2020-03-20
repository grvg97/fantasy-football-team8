package GameLogic;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeagueIterator implements Iterator<League> {

    private League[] database;
    private int index = 0;

    public LeagueIterator(League[] database) {
        this.database = database;
    }

    @Override
    public boolean hasNext() {
        if (index >= database.length || database[index] == null) {
            return false;
        }
        return true;
    }

    @Override
    public League next() {
        return database[index ++];
    }

    @Override
    public void remove() {
        for (int i = index - 1; i < database.length - 1; i++) {
            database[i] = database[i+1];
        }

        database[database.length - 1] = null;
    }
}
