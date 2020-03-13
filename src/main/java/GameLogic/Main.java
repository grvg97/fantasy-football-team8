package GameLogic;

import UserInterface.AlertBox;
import UserInterface.LoginWindow;
import UserInterface.StartWindow;

import UserInterface.TutorialWindow;
import javafx.application.Application;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.stage.Stage;  // The entire window is called the stage

import java.io.IOException;


// layout -> scene -> window

// EventHandler<ActionEvent> is not needed when using lamba for event handling
public class Main extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }


    private void closeProgram(Stage window) {
        window.close();
    }


    // Our main javafx code
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Fantasy Football");


        TutorialWindow.setScene();
        LoginWindow.setScene(TutorialWindow.getScene(), primaryStage);
        StartWindow.display(LoginWindow.getScene(), primaryStage);

        // Called when the close button on the window is clicked
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Game is saved and closed");
            closeProgram(primaryStage);
        });

    }

}
