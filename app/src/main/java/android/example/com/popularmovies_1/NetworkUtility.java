package android.example.com.popularmovies_1;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtility {


    // Method to convert string to URL
    public static URL makeUrl(String URLString){

    Uri popularUri= Uri.parse(URLString);
    URL popularURL = null;
    try {
    popularURL = new URL(popularUri.toString());
    } catch (MalformedURLException e){
        e.printStackTrace();
    }
    return popularURL;
    }


    //Method to get read the webpage (JSON data) and return as a string (urlResponse)

    public static String urlResponse ( URL url) throws IOException{
        HttpURLConnection uRLConnection = (HttpURLConnection)url.openConnection();

        try{
            InputStream stream = uRLConnection.getInputStream();
            Scanner scanner  = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput){
                return scanner.next();
            }
            else {
                return null;
            }
        } finally {
            uRLConnection.disconnect();
        }


    }

}
