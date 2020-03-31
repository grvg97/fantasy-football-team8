package UserInterface;

import GameLogic.Player;
import javafx.scene.control.Alert;


/* This class handles the restrictions of the game. */
public class HandleError {

    private HandleError() {} // Hides the implicit constructor

    // Function to construct errors by passing the header and the content
    public static void errorMessage(String headerText, String contentText) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText(headerText);
        error.setContentText(contentText);
        error.showAndWait();
    }

    // This function is an exception and it is used in only one place
    public static void infoMessage(String headerText, String contentText) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeight(200.0);
        info.setHeaderText(headerText);
        info.setContentText(contentText);
        info.showAndWait();
    }
}
