import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<User> users = new ArrayList<>();

    public User addUser(User user) {
        user.setId(users.size());
        users.add(user);
        return user;
    }

    public User authUser(String username, String password) {
        for(User user : users) {
            if(user.getUsername().equals(username))
                if(user.getPassword().equals(password))
                    return user;
                else
                    return null;
        }

        return null;
    }
}
