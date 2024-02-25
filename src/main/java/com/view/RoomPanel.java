package com.view;

import com.controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Objects;

public class RoomPanel extends BorderPane {
    final private VBox myContentBox;
    private ImageView myMonsterImage;
    private ImageView myItemImage;
    RoomPanel() {
        myContentBox = new VBox();

        setCenter(myContentBox);
        myContentBox.setAlignment(Pos.CENTER);

        updateRoom();
    }

    private ImageView createMonsterImage() {
        switch (DungeonAdventure.myMonster.toString()) {
            case "Skeleton" -> myMonsterImage = createImage("Skeleton.png");
            default -> {return null;}
        }

        myMonsterImage.setFitWidth(150);
        myMonsterImage.setPreserveRatio(true);

        return myMonsterImage;
    }

    private ImageView createItemImage() {
        switch (DungeonAdventure.myHero.getRoom().getItem().toString()) {
            case "Health Potion" -> myItemImage = createImage("HealthPotion.png");
            case "Vision Potion" -> myItemImage = createImage("VisionPotion.png");
            case "Damage Potion" -> myItemImage = createImage("DamagePotion.png");
            default -> {return null;}
        }

        myItemImage.setFitWidth(150);
        myItemImage.setPreserveRatio(true);

        return myItemImage;
    }

    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(theFileLocation));
    }

    private void updateRoom() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (DungeonAdventure.myMonster != null && DungeonAdventure.myMonster.getIsDead()) {
                myContentBox.getChildren().remove(myMonsterImage);
            } else if (myContentBox.getChildren().size() == 0 && DungeonAdventure.myMonster != null) {
                myContentBox.getChildren().add(createMonsterImage());
            }

            if (DungeonAdventure.myHero.getRoom().getItem() != null) {
                myContentBox.getChildren().add(createItemImage());
            } else if (!myContentBox.getChildren().contains(myItemImage) &&
                    DungeonAdventure.myHero.getRoom().getItem() == null) {
                myContentBox.getChildren().remove(myItemImage);
            }

            // Working on some "animation" stuff
//            if (DungeonAdventure.myMonster != null && DungeonAdventure.myMonster.getUsingTurn() == 'a') {
//                Timeline attackAnim = new Timeline(new KeyFrame(Duration.seconds(1), attackEvent -> {
//                    ColorAdjust colorAdjust = new ColorAdjust();
//                    colorAdjust.setBrightness(500);
//                    myContentBox.getChildren().remove(myMonsterImage);
//                    myMonsterImage.setEffect(colorAdjust);
//                    myContentBox.getChildren().add(myMonsterImage);
//                }));
//                attackAnim.setOnFinished(attackEventOver -> myMonsterImage.setEffect(null));
//                DungeonAdventure.myMonster.setUsingTurn('f');
//            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
