package view;

import controller.DungeonAdventure;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> startButtonAction(theStage, borderPane, startButton));
        borderPane.setCenter(startButton);
        BorderPane.setAlignment(startButton, Pos.CENTER);

        Scene scene = new Scene(borderPane);
        theStage.setScene(scene);

        theStage.show();

    }

    private void startButtonAction(Stage theStage, BorderPane borderPane, Button startButton) {
        borderPane.getChildren().remove(startButton); // Remove the Start Game button

        if (buttonPanel == null) {
            buttonPanel = new ButtonPanel(DungeonAdventure.myHero);
            logPanel = new LogPanel();
            statPanel = new StatPanel(DungeonAdventure.myHero);
            roomPanel = new RoomPanel();
        }
        borderPane.setBottom(buttonPanel);
        borderPane.setRight(statPanel);
        borderPane.setTop(logPanel);
//        borderPane.setLeft(mapPanel);
        borderPane.setCenter(roomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, logPanel);

        borderPane.setTop(vBox);

    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        MenuItem helpItem = new MenuItem("Help");

        exitItem.setOnAction(actionEvent -> System.exit(0));

        fileMenu.getItems().addAll(saveItem, exitItem);
        helpMenu.getItems().add(helpItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }
    public static void main(String[] theArgs) {
        launch(theArgs);
    }
}
