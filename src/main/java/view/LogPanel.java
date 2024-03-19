/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
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

/**
 * A custom UI component representing the game log panel in a game.
 * This panel contains a non-editable text area within a scroll pane, displaying ongoing game events and actions.
 * It is updated periodically to reflect the latest game status and actions, providing players with feedback
 * and history of their actions and the game's responses.
 */
public class  LogPanel extends BorderPane {
    final private TextArea myLog;
    final private ScrollPane myScrollPane;

    /**
     * Constructs a new LogPanel. Initializes the text area and scroll pane used to display the game log.
     * Sets up the layout and styles for displaying the log content.
     */
    public LogPanel() {
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

    /**
     * Initializes and returns the text area used for the game log. Sets properties such as editability,
     * focus traversability, font, and wrap text. The scroll pane's vertical scroll bar policy is also defined here.
     *
     * @return The initialized TextArea for the game log.
     */
    private TextArea createLog() {
        myLog.setEditable(false);
        myLog.setFocusTraversable(false);
        myLog.setFont(new Font("Times New Roman", 14));
        myLog.setWrapText(true);

        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return myLog;
    }

    /**
     * Continuously updates the text area with the latest game log entries from the DungeonAdventure controller.
     * This method uses a Timeline to periodically check for and display new log messages.
     */
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
