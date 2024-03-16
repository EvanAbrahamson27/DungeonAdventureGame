package view;

import controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RoomPanel extends BorderPane {
    final private VBox myContentBox;
    private static ImageView myMonsterImage;
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
            case "Gremlin" -> myMonsterImage = createImage("Gremlin.png");
            case "Ogre" -> myMonsterImage = createImage("Ogre.png");
            default -> {return null;}
        }

        myMonsterImage.setFitWidth(150);
        myMonsterImage.setPreserveRatio(true);

        return myMonsterImage;
    }

    private ImageView createItemImage() {
        switch (CharacterWindow.myHero.getRoom().getItem().toString()) {
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

            if (CharacterWindow.myHero.getRoom().getItem() != null) {
                myContentBox.getChildren().add(createItemImage());
            } else if (!myContentBox.getChildren().contains(myItemImage) &&
                    CharacterWindow.myHero.getRoom().getItem() == null) {
                myContentBox.getChildren().remove(myItemImage);
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }

//    public static void attackAnimation() {
//        Image tempImage;
//        Image newImage;
//        switch (DungeonAdventure.myMonster.toString()) {
//            case "Skeleton" -> {newImage = new Image("SkeletonDamage.png"); tempImage = new Image("Skeleton.png");}
//            case "Gremlin" -> {newImage = new Image("GremlinDamage.png"); tempImage = new Image("Gremlin.png");}
//            case "Ogre" -> {newImage = new Image("OgreDamage.png"); tempImage = new Image("Ogre.png");}
//            default -> {return;}
//        }
//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.seconds(0), event -> myMonsterImage.setImage(newImage)),
//                new KeyFrame(Duration.seconds(0.5), event -> myMonsterImage.setImage(tempImage))
//        );
//        timeline.setCycleCount(1);
//
//        timeline.play();
//    }
}
