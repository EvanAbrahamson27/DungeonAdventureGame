package view;

import model.DungeonCharacter;
import model.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ButtonPanel extends JPanel {
    public ButtonPanel(Hero thePlayer) {
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addButtons(thePlayer);
    }

    private void addButtons(DungeonCharacter theTarget) {
        JButton button = new JButton("Test Damage");
        button.addActionListener(e -> {
            Random r = new Random();
            theTarget.takeDamage(r.nextInt(theTarget.getDamageMax() - theTarget.getDamageMin())
                    + theTarget.getDamageMin());
        });
        add(button);
        button = new JButton("Test Heal");
        button.addActionListener(e -> theTarget.heal(5));
        add(button);
    }
}
