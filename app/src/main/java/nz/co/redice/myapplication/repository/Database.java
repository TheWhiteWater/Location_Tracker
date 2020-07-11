package nz.co.redice.myapplication.repository;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {LocationModel.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract LocationDao getDao();
}