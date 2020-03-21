package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
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


    /* Constructs the listView*/
    public static ListView<Player> constructPlayers(List<Player> players) {
        ListView<Player> playersView = new ListView<>();
        for (Player player: players) {
            playersView.getItems().add(player);
        }
        setListViewToString(playersView);
        return playersView;
    }


    /* Buy player if certain restrictions are met*/
    private static void buyPlayer(User user, Player selectedPlayer) {

        // If player is already in team, user can't buy it
        if (user.getTeamPlayers().contains(selectedPlayer))
            HandleError.playerExists(selectedPlayer);

        else
            user.buyPlayer(selectedPlayer);
    }


    /* Sells the player and adds the cost of credits to the user's credits*/
    private static void sellPlayer(User user, Player selectedPlayer) {
        user.sellPlayer(selectedPlayer);
    }


    private static boolean formationRestrictionMet(User user) {
        return user.getTeamPositionCount(Positions.GK) == Formation.GKCOUNT.getValue()
                && user.getTeamPositionCount(Positions.DEF) == Formation.DEFCOUNT.getValue()
                && user.getTeamPositionCount(Positions.MID) == Formation.MIDCOUNT.getValue()
                && user.getTeamPositionCount(Positions.FWD) == Formation.FORCOUNT.getValue();
    }


    /* Set the scene */
    public static void setScene(Stage window, User user) throws IOException {
        List<Player> players = HandleApi.getInstance().getJsonObject().getPlayers();

        ListView<Player> userTeamView = constructPlayers(user.getTeamPlayers());
        ListView<Player> playerMarketView = constructPlayers(players);

        Label transferLabel = new Label("The Transfer Window");

        Button buyButton = new Button("<< Buy");
        Button sellButton = new Button("Sell >>");
        Button seeTransferButton = new Button("See Transfer");
        Button backButton = new Button("Back");
        Button playerInfoButton = new Button("Open Player");

        /*
         * 'buyButton' buys the player based on restrictions
         * 'sellButton' sells the player and adds the credits
         * 'finishButton' finished transfer iff the transfer is done according to rules
         * 'backButton' Goes back
         */
        buyButton.setOnAction(event -> {
            Player selectedMarketPlayer = playerMarketView.getSelectionModel().getSelectedItem();
            if (selectedMarketPlayer != null)
                buyPlayer(user, selectedMarketPlayer);
        });

        sellButton.setOnAction(event -> {
            Player selectedTeamPlayer = userTeamView.getSelectionModel().getSelectedItem();
            if (selectedTeamPlayer != null)
                sellPlayer(user, selectedTeamPlayer);
        });

        seeTransferButton.setOnAction(event -> {
            // When we set the scene of the window again, we see the changes.
            try {
                setScene(window, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.setScene(transferScene);

        });

        backButton.setOnAction(event -> {
            if (formationRestrictionMet(user))
                window.setScene(UserWindow.getScene());
            else
                HandleError.generalFormationRestriction();
        });

        playerInfoButton.setOnAction(event -> {
            Player selectedMarketPlayer = playerMarketView.getSelectionModel().getSelectedItem();
            if (selectedMarketPlayer != null)
                PlayerWindow.display(selectedMarketPlayer);
        });


        // Construct the layout using GridPane
        GridPane grid = new GridPane(); grid.setPadding(new Insets(10,10,10,10));
        GridPane.setConstraints(transferLabel, 1, 0);
        GridPane.setConstraints(userTeamView, 0, 1);
        GridPane.setConstraints(buyButton, 1, 2);
        GridPane.setConstraints(sellButton, 1, 3);
        GridPane.setConstraints(playerMarketView, 2, 1);
        GridPane.setConstraints(seeTransferButton, 3,  3);
        GridPane.setConstraints(backButton, 3, 4);
        GridPane.setConstraints(playerInfoButton, 2, 2);

        grid.getChildren().addAll(
                transferLabel, userTeamView, buyButton, sellButton,
                playerMarketView, seeTransferButton, backButton,
                playerInfoButton
        );

        // Set the current constructed layout to the transfer scene
        transferScene = new Scene(grid, 800, 800);
    }


    public static void display(User user) throws IOException {
        Stage window = new Stage();
        setScene(window, user);

        window.setScene(transferScene);
        window.show();

    }
    public static Scene getScene() {
        return transferScene;
    }
}
