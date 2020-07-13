package com.company.Frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
  Upon being run, a window should open and
  the should be a field (number input) that
  creates the specified number of objects to
  have preferences (and number of preferences).
  After the number has been set, the first proposer
  is named. After that, a field (text input) will
  appear in order to name the acceptors (in order
  of preference). After the first proposer, all other
  objects are named similarly, but instead of naming
  their preferences, they have to choose (via button
  click) from the preferences of the first proposer.
  After every preference has been chosen, the results
  are shown. (The Exit should appear (probably).
  Optional reset button and optional preference
  reset button).
 */

/**
 * Methods that should be accessed, in order
 * for the app to work:
 * Algorithm.input(proposers,acceptors,proposerPreferences,acceptorPreferences);
 * Algorithm.calculateMatches();
 * Algorithm.printMatches(); (will probably be maintained for debugging,
 * but also likely be replaced in the graphical part)
 */

public class GSApp extends Application {

    public static void startApp(String[] args) {
        launch(args);
    }

    private final String title = "Matching";

    @Override
    public void start(Stage primaryStage) {

        final StackPane stackPane = new StackPane();
        final Scene scene = new Scene(stackPane, 800, 600);
        scene.setFill(Color.BROWN);

        final Button createButton = new Button();
        createButton.setText("Create new Object");
        createButton.setOnAction(event -> {
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("alert");
            alert.setContentText("Object created");
            alert.showAndWait();
        });

        stackPane.getChildren().add(createButton);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}