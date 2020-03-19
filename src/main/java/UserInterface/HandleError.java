package UserInterface;

import GameLogic.Player;
import GameLogic.Positions;
import javafx.scene.control.Alert;


// This class handles the restrictions of the game.
public class HandleError {

    private HandleError() {} // Hides the implicit constructor

    // Team size must be at least 11 in order for the team to be created and saved to database
    public static void teamSize(int teamSize) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team size must be at least 11!");
        error.setContentText("The size of the team is " + teamSize);
        error.showAndWait();
    }

    // Player can't be added to team without team existing.
    public static void teamMustExistence() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team must be created!");
        error.setContentText("User must first create team in order\nto add players to that team.");
        error.showAndWait();
    }

    // Team name can't be left blank. Team won't be created
    public static void teamNameBlank() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team name can't be left blank!");
        error.setContentText("Please give your team a name");
        error.showAndWait();
    }

    // This function is used in TutorialWindow. It restricts the players from specific positions according to 4-3-3
    public static void formationRestriction(Positions position, int positionCount) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Formation Restriction!");
        error.setContentText("The position " + position + " must have exactly " + positionCount + " players.");
        error.showAndWait();
    }

    // Username or Password can't be left blank
    public static void signUpRestriction() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Invalid Username or Password!");
        error.setContentText("Username or Password can not be left blank");
        error.showAndWait();
    }

    // Passwords must match in the SignUpWindow
    public static void passwordMismatch() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Password Mismatch!");
        error.setContentText("The passwords you have typed don't match.\nPlease rewrite your passwords.");
        error.showAndWait();
    }

    // User can't add player to team if it already exists
    public static void playerExists(Player player) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Player Exists!");
        error.setContentText(player.getFullName() + " already exists in the team.");
        error.showAndWait();
    }

}
