package UserInterface;

import GameLogic.Database;
import GameLogic.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.function.*;

public class LoginWindow {
    private static Scene loginScene;


    public static void setScene(Stage window) {
        Button loginButton = new Button("Login");
        Button signupButton = new Button("Sign up");

        Label label = new Label("Welcome to Fantasy Football.\nEnter username and password.");
        label.setStyle("-fx-font-size: 15px");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        usernameField.setMaxWidth(120);
        passwordField.setMaxWidth(120);
        usernameField.setPromptText("username");
        passwordField.setPromptText("password");

        loginButton.setMinWidth(120);
        signupButton.setMinWidth(120);

        // Assigns the username and password to the attributes when button clicked
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // TODO: verify username and password checking the database
            User user = Database.getInstance().authUser(username, password);
            UserWindow.setScene(window, user);

            // if username and password exists in userDatabase
            window.setScene(UserWindow.getScene()); // UserWindow
        });

        signupButton.setOnAction(event -> window.setScene(SignUpWindow.getScene()));

        // Construct the layout of the scene using the GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER); grid.setVgap(10);
        GridPane.setConstraints(label, 0, 0);
        GridPane.setConstraints(usernameField, 0, 2);
        GridPane.setConstraints(passwordField, 0, 3);
        GridPane.setConstraints(loginButton, 0, 4);
        GridPane.setConstraints(signupButton, 0, 5);
        grid.getChildren().addAll(label, usernameField, passwordField, loginButton, signupButton);

        loginScene = new Scene(grid, 500, 500);
    }


    public static Scene getScene() {
        return loginScene;
    }

}
