package UserInterface;

import GameLogic.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


public class UserWindow {

    private static Scene userScene;

    /* Construct the ListView by adding the selected players */
    private static ListView<Player> constructTeamView(List<Player> players, Double width) {
        ListView<Player> view = new ListView<>();
        view.setMaxHeight(width);
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
                    // If player is vice or normal captain, put and indicator and make the text bold.
                    if (player.isCaptain()) {
                        super.setStyle("-fx-font-weight: bold");
                        setText("C: " + player.getPositionName() + " " + player.getFullName());
                    }
                    else if (player.isViceCaptain()) {
                        super.setStyle("-fx-font-weight: bold");
                        setText("VC: " + player.getPositionName() + " " + player.getFullName());
                    }
                    else {
                        super.setStyle("-fx-font-weight: light");
                        setText(player.getPositionName() + " " + player.getFullName());
                    }
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

        ListView<Player> startersView = constructTeamView(user.getTeamStarters(), 300.0);
        ListView<Player> benchView = constructTeamView(user.getTeamBench(), 100.0);
        ListView<League> leagueView = constructLeagueView(IOHandler.getInstance().getLeagues());

        Label userLabel = new Label("Welcome to your team and league page");
        Label username = new Label("Username: " + user.getUsername());
        Label teamName = new Label("Team name: " + user.getTeamName());

        Button playerInfoButton = new Button("Open player");
        Button leagueInfoButton = new Button("Open League");
        Button transferWindowButton= new Button("Transfer Window");
        Button logoutButton = new Button("Logout");
        Button createLeagueButton = new Button("Create League");
        Button joinLeagueButton = new Button("Join League");
        Button exitLeagueButton = new Button("Exit League");
        Button deleteLeagueButton = new Button("Delete League");
        Button pickCaptainButton = new Button("Pick Captain");
        Button pickViceCaptainButton = new Button("Pick Vice Captain");


        // Display the selected player's stats
        playerInfoButton.setOnAction(event -> {
            Player selectedPlayer = startersView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                PlayerWindow.display(selectedPlayer);
            }

        });

        // Displays info about the selected league
        leagueInfoButton.setOnAction(event -> {
            League selectedLeague = leagueView.getSelectionModel().getSelectedItem();
            if (selectedLeague != null) {
                LeagueWindow.display(selectedLeague);
            }
        });

        // Open the transfer window
        transferWindowButton.setOnAction(event -> {
            try { window.setScene(TransferWindow.getScene(window, user)); }
            catch (IOException e) { e.printStackTrace(); }
        });

        // Goes back to the login scene
        logoutButton.setOnAction(event -> window.setScene(LoginWindow.getScene(window)));

        // Create a custom league and add to leagueView
        createLeagueButton.setOnAction(event -> {
            CreateLeagueWindow.display(user, leagueView);
        });

        // Delete the league if you are the manager
        deleteLeagueButton.setOnAction(event -> {
            League selectedLeague = leagueView.getSelectionModel().getSelectedItem();
            if (selectedLeague != null) {
                user.deleteLeague(selectedLeague);
                if (selectedLeague.getManager() == user.getUsername())
                    leagueView.getItems().remove(selectedLeague);
                    leagueView.refresh();
            }
        });

        // Join a league
        joinLeagueButton.setOnAction(event -> {
            League selectedLeague = leagueView.getSelectionModel().getSelectedItem();
            if (selectedLeague != null) {
                user.joinLeague(selectedLeague);
            }
        });

        // Exit the selected league
        exitLeagueButton.setOnAction(event -> {
            League selectedLeague = leagueView.getSelectionModel().getSelectedItem();
            if (selectedLeague != null) {
                try {
                    user.exitLeague(selectedLeague);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        pickCaptainButton.setOnAction(event -> {
            Player selectedPlayer = startersView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                user.pickCaptain(selectedPlayer);
                startersView.refresh();
            }
        });

        pickViceCaptainButton.setOnAction(event -> {
            Player selectedPlayer = startersView.getSelectionModel().getSelectedItem();
            if (selectedPlayer != null) {
                user.pickViceCaptain(selectedPlayer);
                startersView.refresh();
            }
        });


        VBox labels = new VBox(10);
        labels.getChildren().addAll(userLabel, username, teamName);

        VBox topHBox = new VBox(10);
        topHBox.getChildren().addAll(transferWindowButton, logoutButton);
        topHBox.setAlignment(Pos.BASELINE_RIGHT);

        HBox teamButtons = new HBox(5);
        teamButtons.getChildren().addAll(playerInfoButton, leagueInfoButton);

        HBox teamButtons2 = new HBox(5);
        teamButtons2.getChildren().addAll(pickCaptainButton, pickViceCaptainButton);

        HBox leagueButtons = new HBox(5);
        leagueButtons.getChildren().addAll(joinLeagueButton, exitLeagueButton);

        HBox leagueButtons2 = new HBox(5);
        leagueButtons.getChildren().addAll(createLeagueButton, deleteLeagueButton);


        VBox teamVBox = new VBox(5);
        teamVBox.getChildren().addAll(startersView, benchView, teamButtons, teamButtons2);

        VBox leagueVBox = new VBox(5);
        leagueVBox.getChildren().addAll(leagueView, leagueButtons, leagueButtons2);

        // Construct layout using GridPane
        GridPane grid = new GridPane(); grid.setHgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10)); grid.setVgap(10);
        GridPane.setConstraints(labels, 0, 0);
        GridPane.setConstraints(topHBox, 1, 0);
        GridPane.setConstraints(teamVBox, 0, 1);
        GridPane.setConstraints(leagueVBox, 1, 1);


        grid.getChildren().addAll(
            labels, teamVBox, leagueVBox, topHBox
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

    /* The window of this class pops up when the user clicks the createLeagueButton */
    private static class CreateLeagueWindow {
        private static Scene createLeagueScene;
        private static String leagueName;
        private static Stage newWindow = new Stage();


        private static void setScene(User user, ListView<League> leagueView) {
            TextField leagueNameField = new TextField("League Name");
            Button submitButton = new Button("Submit");

            submitButton.setOnAction(event -> {
                if (leagueNameField.getText().equals(""))
                    HandleError.textFieldBlank();
                else {
                    leagueName = leagueNameField.getText();
                    League customLeague = user.createLeague(leagueName);

                    leagueView.getItems().add(customLeague);
                    leagueView.refresh();
                    newWindow.close();
                }
            });

            // Layout construction and building the scene
            HBox createLeagueBox = new HBox(10);
            createLeagueBox.getChildren().addAll(leagueNameField, submitButton);

            createLeagueScene = new Scene(createLeagueBox);
        }

        public static void display(User user, ListView<League> leagueView) {
            CreateLeagueWindow.setScene(user, leagueView);
            newWindow.setScene(createLeagueScene);
            newWindow.show();
        }

        public static String getLeagueName() {
            return leagueName;
        }
    }
}
