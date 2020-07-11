package nz.co.redice.myapplication.repository;

public class CustomLocation {
    Double latitude;
    Double longitude;

    public CustomLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
