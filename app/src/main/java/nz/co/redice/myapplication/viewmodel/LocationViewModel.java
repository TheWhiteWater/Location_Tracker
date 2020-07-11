package nz.co.redice.myapplication.viewmodel;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nz.co.redice.myapplication.service.LocationUpdateHelper;

public class LocationViewModel extends ViewModel {

    private MutableLiveData<Location> currentLocation;

    public LiveData<Location> getCurrentLocation() {
        if (currentLocation == null) {
            currentLocation = new MutableLiveData<>();
        }
        return currentLocation;
    }


}
