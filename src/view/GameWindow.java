package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.Hero;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.io.IOException;

public class GameWindow extends Application {
    protected ButtonPanel buttonPanel;
    protected StatPanel statPanel;
    protected LogPanel logPanel;
    protected MapPanel mapPanel;
    protected RoomPanel roomPanel;

//    public GameWindow(Hero thePlayer) throws IOException {
//        buttonPanel = new ButtonPanel(thePlayer);
//        statPanel = new StatPanel(thePlayer);
//        logPanel = new LogPanel();
//        mapPanel = new MapPanel();
//        roomPanel = new RoomPanel();
//    }

    @Override
    public void start(Stage theStage) {
        logPanel = new LogPanel();
        theStage.setTitle("Dungeon Escape");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        BorderPane borderPane = new BorderPane();

        borderPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        borderPane.setBottom(buttonPanel);
//        borderPane.setRight(statPanel);
        borderPane.setTop(logPanel);
//        borderPane.setLeft(mapPanel);
//        borderPane.setCenter(roomPanel);

        Scene scene = new Scene(borderPane);
        theStage.setScene(scene);

        theStage.show();
    }

    public static void main(String[] theArgs) {
        launch(theArgs);
    }
}
