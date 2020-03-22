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
import java.io.IOException;
import java.util.Map;
import java.util.List;


public class UserWindow {

    private static Scene userScene;
    private static User myUser;


    /* Construct the ListView by adding the selected players */
    private static ListView<Player> constructTeamView(List<Player> players) {
        ListView<Player> view = new ListView<>();
        for (Player player: players) {
            view.getItems().add(player);
        }
        setViewToPlayerName(view);
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
                }
                else {
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
        setViewToLeagueName(leagueListView);
        return leagueListView;
    }


    public static void setScene(Stage window, User user) {

        ListView<Player> teamView = constructTeamView(user.getTeamPlayers());
        ListView<League> leagueView = constructLeagueView(new Database().getLeagues());

        Label userLabel = new Label("Welcome to your team and league page");
        Label username = new Label("Username: " + user.getUsername());
        Label teamName = new Label("Team name: " + user.getTeamName());

        Button playerInfo = new Button("Open player");
        Button leagueInfo = new Button("Open League");
        Button tranferWindow = new Button("Transfer Window");

        // Display the selected player's stats
        playerInfo.setOnAction(event -> {
            Player selectedPlayer = teamView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                PlayerWindow.display(selectedPlayer);
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

        tranferWindow.setOnAction(event -> {
            try {
                TransferWindow.setScene(window, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
            window.setScene(TransferWindow.getScene());
        });


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(userLabel, 1, 0);
        GridPane.setConstraints(username, 0, 0);
        GridPane.setConstraints(teamName, 0, 1);
        GridPane.setConstraints(teamView, 0, 2);
        GridPane.setConstraints(playerInfo, 0, 3);
        GridPane.setConstraints(leagueView, 1, 2);
        GridPane.setConstraints(leagueInfo, 1, 3);
        GridPane.setConstraints(tranferWindow, 1, 4);

        grid.getChildren().addAll(
                userLabel, teamName, teamView, playerInfo, leagueInfo, tranferWindow, leagueView, username
        );

        userScene = new Scene(grid);
        myUser = user;
    }

    public static User getUser() {
        return myUser;
    }

    public static Scene getScene() {
        return userScene;
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
