package nz.co.redice.myapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import nz.co.redice.myapplication.repository.models.LocationModel;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations")
    LiveData<List<LocationModel>> getAllLocations();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertLocation(LocationModel locationModel);

    @Query("DELETE FROM locations WHERE uuid = :uuid")
    Single<Void> deleteLocation(int uuid);

    @Query("DELETE FROM locations")
    Single<Void> deleteAllLocations();

    @Query("SELECT * FROM locations ORDER BY uuid DESC LIMIT 1")
    LiveData<LocationModel> getLastLocation();

    @Query("SELECT COALESCE(MAX(uuid)+1, 0) FROM locations")
    LiveData<Integer> getDatabaseEntries();

}
