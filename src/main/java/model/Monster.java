/**
 * TCSS 360
 * Contributors: Aaniyah Alyes, Belle Kim, Evan Abrahamson, Isabelle del Castillo
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.CharacterWindow;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents a monster character in a dungeon-based game. This class extends the DungeonCharacter class,
 * implementing additional attributes and behaviors specific to monsters, such as healing abilities and room location.
 * Monsters can attack heroes, heal themselves, and are associated with specific rooms in the dungeon.

 */
public class Monster extends DungeonCharacter implements Serializable {
    private double myChanceToHeal;
    private int myHealMin;
    private int myHealMax;
    final private int myRoomX;
    final private int myRoomY;
    private String myImageFile;

    /**
     * Constructs a Monster with specified attributes. This includes combat and healing capabilities, as well as the monster's position in the dungeon.
     *
     * @param theName The name of the monster.
     * @param theHealthPoints The health points of the monster.
     * @param theDamageMin The minimum damage the monster can inflict.
     * @param theDamageMax The maximum damage the monster can inflict.
     * @param theAttackSpeed The attack speed of the monster.
     * @param theChanceToHit The chance the monster has to hit its target.
     * @param theChanceToHeal The chance the monster has to heal itself.
     * @param theHealMin The minimum amount of health the monster can heal.
     * @param theHealMax The maximum amount of health the monster can heal.
     * @param theX The X coordinate of the room where the monster is located.
     * @param theY The Y coordinate of the room where the monster is located.
     * @param theImageFile The file path of the image representing the monster.
     */
    public Monster(final String theName, final int theHealthPoints, final int theDamageMin, final int theDamageMax,
                   final int theAttackSpeed, final double theChanceToHit, final double theChanceToHeal, final int theHealMin,
                   final int theHealMax, final int theX, final int theY, String theImageFile) {
        super(theName, theHealthPoints, theDamageMin, theDamageMax, theAttackSpeed, theChanceToHit);
        this.myChanceToHeal = theChanceToHeal;
        this.myHealMin = theHealMin;
        this.myHealMax = theHealMax;
        this.myRoomX = theX;
        this.myRoomY = theY;
        this.myImageFile = theImageFile;
    }

    /**
     * Executes the monster's actions during its turn in combat. This may include attacking the hero,
     * healing itself, or decrementing the hero's skill cooldown. The actions taken are based on a random choice.
     */
    public void useTurn() {
        if (!getIsDead()) {
            while (getTurns() > 0) {
                Random movePicker = new Random();
                switch (movePicker.nextInt(3)) {
                    case 0, 1 -> {
                        if (!CharacterWindow.myHero.blockAttack()) {
                            attack(CharacterWindow.myHero);} else setTurns(getTurns() - 1);
                        }
                    case 2 -> {heal(5);setTurns(getTurns() - 1);}
                }
            }
            CharacterWindow.myHero.setSkillCooldown(CharacterWindow.myHero.getSkillCooldown() - 1);
            CharacterWindow.myHero.startBattle(this);
        }
    }

//    public ImageView getImage() {
//        Image monsterImage = new Image("Skeleton.png");
//        ImageView monsterImageView = new ImageView(monsterImage);
//        monsterImageView.setFitWidth(25);
//        monsterImageView.setPreserveRatio(true);
//
//        return monsterImageView;
//    }

    /**
     * Retrieves an ImageView object of the monster's image. This can be used for displaying the monster in the UI.
     *
     * @return ImageView containing the monster's image, set with appropriate dimensions and preserving the image ratio.
     */
    public ImageView getImage() {
        Image monsterImage = new Image(myImageFile);
        ImageView monsterImageView = new ImageView(monsterImage);
        monsterImageView.setFitWidth(25);
        monsterImageView.setPreserveRatio(true);
        return monsterImageView;
    }
}
