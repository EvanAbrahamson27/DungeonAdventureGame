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
import com.google.gson.Gson;
import model.DungeonMap;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import model.Hero;

import javafx.stage.FileChooser;

public class GameWindow extends Application {
    private ButtonPanel myButtonPanel;
    private StatPanel myStatPanel;
    private LogPanel myLogPanel;
    private static MapPanel myMapPanel;
    private RoomPanel myRoomPanel;
    private static BorderPane myBorderPane;
    private static Hero myHero;


    @Override
    public void start(final Stage theStage) {

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


        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        startButton.setOnAction(e -> {startButtonAction(theStage, myBorderPane, startButton);
            myBorderPane.getChildren().remove(dungeonImageView);});
//        borderPane.setCenter(startButton);
//        BorderPane.setAlignment(startButton, Pos.CENTER);

        Button loadGame = new Button("Load Game");
        loadGame.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        loadGame.setOnAction(e -> loadGameAction());
//        borderPane.setCenter(loadGame);
//        BorderPane.setAlignment(loadGame, Pos.TOP_CENTER);

        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        helpButton.setOnAction(e -> helpButtonAction(theStage, myBorderPane, helpButton));

        Button exitButton = new Button("Exit Game");
        exitButton.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 15px; -fx-padding: 10 50 10 50; -fx-background-color: maroon; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        exitButton.setOnAction(e -> theStage.close());
        // exitButtonAction(theStage, borderPane, exitButton));

        helpButton.setMinWidth(185);
        buttons.getChildren().addAll(startButton, loadGame, helpButton, exitButton);
        myBorderPane.setCenter(buttons);

        Scene scene = new Scene(myBorderPane);
        scene.getStylesheets().add("Style.css");
        theStage.setScene(scene);

        theStage.show();

    }

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

        this.myHero = CharacterWindow.myHero;

    }



    private void helpButtonAction(final Stage theStage, final BorderPane theBorderPane, final Button theHelpButton) {
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        Menu debugMenu = new Menu("Debug");
        debugMenu.setStyle("-fx-text-fill: black");
        Menu debugClassMenu = new Menu("Change Class");
        debugClassMenu.setStyle("-fx-text-fill: black");

        MenuItem saveItem = new MenuItem("Save");
        MenuItem loadItem = new MenuItem("Load");
        saveItem.setStyle("-fx-text-fill: black");
        loadItem.setStyle("-fx-text-fill: black");
        int randomVal = (int) (Math.random() * 1000);
        String saveFileName = "./saved-games/save-game-" + randomVal + ".ser";
        saveItem.setOnAction(e -> saveGame(saveItem, saveFileName)); // Invoke saveGame method
        loadItem.setOnAction(e -> loadGameAction()); // Invoke loadGame method
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

        fileMenu.getItems().addAll(saveItem, loadItem, restartItem, exitItem);
        helpMenu.getItems().addAll(helpItem, debugMenu);
        debugMenu.getItems().addAll(debugClassMenu, dbDamageBoost, dbHealthBoost, dbDie);
        debugClassMenu.getItems().addAll(dbClassP, dbClassT, dbClassW);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        menuBar.setStyle("-fx-background-color: black;");

        return menuBar;
    }

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

        this.myHero = CharacterWindow.myHero;
    }

    public static void main(final String[] theArgs) {
        launch(theArgs);
    }

    public static void openGameOverWindow(final boolean theLoss) {
        new GameOverWindow(theLoss);
    }

    private void saveGame(final MenuItem theSaveItem, final String theFilename) {
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
    private void SaveGameExit(String theFilename) {

        SavedState savedState = new SavedState(CharacterWindow.myDungeonMap, CharacterWindow.myDungeonMap.getHero());

        try (FileOutputStream fileOS = new FileOutputStream(theFilename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(savedState);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }
        System.exit(0);
    }

    private void SaveGameContinue(String theFilename) {
        SavedState savedState = new SavedState(CharacterWindow.myDungeonMap, CharacterWindow.myDungeonMap.getHero());

        try (FileOutputStream fileOS = new FileOutputStream(theFilename); ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {

            objectOS.writeObject(savedState);
            objectOS.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was an error saving the game.");
        }
    }

    private void loadGameAction() {

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Load Game");

        // List files in "saved-games" directory
        ArrayList<String> fileNames = new ArrayList<>();
        try {
            File savedGameDir = new File("./saved-games");
            File[] savedGameFiles = savedGameDir.listFiles();

            for (int i = 0; i < savedGameFiles.length; i++) {
                fileNames.add(savedGameFiles[i].getName());
            }
        } catch (NullPointerException e) {
            System.out.println("Error loading files from 'saved-games' directory.");
        }

//        System.out.println("List of saved games: " + fileNames.toString());

        // Create buttons for each saved game option
        ArrayList<Button> loadGameButtons = new ArrayList<>();
        for (String fileName : fileNames) {
            Button loadGame = new Button(fileName);
            loadGame.setOnAction(e -> {
                loadGame(fileName);
                dialogStage.close();
            });

            loadGameButtons.add(loadGame);
        }

        // Add buttons to a layout
        VBox dialogVBox = new VBox(20);
        dialogVBox.getChildren().addAll(new Label("Choose a saved game:"));

        for (Button button : loadGameButtons) {
            dialogVBox.getChildren().add(button);
        }

        dialogVBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dialogVBox);

        // Position items in scroll pane to center
        scrollPane.setFitToWidth(true);

        // Create a scene for the dialog and set it to the stage
        Scene dialogScene = new Scene(scrollPane, 300, 200);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void loadGame(final String theFilename) {

        try (FileInputStream fileIS = new FileInputStream("./saved-games/" + theFilename); ObjectInputStream objectIS = new ObjectInputStream(fileIS)) {

            SavedState savedState = (SavedState) objectIS.readObject();

            restartWindow(savedState.getMyHero(), savedState.getMyDungeonMap());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("There was an error loading the game.");
        }
    }

    public void restartWindow(Hero theHero, DungeonMap theDungeonMap) {

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

        new CharacterWindow(theHero, theDungeonMap);

        myLogPanel = new LogPanel();
        myStatPanel = new StatPanel();
        myRoomPanel = new RoomPanel();
        myMapPanel = new MapPanel();
        myButtonPanel = new ButtonPanel(theHero, myMapPanel);

        myBorderPane.setBottom(myButtonPanel);
        myBorderPane.setLeft(myMapPanel);
        myBorderPane.setTop(myLogPanel);
        myBorderPane.setRight(myStatPanel);
        myBorderPane.setCenter(myRoomPanel);

        MenuBar menuBar = createMenuBar();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, myLogPanel);

        myBorderPane.setTop(vBox);

        this.myHero = theHero;
    }

}