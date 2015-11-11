package org.jscreened;

import org.jscreened.io.Connector;

/**
 * Created by Kevin on 11-11-2015.
 */
public  final class JScreened {

    public static void main(final String... args) {
        final Connector connector = new Connector();
        //connector.connect();
        System.out.println(connector.getResponse());
    }
}
