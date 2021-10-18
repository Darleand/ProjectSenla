package by.senla.weblocatiom.service;

import by.senla.weblocatiom.entity.Coordinates;
import by.senla.weblocatiom.entity.Location;
import by.senla.weblocatiom.entity.Parameters;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class Encoder {
    private final JsonReader jsonReader;

    @Autowired
    public Encoder(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Cacheable(value = "loc", key = "#coordinates")
    public Location encoderToLocation(Coordinates coordinates) {
        String resultUrl = Parameters.API_URL.get() + "latlng=" +
                encoderParamsForCoordinates(coordinates) + "&language=ru" + "&key=" + Parameters.API_KEY.get();
        JSONObject response;
        try {
            response = jsonReader.read(resultUrl);
        } catch (IOException e) {
            throw new Exceptions();
        }
        JSONObject locate = response.getJSONArray("results").getJSONObject(0);
        return new Location(locate.getString("formatted_address"));
    }

    @Cacheable(value = "cor", key = "#location")
    public Coordinates encoderToCoordinate(Location location) {
        String resultUrl = Parameters.API_URL.get() + "address=" +
                encoderParamsForLocation(location) + "&key=" + Parameters.API_KEY.get();
        JSONObject response;
        try {
            response = jsonReader.read(resultUrl);
        } catch (IOException e) {
            throw new Exceptions();
        }
        JSONObject locate = response.getJSONArray("results").getJSONObject(0);
        locate = locate.getJSONObject("geometry");
        locate = locate.getJSONObject("location");
        return new Coordinates(locate.getDouble("lat"), locate.getDouble("lng"));
    }

    public String encoderParamsForLocation(Location location) {
        return location.getLocation().replaceAll("\\s+", "+");
    }

    public String encoderParamsForCoordinates(Coordinates coordinates) {
        return coordinates.getLat() + "," + coordinates.getLng();
    }
}
