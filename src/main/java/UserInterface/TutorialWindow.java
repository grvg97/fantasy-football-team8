package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.List;


public class TutorialWindow {
    private static Scene tutorialScene;

    private TutorialWindow() {}

    /* Constructs the ComboBox that contains players and displays them as strings */
    private static ComboBox<Player> constructPlayerBox(List<Player> players, Positions position) {
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


    /* Selects the players for the team. Wraps the 'constructPlayerBox' function. */
    private static ComboBox<Player> selectPlayers (
            User user, List<Player> players, Positions position, int positionCount, String Pos
    ) {
        ComboBox<Player> playerBox = constructPlayerBox(players, position);
        playerBox.setPromptText("Select " + positionCount + " " + Pos);


        playerBox.setOnAction(event -> {
            Player selectedPlayer = playerBox.getValue();

            // User can't buy player unless their is not team
            if (!user.hasTeam())
                HandleError.teamMustExistence();

            // User can't buy the player with the specific position if that position is full.
            else if (user.getTeamPositionCount(position) == positionCount)
                HandleError.formationRestriction(position, positionCount);

            // If player is already in team, user can't buy it
            else if (user.getTeamPlayers().contains(selectedPlayer))
                HandleError.playerExists(selectedPlayer);

            else
                user.buyPlayer(selectedPlayer);

        });
        return playerBox;
    }


    private static void addToDatabase(User user, League globalLeague) {
        Database.add(user);
        globalLeague.addUser(user);
    }

    /* Sets the scene of the view 'TutorialWindow' */
    public static void setScene(Stage window, User user) throws IOException {

        // Get the json object and the players from the player market
        List<Player> players = HandleApi.getJsonObject().getPlayers();

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
                selectPlayers(user, players, Positions.GK, Formation.GKCOUNT.getValue(), "GK");

        ComboBox<Player> DEFBox =
                selectPlayers(user, players, Positions.DEF, Formation.DEFCOUNT.getValue(), "DEF");

        ComboBox<Player> MIDBox =
                selectPlayers(user, players, Positions.MID, Formation.MIDCOUNT.getValue(), "MID");

        ComboBox<Player> FORBox =
                selectPlayers(user, players, Positions.FWD, Formation.FORCOUNT.getValue(), "FOR");


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
            // If the team size is larger than 11, user will be add
            // user can continue to the next scene of the game
            if (user.getTeamSize() >= 11) {
                addToDatabase(user, Database.getGlobalLeague());
                UserWindow.setScene(user);
                window.setScene(UserWindow.getScene());
            }
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
