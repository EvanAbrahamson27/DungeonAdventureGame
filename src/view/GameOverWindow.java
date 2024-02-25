package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverWindow extends Stage {

    public GameOverWindow() {
        setTitle("Game Over!");
        setWidth(400);
        setHeight(100);

        initModality(Modality.APPLICATION_MODAL);

        Label gameOverLabel = new Label("Game Over! Better luck next time!");

        Button continueButton = new Button("Restart");
        continueButton.setOnAction(actionEvent -> {DungeonAdventure.setupGame();close();});

        Button loadButton = new Button("Load Game");

        Button endButton = new Button("End");
        endButton.setOnAction(actionEvent -> System.exit(0));

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(continueButton, loadButton, endButton);
        buttonBox.setSpacing(50);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(gameOverLabel, buttonBox);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Scene gameOverScene = new Scene(layout, 400, 100);
        setScene(gameOverScene);

        setOnCloseRequest(event -> System.exit(0));

        show();
    }
}
