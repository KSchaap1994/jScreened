package org.jscreened.util;

import org.jnativehook.GlobalScreen;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 17-9-2016
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public final class Utils {

    public static void disableLogging() {
        LogManager.getLogManager().reset();

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }
}
