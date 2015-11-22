package org.jscreened.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 22-11-2015
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public final class Notifier {

    private final BufferedImage TRAY_IMAGE;
    private final TrayIcon TRAY_ICON;

    public Notifier() {
        try {
            TRAY_IMAGE = ImageIO.read(Notifier.class.getResourceAsStream("/resources/Tray.png"));
            TRAY_ICON = makeIcon();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException();
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            final SystemTray tray = SystemTray.getSystemTray();
            tray.add(TRAY_ICON);

            final PopupMenu menu = new PopupMenu();

            final MenuItem item = new MenuItem("About");
            item.addActionListener(e -> new About());

            menu.add(item);

            final MouseHandler mouseHandler = new MouseHandler();

            TRAY_ICON.setPopupMenu(menu);
            TRAY_ICON.addMouseListener(mouseHandler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private TrayIcon makeIcon() {
        return new TrayIcon(TRAY_IMAGE, "jScreened");
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
