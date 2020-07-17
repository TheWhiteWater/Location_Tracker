package nz.co.redice.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nz.co.redice.myapplication.repository.models.LocationModel;
import nz.co.redice.myapplication.repository.Repository;

public class LocationViewModel extends ViewModel {

    private Repository mRepository;


    public LocationViewModel(Repository repository) {
        mRepository = repository;
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

    public LiveData<LocationModel> getLastKnowLocation() {
        return mRepository.getLastLocation();
    }

    public LiveData<Integer> getDatabaseSize() {
        return mRepository.getNumberOfEntries();
    }

}
