package view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Hero;

import java.util.Random;

public class ButtonPanel extends BorderPane {
    public ButtonPanel(Hero thePlayer) {
        setStyle("-fx-border-color: black;");
        setCenter(addButtons(thePlayer));
    }

    private HBox addButtons(Hero thePlayer) {
        HBox buttonBox = new HBox(5);
        Button testDamageButton = new Button("Test Damage");
        testDamageButton.setOnAction(e -> {
            Random r = new Random();
            thePlayer.takeDamage(r.nextInt(thePlayer.getDamageMax() + 1 - thePlayer.getDamageMin())
                    + thePlayer.getDamageMin());
        });

        Button testHealButton = new Button("Test Heal");
        testHealButton.setOnAction(e -> thePlayer.heal(5));
        Button useItemButton = new Button("Use Item");
        useItemButton.setOnAction(e -> thePlayer.useItem("Healing Potion", thePlayer)); //Temp just heal

        buttonBox.getChildren().addAll(testDamageButton, testHealButton, useItemButton);

        return buttonBox;
    }
}
