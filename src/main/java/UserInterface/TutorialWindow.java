package UserInterface;

import GameLogic.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.List;
import java.util.function.*;

public class TutorialWindow {
    private static Scene tutorialScene;
    private static User user = SignUpWindow.getUser(); // TODO: This is null why????


    private static ComboBox<Player> constructPlayerChoices(List<Player> players, int position) {
        ComboBox<Player> playerChoices = new ComboBox<>();
        for (Player player: players) {
            if (player.getPosition() == position)
                playerChoices.getItems().add(player);
        }
        playerChoices.setConverter(new StringConverter<Player>() {
            @Override
            public String toString(Player object) {
                return object.getFullName();
            }

            @Override
            public Player fromString(String string) {
                return playerChoices.getItems().stream().filter(player ->
                        player.getFullName().equals(string)).findFirst().orElse(null);
            }
        });
        return playerChoices;
    }


    public static void setScene(Stage primaryStage) throws IOException {
        MarketPlace marketPlace = HandleApi.getJsonObject();
        List<Player> players = marketPlace.getPlayers();

        Button nextButton = new Button("Next");
        Label info = new Label("Welcome to the tutorial.\nPlease select 11 players");

        TextField teamName = new TextField(); teamName.setPromptText("team name");
        Button submit = new Button("Submit");
        submit.setOnAction(event -> user.createTeam(teamName.getText())); // Initialize team

        // TODO: Use ListView OR ComboBox instead of Choice box to select multiple elements with ObservableList
        ComboBox<Player> goalkeeperChoices = constructPlayerChoices(players, 1);
        goalkeeperChoices.setPromptText("Select " + SquadRestriction.GKCOUNT.getNumVal() + " GK");
        goalkeeperChoices.setOnAction(event -> System.out.println(user == null));

        ComboBox<Player> defenderChoices = constructPlayerChoices(players, 2);
        defenderChoices.setPromptText("Select " + SquadRestriction.DEFCOUNT.getNumVal() + " DEF");
        defenderChoices.setOnAction(event -> user.buyPlayer(defenderChoices.getValue()));

        ComboBox<Player> midfielderChoices = constructPlayerChoices(players, 3);
        midfielderChoices.setPromptText("Select " + SquadRestriction.MIDCOUNT.getNumVal() + " MID");
        midfielderChoices.setOnAction(event -> user.buyPlayer(midfielderChoices.getValue()));

        ComboBox<Player> forwardChoices = constructPlayerChoices(players, 4);
        forwardChoices.setPromptText("Select " + SquadRestriction.FORCOUNT.getNumVal() + " FOR");
        forwardChoices.setOnAction(event -> user.buyPlayer(forwardChoices.getValue()));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER); grid.setVgap(10);

        GridPane.setConstraints(teamName, 0, 0);
        GridPane.setConstraints(submit, 0, 1);
        GridPane.setConstraints(goalkeeperChoices, 0, 2);
        GridPane.setConstraints(defenderChoices, 0, 3);
        GridPane.setConstraints(midfielderChoices, 0, 4);
        GridPane.setConstraints(forwardChoices, 0, 5);
        GridPane.setConstraints(nextButton, 0, 6);

        BorderPane border = new BorderPane();
        grid.getChildren().addAll(
                teamName, submit, goalkeeperChoices, defenderChoices, midfielderChoices, forwardChoices, nextButton
        );

        border.setTop(info);
        border.setCenter(grid);

        // If all conditions are met, submit info
        nextButton.setOnAction(event -> {

        });

        // Create scene with the 'border' layout
        tutorialScene = new Scene(border, 500, 500);
    }


    public static Scene getScene() {
        return tutorialScene;
    }
}
