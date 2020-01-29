package android.example.com.popularmovies_1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.net.URL;
import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView rv_Review;

    ArrayList<String> ArrayDetail;
    ArrayList<String> trailerName, authorList, contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        rv_Review = findViewById(R.id.rv_Review);

        Intent mIntent = getIntent();
        int reference = mIntent.getIntExtra("number", 0);
        int[] movieIDArray = MainActivity.movieIDArray;
        String movieReviewUri = new UriBuilder().makeURI2(String.valueOf(movieIDArray[reference]), "reviews");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_Review.setLayoutManager(linearLayoutManager);
        rv_Review.setHasFixedSize(false);
        new gettingResponse3().execute(movieReviewUri);


    }


    private class gettingResponse3 extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String URLString = params[0];
            URL moviesURL = NetworkUtility.makeUrl(URLString);

            try {
                String[] JSONResponse = {NetworkUtility.urlResponse(moviesURL)};

                return JSONResponse;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] JSONData) {
            JSONUtil jsonUtil = new JSONUtil();
            jsonUtil.populateReviewsFromJson(JSONData[0]);
            contentList = jsonUtil.contentArrayList;
            authorList = jsonUtil.authorArrayList;

            ReviewAdapter adapter = new ReviewAdapter(authorList, contentList);

            rv_Review.setAdapter(adapter);




        }
    }

}