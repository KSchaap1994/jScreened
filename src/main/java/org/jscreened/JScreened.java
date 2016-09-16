package org.jscreened;

import org.jscreened.io.Connector;
import org.jscreened.ui.Notifier;
import org.jscreened.util.gson.Data;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class JScreened {

    public static void main(final String... args) {
        final Notifier notifier = new Notifier();
        notifier.start();
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
}
