package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        theStage.setTitle("Dungeon Adventure");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        BorderPane borderPane = new BorderPane();

        // Add game background image
        Image dungeonBackground = new Image("images/TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        borderPane.getChildren().add(dungeonImageView);

        Label title = new Label("Dungeon Adventure");
        title.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 50px; -fx-padding: 40 50 10 50; -fx-text-fill: white; -fx-effect: null;");

        borderPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox buttons = new VBox(5);
        buttons.setAlignment(Pos.CENTER);


        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        startButton.setOnAction(e -> {startButtonAction(theStage, borderPane, startButton);
                borderPane.getChildren().remove(dungeonImageView);});
//        borderPane.setCenter(startButton);
//        BorderPane.setAlignment(startButton, Pos.CENTER);

        Button loadGame = new Button("Load Game");
        loadGame.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        loadGame.setOnAction(e -> loadGameAction(theStage, borderPane, loadGame));
//        borderPane.setCenter(loadGame);
//        BorderPane.setAlignment(loadGame, Pos.TOP_CENTER);

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        helpButton.setOnAction(e -> helpButtonAction(theStage, borderPane, helpButton));

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        exitButton.setOnAction(e -> theStage.close());
        // exitButtonAction(theStage, borderPane, exitButton));

        helpButton.setMinWidth(185);
        buttons.getChildren().addAll(startButton, loadGame, helpButton, exitButton);
        borderPane.setCenter(buttons);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("Style.css");
        theStage.setScene(scene);

        theStage.show();

    }

    private void startButtonAction(Stage theStage, BorderPane borderPane, Button startButton) {
        borderPane.getChildren().remove(startButton); // Remove the Start Game button

        if (buttonPanel == null) {
            buttonPanel = new ButtonPanel();
            logPanel = new LogPanel();
            statPanel = new StatPanel();
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

    private void loadGameAction(Stage theStage, BorderPane borderPane, Button loadGame) {
    }

    private void helpButtonAction(Stage theStage, BorderPane borderPane, Button helpButton) {
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        Menu debugMenu = new Menu("Debug");
        Menu debugClassMenu = new Menu("Change Class");

        MenuItem saveItem = new MenuItem("Save");
        MenuItem restartItem = new MenuItem("Restart");
        MenuItem exitItem = new MenuItem("Exit");
        MenuItem helpItem = new MenuItem("Help");

        MenuItem dbClassP = new MenuItem("Priestess");
        MenuItem dbClassW = new MenuItem("Warrior");
        MenuItem dbClassT = new MenuItem("Thief");
        dbClassP.setOnAction(actionEvent -> DungeonAdventure.setClass("Priestess"));
        dbClassW.setOnAction(actionEvent -> DungeonAdventure.setClass("Warrior"));
        dbClassT.setOnAction(actionEvent -> DungeonAdventure.setClass("Thief"));

        MenuItem dbDamageBoost = new MenuItem("Damage Boost");
        dbDamageBoost.setOnAction(actionEvent ->
                DungeonAdventure.myHero.setDamageRange(99999, 100000));
        MenuItem dbHealthBoost = new MenuItem("Health Boost");
        dbHealthBoost.setOnAction(actionEvent ->
                DungeonAdventure.myHero.heal(99999));
        MenuItem dbDie = new MenuItem("Die");
        dbDie.setOnAction(actionEvent -> DungeonAdventure.myHero.die());

        exitItem.setOnAction(actionEvent -> System.exit(0));

        fileMenu.getItems().addAll(saveItem, exitItem);
        helpMenu.getItems().addAll(helpItem, debugMenu);
        debugMenu.getItems().addAll(debugClassMenu, dbDamageBoost, dbHealthBoost, dbDie);
        debugClassMenu.getItems().addAll(dbClassP, dbClassT, dbClassW);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }
    public static void main(String[] theArgs) {
        launch(theArgs);
    }

    public static void openGameOverWindow() {
        new GameOverWindow();
    }
}
