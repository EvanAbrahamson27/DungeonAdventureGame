package view;

import controller.DungeonAdventure;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class GameWindow extends Application {
    protected ButtonPanel buttonPanel;
    protected StatPanel statPanel;
    protected LogPanel logPanel;
    protected MapPanel mapPanel;
    protected RoomPanel roomPanel;

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Dungeon Escape");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        BorderPane borderPane = new BorderPane();

        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        buttonPanel = new ButtonPanel(DungeonAdventure.myHero);
        logPanel = new LogPanel();
        statPanel = new StatPanel(DungeonAdventure.myHero);
        roomPanel = new RoomPanel();

        borderPane.setBottom(buttonPanel);
        borderPane.setRight(statPanel);
        borderPane.setTop(logPanel);
//        borderPane.setLeft(mapPanel);
        borderPane.setCenter(roomPanel);

        Scene scene = new Scene(borderPane);
        theStage.setScene(scene);

        theStage.show();
    }

    public static void main(String[] theArgs) {
        launch(theArgs);
    }
}
