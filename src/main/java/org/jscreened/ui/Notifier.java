package org.jscreened.ui;

import org.jscreened.util.ResourceHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

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
        TRAY_IMAGE = ResourceHelper.loadImage("/Tray.png");
        TRAY_ICON = makeIcon();
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

            final MenuItem about = new MenuItem("About");
            about.addActionListener(e -> new About());

            final MenuItem exit = new MenuItem("Exit");
            exit.addActionListener(e -> System.exit(2));

            menu.add(about);
            menu.add(exit);

            TRAY_ICON.setPopupMenu(menu);
            TRAY_ICON.addMouseListener(new MouseHandler());

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
