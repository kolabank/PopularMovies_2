package android.example.com.popularmovies_1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {FavourtiesEntry.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String LOG_TAG = AppDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favouritesList";
    private static AppDataBase databaseInstance;

    public static AppDataBase getInstance(Context context){

        if (databaseInstance==null){
                synchronized (LOCK){

                    Log.d( LOG_TAG, "Creating new database instance");
                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, AppDataBase.DATABASE_NAME).
                            allowMainThreadQueries()    //This is temporary
                            .build();
                }
        }

        Log.d (LOG_TAG, "Getting the database instance");
        return databaseInstance;
    }


    public abstract FavouritesDao favouritesDao();


}
