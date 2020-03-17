package GameLogic;

import UserInterface.*;

import javafx.application.Application;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;  // The entire window is called the stage

import java.io.IOException;


/*
                           /
StartWindow -> LoginWindow
*/


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

        // Create scenes
        UserWindow.setScene(primaryStage);
        SignUpWindow.setScene(primaryStage);
        TutorialWindow.setScene(primaryStage);
        LoginWindow.setScene(primaryStage);
        StartWindow.setScene(primaryStage);


        primaryStage.setTitle("Fantasy Football");
        primaryStage.setScene(StartWindow.getScene());
        primaryStage.show();

        // Called when the close button on the window is clicked
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Game is closed");
            closeProgram(primaryStage);
        });
    }

}
