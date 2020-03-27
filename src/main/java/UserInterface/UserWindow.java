package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


public class UserWindow {

    private static Scene userScene;

    /* Construct the ListView by adding the selected players */
    private static ListView<Player> constructTeamView(List<Player> players) {
        ListView<Player> view = new ListView<>();
        Iterator<Player> it = players.iterator();
        while (it.hasNext())
            view.getItems().add(it.next());

        setViewToPlayerName(view);
        return view;
    }


    /* This function allows the ListView to store 'Player' objects and display them as strings */
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


    /* This function allows the ListView to store league objects and display them as strings */
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


    /* Construct the ListView containing league objects and display them as strings */
    private static ListView<League> constructLeagueView(List<League> leagues) {
        ListView<League> leagueListView = new ListView<>();
        Iterator<League> it = leagues.iterator();
        while (it.hasNext())
            leagueListView.getItems().add(it.next());

        setViewToLeagueName(leagueListView);
        return leagueListView;
    }


    /* Construct the scene by adding certain elements to the layout: GridPane. Then, create the scene */
    private static void setScene(Stage window, User user) {

        ListView<Player> teamView = constructTeamView(user.getFullTeam());
        ListView<League> leagueView = constructLeagueView(IOHandler.getInstance().getLeagues());

        Label userLabel = new Label("Welcome to your team and league page");
        Label username = new Label("Username: " + user.getUsername());
        Label teamName = new Label("Team name: " + user.getTeamName());

        Button playerInfo = new Button("Open player");
        Button leagueInfo = new Button("Open League");
        Button transferWindow = new Button("Transfer Window");
        Button logoutButton = new Button("Logout");
        Button createLeagueButton = new Button("Create League");
        Button joinLeagueButton = new Button("Join League");
        Button exitLeagueButton = new Button("Exit League");

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
                LeagueWindow.display(selectedLeague);
            }
        });

        // Open the transfer window
        transferWindow.setOnAction(event -> {
            try { window.setScene(TransferWindow.getScene(window, user)); }
            catch (IOException e) { e.printStackTrace(); }
        });

        // Goes back to the login scene
        logoutButton.setOnAction(event -> window.setScene(LoginWindow.getScene(window)));

        // Create a custom league and add to leagueView
        createLeagueButton.setOnAction(event -> {
//            user.createLeague();
        });

        // Construct layout using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(userLabel, 1, 0);
        GridPane.setConstraints(username, 0, 0);
        GridPane.setConstraints(teamName, 0, 1);
        GridPane.setConstraints(teamView, 0, 2);
        GridPane.setConstraints(playerInfo, 0, 3);
        GridPane.setConstraints(leagueView, 1, 2);
        GridPane.setConstraints(leagueInfo, 1, 3);
        GridPane.setConstraints(transferWindow, 1, 4);
        GridPane.setConstraints(logoutButton, 0, 4);

        grid.getChildren().addAll(
                userLabel, teamName, teamView,
                playerInfo, leagueInfo, transferWindow,
                leagueView, username, logoutButton
        );

        userScene = new Scene(grid);

        // Save game upon closing it
        window.setOnCloseRequest(event -> {
            try { IOHandler.getInstance().save(); }
            catch (IOException e) { e.printStackTrace(); }
        });

    }

    /* Get scene after setting it */
    public static Scene getScene(Stage window, User user) {
        setScene(window, user);
        return userScene;
    }


    /* Private inner static class used to display the teams inside the league */
    private static class LeagueWindow {

        private static Scene leagueScene;
        private LeagueWindow() {}


        /* Construct the ListView by adding strings to it */
        private static ListView<String> constructLeagueInfoView(League league) {
            ListView<String> teamListView = new ListView<>();
            for (Map.Entry mapElement : league.getTeamPoints().entrySet()) {
                teamListView.getItems().add(
                    "Team name: " + mapElement.getKey() + " : Points: " + mapElement.getValue()
                );
            }
            return teamListView;
        }

        /* Label, view added to -> layout(VBox) leads to -> Scene */
        private static void setScene(League league) {
            ListView<String> leagueInfoView = constructLeagueInfoView(league);
            Label label = new Label("League name: " + league.getName() + "\nLeague manager: " + league.getManager());

            VBox view = new VBox(20);
            view.getChildren().addAll(label, leagueInfoView);
            leagueScene = new Scene(view);
        }

        /* This function displays the whole window as an extra window*/
        public static void display(League league) {
            Stage leagueStage = new Stage();
            setScene(league);

            leagueStage.setScene(leagueScene);
            leagueStage.show();
        }

    }
}
