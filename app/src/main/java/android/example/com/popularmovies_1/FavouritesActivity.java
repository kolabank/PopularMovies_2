package android.example.com.popularmovies_1;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class FavouritesActivity extends AppCompatActivity {

   private RecyclerView rv_Favourites;

    private AppDataBase favDB;

    private String[] thumbNailArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        rv_Favourites = findViewById(R.id.rv_favourites);

        favDB = AppDataBase.getInstance(getApplicationContext());

        setTitle("Favourites");

        updateDisplay();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favourite_delete, menu);
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.deleteFavourites){

                   favDB.favouritesDao().deleteAllFavourites();
                   updateDisplay();

        }

        return super.onOptionsItemSelected(item);
    }


    public void updateDisplay(){

        int rowCount= favDB.favouritesDao().getCount();

        thumbNailArray = new String[rowCount];

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); //GridLayoutManager takes 2 parameters
        rv_Favourites.setLayoutManager(gridLayoutManager);
        rv_Favourites.setHasFixedSize(false);

        LiveData <String[]>thumbNailArray;

        thumbNailArray = favDB.favouritesDao().getAllThubNails();

        thumbNailArray.observe(this, new Observer<String[]>() {
            @Override
            public void onChanged(@Nullable String[] strings) {

                ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(strings);

                rv_Favourites.setAdapter(thumbnailAdapter);
            }
        });

    }

}
