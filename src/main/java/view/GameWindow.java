/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.util.Duration;
import java.util.Objects;

/**
 * The main game window for the dungeon adventure game, handling the overall game interface including starting,
 * loading, and restarting the game. This class extends the Application class of JavaFX and sets up the game's
 * primary Stage including buttons, panels, and other interactive elements. It also manages the background music
 * and the saving and loading of game states.
 */
public class GameWindow extends Application {
    private ButtonPanel myButtonPanel;
    private StatPanel myStatPanel;
    private LogPanel myLogPanel;
    private static MapPanel myMapPanel;
    private RoomPanel myRoomPanel;
    private static BorderPane myBorderPane;

    /**
     * Starts the main game window, setting up the layout, background, buttons, and event handlers. It initializes
     * the game environment and displays the initial game scene with options to start, load, or exit the game.
     *
     * @param theStage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(final Stage theStage) {
        backgroundMusic();

        theStage.setTitle("Dungeon Adventure");
        theStage.setWidth(1200);
        theStage.setHeight(600);

        myBorderPane = new BorderPane();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(myBorderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(myBorderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        myBorderPane.getChildren().add(dungeonImageView);

        Label title = new Label("Dungeon Adventure");
        title.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 50px; -fx-padding: 40 50 10 50; -fx-text-fill: white; -fx-effect: null; -fx-background-color: rgba(0, 0, 0, 0.0);");

        myBorderPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox buttons = new VBox(5);
        buttons.setAlignment(Pos.CENTER);


        Media buttonClickSound = new Media(Objects.requireNonNull(getClass().getResource("/buttonClick.mp3")).toExternalForm());
        MediaPlayer buttonMediaPlayer = new MediaPlayer(buttonClickSound);

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        startButton.setOnAction(e -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            startButtonAction(theStage, myBorderPane, startButton);
            myBorderPane.getChildren().remove(dungeonImageView);});

        Button loadGame = new Button("Load Game");
        loadGame.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        loadGame.setOnAction(e -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            loadGameAction(theStage, myBorderPane, loadGame);
        });

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        helpButton.setOnAction(e -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            helpButtonAction(theStage, myBorderPane, helpButton);
        });

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        exitButton.setOnAction(e -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            theStage.close();
        });
        // exitButtonAction(theStage, borderPane, exitButton));

        helpButton.setMinWidth(185);
        buttons.getChildren().addAll(startButton, loadGame, helpButton, exitButton);
        myBorderPane.setCenter(buttons);

        Scene scene = new Scene(myBorderPane);
        scene.getStylesheets().add("Style.css");
        theStage.setScene(scene);

        theStage.show();

    }

    /**
     * Initiates the start of a new game by opening the character selection window and setting up the game environment.
     * It removes the initial start button and replaces the scene with the game layout, including the map, stat panels,
     * and other game controls.
     *
     * @param theStage The main stage of the application.
     * @param theBorderPane The main layout container for the application.
     * @param theStartButton The start game button.
     */
    private void startButtonAction(final Stage theStage, final BorderPane theBorderPane, final Button theStartButton) {

        GameWindow.myBorderPane.getChildren().remove(theStartButton); // Remove the Start Game button

        new CharacterWindow();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(GameWindow.myBorderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(GameWindow.myBorderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        GameWindow.myBorderPane.getChildren().add(dungeonImageView);

        if (myButtonPanel == null) {
            myLogPanel = new LogPanel();
            myStatPanel = new StatPanel();
            myRoomPanel = new RoomPanel();
            myMapPanel = new MapPanel();
            myButtonPanel = new ButtonPanel(CharacterWindow.myHero, myMapPanel);
        }

        GameWindow.myBorderPane.setBottom(myButtonPanel);
        GameWindow.myBorderPane.setLeft(myMapPanel);
        GameWindow.myBorderPane.setTop(myLogPanel);
        GameWindow.myBorderPane.setRight(myStatPanel);
        GameWindow.myBorderPane.setCenter(myRoomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, myLogPanel);

        GameWindow.myBorderPane.setTop(vBox);

    }

    /**
     * Handles the action for loading a game. This method is intended to be implemented with functionality to load a
     * saved game state.
     *
     * @param theStage The main stage of the application.
     * @param theBorderPane The main layout container for the application.
     * @param theLoadGame The button for loading a game.
     */
    private void loadGameAction(final Stage theStage, final BorderPane theBorderPane, final Button theLoadGame) {
    }

    /**
     * Provides help information to the player. This method can be expanded to display a help dialog or information screen.
     *
     * @param theStage The main stage of the application.
     * @param theBorderPane The main layout container for the application.
     * @param theHelpButton The help button.
     */
    private void helpButtonAction(final Stage theStage, final BorderPane theBorderPane, final Button theHelpButton) {
    }

    /**
     * Creates and returns a MenuBar containing game options like saving and exiting, and debug options for changing
     * the character class and enhancing player abilities for testing purposes.
     *
     * @return A fully constructed MenuBar with file and help options.
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        Menu debugMenu = new Menu("Debug");
        debugMenu.setStyle("-fx-text-fill: black");
        Menu debugClassMenu = new Menu("Change Class");
        debugClassMenu.setStyle("-fx-text-fill: black");

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
        dbClassP.setStyle("-fx-text-fill: black");
        MenuItem dbClassW = new MenuItem("Warrior");
        dbClassW.setStyle("-fx-text-fill: black");
        MenuItem dbClassT = new MenuItem("Thief");
        dbClassT.setStyle("-fx-text-fill: black");
        dbClassP.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Priestess"));
        dbClassW.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Warrior"));
        dbClassT.setOnAction(actionEvent -> CharacterWindow.myDungeonMap.getHero().setClass("Thief"));

        MenuItem dbDamageBoost = new MenuItem("Damage Boost");
        dbDamageBoost.setStyle("-fx-text-fill: black");
        dbDamageBoost.setOnAction(actionEvent ->
                CharacterWindow.myHero.setDamageRange(99999, 100000));
        MenuItem dbHealthBoost = new MenuItem("Health Boost");
        dbHealthBoost.setStyle("-fx-text-fill: black");
        dbHealthBoost.setOnAction(actionEvent ->
                CharacterWindow.myHero.heal(99999));
        MenuItem dbDie = new MenuItem("Die");
        dbDie.setStyle("-fx-text-fill: black");
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

    /**
     * Restarts the game window, clearing all current content and initializing a new game environment.
     */
    public void restartWindow() {

        // Restart the game
        myBorderPane.getChildren().clear();

        // Add game background image
        Image dungeonBackground = new Image("TempDungeonImage.jpg");
        ImageView dungeonImageView = new ImageView(dungeonBackground);

        // Adjust the image to fill the game window
        dungeonImageView.fitWidthProperty().bind(myBorderPane.widthProperty());
        dungeonImageView.fitHeightProperty().bind(myBorderPane.heightProperty());
        dungeonImageView.setPreserveRatio(false);

        // Add image and updated properties to the borderPane to display in the background
        myBorderPane.getChildren().add(dungeonImageView);

        new CharacterWindow();

        if (myButtonPanel == null) {
            myLogPanel = new LogPanel();
            myStatPanel = new StatPanel();
            myRoomPanel = new RoomPanel();
            myMapPanel = new MapPanel();
            myButtonPanel = new ButtonPanel(CharacterWindow.myHero, myMapPanel);
        }

        myBorderPane.setBottom(myButtonPanel);
        myBorderPane.setLeft(myMapPanel);
        myBorderPane.setTop(myLogPanel);
        myBorderPane.setRight(myStatPanel);
        myBorderPane.setCenter(myRoomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, myLogPanel);

        myBorderPane.setTop(vBox);
    }

    /**
     * Launches the application. This is the entry point method called by the JavaFX runtime.
     *
     * @param theArgs Command line arguments passed to the application.
     */
    public static void main(final String[] theArgs) {
        launch(theArgs);
    }


    /**
     * Opens the game over window, displaying a message based on the outcome of the game and providing options to restart or exit.
     *
     * @param theLoss Indicates whether the game ended in a loss (true) or a win (false).
     */
    public static void openGameOverWindow(final boolean theLoss) {
        new GameOverWindow(theLoss);
    }


    /**
     * Opens a dialog for saving the game, offering options to save and continue or save and exit.
     *
     * @param theSaveItem The menu item that triggered the save action.
     * @param theFilename The filename under which to save the game state.
     */
    public void saveGame(final MenuItem theSaveItem, final String theFilename) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Save Game");

        Button sac = new Button("Save and Continue");
        sac.setOnAction(e -> {
            SaveGameContinue(theFilename);
            dialogStage.close(); // Close the dialog when the button is clicked
        });

        Button sae = new Button("Save and Exit");
        sae.setOnAction(e -> {
            SaveGameExit(theFilename);
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

    /**
     * Saves the game state and exits the application.
     *
     * @param theFilename The filename under which to save the game state.
     */
    private void SaveGameExit(String theFilename) {
        try (FileOutputStream fileOS = new FileOutputStream(theFilename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(CharacterWindow.myHero);
            objectOS.writeObject(CharacterWindow.myDungeonMap);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }
        System.exit(0);
    }

    /**
     * Saves the game state and continues playing.
     *
     * @param theFilename The filename under which to save the game state.
     */
    private void SaveGameContinue(String theFilename) {
        try (FileOutputStream fileOS = new FileOutputStream(theFilename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(CharacterWindow.myHero);
            objectOS.writeObject(CharacterWindow.myDungeonMap);

            objectOS.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }
    }

    /**
     * Initializes and plays background music in a loop. Handles exceptions if the music file cannot be found or loaded.
     */
    private void backgroundMusic() {
        try {
            Media backgroundMusic = new Media(Objects.requireNonNull(getClass().getResource("/audio.mp3")).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);

            // Set the music to play in a loop
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            mediaPlayer.setVolume(0.2);

            mediaPlayer.play();
        } catch (NullPointerException e) {
            System.out.println("Error loading background music: " + e.getMessage());
        }
    }

}