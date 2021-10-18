package by.senla.weblocatiom.controller;

import by.senla.weblocatiom.entity.Coordinates;
import by.senla.weblocatiom.entity.Location;
import by.senla.weblocatiom.service.Encoder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/geocoding")
public class LocationController {
    private final Encoder encoder;

    public LocationController(Encoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/coordinates=location")
    @Cacheable(cacheNames = "coordinate")
    public Location toLocation(@RequestBody Coordinates coordinates) {
        return encoder.encoderToLocation(coordinates);
    }

    @PostMapping("/location=coordinates")
    @Cacheable(cacheNames = "location")
    public Coordinates toCoordinates(@RequestBody Location location) {
        return encoder.encoderToCoordinate(location);
    }
}
