package nz.co.redice.myapplication.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class Repository {

    private LocationDao mDao;
    private LiveData<List<LocationModel>> allLocations;

    @Inject
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

}
