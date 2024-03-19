/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
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

import java.io.Serializable;

/**
 * Represents the panel in the game that displays the current room's content,
 * including any monsters or items present. This class is responsible for updating the visual representation
 * of the room as the player navigates through the dungeon.
 */
public class RoomPanel extends BorderPane implements Serializable {
    final private transient VBox myContentBox;

    private static ImageView myMonsterImage;
    private ImageView myItemImage;

    /**
     * Constructs a new RoomPanel. Initializes the content box and sets up the panel to display room content,
     * such as monsters or items, centered within the panel.
     */
    public RoomPanel() {
        myContentBox = new VBox();

        setCenter(myContentBox);
        myContentBox.setAlignment(Pos.CENTER);

        updateRoom();
    }

    /**
     * Creates and returns an ImageView for a monster image based on the current room's monster.
     * The method selects the correct image file based on the type of monster present in the room.
     *
     * @return An ImageView containing the monster's image, or null if there is no monster.
     */
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

    /**
     * Creates and returns an ImageView for an item image based on the current room's item.
     * The method selects the correct image file based on the type of item present in the room.
     *
     * @return An ImageView containing the item's image, or null if there is no item.
     */
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

    /**
     * Creates and returns an ImageView from a specified file location.
     * This utility method is used to create image views for monsters and items.
     *
     * @param theFileLocation The location of the image file to load.
     * @return An ImageView object containing the image from the specified file location.
     */
    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(theFileLocation));
    }

    /**
     * Updates the room panel to reflect the current room's state. This includes displaying or removing monster
     * and item images based on whether they are present and alive in the room. This method is called repeatedly
     * to ensure the panel reflects the current state of the room.
     */
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
