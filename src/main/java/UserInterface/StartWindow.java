package UserInterface;

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;  // The entire window is called the stage
import javafx.scene.layout.*; // Layout is how you want everything arranged on your screen

public class StartWindow {

    private static Scene startScene;
    private static VBox layout = new VBox(20);;
    private static Stage window = new Stage();
    private static Button startButton = new Button("Start game");

    public static void display(Scene loginScene) {
        startButton.setOnAction(event -> window.setScene(loginScene));

        Label label = new Label("Welcome to Fantasy football");
        layout.getChildren().addAll(label, startButton);
        startScene = new Scene(layout, 500, 500);

        window.setWidth(200);
        window.setTitle("Fantasy Football");
        window.setScene(startScene);
        window.show();
    }

    public static Scene getScene() {
        return startScene;
    }


}
