package UserInterface;

import GameLogic.Player;
import GameLogic.User;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class UserWindow {
    private static Scene userScene;


    /* Construct the ListView by adding the selected players */
    private static ListView<Player> constructTeamView(List<Player> players) {
        ListView<Player> view = new ListView<>();
        for (Player player: players) {
            view.getItems().add(player);
        }
        return view;
    }


    /*
     * This function allows the ListView to store 'Player'
     * objects and display them as strings
    */
    private static void setViewToPlayerName(ListView<Player> teamView) {
        teamView.setCellFactory(param -> new ListCell<Player>() {
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


    public static void setScene(User user) {

        ListView<Player> teamView = constructTeamView(user.getTeamPlayers());
        setViewToPlayerName(teamView);

        VBox layout = new VBox(20);
        Label label = new Label("Welcome to your team and league page");
        Label username = new Label("Username: " + user.getUsername());
        Label teamName = new Label("Team name: " + user.getTeamName());

        Button playerInfo = new Button("Open player");
        Button createLeague = new Button("Create League");

        playerInfo.setOnAction(event -> {
            Player selectedPlayer = teamView.getSelectionModel().getSelectedItem();
            PlayerWindow.setScene(selectedPlayer);

            Stage playerStage = new Stage();
            playerStage.setScene(PlayerWindow.getScene());
            playerStage.show();
        });

        createLeague.setOnAction(event -> {

        });

        layout.getChildren().addAll(label, username, teamName, teamView, playerInfo, createLeague);
        userScene = new Scene(layout);

        // All the leagues and the team of the user are displayed here
    }


    public static Scene getScene() {
        return userScene;
    }

    /* */
    private class TransferWindow {
        public void setScene() {

        }
    }
    private class LeaguesWindow {
        private Scene LeagueScene;
    }
}
