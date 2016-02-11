package me.hugomedina.themovielister;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.parse.Parse;
import com.parse.ParseObject;

import me.hugomedina.themovielister.objects.parse.Movie;

/**
 * Created by Hugo on 2/2/2016.
 */
public class MovieListerApplication extends Application{

    //check is there's internet conection
    public static boolean isOnline;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        ParseObject.registerSubclass(Movie.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        checkIfOnline();
    }

    public void checkIfOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            isOnline = true;
        }
        else {
            isOnline = false;
        }
    }

}
