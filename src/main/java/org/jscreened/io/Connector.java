package org.jscreened.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.jscreened.util.RequestMethod;
import org.jscreened.util.gson.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kevin on 11-11-2015.
 */
public final class Connector {

    private final String api = "https://api.imgur.com/3/image";
    private final String request = "Client-ID";
    private final String clientId = "fdaa08c932d9a7e";
    private Response response;
    private final ScreenedImage image;

    public Connector(final ScreenedImage image) {
        this.image = image;
        connect();
    }

    private boolean connect() {
        try {
            final URL url = new URL(getApi());
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            final String data = image.getData();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod(RequestMethod.POST);
            connection.setRequestProperty("Authorization", String.format("%s %s", request, clientId));
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.connect();

            final OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(data);
            osw.flush();

            int bytesRead;
            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            final InputStream is = connection.getInputStream();
            final byte[] bytes = new byte[1024];
            while ((bytesRead = is.read(bytes, 0, bytes.length)) > 0) {
                buffer.write(bytes, 0, bytesRead);
            }

            buffer.flush();

            final StringReader sr = new StringReader(buffer.toString());

            final Gson gson = new GsonBuilder().create();
            final JsonReader reader = new JsonReader(sr);

            response = gson.fromJson(reader, Response.class);
            System.out.println(response.getData().getLink());

            osw.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Response getResponse() {
        return response;
    }

    public String getApi() {
        return api;
    }

    public String getClientId() {
        return clientId;
    }
}
