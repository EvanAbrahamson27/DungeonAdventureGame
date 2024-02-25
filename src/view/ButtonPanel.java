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

import java.util.ArrayList;
import java.util.List;

public class ButtonPanel extends BorderPane {
    private final Font myFont;
    private Button myAttackButton;
    private Button myUseItemButton;
    private Button mySkillButton;
    private final List<Button> myMovementButtons;

    public ButtonPanel() {
        myMovementButtons = new ArrayList<>();
        setStyle("-fx-border-color: black;");
        myFont = new Font("Times New Roman", 30);
        setCenter(addButtons());
        updateButtons();
    }

    private HBox addButtons() {
        HBox buttonBox = new HBox(5);

        myAttackButton = new Button("Attack");
        myAttackButton.setOnAction(e -> DungeonAdventure.myHero.attack(DungeonAdventure.myMonster));
        disableButton(myAttackButton);
        myAttackButton.setFont(myFont);

        mySkillButton = new Button("Use Skill: " + DungeonAdventure.myHero.getSkillName() + "!");
        mySkillButton.setOnAction(e -> DungeonAdventure.myHero.performSpecialSkill());
        disableButton(mySkillButton);
        mySkillButton.setFont(myFont);

        myUseItemButton = new Button("Use Item");
        myUseItemButton.setOnAction(e -> DungeonAdventure.myHero.useItem(
                "Healing Potion", DungeonAdventure.myHero)); //Temp just heal
        myUseItemButton.setFont(myFont);

        Button NorthButton = new Button("Move North");
        NorthButton.setOnAction(e -> DungeonAdventure.myHero.move(
                DungeonAdventure.myHero.getX(), DungeonAdventure.myHero.getY() + 1));
        NorthButton.setFont(myFont);
        Button WestButton = new Button("Move West");
        WestButton.setOnAction(e -> DungeonAdventure.myHero.move(
                DungeonAdventure.myHero.getX() - 1, DungeonAdventure.myHero.getY()));
        WestButton.setFont(myFont);
        Button SouthButton = new Button("Move South");
        SouthButton.setOnAction(e -> DungeonAdventure.myHero.move(
                DungeonAdventure.myHero.getX(), DungeonAdventure.myHero.getY() - 1));
        SouthButton.setFont(myFont);
        Button EastButton = new Button("Move East");
        EastButton.setOnAction(e -> DungeonAdventure.myHero.move(
                DungeonAdventure.myHero.getX() + 1, DungeonAdventure.myHero.getY()));
        EastButton.setFont(myFont);

        myMovementButtons.add(NorthButton);
        myMovementButtons.add(WestButton);
        myMovementButtons.add(SouthButton);
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
            if (!mySkillButton.getText().equals(
                    "Use Skill: " + DungeonAdventure.myHero.getSkillName() + "!")) {
                mySkillButton.setText("Use Skill: " + DungeonAdventure.myHero.getSkillName() + "!");
            }

            if (DungeonAdventure.myHero.getIsDead()) {
                for (Button button : myMovementButtons) {
                    disableButton(button);
                }
                disableButton(myAttackButton);
                disableButton(mySkillButton);
                disableButton(myUseItemButton);
            }
            if (DungeonAdventure.myMonster != null && !DungeonAdventure.myMonster.getIsDead() &&
                !DungeonAdventure.myHero.getIsDead()) {
                if (myAttackButton.isDisabled()) enableButton(myAttackButton);
                if (DungeonAdventure.myHero.getSkillCooldown() <= 0 && !DungeonAdventure.myHero.getIsDead()) {
                    enableButton(mySkillButton);
                } else {
                    disableButton(mySkillButton);
                }
                for (Button button : myMovementButtons) {
                    disableButton(button);
                    canMove[0] = false;
                }
            }
            else if ((DungeonAdventure.myMonster == null || DungeonAdventure.myMonster.getIsDead())) {
                if (!myAttackButton.isDisabled()) {
                    disableButton(myAttackButton);
                    disableButton(mySkillButton);
                    for (Button button : myMovementButtons) {
                        enableButton(button);
                    }
                }
                canMove[0] = true;
            }

            if (DungeonAdventure.myHero.getInventory().size() == 0 && !myUseItemButton.isDisabled())
                disableButton(myUseItemButton);
            else if (DungeonAdventure.myHero.getInventory().size() > 0 && myUseItemButton.isDisabled()
            && !DungeonAdventure.myHero.getIsDead())
                enableButton(myUseItemButton);

            if (canMove[0] && !DungeonAdventure.myHero.getIsDead()) {
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
