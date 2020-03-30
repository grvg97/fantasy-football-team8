package UserInterface;

import GameLogic.Player;
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

    // Construct the error based on the message you want to give to the user
    public static void errorMessage(String header, String content) {
        constructError(header, content);
    }

}
