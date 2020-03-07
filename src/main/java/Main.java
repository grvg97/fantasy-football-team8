import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        User user = Game.authenticateUser();
        Game.start(user);
    }
}