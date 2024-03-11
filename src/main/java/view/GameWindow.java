package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GameWindow extends Application {
    protected ButtonPanel buttonPanel;
    protected StatPanel statPanel;
    protected LogPanel logPanel;
    protected static MapPanel mapPanel;
    protected RoomPanel roomPanel;
    protected static BorderPane borderPane;

    @Override
    public void start(Stage theStage) {

        theStage.setTitle("Dungeon Adventure");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        this.borderPane = new BorderPane();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(this.borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(this.borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        this.borderPane.getChildren().add(dungeonImageView);

        Label title = new Label("Dungeon Adventure");
        title.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 50px; -fx-padding: 40 50 10 50; -fx-text-fill: white; -fx-effect: null; -fx-background-color: rgba(0, 0, 0, 0.0);");

        this.borderPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox buttons = new VBox(5);
        buttons.setAlignment(Pos.CENTER);


        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        startButton.setOnAction(e -> {startButtonAction(theStage, this.borderPane, startButton);
            this.borderPane.getChildren().remove(dungeonImageView);});
//        borderPane.setCenter(startButton);
//        BorderPane.setAlignment(startButton, Pos.CENTER);

        Button loadGame = new Button("Load Game");
        loadGame.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        loadGame.setOnAction(e -> loadGameAction(theStage, this.borderPane, loadGame));
//        borderPane.setCenter(loadGame);
//        BorderPane.setAlignment(loadGame, Pos.TOP_CENTER);

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        helpButton.setOnAction(e -> helpButtonAction(theStage, this.borderPane, helpButton));

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        exitButton.setOnAction(e -> theStage.close());
        // exitButtonAction(theStage, borderPane, exitButton));

        helpButton.setMinWidth(185);
        buttons.getChildren().addAll(startButton, loadGame, helpButton, exitButton);
        this.borderPane.setCenter(buttons);

        Scene scene = new Scene(this.borderPane);
        scene.getStylesheets().add("Style.css");
        theStage.setScene(scene);

        theStage.show();

    }

    private void startButtonAction(Stage theStage, BorderPane borderPane, Button startButton) {
        this.borderPane.getChildren().remove(startButton); // Remove the Start Game button

        new CharacterWindow();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(this.borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(this.borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        this.borderPane.getChildren().add(dungeonImageView);

        if (buttonPanel == null) {
            logPanel = new LogPanel();
            statPanel = new StatPanel();
            roomPanel = new RoomPanel();
            mapPanel = new MapPanel();
            buttonPanel = new ButtonPanel(CharacterWindow.myHero, mapPanel);
        }

        this.borderPane.setBottom(buttonPanel);
        this.borderPane.setLeft(mapPanel);
        this.borderPane.setTop(logPanel);
        this.borderPane.setRight(statPanel);
        this.borderPane.setCenter(roomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, logPanel);

        this.borderPane.setTop(vBox);

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
        saveItem.setStyle("-fx-text-fill: black");
        saveItem.setOnAction(e -> saveGame(saveItem, "savegame")); // Invoke saveGame method
        MenuItem restartItem = new MenuItem("Restart");
        restartItem.setStyle("-fx-text-fill: black");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setStyle("-fx-text-fill: black");
        MenuItem helpItem = new MenuItem("Help");
        helpItem.setStyle("-fx-text-fill: black");


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

        return menuBar;
    }

    public void saveGame(MenuItem saveItem, String filename) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Save Game");

        Button sac = new Button("Save and Continue");
        sac.setOnAction(e -> {
            SaveGameContinue(filename);
            dialogStage.close(); // Close the dialog when the button is clicked
        });

        Button sae = new Button("Save and Exit");
        sae.setOnAction(e -> {
            SaveGameExit(filename);
            dialogStage.close(); // Close the dialog when the button is clicked
        });

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(sac, sae);
        buttonsBox.setAlignment(Pos.CENTER);

        VBox dialogVBox = new VBox(20);
        dialogVBox.getChildren().addAll(new Label("Save Options"), buttonsBox);
        dialogVBox.setAlignment(Pos.CENTER);

        Scene dialogScene = new Scene(dialogVBox, 300, 150);
        dialogStage.setScene(dialogScene);
        dialogStage.show();

    }

    private void SaveGameExit(String filename) {
        try (FileOutputStream fileOS = new FileOutputStream(filename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(CharacterWindow.myHero);
            objectOS.writeObject(CharacterWindow.myDungeonMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }
        System.exit(0);
    }

    private void SaveGameContinue(String filename) {
        try (FileOutputStream fileOS = new FileOutputStream(filename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(CharacterWindow.myHero);
            objectOS.writeObject(CharacterWindow.myDungeonMap);

            objectOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }

    }

    public void restartWindow() {

        // Restart the game
        this.borderPane.getChildren().clear();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(this.borderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(this.borderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        this.borderPane.getChildren().add(dungeonImageView);

        new CharacterWindow();

        if (buttonPanel == null) {
            logPanel = new LogPanel();
            statPanel = new StatPanel();
            roomPanel = new RoomPanel();
            mapPanel = new MapPanel();
            buttonPanel = new ButtonPanel(CharacterWindow.myHero, mapPanel);
        }

        this.borderPane.setBottom(buttonPanel);
        this.borderPane.setLeft(mapPanel);
        this.borderPane.setTop(logPanel);
        this.borderPane.setRight(statPanel);
        this.borderPane.setCenter(roomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, logPanel);

        this.borderPane.setTop(vBox);
    }

    public static void main(String[] theArgs) {
        launch(theArgs);
    }

    public static void openGameOverWindow() {
        new GameOverWindow();
    }
}
