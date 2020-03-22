package UserInterface;

import GameLogic.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.function.*;

public class SignUpWindow {

    private static Scene signUpScene;


    private static boolean restrictionsMet(String username, String password, String password2) {
        // If username or password field is blank, don't enter the tutorial window scene
        if (username.equals("") || password.equals("")) {
            HandleError.signUpRestriction();
            return false;
        }

        // Password mismatch is not allowed
        else if (!password.equals(password2)) {
            HandleError.passwordMismatch();
            return false;
        }
        return true;
    }


    public static void setScene(Stage window) {

        final String[] username = new String[1];
        final String[] password = new String[1];
        final String[] password2 = new String[1];

        Label label = new Label("Please enter a username and password");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField passwordField2 = new PasswordField();

        usernameField.setMaxWidth(150); usernameField.setPromptText("username");
        passwordField.setMaxWidth(150); passwordField.setPromptText("password");
        passwordField2.setMaxWidth(150); passwordField2.setPromptText("password");

        Button signupButton = new Button("Sign up");

        // Assigns the username and password to the attributes when button clicked
        signupButton.setOnAction(event -> {
            username[0] = usernameField.getText();
            password[0] = passwordField.getText();
            password2[0] = passwordField2.getText();

            if (restrictionsMet(username[0], password[0], password2[0])) {
                User user = new User(username[0], password[0]);
                try { TutorialWindow.setScene(window, user); }
                catch (IOException e) { e.printStackTrace(); }

                window.setScene(TutorialWindow.getScene());
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(usernameField, 0, 1);
        GridPane.setConstraints(passwordField, 0, 2);
        GridPane.setConstraints(passwordField2, 0, 3);
        GridPane.setConstraints(signupButton, 0, 4);

        grid.getChildren().addAll(
                label, usernameField, passwordField, passwordField2, signupButton
        );

        signUpScene = new Scene(grid, 500, 500);
    }


    public static Scene getScene() {
        return signUpScene;
    }

}
