package com.kschaap1994.jscreened.ui;

import com.kschaap1994.imgur.ImgurClient;
import org.apache.commons.imaging.ImageFormats;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class SnipIt extends JFrame {

    public SnipIt() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(new SnipItPane());
        setBounds(getVirtualBounds());
        setVisible(true);
    }

    public class SnipItPane extends JPanel {

        private Point mouseAnchor;
        private Point dragPoint;

        private SelectionPane selectionPane;
        private int x;
        private int y;
        private int width;
        private int height;

        private SnipItPane() {
            setOpaque(false);
            setLayout(null);
            selectionPane = new SelectionPane();
            add(selectionPane);

            MouseAdapter adapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseAnchor = e.getPoint();
                    dragPoint = null;
                    selectionPane.setLocation(mouseAnchor);
                    selectionPane.setSize(0, 0);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    dragPoint = e.getPoint();
                    width = dragPoint.x - mouseAnchor.x;
                    height = dragPoint.y - mouseAnchor.y;

                    x = mouseAnchor.x;
                    y = mouseAnchor.y;

                    if (width < 0) {
                        x = dragPoint.x;
                        width *= -1;
                    }
                    if (height < 0) {
                        y = dragPoint.y;
                        height *= -1;
                    }
                    selectionPane.setBounds(x, y, width, height);
                    selectionPane.revalidate();
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    SnipIt.super.dispose();
                    try {
                        final Robot robot = new Robot();
                        final BufferedImage image = robot.createScreenCapture(new Rectangle(x, y, width, height));

                        final ImgurClient client = new ImgurClient("fdaa08c932d9a7e");
                        final com.kschaap1994.imgur.model.Image imgurImage = client.uploadImage(image, ImageFormats.PNG);

                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().browse(new URI(imgurImage.link));
                        }

                    } catch (AWTException | URISyntaxException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            };
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            final Graphics2D g2d = (Graphics2D) g.create();

            final Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
            final Area area = new Area(bounds);
            area.subtract(new Area(selectionPane.getBounds()));

            g2d.setColor(new Color(192, 192, 192, 64));
            g2d.fill(area);

        }
    }

    public class SelectionPane extends JPanel {

        private final JButton button;
        private final JLabel label;

        private SelectionPane() {
            button = new JButton("Close");
            setOpaque(false);

            label = new JLabel("Rectangle");
            label.setOpaque(true);
            label.setBorder(new EmptyBorder(4, 4, 4, 4));
            label.setBackground(Color.GRAY);
            label.setForeground(Color.WHITE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(label, gbc);

            gbc.gridy++;
            add(button, gbc);

            button.addActionListener(e -> SwingUtilities.getWindowAncestor(SelectionPane.this).dispose());

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    label.setText("Rectangle " + getX() + "x" + getY() + "x" + getWidth() + "x" + getHeight());
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            final Graphics2D g2d = (Graphics2D) g.create();

            float dash[] = {10.0f};
            BasicStroke dashed =
                    new BasicStroke(3.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash, 0.0f);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(dashed);
            g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
            g2d.dispose();
        }
    }

    private static Rectangle getVirtualBounds() {
        final Rectangle bounds = new Rectangle(0, 0, 0, 0);

        final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        bounds.add(ge.getDefaultScreenDevice().getDefaultConfiguration().getBounds());

        return bounds;
    }
}