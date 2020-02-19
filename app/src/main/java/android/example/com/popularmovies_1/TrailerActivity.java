package android.example.com.popularmovies_1;

import android.content.Intent;
import android.example.com.popularmovies_1.networking.NetworkUtility;
import android.example.com.popularmovies_1.networking.UriBuilder;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;

public class TrailerActivity extends AppCompatActivity {

    ListView trailerListView;

    ArrayList<String> idArrayDetail;
    ArrayList<String> trailerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        trailerListView = findViewById(R.id.lv_trailer);
        Intent mIntent = getIntent();
        int reference = mIntent.getIntExtra("number",0);
        int[] movieIDArray = MainActivity.movieIDArray;
        String movieIdUri = new UriBuilder().makeURI2(String.valueOf(movieIDArray[reference]), "videos");

        new gettingResponse2().execute(movieIdUri);

        setTitle("Trailers");

    }


    private class gettingResponse2 extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            String URLString = params[0];
            URL moviesURL = NetworkUtility.makeUrl(URLString);

            try {

                return new String[]{NetworkUtility.urlResponse(moviesURL)};

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String[] JSONData) {

            JSONUtil jsonUtil = new JSONUtil();
            jsonUtil.populateIdFromJson(JSONData[0]);

            idArrayDetail= jsonUtil.idArrayList;
            trailerName = jsonUtil.trailerNameList;

            ArrayAdapter<String> trailerAdapter= new ArrayAdapter<String>(TrailerActivity.this, android.R.layout.simple_list_item_1, trailerName);
            trailerListView.setAdapter(trailerAdapter);

            //Creating the ListView listener

            AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){


                @Override
                public void onItemClick(AdapterView<?> trailerListView, View view, int position, long id) {

                    String trailerUri = new UriBuilder().maketrailerURI(idArrayDetail.get(position));

                    Uri webpage = Uri.parse(trailerUri);

                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                    if (intent.resolveActivity(getPackageManager())!=null){
                        startActivity (intent);
                    }

                }
            };

            trailerListView.setOnItemClickListener(itemClickListener);
        }

    }





}
