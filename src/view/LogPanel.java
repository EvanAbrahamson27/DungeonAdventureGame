package view;

import controller.DungeonAdventure;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {
    final private JTextArea log;
    LogPanel() {
        setLayout(new GridLayout(1,0));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(new JLabel("Game Log:"));
        log = new JTextArea();
        log.setEditable(false);
        log.setFocusable(false);
        log.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        log.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(log);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(log);
        updateLog();
        setSize(500, 200);
    }

    private void updateLog() {
        int delay = 100;
        Timer updateTimer = new Timer(delay, e ->
                log.setText(DungeonAdventure.getLog()));
        updateTimer.start();
    }
}
