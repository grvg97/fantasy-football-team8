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
import java.util.function.*;

public class SignUpWindow {

    private static String username;
    private static String password;
    private static String password2;
    private static Scene signUpScene;


    public static void setScene(Stage window) {
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
            username = usernameField.getText();
            password = passwordField.getText();
            password2 = passwordField2.getText();

            // If username or password field is blank, don't enter the tutorial window scene
            if (username.equals("") || password.equals(""))
                HandleError.signUpRestriction();

            // Password mismatch is not allowed
            else if (!password.equals(password2))
                HandleError.passwordMismatch();

            // Passing the username and password after certain restrictions have been met
            else
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

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
