package UserInterface;

import GameLogic.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.util.function.*;

public class SignUpWindow {

    private static String username;
    private static String password;
    private static Scene signupScene;
    private static User user;

    public static User setScene(Stage window) {
        Label label = new Label("Please enter a username and password");

        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        usernameField.setMaxWidth(150);
        passwordField.setMaxWidth(150);
        usernameField.setPromptText("username");
        passwordField.setPromptText("password");

        Button signupButton = new Button("Sign up");
        // Assigns the username and password to the attributes when button clicked
        signupButton.setOnAction(event -> {
            username = usernameField.getText();
            password = passwordField.getText();
            user = new User(username, password);
            System.out.println(user == null);
            window.setScene(TutorialWindow.getScene());
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
        GridPane.setConstraints(signupButton, 0, 3);
        GridPane.setConstraints(saveButton, 0, 4);

        grid.getChildren().addAll(label, usernameField, passwordField, signupButton, saveButton);

        signupScene = new Scene(grid, 500, 500);
        return user;
    }


    public static Scene getScene() {
        return signupScene;
    }
    public static User getUser() {
        return user;
    }

    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }

}
