package view;

import controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class LogPanel extends BorderPane {
    final private TextArea log;
    LogPanel() {
        log = new TextArea();
        setStyle("-fx-border-color: black;");
        VBox contentBox = new VBox();
        contentBox.getChildren().addAll(new Label("Game Log:"), createLog());

        updateLog();
        setPrefSize(500, 200);
    }

    private TextArea createLog() {
        log.setEditable(false);
        log.setFocusTraversable(false);
        log.setFont(new Font("Times New Roman", 10));
        log.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(log);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return log;
    }

    private void updateLog() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event ->
                log.setText(DungeonAdventure.getLog())));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
