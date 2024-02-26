package view;

import controller.DungeonAdventure;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Hero;

import java.util.ArrayList;
import java.util.List;

public class ButtonPanel extends BorderPane {
    private final Font myFont;
    private Button myAttackButton;
    private Button myUseItemButton;
    private Button mySkillButton;
    private final List<Button> myMovementButtons;
    private MapPanel myMapPanel;

    public ButtonPanel(Hero thePlayer, MapPanel mapPanel) {
        this.myMapPanel = mapPanel;
        myMovementButtons = new ArrayList<>();
        setStyle("-fx-border-color: black;");
        myFont = new Font("Times New Roman", 30);
        setCenter(addButtons(thePlayer));
        updateButtons();
    }

    private HBox addButtons(Hero thePlayer) {
        HBox buttonBox = new HBox(5);

        myAttackButton = new Button("Attack");
        myAttackButton.setOnAction(e -> thePlayer.attack(DungeonAdventure.myMonster));
        disableButton(myAttackButton);
        myAttackButton.setFont(myFont);

        mySkillButton = new Button("Use Skill: " + thePlayer.getSkillName() + "!");
        mySkillButton.setOnAction(e -> thePlayer.performSpecialSkill());
        disableButton(mySkillButton);
        mySkillButton.setFont(myFont);

        myUseItemButton = new Button("Use Item");
        myUseItemButton.setOnAction(e -> thePlayer.useItem("Healing Potion", thePlayer)); //Temp just heal
        myUseItemButton.setFont(myFont);

        Button NorthButton = new Button("Move North");
        NorthButton.setOnAction(e -> {
            thePlayer.move(thePlayer.getX(), thePlayer.getY() - 1);
            myMapPanel.refreshMap();
        });
        NorthButton.setFont(myFont);
        Button WestButton = new Button("Move West");
        WestButton.setOnAction(e -> {
            thePlayer.move(thePlayer.getX() - 1, thePlayer.getY());
            myMapPanel.refreshMap();
        });
        WestButton.setFont(myFont);
        Button SouthButton = new Button("Move South");
        SouthButton.setOnAction(e -> {
            thePlayer.move(thePlayer.getX(), thePlayer.getY() + 1);
            myMapPanel.refreshMap();
        });
        SouthButton.setFont(myFont);
        Button EastButton = new Button("Move East");
        EastButton.setOnAction(e -> {
            thePlayer.move(thePlayer.getX() + 1, thePlayer.getY());
            myMapPanel.refreshMap();
        });
        EastButton.setFont(myFont);

        myMovementButtons.add(SouthButton);
        myMovementButtons.add(WestButton);
        myMovementButtons.add(NorthButton);
        myMovementButtons.add(EastButton);

        buttonBox.getChildren().addAll(myAttackButton, mySkillButton, myUseItemButton, NorthButton,
                WestButton, SouthButton, EastButton);
        buttonBox.setAlignment(Pos.CENTER);

        return buttonBox;
    }

    private void enableButton(final Button theButton) {
        theButton.setDisable(false);
        theButton.setStyle("-fx-opacity: 1.0;");
    }
    private void disableButton(final Button theButton) {
        theButton.setDisable(true);
        theButton.setStyle("-fx-opacity: 0.5;");
    }

    private void updateButtons() {
        final boolean[] canMove = {true};
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (DungeonAdventure.myMonster != null && !DungeonAdventure.myMonster.getIsDead()) {
                if (myAttackButton.isDisabled()) enableButton(myAttackButton);
                if (DungeonAdventure.myHero.getSkillCooldown() <= 0) {
                    enableButton(mySkillButton);
                } else {
                    disableButton(mySkillButton);
                }
                for (Button button : myMovementButtons) {
                    disableButton(button);
                    canMove[0] = false;
                }
            }
            else if ((DungeonAdventure.myMonster == null || DungeonAdventure.myMonster.getIsDead())
                    && !myAttackButton.isDisabled()) {
                disableButton(myAttackButton);
                disableButton(mySkillButton);
                for (Button button : myMovementButtons) {
                    enableButton(button);
                    canMove[0] = true;
                }
            }

            if (DungeonAdventure.myHero.getInventory().size() == 0 && !myUseItemButton.isDisabled())
                disableButton(myUseItemButton);
            else if (DungeonAdventure.myHero.getInventory().size() > 0 && myUseItemButton.isDisabled())
                enableButton(myUseItemButton);

            if (canMove[0]) {
                if (DungeonAdventure.myDungeonMap.getRoomAtLocation(DungeonAdventure.myHero.getX(),
                        DungeonAdventure.myHero.getY() + 1) == null) disableButton(myMovementButtons.get(0));
                else enableButton(myMovementButtons.get(0));
                if (DungeonAdventure.myDungeonMap.getRoomAtLocation(DungeonAdventure.myHero.getX() - 1,
                        DungeonAdventure.myHero.getY()) == null) disableButton(myMovementButtons.get(1));
                else enableButton(myMovementButtons.get(1));
                if (DungeonAdventure.myDungeonMap.getRoomAtLocation(DungeonAdventure.myHero.getX(),
                        DungeonAdventure.myHero.getY() - 1) == null) disableButton(myMovementButtons.get(2));
                else enableButton(myMovementButtons.get(2));
                if (DungeonAdventure.myDungeonMap.getRoomAtLocation(DungeonAdventure.myHero.getX() + 1,
                        DungeonAdventure.myHero.getY()) == null) disableButton(myMovementButtons.get(3));
                else enableButton(myMovementButtons.get(3));
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
