package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Room;

import java.io.Serializable;

public class MapPanel extends BorderPane implements Serializable {
    private transient GridPane myMapGrid;
    private transient VBox myContentBox;

    MapPanel() {
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

    public void refreshMap() {
        this.myMapGrid.getChildren().clear();
        drawMap();
    }
}
