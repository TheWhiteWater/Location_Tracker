package nz.co.redice.myapplication.di;

import android.app.Application;

import androidx.room.Room;

import nz.co.redice.myapplication.repository.Database;

class MyApplication extends Application {

    public Database mDatabase;
    public MyApplication instance;



    @Override
    public void onCreate() {
        super.onCreate();
        Database mDatabase = Room.databaseBuilder(getApplicationContext(),
                Database.class, "locations.db").build();

    }
}
