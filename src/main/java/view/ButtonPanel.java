/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package view;

import model.*;
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

/**
 * The ButtonPanel class is a component of the game's user interface in a dungeon-crawler style game.
 * It extends BorderPane and contains control buttons for player actions.
 * This class is responsible for managing the interaction between the player's actions and the game's mechanics,
 */
public class ButtonPanel extends BorderPane {
    private final Font myFont;
    private Button myAttackButton;
    private Button myUseItemButton;
    private Button mySkillButton;
    private final List<Button> myMovementButtons;
    private final MapPanel myMapPanel;
    private final Hero myPlayer;

    /**
     * Constructs a ButtonPanel associated with a specific Hero and MapPanel. This panel includes control buttons for player actions such as moving, attacking, using skills, and using items.
     *
     * @param thePlayer The Hero associated with this button panel.
     * @param mapPanel The MapPanel displaying the current dungeon map associated with this button panel.
     */
    public ButtonPanel(final Hero thePlayer, final MapPanel mapPanel) {
        this.myPlayer = thePlayer;
        this.myMapPanel = mapPanel;
        myMovementButtons = new ArrayList<>();
        setStyle("-fx-border-color: black;");
        myFont = new Font("Times New Roman", 20);
        setCenter(addButtons());
        updateButtons();
    }

    /**
     * Creates and arranges all control buttons including movement, attack, skill, and item use buttons. Sets up their initial properties and event handlers.
     *
     * @return HBox containing all the control buttons arranged horizontally.
     */
    private HBox addButtons() {
        HBox buttonBox = new HBox(5);

        myAttackButton = new Button("Attack");
        myAttackButton.setOnAction(e -> CharacterWindow.myHero.attack(DungeonAdventure.myMonster));
        disableButton(myAttackButton);
        myAttackButton.setFont(myFont);

        mySkillButton = new Button("Use Skill: " + CharacterWindow.myHero.getSkillName() + "!");
        mySkillButton.setOnAction(e -> CharacterWindow.myHero.performSpecialSkill());
        disableButton(mySkillButton);
        mySkillButton.setFont(myFont);

        myUseItemButton = new Button("Use Item");
        myUseItemButton.setOnAction(e -> CharacterWindow.myHero.useItem(
                "Healing Potion", CharacterWindow.myHero)); //Temp just heal
        myUseItemButton.setFont(myFont);

        Button NorthButton = new Button("Move North");
        NorthButton.setOnAction(e -> {
            this.myPlayer.move(this.myPlayer.getX(), this.myPlayer.getY() - 1);
            myMapPanel.refreshMap();
        });
        NorthButton.setFont(myFont);
        Button WestButton = new Button("Move West");
        WestButton.setOnAction(e -> {
            this.myPlayer.move(this.myPlayer.getX() - 1, this.myPlayer.getY());
            myMapPanel.refreshMap();
        });
        WestButton.setFont(myFont);
        Button SouthButton = new Button("Move South");
        SouthButton.setOnAction(e -> {
            this.myPlayer.move(this.myPlayer.getX(), this.myPlayer.getY() + 1);
            myMapPanel.refreshMap();
        });
        SouthButton.setFont(myFont);
        Button EastButton = new Button("Move East");
        EastButton.setOnAction(e -> {
            this.myPlayer.move(this.myPlayer.getX() + 1, this.myPlayer.getY());
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

    /**
     * Enables a button, making it clickable and fully opaque.
     *
     * @param theButton The button to be enabled.
     */
    private void enableButton(final Button theButton) {
        theButton.setDisable(false);
        theButton.setStyle("-fx-opacity: 1.0;");
    }


    /**
     * Disables a button, making it unclickable and partially transparent.
     *
     * @param theButton The button to be disabled.
     */
    private void disableButton(final Button theButton) {
        theButton.setDisable(true);
        theButton.setStyle("-fx-opacity: 0.5;");
    }

    /**
     * Regularly updates the buttons' states based on the current game status. This includes enabling
     * or disabling buttons based on the Hero's and the Monster's states, updating the skill button text,
     * and controlling the visibility and usability of movement buttons based on the dungeon map and hero's position.
     */
    private void updateButtons() {
        final boolean[] canMove = {true};
        Timeline updateTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!mySkillButton.getText().equals(
                    "Use Skill: " + CharacterWindow.myHero.getSkillName() + "!")) {
                mySkillButton.setText("Use Skill: " + CharacterWindow.myHero.getSkillName() + "!");
            }

            if (CharacterWindow.myHero.getIsDead()) {
                for (Button button : myMovementButtons) {
                    disableButton(button);
                }
                disableButton(myAttackButton);
                disableButton(mySkillButton);
                disableButton(myUseItemButton);
            }
            if (DungeonAdventure.myMonster != null && !DungeonAdventure.myMonster.getIsDead() &&
                !CharacterWindow.myHero.getIsDead()) {
                if (myAttackButton.isDisabled()) enableButton(myAttackButton);
                if (CharacterWindow.myHero.getSkillCooldown() <= 0 && !CharacterWindow.myHero.getIsDead()) {
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

            if (CharacterWindow.myHero.getInventory().size() == 0 && !myUseItemButton.isDisabled())
                disableButton(myUseItemButton);
            else if (CharacterWindow.myHero.getInventory().size() > 0 && myUseItemButton.isDisabled()
            && !CharacterWindow.myHero.getIsDead())
                enableButton(myUseItemButton);

            if (canMove[0] && !CharacterWindow.myHero.getIsDead()) {
                if (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX(),
                        CharacterWindow.myHero.getY() + 1) == null ||
                        (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX(),
                                CharacterWindow.myHero.getY() + 1).isWall()))
                    disableButton(myMovementButtons.get(0));
                else enableButton(myMovementButtons.get(0));
                if (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX() - 1,
                        CharacterWindow.myHero.getY()) == null ||
                        (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX() - 1,
                                CharacterWindow.myHero.getY()).isWall()))
                    disableButton(myMovementButtons.get(1));
                else enableButton(myMovementButtons.get(1));
                if (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX(),
                        CharacterWindow.myHero.getY() - 1) == null ||
                        (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX(),
                        CharacterWindow.myHero.getY() - 1).isWall()))
                    disableButton(myMovementButtons.get(2));
                else enableButton(myMovementButtons.get(2));
                if (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX() + 1,
                        CharacterWindow.myHero.getY()) == null ||
                        (CharacterWindow.myDungeonMap.getRoomAtLocation(CharacterWindow.myHero.getX() + 1,
                                CharacterWindow.myHero.getY()).isWall()))
                    disableButton(myMovementButtons.get(3));
                else enableButton(myMovementButtons.get(3));
            }
        }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }
}
