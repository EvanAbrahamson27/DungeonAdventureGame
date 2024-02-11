package view;

import controller.DungeonAdventure;
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
    RoomPanel() {
        Image dungeonBackground = new Image(Objects.requireNonNull(getClass()
                .getResource("/images/TempDungeonImage.jpg")).toExternalForm());
        setBackground(new Background(new BackgroundImage(dungeonBackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false))));

        myContentBox = new VBox();
        myContentBox.getChildren().add(createMonsterImage());

        setCenter(myContentBox);
        myContentBox.setAlignment(Pos.CENTER);

        updateEnemy();
    }

    private ImageView createMonsterImage() {
        switch (DungeonAdventure.myMonster.toString()) {
            case "Zombie" -> myMonsterImage = createImage("/images/HealthPotion.png");
            case "Skeleton" -> myMonsterImage = createImage("/images/Skeleton.png");
            default -> {return null;}
        }

        myMonsterImage.setFitWidth(150);
        myMonsterImage.setPreserveRatio(true);

        return myMonsterImage;
    }

    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource(theFileLocation)).toExternalForm()));
    }

    private void updateEnemy() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (DungeonAdventure.myMonster.getIsDead()) {
                myContentBox.getChildren().remove(myMonsterImage);
            } else if (myContentBox.getChildren().size() == 0) {
                myContentBox.getChildren().add(createMonsterImage());
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
