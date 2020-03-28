package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TutorialWindow {

    private static Scene tutorialScene;

    private static ListView<Player> userTeamView = new ListView<>();

    /*
     * This function allows the ListView to store 'Leagues'
     * objects and display them as strings
     */
    private static void setListViewToString(ListView<Player> playersView) {
        playersView.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player player, boolean empty) {
                super.updateItem(player, empty);

                if (empty || player == null || player.getFullName() == null) {
                    setText(null);
                } else {
                    setText(player.getPositionName() + " " + player.getFullName());
                }
            }
        });
    }


    /* Constructs the listView*/
    private static ListView<Player> constructPlayers(List<Player> players) {
        ListView<Player> playersView = new ListView<>();
        Iterator<Player> it = players.iterator();

        while (it.hasNext())
            playersView.getItems().add(it.next());

        // Sets the view of the list from player objects the player name's strings
        setListViewToString(playersView);
        return playersView;
    }


    private static boolean formationRestrictionMet(User user) {
        return user.getTeamPositionCount(Positions.GK) == Formation.GKCOUNT.getValue()
                && user.getTeamPositionCount(Positions.DEF) == Formation.DEFCOUNT.getValue()
                && user.getTeamPositionCount(Positions.MID) == Formation.MIDCOUNT.getValue()
                && user.getTeamPositionCount(Positions.FWD) == Formation.FORCOUNT.getValue();
    }

    /* Filters the players based on the selected position */
    private static void filterSelection(List<Player> players, ComboBox<String> cmbFilter, ListView<Player> lstLeftList) {
        switch (cmbFilter.getValue()){
            case "All":
                lstLeftList.getItems().clear();
                players.forEach(player -> lstLeftList.getItems().add(player));
                break;
            case "GK":
                lstLeftList.getItems().clear();
                players.forEach(player -> {
                    if (player.getPosition() == Positions.GK) {
                        lstLeftList.getItems().add(player);
                    }
                });
                break;
            case "DEF":
                lstLeftList.getItems().clear();
                players.forEach(player -> {
                    if (player.getPosition() == Positions.DEF) {
                        lstLeftList.getItems().add(player);
                    }
                });
                break;
            case "MID":
                lstLeftList.getItems().clear();
                players.forEach(player -> {
                    if (player.getPosition() == Positions.MID) {
                        lstLeftList.getItems().add(player);
                    }
                });
                break;
            case "FWD":
                lstLeftList.getItems().clear();
                players.forEach(player -> {
                    if (player.getPosition() == Positions.FWD) {
                        lstLeftList.getItems().add(player);
                    }
                });
                break;
        }
    }

    /* Set the scene */
    public static void setScene(Stage window, User user) throws IOException {
        List<Player> players = HandleApi.getInstance().getJsonObject().getPlayers();

        ListView<Player> playerMarketView = constructPlayers(players);
        Label creditLabel = new Label();
        creditLabel.setText("Credits = " + user.getCredits());

        Button buyButton = new Button("<< Buy");
        Button sellButton = new Button("Sell >>");
        Button playerInfoButton = new Button("Open Player");
        Button nextButton = new Button("Next");
        TextField teamName = new TextField(); teamName.setPromptText("team name");

        ComboBox<String> playerFilterBox = new ComboBox<>();
        playerFilterBox.getItems().addAll("All", "GK", "DEF", "MID", "FWD");
        playerFilterBox.getSelectionModel().selectFirst();

        playerFilterBox.setOnAction(event -> filterSelection(players, playerFilterBox, playerMarketView));

        // Don't accept the team name if it's blank
        Button createTeamButton = new Button("Create Team");
        createTeamButton.setOnAction(event ->
        {
            if (teamName.getText().equals(""))
                HandleError.textFieldBlank();
            else if (user.hasTeam())
                HandleError.errorMessage("Team Exists!", "You already have a team.");
            else
                user.createTeam(teamName.getText());
        });


        /*
         * 'buyButton' buys the player based on restrictions
         * 'sellButton' sells the player and adds the credits
         * 'finishButton' finished transfer iff the transfer is done according to rules
         * 'backButton' Goes back
         */
        buyButton.setOnAction(event -> {
            Player selectedPlayer = playerMarketView.getSelectionModel().getSelectedItem();

            // User can't buy player unless their is not team
            if (!user.hasTeam())
                HandleError.teamMustExist();

            // If player is already in team, user can't buy it
            else if (user.getTeamStarters().contains(selectedPlayer))
                HandleError.playerExists(selectedPlayer);

            // Buy the player and refresh the ListView to see the changes
            else {
                user.buyPlayer(selectedPlayer);
                userTeamView.getItems().add(selectedPlayer);
                userTeamView.refresh();
                setListViewToString(userTeamView);

                creditLabel.setText("Credits = " + user.getCredits());
            }

        });

        // Sell the player and refresh the ListView to see the changes
        sellButton.setOnAction(event -> {
            Player selectedPlayer = userTeamView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                user.sellPlayer(selectedPlayer);
                userTeamView.getItems().remove(selectedPlayer);
                userTeamView.refresh();

                creditLabel.setText("Credits = " + user.getCredits());
            }
        });


        playerInfoButton.setOnAction(event -> {
            Player selectedMarketPlayer = playerMarketView.getSelectionModel().getSelectedItem();
            if (selectedMarketPlayer != null)
                PlayerWindow.display(selectedMarketPlayer);
        });

        nextButton.setOnAction(event -> {
            if (formationRestrictionMet(user))
            {
                // Add user to database and global league after creation
                IOHandler handleIO = IOHandler.getInstance();
                handleIO.getGlobalLeague().addUser(user);
                handleIO.add(user);
                try {
                    handleIO.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                window.setScene(UserWindow.getScene(window, user));
            }
            else
                HandleError.generalFormationRestriction();
        });

        HBox teamHBox = new HBox(10);
        teamHBox.getChildren().addAll(teamName, createTeamButton);

        // Construct the layout using GridPane
        GridPane grid = new GridPane(); grid.setPadding(new Insets(10,10,10,10)); grid.setVgap(10);
        GridPane.setConstraints(teamHBox, 0, 0);
        GridPane.setConstraints(userTeamView, 0, 1);
        GridPane.setConstraints(creditLabel, 0, 2);
        GridPane.setConstraints(buyButton, 1, 2);
        GridPane.setConstraints(sellButton, 1, 3);
        GridPane.setConstraints(playerFilterBox, 2, 0);
        GridPane.setConstraints(playerMarketView, 2, 1);
        GridPane.setConstraints(playerInfoButton, 2, 2);
        GridPane.setConstraints(nextButton, 2, 3);


        grid.getChildren().addAll(
                userTeamView, buyButton, sellButton,
                playerMarketView, teamHBox,
                playerInfoButton, nextButton,
                creditLabel, playerFilterBox
        );

        // Set the current constructed layout to the tutorialScene
        tutorialScene = new Scene(grid);
    }


    public static Scene getScene(Stage window, User user) throws IOException {
        setScene(window, user);
        return tutorialScene;
    }
}
