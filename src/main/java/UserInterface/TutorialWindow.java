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

    // Constructs the ComboBox that contains players and displays them as strings
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

    // Selects the players for the team. Wraps the 'constructPlayerBox' function.
    private static ComboBox<Player> selectPlayers (
            User user, List<Player> players, int position, int positionCount, String Pos
    ) {
        ComboBox<Player> playerBox = constructPlayerBox(players, position);
        playerBox.setPromptText("Select " + positionCount + " " + Pos);

        // User can't buy player unless their is not team
        // User can't buy the player with the specific position if that position is full.
        playerBox.setOnAction(event -> {

            if (!user.hasTeam())
                HandleError.teamMustExistence();

            else if (user.getTeamPositionCount(position) == positionCount)
                HandleError.formationRestriction(position, positionCount);

            else
                user.buyPlayer(playerBox.getValue());

        });
        return playerBox;
    }


    // Sets the scene of the view 'TutorialWindow'
    public static void setScene(Stage window) throws IOException {

        // Get the json object and pass it to playerMarket
        PlayerMarket playerMarket = HandleApi.getJsonObject();
        List<Player> players = playerMarket.getPlayers();
        User user = new User(SignUpWindow.getUsername(), SignUpWindow.getPassword());

        // Create the button and label
        Button nextButton = new Button("Next");
        Label info = new Label("Welcome to the tutorial. In this part of the game you will construct your team.");
        info.setAlignment(Pos.CENTER);
        TextField teamName = new TextField(); teamName.setPromptText("team name");

        // Don't accept the team name if it's blank
        Button createTeamButton = new Button("Create Team");
        createTeamButton.setOnAction(event -> {

            if (teamName.getText().equals(""))
                HandleError.teamNameBlank();
            else
                user.createTeam(teamName.getText());
        });

        // Construct team
        ComboBox<Player> GKBox =
                selectPlayers(user, players, Positions.GK.getPositionVal(), Formation.GKCOUNT.getValue(), "GK");

        ComboBox<Player> DEFBox =
                selectPlayers(user, players, Positions.DEF.getPositionVal(), Formation.DEFCOUNT.getValue(), "DEF");

        ComboBox<Player> MIDBox =
                selectPlayers(user, players, Positions.MID.getPositionVal(), Formation.MIDCOUNT.getValue(), "MID");

        ComboBox<Player> FORBox =
                selectPlayers(user, players, Positions.FOR.getPositionVal(), Formation.FORCOUNT.getValue(), "FOR");


        // Create grid and add the elements to the grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER); grid.setVgap(15);
        GridPane.setConstraints(teamName, 0, 0);
        GridPane.setConstraints(createTeamButton, 0, 1);
        GridPane.setConstraints(GKBox, 0, 3);
        GridPane.setConstraints(DEFBox, 0, 4);
        GridPane.setConstraints(MIDBox, 0, 5);
        GridPane.setConstraints(FORBox, 0, 6);
        GridPane.setConstraints(nextButton, 0, 7);

        grid.getChildren().addAll(
                teamName, createTeamButton, GKBox, DEFBox, MIDBox, FORBox, nextButton
        );

        // Set the configuration of the border
        BorderPane border = new BorderPane();
        border.setTop(info);
        border.setCenter(grid);

        // If all conditions are met, submit info
        nextButton.setOnAction(event -> {
            // TODO: save user to database

            // If the team size is larger than 11, user can continue to the next scene of the game
            if (user.getTeamSize() >= 11)
                window.setScene(UserWindow.getScene());
            else
                HandleError.teamSize(user.getTeamSize());
        });

        // Create scene with the 'border' layout
        tutorialScene = new Scene(border, 500, 500);
    }


    public static Scene getScene() {
        return tutorialScene;
    }
}
