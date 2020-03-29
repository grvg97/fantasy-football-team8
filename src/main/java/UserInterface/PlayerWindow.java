package UserInterface;

import GameLogic.Player;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


/* This window displays the stats and information about the player*/
public class PlayerWindow {

    private static Scene PlayerScene;

    private static ListView<String> constructStatView(HashMap<String, Integer> stats) {
        ListView<String> statsView = new ListView<>();
        for (Map.Entry statElem : stats.entrySet()) {
            statsView.getItems().add(statElem.getKey() + ": " + statElem.getValue());
        }
        return statsView;
    }

    public static void setScene(Player player) {
        HashMap<String, Integer> stats =  player.getStats();
        ListView<String> statView = constructStatView(stats);
        Label label = new Label("Cost: " + player.getCost() + "\nInjured: " + player.isInjured() +
                "\nIS CAPTAIN: " + player.isCaptain() + "\n IS VICE CAPTAIN: " + player.isViceCaptain());

        VBox view = new VBox(20);
        view.getChildren().addAll(label, statView);
        PlayerScene = new Scene(view);
    }

    public static void display(Player selectedPlayer) {
        Stage playerStage = new Stage();
        PlayerWindow.setScene(selectedPlayer);

        playerStage.setScene(PlayerScene);
        playerStage.show();
    }
}
