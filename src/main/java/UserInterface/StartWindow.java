package UserInterface;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;  // The entire window is called the stage
import javafx.scene.layout.*; // Layout is how you want everything arranged on your screen


public class StartWindow {
    private static Scene startScene;

    public static void setScene(Stage window) {
        Label label = new Label("Welcome to Fantasy Football");
        label.setStyle("-fx-font: 20px Serif");
        VBox layout = new VBox(20);
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");

        loginButton.setOnAction(event -> window.setScene(LoginWindow.getScene()));
        signUpButton.setOnAction(event -> window.setScene(SignUpWindow.getScene()));
        loginButton.setMinWidth(120);
        signUpButton.setMinWidth(120);

        layout.getChildren().addAll(label, loginButton, signUpButton);
        layout.setAlignment(Pos.CENTER);
        startScene = new Scene(layout, 500, 500);
    }


    public static Scene getScene() {
        return startScene;
    }
}
