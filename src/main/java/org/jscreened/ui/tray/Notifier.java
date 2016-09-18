package org.jscreened.ui.tray;

import org.jscreened.ui.About;
import org.jscreened.util.KeyListener;
import org.jscreened.util.ResourceHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private final org.jscreened.util.KeyListener LISTENER;

    public Notifier() {
        TRAY_IMAGE = ResourceHelper.loadImage("/tray.png");
        TRAY_ICON = makeIcon();
        LISTENER = new KeyListener();
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
            if (e.isPopupTrigger())
                TRAY_ICON.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
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
