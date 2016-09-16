package org.jscreened;

import org.jnativehook.GlobalScreen;
import org.jscreened.ui.Notifier;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class JScreened {

    public static void main(final String... args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                    UnsupportedLookAndFeelException ignored) {
            }
        });

        LogManager.getLogManager().reset();

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        final Notifier notifier = new Notifier();
        notifier.start();

    }
//        final Connector connector = new Connector();
//        long start = System.currentTimeMillis();
//        long end = 0;
//        if (connector.connect()) {
//            end = System.currentTimeMillis();
//            final Data data = connector.getResponse().getData();
//            System.out.println(data.getLink());
//        } else
//            System.err.println("Could not connect to IMGUR API!");
}
