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
    protected static MapPanel mapPanel;
    protected RoomPanel roomPanel;
    protected static BorderPane borderPane;

    @Override
    public void start(final Stage theStage) {

        theStage.setTitle("Dungeon Adventure");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        borderPane = new BorderPane();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        borderPane.getChildren().add(dungeonImageView);

        Label title = new Label("Dungeon Adventure");
        title.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 50px; -fx-padding: 40 50 10 50; -fx-text-fill: white; -fx-effect: null; -fx-background-color: rgba(0, 0, 0, 0.0);");

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

    private void startButtonAction(final Stage theStage, final BorderPane borderPane, final Button startButton) {
        GameWindow.borderPane.getChildren().remove(startButton); // Remove the Start Game button

        new CharacterWindow();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(GameWindow.borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(GameWindow.borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        GameWindow.borderPane.getChildren().add(dungeonImageView);

        if (buttonPanel == null) {
            logPanel = new LogPanel();
            statPanel = new StatPanel();
            roomPanel = new RoomPanel();
            mapPanel = new MapPanel();
            buttonPanel = new ButtonPanel(CharacterWindow.myHero, mapPanel);
        }

        GameWindow.borderPane.setBottom(buttonPanel);
        GameWindow.borderPane.setLeft(mapPanel);
        GameWindow.borderPane.setTop(logPanel);
        GameWindow.borderPane.setRight(statPanel);
        GameWindow.borderPane.setCenter(roomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, logPanel);

        GameWindow.borderPane.setTop(vBox);

    }

    private void loadGameAction(final Stage theStage, final BorderPane borderPane, final Button loadGame) {
    }

    private void helpButtonAction(final Stage theStage, final BorderPane borderPane, final Button helpButton) {
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
        dbClassP.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Priestess"));
        dbClassW.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Warrior"));
        dbClassT.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Thief"));

        MenuItem dbDamageBoost = new MenuItem("Damage Boost");
        dbDamageBoost.setOnAction(actionEvent ->
                CharacterWindow.myHero.setDamageRange(99999, 100000));
        MenuItem dbHealthBoost = new MenuItem("Health Boost");
        dbHealthBoost.setOnAction(actionEvent ->
                CharacterWindow.myHero.heal(99999));
        MenuItem dbDie = new MenuItem("Die");
        dbDie.setOnAction(actionEvent -> CharacterWindow.myHero.die());

        restartItem.setOnAction(actionEvent -> DungeonAdventure.setupGame());
        exitItem.setOnAction(actionEvent -> System.exit(0));

        fileMenu.getItems().addAll(saveItem, restartItem, exitItem);
        helpMenu.getItems().addAll(helpItem, debugMenu);
        debugMenu.getItems().addAll(debugClassMenu, dbDamageBoost, dbHealthBoost, dbDie);
        debugClassMenu.getItems().addAll(dbClassP, dbClassT, dbClassW);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: black;");

        return menuBar;
    }

    public void restartWindow() {

        // Restart the game
        borderPane.getChildren().clear();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        borderPane.getChildren().add(dungeonImageView);

        new CharacterWindow();

        if (buttonPanel == null) {
            logPanel = new LogPanel();
            statPanel = new StatPanel();
            roomPanel = new RoomPanel();
            mapPanel = new MapPanel();
            buttonPanel = new ButtonPanel(CharacterWindow.myHero, mapPanel);
        }

        borderPane.setBottom(buttonPanel);
        borderPane.setLeft(mapPanel);
        borderPane.setTop(logPanel);
        borderPane.setRight(statPanel);
        borderPane.setCenter(roomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, logPanel);

        borderPane.setTop(vBox);
    }

    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

    public static void openGameOverWindow(final boolean theLoss) {
        new GameOverWindow(theLoss);
    }
}
