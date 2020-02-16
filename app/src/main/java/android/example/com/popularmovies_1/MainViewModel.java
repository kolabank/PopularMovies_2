package android.example.com.popularmovies_1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.example.com.popularmovies_1.database.AppDataBase;
import android.support.annotation.NonNull;

public class MainViewModel extends AndroidViewModel {

    private LiveData<String[]> thumbNailArray;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDataBase dataBase = AppDataBase.getInstance(this.getApplication());
        thumbNailArray = dataBase.favouritesDao().getAllThubNails();


    }

    public LiveData<String[]> getThumbNailArray (){

        return thumbNailArray;

    }
}
