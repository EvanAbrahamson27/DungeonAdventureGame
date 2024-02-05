package view;

import model.Hero;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame {
    public GameWindow(Hero thePlayer) throws IOException {
        setTitle("Dungeon Escape");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBackground(Color.WHITE);

        add(new ButtonPanel(thePlayer), BorderLayout.SOUTH);
        add(new StatPanel(thePlayer),BorderLayout.EAST);
        add(new LogPanel(),BorderLayout.NORTH);
        add(new MapPanel(),BorderLayout.WEST);
        add(new RoomPanel(),BorderLayout.CENTER);

        setVisible(true);
    }
}
