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
import io.reactivex.Single;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations")
    LiveData<List<LocationModel>> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Void> insertLocation(LocationModel locationModel);

    @Query("DELETE FROM locations WHERE uuid = :uuid")
    Single<Void> deleteLocation(int uuid);

    @Query("DELETE FROM locations")
    Single<Void> deleteAllLocations();

}
