package org.jscreened.io;

import org.jscreened.util.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class Connector {

    private final String api = "https://api.imgur.com/3/image";
    private final String clientId = "Client-ID fdaa08c932d9a7e";
    private final StringBuilder stringBuilder = new StringBuilder();

    public boolean connect() {
        try {
            final URL url = new URL(getApi());
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            final Image image = new Image();
            image.create();
            final String data = image.getData();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod(RequestMethod.POST);
            connection.setRequestProperty("Authorization", getClientId());
            connection.setRequestMethod(RequestMethod.POST);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            System.out.println(data);

            connection.connect();

            System.out.println("Connected");

            final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(data);
            osw.flush();

            System.out.println("Writing data");

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            rd.close();
            osw.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setResponse() {

    }

    public String getResponse() {
        String output = stringBuilder.toString();
        return output;
    }

    public String getApi() {
        return api;
    }

    public String getClientId() {
        return clientId;
    }
}
