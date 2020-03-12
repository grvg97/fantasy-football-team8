import UserInterface.AlertBox;
import UserInterface.LoginWindow;
import UserInterface.StartWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.scene.layout.VBox;
import javafx.stage.Stage;  // The entire window is called the stage

import java.io.IOException;


// layout -> scene -> window

// EventHandler<ActionEvent> is not needed when using lamba for event handling
public class Main extends Application {
    public Main() throws IOException {}

    private User user;
    private static Game theGame;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        theGame = game;

        /*
            User user = game.authenticateUser();
            game.start(user);
            game.end(user); // This won't be user here
        */
        launch(args);
    }

    // Save the game files to json file
    private void saveGame(Stage window) throws IOException {
        theGame.end(user);
    }

    private void closeProgram(Stage window) {
        window.close();
    }


    // Our main javafx code
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;
        window.setTitle("Fantasy Football");

        // IRRELEVANT
        Button alertBox = new Button("Alert Box!!!");
        alertBox.setOnAction(event -> AlertBox.display("The alert box", "Close me dumbass"));

        LoginWindow.setScene();
        StartWindow.display(LoginWindow.getScene(), window);

        // Called when the close button on the window is clicked
        window.setOnCloseRequest(event -> {
            System.out.println("Game is saved and closed");
            try { saveGame(window); }
            catch (IOException e) { e.printStackTrace(); }
            closeProgram(window);
        });

    }

}
