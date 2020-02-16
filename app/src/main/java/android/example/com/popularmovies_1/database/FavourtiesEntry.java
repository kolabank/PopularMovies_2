package android.example.com.popularmovies_1.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "favourites")
public class FavourtiesEntry {


@PrimaryKey (autoGenerate = true)
    private int id;
    private String thumbNailUrl;



    public FavourtiesEntry(int id, String thumbNailUrl) {

        this.id = id;
        this.thumbNailUrl = thumbNailUrl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setThumbNailUrl(String thumbnailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }


    public String getThumbNailUrl() {
        return thumbNailUrl;
    }


}
