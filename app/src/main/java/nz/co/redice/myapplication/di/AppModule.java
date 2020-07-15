package nz.co.redice.myapplication.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import nz.co.redice.myapplication.repository.Database;
import nz.co.redice.myapplication.repository.LocationDao;

@InstallIn(ApplicationComponent.class)
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public static Database provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                Database.class, "location_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    public static LocationDao providesDao(Database database) {
        return database.getDao();
    }



}
