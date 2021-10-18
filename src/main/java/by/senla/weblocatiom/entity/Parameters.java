package by.senla.weblocatiom.entity;

public enum Parameters {
    API_KEY("AIzaSyB6FtHe1oFkiTC8KXAwd1BWiU052KAVq9k"),
    API_URL("https://maps.googleapis.com/maps/api/geocode/json?");

    private final String string;

    Parameters(String string) {
        this.string = string;
    }

    public String get() {
        return string;
    }
}
