import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = new Game();
        User user = game.authenticateUser();
        game.start(user);
    }
}