package nz.co.redice.myapplication.repository.models;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Entity(tableName = "locations")
public class LocationModel {


    @PrimaryKey(autoGenerate = true)
    private int uuid;
    private Long timestamp;
    private String address;
    private Double longitude;
    private Double latitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public LocationModel() {
    }

    @Ignore
    public LocationModel(Location location, String address) {
        this.timestamp = System.currentTimeMillis();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.address = address;
    }

    @Override
    public String toString() {
        return "" + Converters.fromTimestamp(timestamp) + "\n" +
                "latitude: " + latitude + "\n" +
                "longitude: " + longitude;
    }


    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
        return formatter.format(timestamp);
    }

    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(timestamp);
    }


}
