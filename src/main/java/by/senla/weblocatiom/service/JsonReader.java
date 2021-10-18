package by.senla.weblocatiom.service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.URL;

@Component
public class JsonReader {

    private String readAll(final Reader rd) throws IOException {
        final StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject read(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();

        try {
            final BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            inputStream.close();
        }
    }
}
