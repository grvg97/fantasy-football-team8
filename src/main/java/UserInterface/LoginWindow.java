package UserInterface;

import GameLogic.IOHandler;
import GameLogic.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/* User can login using this window, or click the signUpButton to go to the SignUpWindow */
public class LoginWindow {
    private static Scene loginScene;

    public static void setScene(Stage window) {
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign up");

        Label label = new Label("Welcome to Fantasy Football.\nEnter username and password.");
        label.setStyle("-fx-font-size: 15px");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        usernameField.setMaxWidth(120);
        passwordField.setMaxWidth(120);
        usernameField.setPromptText("username");
        passwordField.setPromptText("password");

        loginButton.setMinWidth(120);
        signUpButton.setMinWidth(120);

        // Assigns the username and password to the attributes when button clicked
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Verifies username and password checking the database
            User user = IOHandler.getInstance().authUser(username, password);

            // Empty the text field after logging in,
            // so when logged out the text fields are empty
            usernameField.setText("");
            passwordField.setText("");

            if (user != null) {
                window.setScene(UserWindow.getScene(window, user));
            }
            else {
                HandleError.userDoesNotExist();
            }

        });

        signUpButton.setOnAction(event -> window.setScene(SignUpWindow.getScene(window)));

        // Construct the layout of the scene using the GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER); grid.setVgap(10);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(usernameField, 0, 2);
        GridPane.setConstraints(passwordField, 0, 3);
        GridPane.setConstraints(loginButton, 0, 4);
        GridPane.setConstraints(signUpButton, 0, 5);
        grid.getChildren().addAll(label, usernameField, passwordField, loginButton, signUpButton);

        loginScene = new Scene(grid);
    }

    public static Scene getScene(Stage window) {
        setScene(window);
        return loginScene;
    }

}
