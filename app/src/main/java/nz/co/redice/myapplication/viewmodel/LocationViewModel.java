package nz.co.redice.myapplication.viewmodel;

import android.location.Location;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import nz.co.redice.myapplication.repository.CustomLocation;
import nz.co.redice.myapplication.repository.LocationModel;
import nz.co.redice.myapplication.repository.Repository;

public class LocationViewModel extends ViewModel {

    private final Repository mRepository;
    private final SavedStateHandle savedStateHandle;
    private LiveData<List<LocationModel>> allLocations;

    public void insertLocation(Location location) {
        mRepository.insert(new LocationModel(System.currentTimeMillis(),
                new CustomLocation(location.getLatitude(), location.getLongitude())));
    }

    @ViewModelInject
    public LocationViewModel(Repository repository,
                             @Assisted SavedStateHandle savedStateHandle) {
        mRepository = repository;
        this.savedStateHandle = savedStateHandle;
    }

    public void deleteLocation(int uuid) {
        mRepository.delete(uuid);
    }

    public void deleteAllLocations() {
        mRepository.deleteAllLocations();
    }

    public LiveData<List<LocationModel>> getAllLocations() {
        return mRepository.getAllLocations();
    }


}
