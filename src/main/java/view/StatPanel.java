package view;

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

import java.io.Serializable;

public class StatPanel extends BorderPane implements Serializable {
    private transient Label statsArea;
    private transient HBox myInventoryImages;
    final private transient VBox myContentBox;

    StatPanel() {
        // setStyle("-fx-border-color: black;");
        setStyle("-fx-background-opacity: 0.0;");

        myContentBox = new VBox();
        myContentBox.getChildren().addAll(createStatsList(), createItemImages());
        myContentBox.setStyle("-fx-background-opacity: 0.0;");

        setCenter(myContentBox);

        updateStats();
    }

    private Label createStatsList() {
        statsArea = new Label();
        statsArea.setText("Test!");
        statsArea.setFont(new Font("Times New Roman", 20));

        statsArea.setStyle("-fx-padding: 10;");

        return statsArea;
    }

    private HBox createItemImages() {
        myInventoryImages = new HBox(5);
        for (Item item : CharacterWindow.myHero.getInventory()) {
            ImageView imageView;
            Tooltip tooltip;
            switch (item.getMyItemType()) {
                case 'h' -> {imageView = createImage("HealthPotion.png");
                    tooltip = new Tooltip(item.toString());}
                case 'd' -> {imageView = createImage("DamagePotion.png");
                    tooltip = new Tooltip(item.toString());}
                case 'v' -> {imageView = createImage("VisionPotion.png");
                    tooltip = new Tooltip(item.toString());}
                case 'p' -> {imageView = createImage("Pillar.png");
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

    private void updateStats() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!statsArea.getText().equals(CharacterWindow.myHero.toString()))
                statsArea.setText(CharacterWindow.myHero.toString());
            if (CharacterWindow.myHero.getInventory().size() != myInventoryImages.getChildren().size()) {
                myContentBox.getChildren().remove(myInventoryImages);
                myContentBox.getChildren().add(createItemImages());
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
