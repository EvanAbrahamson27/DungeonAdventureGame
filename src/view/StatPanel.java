package view;

import controller.DungeonAdventure;
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
import model.Item;

import java.util.Objects;

public class StatPanel extends BorderPane {
    private Label statsArea;
    private HBox myInventoryImages;
    final private VBox myContentBox;

    StatPanel() {
        setStyle("-fx-border-color: black;");

        myContentBox = new VBox();
        myContentBox.getChildren().addAll(createStatsList(), createItemImages());

        setCenter(myContentBox);

        updateStats();
    }

    private Label createStatsList() {
        statsArea = new Label();
        statsArea.setText("Test!");
        statsArea.setFont(new Font("Times New Roman", 20));

        return statsArea;
    }

    private HBox createItemImages() {
        myInventoryImages = new HBox(5);
        for (Item item : DungeonAdventure.myHero.getInventory()) {
            ImageView imageView;
            Tooltip tooltip;
            switch (item.getMyItemType()) {
                case 'h' -> {imageView = new ImageView(createImage("/images/HealthPotion.png"));
                    tooltip = new Tooltip(item.toString());}
                case 'd' -> {imageView = new ImageView(createImage("/images/DamagePotion.png"));
                    tooltip = new Tooltip(item.toString());}
                case 'v' -> {imageView = new ImageView(createImage("/images/VisionPotion.png"));
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

    private Image createImage(final String theFileLocation) {
            return new Image(Objects.requireNonNull(getClass()
                    .getResource(theFileLocation)).toExternalForm());
    }

    private void updateStats() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!statsArea.getText().equals(DungeonAdventure.myHero.toString()))
                statsArea.setText(DungeonAdventure.myHero.toString());
            if (DungeonAdventure.myHero.getInventory().size() != myInventoryImages.getChildren().size()) {
                myContentBox.getChildren().remove(myInventoryImages);
                myContentBox.getChildren().add(createItemImages());
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
