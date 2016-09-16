package org.jscreened.ui;

import org.jscreened.util.ResourceHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 22-11-2015
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public class About extends JDialog {

    private final BufferedImage image = ResourceHelper.loadImage("/about.png");

    public About() {
        super();
        setTitle("About");
        setLayout(new BorderLayout());
        add(new JPanel() {
            { setPreferredSize(new Dimension(getWidth(), image.getHeight(null))); }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int width = (getWidth() / 2) - (image.getWidth(null) / 2);
                final int height = (getHeight() / 2) - (image.getHeight(null) / 2);
                g.drawImage(image, width, height, image.getWidth(null), image.getHeight(null), this);
            }
        }, BorderLayout.NORTH);

        setPreferredSize(new Dimension(400, 275));
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);

        pack();
        setResizable(false);
        setVisible(true);
    }
}
