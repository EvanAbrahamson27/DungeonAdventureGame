/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * A window displayed at the end of the game in a dungeon-crawler style game, indicating whether the player has won or lost.
 * This stage presents different messages and options based on the game's outcome. In the event of a loss, it displays a
 * message encouraging the player to try again. In the event of a victory, it displays a congratulatory
 * message along with a victory image. The window offers options to restart the game, load a saved game, or exit entirely.
 * Additionally, it plays a sound effect.
 */
public class GameOverWindow extends Stage {

    /**
     * Constructs and displays the game over window.
     * Depending on the outcome of the game, the window will display different messages and images to the user.
     * Additionally, it provides buttons to restart the game, load a previous game, or exit the game.
     * A sound effect corresponding to the result (win or loss) will be played upon opening.
     *
     * @param theLoss A boolean indicating whether the player lost the game. True if the player lost, false otherwise.
     */
    public GameOverWindow(final boolean theLoss) {
        setTitle("Game Over!");
        setWidth(400);


        initModality(Modality.APPLICATION_MODAL);


        Label gameOverLabel;
        ImageView victoryImage = new ImageView(new Image("Victory.png"));
        if (theLoss) {
            setHeight(100);
            gameOverLabel = new Label("Game Over! Better luck next time!");
        } else {
            setHeight(400);
            gameOverLabel = new Label("You win!");
        }

        try {
            Media winSound = new Media(Objects.requireNonNull(getClass().getResource("/win.mp3")).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(winSound);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } catch (NullPointerException e) {
            System.out.println("Error loading win sound effect: " + e.getMessage());
        }

        Button continueButton = new Button("Restart");
        continueButton.setOnAction(actionEvent -> {DungeonAdventure.setupGame();close();});

        Button loadButton = new Button("Load Game");

        Button endButton = new Button("End");
        endButton.setOnAction(actionEvent -> System.exit(0));

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(continueButton, loadButton, endButton);
        buttonBox.setSpacing(50);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox();
        if (theLoss) {
            layout.getChildren().addAll(gameOverLabel, buttonBox);
        } else {
            layout.getChildren().addAll(gameOverLabel, victoryImage, buttonBox);
        }
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Scene gameOverScene = new Scene(layout, 400, 100);
        setScene(gameOverScene);

        setOnCloseRequest(event -> System.exit(0));

        show();
    }
}
