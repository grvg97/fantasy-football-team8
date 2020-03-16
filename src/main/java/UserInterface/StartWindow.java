package UserInterface;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;  // The entire window is called the stage
import javafx.scene.layout.*; // Layout is how you want everything arranged on your screen

public class StartWindow {
    private static Scene startScene;
    private static VBox layout = new VBox(20);
    private static Button loginButton = new Button("Login");
    private static Button signUpButton = new Button("Sign Up");


    public static void setScene(Stage primaryStage) {
        loginButton.setOnAction(event -> primaryStage.setScene(LoginWindow.getScene()));
        signUpButton.setOnAction(event -> primaryStage.setScene(SignUpWindow.getScene()));

        Label label = new Label("Welcome to Fantasy football");
        layout.getChildren().addAll(label, loginButton, signUpButton);
        layout.setAlignment(Pos.CENTER);
        startScene = new Scene(layout, 500, 500);
    }

    public static Scene getScene() {
        return startScene;
    }


}
