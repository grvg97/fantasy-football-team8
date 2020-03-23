package GameLogic;

import UserInterface.*;

import javafx.application.Application;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;  // The entire window is called the stage

import javax.xml.crypto.Data;
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

        // Start -> SignUp -> Tutorial -> User
        // Start -> Login -> User

        // Initialize global league
        IOHandler.getInstance().init();

        primaryStage.setTitle("Fantasy Football");
        primaryStage.setScene(LoginWindow.getScene(primaryStage));
        primaryStage.show();


        // Called when the close button on the window is clicked
        primaryStage.setOnCloseRequest(event -> {
            closeProgram(primaryStage);
        });
    }

}
