package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Priestess;
import model.Thief;
import model.Warrior;

import java.util.Objects;

public class CharacterWindow extends Stage {
    private String myName = "";
    public CharacterWindow() {
        setTitle("Character Select");
        setWidth(800);
        setHeight(500);

        initModality(Modality.APPLICATION_MODAL);

        Label classLabel = new Label("Pick your class and name!");

        TextField nameField = new TextField();
        nameField.setText("Enter your name here");
        nameField.setMaxWidth(600);
        nameField.setOnKeyTyped(keyEvent -> myName = nameField.getText());
        if (myName.equals("")) myName = "Adventurer";

        Button warriorButton = new Button("Warrior");
        warriorButton.setOnAction(actionEvent -> {DungeonAdventure.myHero = new Warrior(myName); close();});

        Button priestessButton = new Button("Priestess");
        priestessButton.setOnAction(actionEvent -> {DungeonAdventure.myHero = new Priestess(myName); close();});

        Button thiefButton = new Button("Thief");
        thiefButton.setOnAction(actionEvent -> {DungeonAdventure.myHero = new Thief(myName); close();});

        ImageView warriorImage = new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource("/images/Warrior.png")).toExternalForm()));
        ImageView priestessImage = new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource("/images/Priestess.png")).toExternalForm()));
        ImageView thiefImage = new ImageView(new Image(Objects.requireNonNull(getClass()
                .getResource("/images/Thief.png")).toExternalForm()));
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
    }
}
