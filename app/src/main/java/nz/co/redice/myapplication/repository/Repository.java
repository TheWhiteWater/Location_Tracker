package nz.co.redice.myapplication.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nz.co.redice.myapplication.repository.models.LocationModel;

public class Repository {

    private LocationDao mDao;
    private LiveData<List<LocationModel>> allLocations;

    public Repository(LocationDao dao) {
        mDao = dao;
        allLocations = mDao.getAllLocations();
    }

    public void deleteAllLocations() {
        mDao.deleteAllLocations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void delete(int uuid) {
        mDao.deleteLocation(uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void insert(LocationModel locationModel) {
        mDao.insertLocation(locationModel)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public LiveData<List<LocationModel>> getAllLocations() {
        return allLocations;
    }

    public LiveData<LocationModel> getLastLocation() {
        return mDao.getLastLocation();
    }

    public LiveData<Integer> getNumberOfEntries() {
        return mDao.getDatabaseEntries();
    }

}
