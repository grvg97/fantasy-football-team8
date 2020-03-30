package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TransferWindow {
    private static Scene transferScene;


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

    /* Constructs the listView*/
    private static ListView<Player> constructPlayers(List<Player> players, Double height) {
        ListView<Player> playersView = new ListView<>();
        playersView.setMaxHeight(height);

        Iterator<Player> it = players.iterator();
        while (it.hasNext())
            playersView.getItems().add(it.next());

        // Sets the view of the list from player objects the player name's strings
        setListViewToString(playersView);
        return playersView;
    }


    /* Sells the player and adds the cost of credits to the user's credits*/
    private static void sellPlayer(User user, Player selectedPlayer) {
        user.sellPlayer(selectedPlayer);
    }


    private static boolean formationRestrictionMet(User user) {
        return user.getTeamPositionCount(Positions.GK) == Formation.GKCOUNT.getValue()
                && user.getTeamPositionCount(Positions.DEF) == Formation.DEFCOUNT.getValue()
                && user.getTeamPositionCount(Positions.MID) == Formation.MIDCOUNT.getValue()
                && user.getTeamPositionCount(Positions.FWD) == Formation.FWDCOUNT.getValue();
    }


    /* Set the scene */
    public static void setScene(Stage window, User user) throws IOException {
        List<Player> players = HandleApi.getJsonObject().getPlayers();

        ListView<Player> userTeamView = constructPlayers(user.getTeamStarters(), 300.0);
        ListView<Player> userBenchView = constructPlayers(user.getTeamBench(), 100.0);
        ListView<Player> playerMarketView = constructPlayers(players, 400.0);

        Button buyButton = new Button("<< Buy");
        Button sellButton = new Button("Sell >>");
        Button backButton = new Button("Back");
        Button playerInfoButton = new Button("Open Player");
        Label creditLabel = new Label("Credits = " + user.getCredits());

        // ComboBox is used to filter the players
        ComboBox<String> playerFilterBox = new ComboBox<>();
        playerFilterBox.getItems().addAll("All", "GK", "DEF", "MID", "FWD");
        playerFilterBox.getSelectionModel().selectFirst();

        playerFilterBox.setOnAction(event -> filterSelection(players, playerFilterBox, playerMarketView));


        /*
         * 'buyButton' buys the player based on restrictions
         * 'sellButton' sells the player and adds the credits
         * 'finishButton' finished transfer iff the transfer is done according to rules
         * 'backButton' Goes back
         */
        buyButton.setOnAction(event -> {
            Player selectedMarketPlayer = playerMarketView.getSelectionModel().getSelectedItem();

            if (selectedMarketPlayer != null) {
                if (user.buyPlayer(selectedMarketPlayer)) {
                    if (user.getTeamStarters().size() >= 11) {
                        userBenchView.getItems().addAll(selectedMarketPlayer);
                        userBenchView.refresh();
                    }
                    else {
                        userTeamView.getItems().add(selectedMarketPlayer);
                        userTeamView.refresh();
                    }
                    setListViewToString(userTeamView);
                    setListViewToString(userBenchView);

                    creditLabel.setText("Credits = " + user.getCredits());
                }
            }

        });

        sellButton.setOnAction(event -> {
            Player starterPlayer = userTeamView.getSelectionModel().getSelectedItem();
            Player benchPlayer = userBenchView.getSelectionModel().getSelectedItem();

            // Sell the starter player and make benchPlayer null to also not sell it
            if (starterPlayer != null) {
                benchPlayer = null;
                sellPlayer(user, starterPlayer);

                userTeamView.getItems().remove(starterPlayer);
                userTeamView.refresh();
                setListViewToString(userTeamView);

                creditLabel.setText("Credits = " + user.getCredits());
            }

            // Sell the bench player and make startPlayer null to also not sell it
            else if (benchPlayer != null) {
                starterPlayer = null;
                sellPlayer(user, benchPlayer);

                userBenchView.getItems().remove(benchPlayer);
                userBenchView.refresh();
                setListViewToString(userBenchView);

                creditLabel.setText("Credits = " + user.getCredits());
            }
        });

        backButton.setOnAction(event -> {
            if (formationRestrictionMet(user)) {
                User refreshedUser = IOHandler.getInstance().getUser(user.getId());
                window.setScene(UserWindow.getScene(window, refreshedUser));
            }
            else
                HandleError.errorMessage("Formation Restriction!",
                        "The formation must be 4-3-3.");
        });

        playerInfoButton.setOnAction(event -> {
            Player selectedMarketPlayer = playerMarketView.getSelectionModel().getSelectedItem();
            if (selectedMarketPlayer != null)
                PlayerWindow.display(selectedMarketPlayer);
        });

        VBox teamVBox = new VBox(5);
        teamVBox.getChildren().addAll(creditLabel, userTeamView, userBenchView);

        VBox marketVBox = new VBox(5);
        marketVBox.getChildren().addAll(playerFilterBox, playerMarketView);

        VBox operationButtons = new VBox(5); operationButtons.setAlignment(Pos.CENTER);
        operationButtons.getChildren().addAll(buyButton, sellButton);

        HBox viewsBox = new HBox(5);
        viewsBox.getChildren().addAll(teamVBox, operationButtons, marketVBox);

        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(playerInfoButton, backButton);

        // Construct the layout using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10)); grid.setVgap(10);

        GridPane.setConstraints(viewsBox, 0, 0);
        GridPane.setConstraints(buttonBox, 0, 1);

        grid.getChildren().addAll(viewsBox, buttonBox);

        // Set the current constructed layout to the transfer scene
        transferScene = new Scene(grid);
    }


    public static Scene getScene(Stage window, User user) throws IOException {
        setScene(window, user);
        return transferScene;
    }
}