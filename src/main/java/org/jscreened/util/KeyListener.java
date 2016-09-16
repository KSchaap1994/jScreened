package org.jscreened.util;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jscreened.ui.SnipIt;

/**
 * Created by IntelliJ IDEA
 * User: Kevin
 * Date: 16-9-2016
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class KeyListener implements NativeKeyListener {

    private final int DEFAULT_KEY_CODE = 88; //F12

    public KeyListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.isActionKey() && e.getKeyCode() == DEFAULT_KEY_CODE) {
            new SnipIt();
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }
}
