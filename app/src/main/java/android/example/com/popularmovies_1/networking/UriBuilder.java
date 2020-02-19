package android.example.com.popularmovies_1.networking;

import android.net.Uri;


public class UriBuilder {

    private String SCHEME = "https";
    private String MAIN_URI_STRING = "api.themoviedb.org";
    private String PATH1 = "3";
    private String PATH2 = "movie";
    private String API_KEY = "api_key";
    private String API_KEY_value = "ac151895b9e322dd2d1a1cedef5bf9ab";

    private String YOUTUBE_MAIN = "youtube.com";
    private String TRAILER_PATH = "watch";
    private String TRAILER_QUERY= "v";



    public String makeURI (String uriPath){

    Uri.Builder builder = new Uri.Builder();

    builder.scheme(SCHEME).
            authority(MAIN_URI_STRING)
            .appendPath(PATH1)
            .appendPath(PATH2)
            .appendPath(uriPath)
            .appendQueryParameter(API_KEY, API_KEY_value);

        return builder.build().toString();

    }

    public String makeURI2 (String uriPath2, String traOrRev){


        Uri.Builder builder = new Uri.Builder();

        builder.scheme(SCHEME).
                authority(MAIN_URI_STRING)
                .appendPath(PATH1)
                .appendPath(PATH2)
                .appendPath(uriPath2)
                .appendPath(traOrRev)
                .appendQueryParameter(API_KEY, API_KEY_value);

        String builtUri = builder.build().toString();

        return builtUri;

    }

    public String maketrailerURI (String uriPath3){


        Uri.Builder builder = new Uri.Builder();

        builder.scheme(SCHEME).
                authority(YOUTUBE_MAIN).
                appendPath(TRAILER_PATH)
                 .appendQueryParameter(TRAILER_QUERY, uriPath3);


        String builtUri = builder.build().toString();

        return builtUri;

    }




}
