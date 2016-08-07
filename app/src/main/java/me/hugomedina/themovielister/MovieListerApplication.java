package me.hugomedina.themovielister;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;



/**
 * Created by Hugo on 2/2/2016.
 */
public class MovieListerApplication extends Application{

    /**
     * Check is there's internet conection
     */
    public static boolean isOnline;

    /**
     * Indicated whether is Lollipop or not
     */
    public static boolean isLollipop = false;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        checkIfOnline();

        isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
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
