package UserInterface;

import javafx.scene.control.Alert;

public class HandleError {
    public static void handleTeamSize(int teamSize) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team size must be at least 11!");
        error.setContentText("The size of the team is " + teamSize);
        error.showAndWait();
    }

    public static void handleTeamExistence() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team must be created!");
        error.setContentText("User must first create team in order to add players to that team.");
        error.showAndWait();
    }

    public static void handleTeamName() {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setHeaderText("Team name can't be left blank!");
        error.setContentText("Please give your team a name");
        error.showAndWait();
    }
}
