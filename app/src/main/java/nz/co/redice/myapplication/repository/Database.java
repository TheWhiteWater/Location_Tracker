package nz.co.redice.myapplication.repository;

import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import nz.co.redice.myapplication.repository.models.Converters;
import nz.co.redice.myapplication.repository.models.LocationModel;

@androidx.room.Database(entities = {LocationModel.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {

    public abstract LocationDao getDao();

}

