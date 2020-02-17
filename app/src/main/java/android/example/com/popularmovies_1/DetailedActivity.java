package android.example.com.popularmovies_1;

import android.content.Intent;
import android.example.com.popularmovies_1.database.AppDataBase;
import android.example.com.popularmovies_1.database.FavourtiesEntry;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailedActivity extends AppCompatActivity {

    private TextView tv_DetailedTitle, tv_DetailedReleaseDate, tv_DetailedSynopsis, tv_DetailedUserRating;
    private ImageView iv_DetailedThumbnail;
    private Button bt_trailer, bt_review;
    private int movieReference;

    String movieIdUri;

    String thumbNailString;

    private AppDataBase favDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);


        tv_DetailedTitle = findViewById(R.id.tv_DetailedTitle);
        tv_DetailedReleaseDate = findViewById(R.id.tv_DetailedReleaseDate);
        tv_DetailedUserRating = findViewById(R.id.tv_DetailedUserRating);
        tv_DetailedSynopsis = findViewById(R.id.tv_DetailedPlotSynopsis);
        iv_DetailedThumbnail = findViewById(R.id.iv_DetailedThumbnail);
        bt_review = findViewById(R.id.bt_review);
        bt_trailer = findViewById(R.id.bt_trailer);



        favDB = AppDataBase.getInstance(getApplicationContext());


        Intent intentThatStartedActivity = getIntent();
        movieReference = intentThatStartedActivity.getIntExtra("ItemPosition", 0);
        populateDetailedActivity(movieReference);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favourites_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.addFavourites){


        final FavourtiesEntry favourtiesEntry = new FavourtiesEntry(movieReference,thumbNailString);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                favDB.favouritesDao().insertFavourite(favourtiesEntry);

            }
        });

            Toast.makeText(DetailedActivity.this,"Added to favourites",Toast.LENGTH_SHORT).show();
        }

        else if(id==R.id.removeFavourites){


          final FavourtiesEntry favourtiesEntry = new FavourtiesEntry(movieReference,thumbNailString);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {

                    favDB.favouritesDao().deleteFavourite(favourtiesEntry);

                }
            });
            Toast.makeText(DetailedActivity.this,"Removed from favourites",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    //Method to set values into the layout views

    public void populateDetailedActivity(int position) {

        String userRating = MainActivity.ratingArray[position];
        tv_DetailedUserRating.setText(getString(R.string.detailedUserRating) + " " + userRating);

        String originalTitle = MainActivity.titleArray[position];
        tv_DetailedTitle.setText(originalTitle);

        String releaseDate = MainActivity.dateArrray[position];
        tv_DetailedReleaseDate.setText(getString(R.string.detailedReleaseDate) + " " + releaseDate);

        String plotSynopsis = MainActivity.synopsisArray[position];
        tv_DetailedSynopsis.setText(plotSynopsis);

        thumbNailString = MainActivity.thumbArray[position];
        Picasso.get().load(thumbNailString).into(iv_DetailedThumbnail);

        int movieIdString = MainActivity.movieIDArray[position];

    }

    public void onClickShowTrailer (View view){

        Intent intent = new Intent (DetailedActivity.this, TrailerActivity.class);
        intent.putExtra("number", movieReference);
        startActivity(intent);


    }
    public void onClickShowReviews (View view){

        Intent intent = new Intent (DetailedActivity.this, ReviewActivity.class);
        intent.putExtra("number", movieReference);
        startActivity(intent);


    }

}
