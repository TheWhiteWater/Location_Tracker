package nz.co.redice.myapplication.repository;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations")
    LiveData<List<LocationModel>> getAllLocations();

    @Query("SELECT * FROM locations where date = :date")
    LiveData<List<LocationModel>> getLocationsByDate(Long date);

    @Query("SELECT * FROM locations where uuid = :uuid")
    Observable<LocationModel> getLocation(int uuid);

    @Query("DELETE FROM locations where date = :date")
    void deleteAllLocationOn(Long date);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertLocation(LocationModel locationModel);

    @Query("DELETE FROM locations")
    void deleteAllLocations();

}
