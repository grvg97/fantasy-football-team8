import UserInterface.AlertBox;
import UserInterface.LoginWindow;
import UserInterface.StartWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.stage.Stage;  // The entire window is called the stage


// layout -> scene -> window

// EventHandler<ActionEvent> is not needed when using lamba for event handling
public class Main extends Application {

    public static void main(String[] args) {
        /*
            Game game = new Game();
            User user = game.authenticateUser();
            game.start(user);
        */
        launch(args);
    }

    Stage window;
    Scene startScene, loginScene;

    // Our main javafx code
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Button alertBox = new Button("Alert Box!!!");
        alertBox.setOnAction(event -> AlertBox.display("The alert box", "Close me dumbass"));

        LoginWindow.setScene();
        StartWindow.display(LoginWindow.getScene());
    }

}
