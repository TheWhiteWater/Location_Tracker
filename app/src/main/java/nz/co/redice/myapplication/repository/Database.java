package nz.co.redice.myapplication.repository;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {LocationModel.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract LocationDao getDao();
    private static Database instance;

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "location_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}