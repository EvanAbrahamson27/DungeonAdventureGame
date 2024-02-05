package view;

import model.Hero;

import javax.swing.*;
import java.awt.*;

public class StatPanel extends JPanel {
    private JTextArea statsArea;

    StatPanel(Hero thePlayer) {
        setLayout(new GridLayout(3,0));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(new JLabel("Stats:"));

        createStatsList();
        updateStats(thePlayer);
    }

    private void createStatsList() {
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFocusable(false);
        statsArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        add(statsArea);
    }

    private void updateStats(Hero thePlayer) {
        int delay = 100;
        Timer updateTimer = new Timer(delay, e ->
                statsArea.setText("Health: " + thePlayer.getHealthPoints() +
                        "\nAttack Range: " + thePlayer.getDamageMin() + " - " + thePlayer.getDamageMax() +
                        "\nAttack Speed: " + thePlayer.getAttackSpeed() +
                        "\nChance to Hit: " + thePlayer.getChanceToHit() + "%" +
                        "\n\nClass: Hero\nSpecial Skill: Self Heal"));
        updateTimer.start();
    }
}
