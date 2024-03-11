package view;

import controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class  LogPanel extends BorderPane {
    final private TextArea myLog;
    final private ScrollPane myScrollPane;
    LogPanel() {
        myLog = new TextArea();
        myScrollPane = new ScrollPane(myLog);
        // setStyle("-fx-border-color: black;");
        VBox contentBox = new VBox();
        Label gameLog = new Label("Game Log:");
        gameLog.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15; -fx-padding: 0 10 0 5;");
        contentBox.getChildren().addAll(gameLog, createLog());
        contentBox.setMaxSize(500, 120);

        setCenter(contentBox);

        updateLog();
    }

    private TextArea createLog() {
        myLog.setEditable(false);
        myLog.setFocusTraversable(false);
        myLog.setFont(new Font("Times New Roman", 14));
        myLog.setWrapText(true);

        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return myLog;
    }

    private void updateLog() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!myLog.getText().equals(DungeonAdventure.getLog())) {
                myLog.setText(DungeonAdventure.getLog());
                myLog.positionCaret(myLog.getLength() - 1);
            }}));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
