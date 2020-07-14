package nz.co.redice.myapplication.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import nz.co.redice.myapplication.repository.Database;
import nz.co.redice.myapplication.repository.LocationDao;
import nz.co.redice.myapplication.repository.Repository;

@Module
@InstallIn(ApplicationComponent.class)
public  class AppModule {

    private Context mContext;

    public AppModule(Application context) {
        mContext = context;
    }

    @Singleton
    @Provides
    public static Database provideDatabase (
            @ApplicationContext Context context
    ) {
        return Room.databaseBuilder(context,
                Database.class, "location_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public static LocationDao provideDao(Database database) {
        return  database.getDao();
    }

    @Singleton
    @Provides
    public static Repository provideRepository(LocationDao dao) {
        return new Repository(dao);
    }
}
