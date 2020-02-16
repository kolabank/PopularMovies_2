package android.example.com.popularmovies_1.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface FavouritesDao {

    @Query("SELECT * FROM favourites ORDER BY id")

    FavourtiesEntry[] loadAllFavourites();

    @Insert
    void insertFavourite (FavourtiesEntry favourtiesEntry);

    @Query("SELECT COUNT(id) FROM favourites")
    int getCount();

    @Query("SELECT thumbNailUrl FROM favourites")
    LiveData<String[]> getAllThubNails();

    @Delete
    void deleteFavourite (FavourtiesEntry favourtiesEntry);

    @Query("DELETE FROM favourites")
    void deleteAllFavourites();

}
