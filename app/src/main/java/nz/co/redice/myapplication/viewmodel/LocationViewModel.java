package nz.co.redice.myapplication.viewmodel;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;

import nz.co.redice.myapplication.repository.LocationModel;
import nz.co.redice.myapplication.repository.Repository;

public class LocationViewModel extends ViewModel {

    private Repository mRepository;
    private SavedStateHandle savedStateHandle;
    private LiveData<List<LocationModel>> allLocations;


    @ViewModelInject
    public LocationViewModel(Repository repository,
                             @Assisted SavedStateHandle savedStateHandle) {
        mRepository = repository;
        this.savedStateHandle = savedStateHandle;
        allLocations = mRepository.getAllLocations();
    }

    public void deleteLocation(int uuid) {
        mRepository.delete(uuid);
    }

    public void deleteAllLocations() {
        mRepository.deleteAllLocations();
    }

    public LiveData<List<LocationModel>> getAllLocations() {
        return allLocations;
    }


}
