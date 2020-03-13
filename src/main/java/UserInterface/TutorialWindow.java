package UserInterface;

import GameLogic.HandleApi;
import GameLogic.MarketPlace;
import GameLogic.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.List;
import java.util.function.*;

public class TutorialWindow {
    private static Scene tutorialScene;
    private static Stage window = new Stage();

    public static void display() {
        // Login.setOnAction(event -> window.setScene()); // Authenticate User

        window.setWidth(200);
        window.setScene(tutorialScene);
        window.show();
    }

    public static ChoiceBox<String> constructPlayerChoices(List<Player> players, int position) {
        ChoiceBox<String> playerChoices = new ChoiceBox<>();
        for (Player player: players) {
            if (player.getPosition() == position)
                playerChoices.getItems().add(player.getFullName() + " C: " + player.getCost());
        }
        return playerChoices;
    }

    public static void setScene() throws IOException {
        MarketPlace marketPlace = HandleApi.getJsonObject();
        List<Player> players = marketPlace.getPlayers();

        Label info = new Label("Welcome to the tutorial.\nPlease give your team a name and select 11 players");

        TextField teamName = new TextField(); teamName.setPromptText("team name");
        Button submit = new Button("Submit");

        Label GK = new Label("Select 1 GK");
        ChoiceBox<String> goalkeeperChoices = constructPlayerChoices(players, 1);
        Label DEF = new Label("Select 4 DEF");
        ChoiceBox<String> defenderChoices = constructPlayerChoices(players, 2);
        Label MID = new Label("Select 3 MID");
        ChoiceBox<String> midfielderChoices = constructPlayerChoices(players, 3);
        Label FOR = new Label("Select 3 FOR");
        ChoiceBox<String> forwardChoices = constructPlayerChoices(players, 4);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER); grid.setVgap(10);
        GridPane.setConstraints(GK, 0, 0);
        GridPane.setConstraints(goalkeeperChoices, 0, 1);
        GridPane.setConstraints(DEF, 0, 2);
        GridPane.setConstraints(defenderChoices, 0, 3);
        GridPane.setConstraints(MID, 0, 4);
        GridPane.setConstraints(midfielderChoices, 0, 5);
        GridPane.setConstraints(FOR, 0, 6);
        GridPane.setConstraints(forwardChoices, 0, 7);
        GridPane.setConstraints(submit, 0, 8);

        BorderPane border = new BorderPane();
        grid.getChildren().addAll(
                GK, goalkeeperChoices, DEF, defenderChoices, MID, midfielderChoices, FOR, forwardChoices, submit
        );

        border.setTop(info);
        border.setCenter(grid);

        // If all conditions are met, submit info
        submit.setOnAction(event -> {

        });
        tutorialScene = new Scene(border, 500, 500);
    }


    public static Scene getScene() {
        return tutorialScene;
    }
}
