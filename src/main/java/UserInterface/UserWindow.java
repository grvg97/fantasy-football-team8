package UserInterface;

import GameLogic.Database;
import GameLogic.League;
import GameLogic.Player;
import GameLogic.User;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;
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

    /*
     * This function allows the ListView to store 'Leagues'
     * objects and display them as strings
     */
    private static void setViewToLeagueName(ListView<League> leaguesView) {
        leaguesView.setCellFactory(param -> new ListCell<League>() {
            @Override
            protected void updateItem(League league, boolean empty) {
                super.updateItem(league, empty);

                if (empty || league == null || league.getName() == null) {
                    setText(null);
                } else {
                    setText(league.getName());
                }
            }
        });
    }


    private static ListView<League> constructLeagueView(List<League> leagues) {
        ListView<League> leagueListView = new ListView<>();
        for (League league: leagues) {
            leagueListView.getItems().add(league);
        }
        return leagueListView;
    }


    public static void setScene(User user) {

        ListView<Player> teamView = constructTeamView(user.getTeamPlayers());
        setViewToPlayerName(teamView);

        ListView<League> leagueView = constructLeagueView(Database.getInstance().getLeagues());
        setViewToLeagueName(leagueView);

        Label label = new Label("Welcome to your team and league page");
        Label username = new Label("Username: " + user.getUsername());
        Label teamName = new Label("Team name: " + user.getTeamName());

        Button playerInfo = new Button("Open player");
        Button leagueInfo = new Button("Open League");

        // Display the selected player's stats
        playerInfo.setOnAction(event -> {
            Player selectedPlayer = teamView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {

                PlayerWindow.setScene(selectedPlayer);
                Stage playerStage = new Stage();

                playerStage.setScene(PlayerWindow.getScene());
                playerStage.show();
            }

        });

        // Displays info about the selected league
        leagueInfo.setOnAction(event -> {
            League selectedLeague = leagueView.getSelectionModel().getSelectedItem();
            if (selectedLeague != null) {
                LeagueWindow.setScene(selectedLeague);

                Stage leagueStage = new Stage();
                leagueStage.setScene(LeagueWindow.getScene());
                leagueStage.show();
            }
        });


        VBox pageTop = new VBox(10);
        pageTop.getChildren().addAll(label, username, teamName, teamView, leagueView, leagueInfo, playerInfo);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
//        GridPane.setConstraints(teamView, 0, 0);
//        GridPane.setConstraints(leagueView, 1, 0);


        userScene = new Scene(pageTop);

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

    /* Private inner static class used to display the teams inside the league*/
    private static class LeagueWindow {

        private static Scene leagueScene;

        private LeagueWindow() {}

        private static ListView<String> constructLeagueInfoView(League league) {
            ListView<String> teamListView = new ListView<>();
            for (Map.Entry mapElement : league.getTeamPoints().entrySet()) {
                teamListView.getItems().add(
                    "Team name: " + mapElement.getKey() + " --- Points: " + mapElement.getValue()
                );
            }
            return teamListView;
        }


        public static void setScene(League league) {
            ListView<String> leagueInfoView = constructLeagueInfoView(league);
            Label label = new Label("League name: " + league.getName() + "\nLeague manager: " + league.getManager());

            VBox view = new VBox(20);
            view.getChildren().addAll(label, leagueInfoView);
            leagueScene = new Scene(view);
        }

        public static Scene getScene() {
            return leagueScene;
        }

    }
}
