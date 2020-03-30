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
    private static ListView<Player> constructPlayers(List<Player> players, Double height) {
        ListView<Player> playersView = new ListView<>();
        Iterator<Player> it = players.iterator();
        playersView.setMaxHeight(height);

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
                && user.getTeamPositionCount(Positions.FWD) == Formation.FWDCOUNT.getValue();
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
        List<Player> players = HandleApi.getJsonObject().getPlayers();

        ListView<Player> userTeamView = new ListView<>(); userTeamView.setMaxHeight(300.0);
        ListView<Player> userBenchView = new ListView<>(); userBenchView.setMaxHeight(100.0);
        ListView<Player> playerMarketView = constructPlayers(players, 400.0);

        Label creditLabel = new Label("Credits = " + user.getCredits());
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
                HandleError.errorMessage("Give Team Name!",
                        "Please give your team a name");
            else if (user.hasTeam())
                HandleError.errorMessage("Team Exists!",
                        "You already have a team.");
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
                HandleError.errorMessage("Team Must Exist!",
                        "You must have a team to buy players");

            // Buy the player and refresh the ListView to see the changes
            else {
                if (selectedPlayer != null && user.buyPlayer(selectedPlayer))
                {
                    if (user.getTeamSize() > 11) {
                        userBenchView.getItems().add(selectedPlayer);
                        userBenchView.refresh();
                    }
                    else {
                        userTeamView.getItems().add(selectedPlayer);
                        userTeamView.refresh();
                    }
                    setListViewToString(userTeamView);
                    setListViewToString(userBenchView);

                    creditLabel.setText("Credits = " + user.getCredits());
                }
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
                HandleError.errorMessage("Formation Restriction!",
                        "The formation must be 4-3-3.");
        });

        /* Layout construction using VBox, HBox, GridPane */

        HBox teamHBox = new HBox(10);
        teamHBox.getChildren().addAll(teamName, createTeamButton);

        VBox teamVBox = new VBox(5);
        teamVBox.getChildren().addAll(teamHBox, userTeamView, userBenchView, creditLabel);

        HBox marketButtons = new HBox(5);
        marketButtons.getChildren().addAll(playerInfoButton, nextButton);

        VBox marketVBox = new VBox(5);
        marketVBox.getChildren().addAll(playerFilterBox, playerMarketView, marketButtons);

        VBox operationButtons = new VBox(5); operationButtons.setAlignment(Pos.CENTER);
        operationButtons.getChildren().addAll(buyButton, sellButton);

        HBox layout = new HBox(5);
        layout.getChildren().addAll(teamVBox, operationButtons, marketVBox);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.getChildren().addAll(layout);

        // Set the current constructed layout to the tutorialScene
        tutorialScene = new Scene(grid);
    }


    public static Scene getScene(Stage window, User user) throws IOException {
        setScene(window, user);
        return tutorialScene;
    }
}
