package nz.co.redice.myapplication.repository;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class LocationModel {


    @PrimaryKey(autoGenerate = true)
    private int uuid;
    private Long date;

    @Embedded
    private CustomLocation customLocation1;

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public CustomLocation getCustomLocation1() {
        return customLocation1;
    }

    public void setCustomLocation1(CustomLocation location) {
        this.customLocation1 = location;
    }

    public LocationModel() {
    }

    @Ignore
    public LocationModel(Long date, CustomLocation customLocation1) {
        this.date = date;
        this.customLocation1 = customLocation1;
    }

    @Override
    public String toString() {
        return "date=" + date +
                        ", latitude =" + customLocation1.latitude +
                        ", longitude =" + customLocation1.longitude;
    }
}
