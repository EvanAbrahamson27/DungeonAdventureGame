package com.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import com.model.Hero;
import com.model.Item;

import java.util.Objects;

public class StatPanel extends BorderPane {
    private Label statsArea;
    private HBox myInventoryImages;
    final private VBox myContentBox;

    StatPanel(Hero thePlayer) {
        setStyle("-fx-border-color: black;");

        myContentBox = new VBox();
        myContentBox.getChildren().addAll(createStatsList(), createItemImages(thePlayer));

        setCenter(myContentBox);

        updateStats(thePlayer);
    }

    private Label createStatsList() {
        statsArea = new Label();
        statsArea.setText("Test!");
        statsArea.setFont(new Font("Times New Roman", 20));

        return statsArea;
    }

    private HBox createItemImages(Hero thePlayer) {
        myInventoryImages = new HBox(5);
        for (Item item : thePlayer.getInventory()) {
            ImageView imageView;
            Tooltip tooltip;
            switch (item.getMyItemType()) {
                case 'h' -> {imageView = createImage("HealthPotion.png");
                    tooltip = new Tooltip(item.toString());}
                case 'd' -> {imageView = createImage("DamagePotion.png");
                    tooltip = new Tooltip(item.toString());}
                case 'v' -> {imageView = createImage("VisionPotion.png");
                    tooltip = new Tooltip(item.toString());}
                default -> {return null;}
            }
            Tooltip.install(imageView, tooltip);
            imageView.setFitWidth(40);
            imageView.setPreserveRatio(true);
            myInventoryImages.getChildren().add(imageView);
        }
        return myInventoryImages;
    }

    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(theFileLocation));
    }

    private void updateStats(Hero thePlayer) {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!statsArea.getText().equals(thePlayer.toString()))
                statsArea.setText(thePlayer.toString());
            if (thePlayer.getInventory().size() != myInventoryImages.getChildren().size()) {
                myContentBox.getChildren().remove(myInventoryImages);
                myContentBox.getChildren().add(createItemImages(thePlayer));
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
