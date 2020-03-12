package UserInterface;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button; // The content inside the window is called the scene
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;  // The entire window is called the stage
import javafx.scene.layout.*; // Layout is how you want everything arranged on your screen

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage(); // Blank stage

        // User interaction is blocked till this window is taken care of
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(200);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(event -> window.close());

        VBox layout =  new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(layout); // Adds layout to scene
        window.setScene(alertScene);
        window.showAndWait();
    }
}
