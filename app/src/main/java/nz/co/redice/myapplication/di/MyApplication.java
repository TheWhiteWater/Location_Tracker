package nz.co.redice.myapplication.di;

import android.app.Application;

import androidx.room.Room;

import dagger.hilt.android.HiltAndroidApp;
import nz.co.redice.myapplication.repository.Database;

@HiltAndroidApp
public class MyApplication extends Application {

    private Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = Room.databaseBuilder(this,
                Database.class, "location_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public Database getDatabase() {
        return mDatabase;
    }
}
