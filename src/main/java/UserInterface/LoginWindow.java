package UserInterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.function.*;

public class LoginWindow {
    private static Scene loginScene;
    private static Button loginButton = new Button("Login");

    private static String username;
    private static String password;


    public static void setScene(Scene tutorialScene, Stage window) {
        Label label = new Label("This is the login page");

        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        usernameField.setMaxWidth(150);
        passwordField.setMaxWidth(150);
        usernameField.setPromptText("username");
        passwordField.setPromptText("password");

        // Assigns the username and password to the attributes when button clicked
        loginButton.setOnAction(event -> {
            username = usernameField.getText();
            password = passwordField.getText();
            window.setScene(tutorialScene);
        });

        Button saveButton = new Button("Save and exit");
        saveButton.setOnAction(event -> System.out.println("The game is saved"));

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(usernameField, 0, 1);
        GridPane.setConstraints(passwordField, 0, 2);
        GridPane.setConstraints(loginButton, 0, 3);
        GridPane.setConstraints(saveButton, 0, 4);

        grid.getChildren().addAll(label, usernameField, passwordField, loginButton, saveButton);

        loginScene = new Scene(grid, 500, 500);
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
