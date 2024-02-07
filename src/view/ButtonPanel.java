package view;

import model.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// import javafx.scene.layout.BorderPane;




public class ButtonPanel extends JPanel {
    public ButtonPanel(Hero thePlayer) {
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addButtons(thePlayer);
    }

    private void addButtons(Hero thePlayer) {
        JButton button = new JButton("Test Damage");
        button.addActionListener(e -> {
            Random r = new Random();
            thePlayer.takeDamage(r.nextInt(thePlayer.getDamageMax() + 1 - thePlayer.getDamageMin())
                    + thePlayer.getDamageMin());
        });
        add(button);
        button = new JButton("Test Heal");
        button.addActionListener(e -> thePlayer.heal(5));
        add(button);
        button = new JButton("Use Item");
        button.addActionListener(e -> thePlayer.useItem("Healing Potion", thePlayer)); //Temp just heal
        add(button);
    }
}
