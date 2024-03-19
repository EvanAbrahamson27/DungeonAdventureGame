/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Room;


import java.io.Serializable;

/**
 * A UI component representing the game map panel in a game.
 * This panel displays the game's dungeon map as a grid, with individual cells representing different rooms.
 * Rooms that are visible to the player are shown, allowing the player to see parts of the dungeon they have explored.
 */
public class MapPanel extends BorderPane implements Serializable {
    private transient GridPane myMapGrid;
    private transient VBox myContentBox;


    /**
     * Constructs a new MapPanel. Initializes the content box and map grid, sets up the layout and styles, and draws the initial map.
     * Also sets up an event handler to refresh the map when it is clicked.
     */
    public MapPanel() {
        myContentBox = new VBox();

        // Add left padding to contentBox
        myContentBox.setPadding(new Insets(0, 0, 0, 15));

        Label gameMapLabel = new Label("Game Map:");
        gameMapLabel.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 20px; -fx-font-weight: bold;");

        myContentBox.getChildren().add(gameMapLabel);

        this.myMapGrid = new GridPane();
        this.myMapGrid.setPadding(new Insets(5, 10, 10, 10));

        drawMap();

        myContentBox.getChildren().add(this.myMapGrid);

        myContentBox.setMaxSize(150, 150);
        setCenter(myContentBox);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> refreshMap());
    }

    /**
     * Draws the dungeon map based on the current state of the dungeon. Only rooms that are marked as visible are displayed.
     * This method initializes the grid layout of the map, placing room images in the correct grid positions.
     */
    private void drawMap() {
        // Display the entire map with all rooms
        Room[][] roomMap = CharacterWindow.myDungeonMap.getMap();

        for (int i = 0; i < roomMap.length; i++) {
            for (int j = 0; j < roomMap[i].length; j++) {
                Room currRoom = roomMap[i][j];
                if (currRoom != null && currRoom.isVisible()) {
                    this.myMapGrid.add(currRoom.getImage(), i, j);
                }
            }
        }
    }

    /**
     * Refreshes the displayed map. Clears the current map grid and redraws it to reflect any changes in room visibility or state.
     * This method is typically called to update the map after the player moves or performs an action that changes the dungeon layout.
     */
    public void refreshMap() {
        this.myMapGrid.getChildren().clear();
        drawMap();
    }
}
