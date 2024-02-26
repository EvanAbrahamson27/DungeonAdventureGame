package view;

import controller.*;
import model.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MapPanel extends BorderPane {
    private GridPane myMapGrid;
    private VBox contentBox;

    MapPanel() {
        contentBox = new VBox();

        Label gameMapLabel = new Label("Game Map:");
        contentBox.getChildren().add(gameMapLabel);

        this.myMapGrid = new GridPane();
        this.myMapGrid.setPadding(new Insets(10, 10, 10, 10));

        drawMap();

        contentBox.getChildren().add(this.myMapGrid);

        contentBox.setMaxSize(100, 100);
        setCenter(contentBox);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> refreshMap());
    }

    private void drawMap() {
        // Display the entire map with all rooms
        Room[][] roomMap = DungeonAdventure.myDungeonMap.getMap();

        for (int i = 0; i < roomMap.length; i++) {
            for (int j = 0; j < roomMap[i].length; j++) {
                Room currRoom = roomMap[i][j];
                if (currRoom != null) {
                    this.myMapGrid.add(currRoom.getImage(), i, j);
                }
            }
        }
    }

    public void refreshMap() {
        this.myMapGrid.getChildren().clear();
        drawMap();
    }
}
