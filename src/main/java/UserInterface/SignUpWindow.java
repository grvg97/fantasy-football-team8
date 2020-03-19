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

            // If username or password field is blank, don't enter the tutorial window scene
            if (username[0].equals("") || password[0].equals(""))
                HandleError.signUpRestriction();

            // Password mismatch is not allowed
            else if (!password[0].equals(password2[0]))
                HandleError.passwordMismatch();

            // after certain restrictions have been met User is created.
            else {
                User user = new User(username[0], password[0]);

                try { TutorialWindow.setScene(window, user); }
                catch (IOException e) { e.printStackTrace(); }

                window.setScene(TutorialWindow.getScene());
            }
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
        GridPane.setConstraints(passwordField2, 0, 3);
        GridPane.setConstraints(signupButton, 0, 4);
        GridPane.setConstraints(saveButton, 0, 5);

        grid.getChildren().addAll(
                label, usernameField, passwordField, passwordField2, signupButton, saveButton
        );

        signUpScene = new Scene(grid, 500, 500);
    }


    public static Scene getScene() {
        return signUpScene;
    }

}
