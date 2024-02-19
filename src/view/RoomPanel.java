package view;

import controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.effect.ColorAdjust;
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
        Image dungeonBackground = new Image(Objects.requireNonNull(getClass()
                .getResource("/images/TempDungeonImage.jpg")).toExternalForm());
        setBackground(new Background(new BackgroundImage(dungeonBackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false))));

        myContentBox = new VBox();

        setCenter(myContentBox);
        myContentBox.setAlignment(Pos.CENTER);

        updateRoom();
    }

    private ImageView createMonsterImage() {
        switch (DungeonAdventure.myMonster.toString()) {
            case "Skeleton" -> myMonsterImage = createImage("/images/Skeleton.png");
            default -> {return null;}
        }

        myMonsterImage.setFitWidth(150);
        myMonsterImage.setPreserveRatio(true);

        return myMonsterImage;
    }

    private ImageView createItemImage() {
        switch (DungeonAdventure.myHero.getRoom().getItem().toString()) {
            case "Health Potion" -> myItemImage = createImage("/images/HealthPotion.png");
            case "Vision Potion" -> myItemImage = createImage("/images/VisionPotion.png");
            case "Damage Potion" -> myItemImage = createImage("/images/DamagePotion.png");
            default -> {return null;}
        }

        myItemImage.setFitWidth(150);
        myItemImage.setPreserveRatio(true);

        return myItemImage;
    }

    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(theFileLocation)).toExternalForm()));
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
