package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RoomPanel extends JPanel {
    RoomPanel() throws IOException {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        BufferedImage backgroundBufImage = ImageIO.read(new File("src/view/TempDungeonImage.jpg"));
        Image backgroundImage = backgroundBufImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        add(new JLabel(new ImageIcon(backgroundImage)));
    }
}
