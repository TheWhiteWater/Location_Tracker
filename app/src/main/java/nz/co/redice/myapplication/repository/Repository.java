package nz.co.redice.myapplication.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private final LocationDao mDao;

    public Repository(LocationDao dao) {
        mDao = dao;
    }

    public LiveData<List<LocationModel>> retrieveAllLocations (String category) {
        return mDao.getAllLocations();
    }

    public LocationModel retrieveEntryById(int uuid) {
        return (LocationModel) mDao.getLocation(uuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void clearDatabase() {
        Completable.fromAction(mDao::deleteAllLocations)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

}
