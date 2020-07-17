package nz.co.redice.myapplication.di;

import android.app.Application;

import androidx.room.Room;

import dagger.hilt.android.HiltAndroidApp;
import nz.co.redice.myapplication.repository.Database;
import nz.co.redice.myapplication.repository.LocationDao;
import nz.co.redice.myapplication.repository.Repository;

public class MyApplication extends Application {

    private Database mDatabase;
    private LocationDao mDao;
    private Repository mRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = Room.databaseBuilder(this,
                Database.class, "location_database")
                .build();

        mRepository = new Repository(mDatabase.getDao());
    }


    public LocationDao getDao() {
        return mDatabase.getDao();
    }

    public Database getDatabase() {
        return mDatabase;
    }

    public Repository getRepository() {
        return mRepository;
    }
}
