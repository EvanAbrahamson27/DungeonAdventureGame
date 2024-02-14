package view;

import controller.DungeonAdventure;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.Hero;

public class ButtonPanel extends BorderPane {
    private final Font myFont;

    public ButtonPanel(Hero thePlayer) {
        setStyle("-fx-border-color: black;");
        myFont = new Font("Times New Roman", 30);
        setCenter(addButtons(thePlayer));
    }

    private HBox addButtons(Hero thePlayer) {
        HBox buttonBox = new HBox(5);
        Button attackButton = new Button("Attack " + DungeonAdventure.myMonster.toString());
        attackButton.setOnAction(e -> thePlayer.attack(DungeonAdventure.myMonster));
        attackButton.setFont(myFont);

        Button testHealButton = new Button("Test Heal");
        testHealButton.setOnAction(e -> thePlayer.heal(5));
        testHealButton.setFont(myFont);

        Button useItemButton = new Button("Use Item");
        useItemButton.setOnAction(e -> thePlayer.useItem("Healing Potion", thePlayer)); //Temp just heal
        useItemButton.setFont(myFont);

        buttonBox.getChildren().addAll(attackButton, testHealButton, useItemButton);
        buttonBox.setAlignment(Pos.CENTER);

        return buttonBox;
    }

}
