/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
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

/**
 * Represents the statistics panel in a game. This panel displays the player's current
 * statistics such as health, damage, and any other relevant character information. Additionally, it shows the
 * items currently held in the player's inventory, each represented by an image with a tooltip for description.
 */
public class StatPanel extends BorderPane {
    private Label myStatsArea;
    private HBox myInventoryImages;
    final private VBox myContentBox;

    /**
     * Constructs a new StatPanel. Initializes the content box and sets up the layout for displaying the player's
     * statistics and inventory items. It also sets the initial background opacity and styles for this panel.
     */
    public StatPanel() {
        // setStyle("-fx-border-color: black;");
        setStyle("-fx-background-opacity: 0.0;");

        myContentBox = new VBox();
        myContentBox.getChildren().addAll(createStatsList(), createItemImages());
        myContentBox.setStyle("-fx-background-opacity: 0.0;");

        setCenter(myContentBox);

        updateStats();
    }

    /**
     * Creates and returns a label that lists the player's current statistics. The label is styled and
     * initialized with placeholder text.
     *
     * @return A Label object containing the player's current statistics.
     */
    private Label createStatsList() {
        myStatsArea = new Label();
        myStatsArea.setText("Test!");
        myStatsArea.setFont(new Font("Times New Roman", 20));

        myStatsArea.setStyle("-fx-padding: 10;");

        return myStatsArea;
    }

    /**
     * Creates and returns an HBox containing images representing the items currently in the player's inventory.
     * Each item image comes with a tooltip describing the item. The method loops through the player's inventory
     * and assigns the appropriate image and tooltip based on the item type.
     *
     * @return An HBox containing the inventory item images.
     */
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

    /**
     * Creates and returns an ImageView from a specified file location. This utility method is used to create image
     * views for inventory items.
     *
     * @param theFileLocation The location of the image file to load.
     * @return An ImageView object containing the image from the specified file location.
     */
    private ImageView createImage(final String theFileLocation) {
        return new ImageView(new Image(theFileLocation));
    }

    /**
     * Updates the stat panel to reflect the current state of the player's statistics and inventory. This includes
     * updating the text of the statistics label and the images in the inventory section. The method is called
     * repeatedly to ensure the panel reflects the current state of the player's character and inventory.
     */
    private void updateStats() {
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!myStatsArea.getText().equals(CharacterWindow.myHero.toString()))
                myStatsArea.setText(CharacterWindow.myHero.toString());
            if (CharacterWindow.myHero.getInventory().size() != myInventoryImages.getChildren().size()) {
                myContentBox.getChildren().remove(myInventoryImages);
                myContentBox.getChildren().add(createItemImages());
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
