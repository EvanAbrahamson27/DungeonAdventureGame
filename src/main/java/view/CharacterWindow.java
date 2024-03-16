/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DungeonMap;
import model.Hero;
import java.util.Objects;

/**
 * A window for character selection in a dungeon-crawler style game.
 * This stage allows players to select their character class and enter a name before starting the game.
 * The window includes options for choosing between different character classes, inputting a player name,
 * and visual representations of each character class. Selections made in this window initialize the game's hero
 * and dungeon map based on the player's choices.
 * The window plays a sound upon button click, and sets the stage for the game's starting point
 * by selecting a character class and defining the player's name.
 * After selection, the window will close, and the game initializes the player's character and the dungeon map accordingly.
 */
public class CharacterWindow extends Stage {
    private String myName = "";
    private String myCharacterSelect = "";
    public static DungeonMap myDungeonMap;
    public static Hero myHero;

    /**
     * Constructs and displays the character selection window.
     * Initializes UI components necessary for character selection, including text fields, buttons, and images.
     * Sets up event handlers for the interaction within this window and starts the game with the selected character.
     */
    public CharacterWindow() {
        setTitle("Character Select");
        setWidth(800);
        setHeight(500);

        initModality(Modality.APPLICATION_MODAL);

        javafx.scene.media.Media buttonClickSound = new Media(Objects.requireNonNull(getClass().getResource("/buttonClick.mp3")).toExternalForm());
        MediaPlayer buttonMediaPlayer = new MediaPlayer(buttonClickSound);

        Label classLabel = new Label("Pick your class and name!");

        TextField nameField = new TextField();
        nameField.setText("Enter your name here");
        nameField.setMaxWidth(600);
        nameField.setOnKeyTyped(keyEvent -> myName = nameField.getText());
        if (myName.equals("")) myName = "Adventurer";


        Button warriorButton = new Button("Warrior");
        warriorButton.setOnAction(actionEvent -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            this.myCharacterSelect = "Warrior"; close();});

        Button priestessButton = new Button("Priestess");
        priestessButton.setOnAction(actionEvent -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            this.myCharacterSelect = "Priestess"; close();});

        Button thiefButton = new Button("Thief");
        thiefButton.setOnAction(actionEvent -> {
            buttonMediaPlayer.stop();
            buttonMediaPlayer.play();
            this.myCharacterSelect = "Thief"; close();});

        ImageView warriorImage = new ImageView(new Image("Warrior.png"));
        ImageView priestessImage = new ImageView(new Image("Priestess.png"));
        ImageView thiefImage = new ImageView(new Image("Thief.png"));
        warriorImage.setPreserveRatio(true);
        warriorImage.setFitWidth(100);
        priestessImage.setPreserveRatio(true);
        priestessImage.setFitWidth(100);
        thiefImage.setPreserveRatio(true);
        thiefImage.setFitWidth(100);

        HBox imageBox = new HBox();
        imageBox.getChildren().addAll(warriorImage, priestessImage, thiefImage);
        imageBox.setSpacing(150);
        imageBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(warriorButton, priestessButton, thiefButton);
        buttonBox.setSpacing(150);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        layout.getChildren().addAll(classLabel, nameField, imageBox, buttonBox);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(50);

        Scene characterScene = new Scene(layout, 800, 500);
        setScene(characterScene);

        showAndWait();

        myDungeonMap = new DungeonMap(myCharacterSelect, myName);
        myHero = myDungeonMap.getHero();
        myHero.setRoom(myDungeonMap.getRoomAtLocation(myHero.getX(), myHero.getY()));
    }
}
