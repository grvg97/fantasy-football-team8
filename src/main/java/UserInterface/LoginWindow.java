package UserInterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.function.*;

public class LoginWindow {
    private static Scene loginScene;
    private static VBox layout = new VBox(20);;
    private static Stage window = new Stage();
    private static Button loginButton = new Button("Login");

    private static String username;
    private static String password;

    public static void display() {
        // Login.setOnAction(event -> window.setScene()); // Authenticate User

        window.setWidth(200);
        window.setScene(loginScene);
        window.show();
    }

    public static void setScene() {
        Label label = new Label("This is the login page");

        TextField usernameField = new TextField();
        TextField passwordField = new TextField();

        // Assigns the username and password to the attributes when button clicked
        loginButton.setOnAction(event -> {
            username = usernameField.getCharacters().toString();
            username = passwordField.getCharacters().toString();
        });

        Button saveButton = new Button("Save and exit");
        saveButton.setOnAction(event -> System.out.println("The game is saved"));

        usernameField.setMaxWidth(150);
        passwordField.setMaxWidth(150);
        layout.getChildren().addAll(label, loginButton, usernameField, passwordField, saveButton);
        layout.setAlignment(Pos.CENTER_LEFT);

        loginScene = new Scene(layout, 500, 500);
    }


    public static Scene getScene() {
        return loginScene;
    }

    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }

}
