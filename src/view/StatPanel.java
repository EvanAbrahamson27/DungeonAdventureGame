package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Hero;

public class StatPanel extends BorderPane {
    private Label statsArea;

    StatPanel(Hero thePlayer) {
        setStyle("-fx-border-color: black;");

        VBox contentBox = new VBox();
        contentBox.getChildren().addAll(createStatsList());

        setCenter(contentBox);

        updateStats(thePlayer);
    }

    private Label createStatsList() {
        statsArea = new Label();
        statsArea.setText("Test!");
        statsArea.setFont(new Font("Times New Roman", 20));

        return statsArea;
    }

    private void updateStats(Hero thePlayer) {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event ->
                statsArea.setText("Health: " + thePlayer.getHealthPoints() +
                        "\nAttack Range: " + thePlayer.getDamageMin() + " - " + thePlayer.getDamageMax() +
                        "\nAttack Speed: " + thePlayer.getAttackSpeed() +
                        "\nChance to Hit: " + thePlayer.getChanceToHit() + "%" +
                        "\n\nClass: Hero\nSpecial Skill: Self Heal")));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
