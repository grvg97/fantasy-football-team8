package UserInterface;

import GameLogic.Player;
import GameLogic.Positions;
import javafx.scene.control.Alert;


/* This class handles the restrictions of the game. */
public class HandleError {

    private HandleError() {} // Hides the implicit constructor

    // Function to construct errors by passing the header and the content
    private static void constructError(String headerText, String contentText) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText(headerText);
        error.setContentText(contentText);
        error.showAndWait();
    }

    // Team size must be at least 11 in order for the team to be created and saved to database
    public static void teamSize(int teamSize) {
        constructError("Team size must be at least 11!",
                "The size of the team is " + teamSize);
    }

    // Player can't be added to team without team existing.
    public static void teamMustExistence() {
        constructError("Team must be created!",
                "User must first create team in order\nto add players to that team.");

    }

    // Team name can't be left blank. Team won't be created
    public static void teamNameBlank() {
        constructError("Team name can't be left blank!",
                "Please give your team a name");

    }

    // This function is used in TutorialWindow. It restricts the players from specific positions according to 4-3-3
    public static void formationRestriction(Positions position, int positionCount) {
        constructError("Formation Restriction!",
                "The position " + position + " must have exactly " + positionCount + " players.");

    }

    // Used in Transfer Window
    public static void generalFormationRestriction() {
        constructError("General Formation Restriction!",
                "The formation must be 4-3-3");
    }

    // Username or Password can't be left blank
    public static void signUpRestriction() {
        constructError("Invalid Username or Password!",
                "Username or Password can not be left blank");
    }

    // Passwords must match in the SignUpWindow
    public static void passwordMismatch() {
        constructError("Password Mismatch!",
                "The passwords you have typed don't match.\nPlease rewrite your passwords.");
    }

    // User can't add player to team if it already exists
    public static void playerExists(Player player) {
        constructError("Player Exists!",
                player.getFullName() + " already exists in the team.");
    }

    public static void actionNotAuthorized(String manager) {
        constructError("Not Authorized!",
                "You are not authorized to delete this league");

    }

    public static void maxNumPlayers() {
        constructError("Player Amount Exceeds!",
                "Maximum number of players reached");
    }

    public static void notEnoughCredits(int credits, String player) {
        constructError("Not enough credits!",
                credits + " credits left. Not enough to buy " + player);
    }

    public static void userDoesNotExist() {
        constructError("User Doesn't Exists!",
                "The following username or password is incorrect.");
    }
}
