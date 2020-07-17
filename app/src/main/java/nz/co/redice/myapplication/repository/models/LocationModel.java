package nz.co.redice.myapplication.repository.models;

import android.location.Location;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class LocationModel {


    @PrimaryKey(autoGenerate = true)
    private int uuid;
    private Long timestamp;
    private Double longitude;
    private Double latitude;

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
    public LocationModel(Location location) {
        this.timestamp = System.currentTimeMillis();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

    @Override
    public String toString() {
        return "" + Converters.fromTimestamp(timestamp) + "\n" +
                "latitude =" + latitude + "\n" +
                "longitude =" + longitude;
    }



}
