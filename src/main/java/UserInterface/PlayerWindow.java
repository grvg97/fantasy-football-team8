package UserInterface;

import GameLogic.Player;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

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
        Label label = new Label("Cost: " + player.getCost() + "\nInjured: " + player.isInjured());

        VBox view = new VBox(20);
        view.getChildren().addAll(label, statView);
        PlayerScene = new Scene(view);

    }
    public static Scene getScene() {
        return PlayerScene;
    }
}
