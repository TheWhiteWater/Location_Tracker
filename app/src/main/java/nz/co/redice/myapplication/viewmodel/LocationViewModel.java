package nz.co.redice.myapplication.viewmodel;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import nz.co.redice.myapplication.repository.CustomLocation;
import nz.co.redice.myapplication.repository.LocationModel;
import nz.co.redice.myapplication.repository.Repository;

public class LocationViewModel extends AndroidViewModel {
    private Repository mRepository;

    private LiveData<List<LocationModel>> allLocations;

    public LocationViewModel(@NonNull Application application) {
        super(application);
        mRepository = new Repository(application);
        allLocations = mRepository.getAllLocations();
    }

    public void insertLocation(Location location) {
        mRepository.insert(new LocationModel(System.currentTimeMillis(),
                new CustomLocation(location.getLatitude(), location.getLongitude())));
    }
    public void deleteLocationById(int uuid) {
        mRepository.delete(uuid);
    }

    public void deleteAllLocations() {
        mRepository.deleteAllLocations();
    }

    public LiveData<List<LocationModel>> getAllLocations() {
        return allLocations;
    }


}
