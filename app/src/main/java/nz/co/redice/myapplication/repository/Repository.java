package nz.co.redice.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class Repository {

    private LocationDao mDao;
    private LiveData<List<LocationModel>> allLocations;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        mDao = database.getDao();
        allLocations = mDao.getAllLocations();
    }

    public void deleteAllLocations() {
        mDao.deleteAllLocations()
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void delete(int uuid) {
        mDao.deleteLocation(uuid)
                .subscribeOn(Schedulers.io())
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

}
