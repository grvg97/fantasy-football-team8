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


    private static ComboBox<Player> constructPlayerBox(List<Player> players, int position) {
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


    private static ComboBox<Player> selectPlayers(User user, List<Player> players, int position, int positionCount, String Pos) {
        ComboBox<Player> playerBox = constructPlayerBox(players, position);
        playerBox.setPromptText("Select " + positionCount + " " + Pos);
        playerBox.setOnAction(event -> user.buyPlayer(playerBox.getValue()));

        return playerBox;
    }

    public static void setScene(Stage primaryStage) throws IOException {
        MarketPlace marketPlace = HandleApi.getJsonObject();
        List<Player> players = marketPlace.getPlayers();
        User user = new User(SignUpWindow.getUsername(), SignUpWindow.getPassword());

        Button nextButton = new Button("Next");
        Label info = new Label("Welcome to the tutorial.\nPlease select 11 players");

        TextField teamName = new TextField(); teamName.setPromptText("team name");
        Button submit = new Button("Submit");
        submit.setOnAction(event -> user.createTeam(teamName.getText())); // Initialize team

        ComboBox<Player> GKBox =
                selectPlayers(user, players, Positions.GK.getPositionVal(), Formation.GKCOUNT.getValue(), "GK");

        ComboBox<Player> DEFBox =
                selectPlayers(user, players, Positions.DEF.getPositionVal(), Formation.DEFCOUNT.getValue(), "DEF");

        ComboBox<Player> MIDBox =
                selectPlayers(user, players, Positions.MID.getPositionVal(), Formation.MIDCOUNT.getValue(), "MID");

        ComboBox<Player> FORBox =
                selectPlayers(user, players, Positions.FOR.getPositionVal(), Formation.FORCOUNT.getValue(), "FOR");


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER); grid.setVgap(10);

        GridPane.setConstraints(teamName, 0, 0);
        GridPane.setConstraints(submit, 0, 1);
        GridPane.setConstraints(GKBox, 0, 2);
        GridPane.setConstraints(DEFBox, 0, 3);
        GridPane.setConstraints(MIDBox, 0, 4);
        GridPane.setConstraints(FORBox, 0, 5);
        GridPane.setConstraints(nextButton, 0, 6);

        BorderPane border = new BorderPane();
        grid.getChildren().addAll(
                teamName, submit, GKBox, DEFBox, MIDBox, FORBox, nextButton
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
