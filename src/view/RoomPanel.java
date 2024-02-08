package view;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class RoomPanel extends BorderPane {
    RoomPanel() {
        Image dungeonBackground = new Image(Objects.requireNonNull(getClass()
                .getResource("/view/TempDungeonImage.jpg")).toExternalForm());
        setBackground(new Background(new BackgroundImage(dungeonBackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false))));
    }
}
