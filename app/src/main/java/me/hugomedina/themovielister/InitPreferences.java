package me.hugomedina.themovielister;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hugo on 3/1/2016.
 */
public class InitPreferences {

    private static InitPreferences instance;
    private SharedPreferences pref;

    private final String KEY_DEFAULT_SELECTED_LIST = "defaultList";

    private InitPreferences(Context context) {
        pref = context.getSharedPreferences("preferencesthemovielister", Context.MODE_PRIVATE);
    }

    public static InitPreferences newInstance(Context context){
        if(instance==null){
            instance = new InitPreferences(context);
        }
        return instance;
    }

    public int getDefaultList(){
        return pref.getInt(KEY_DEFAULT_SELECTED_LIST, 0);
    }

    public void setDefaultList(int position){
        pref.edit()
                .putInt(KEY_DEFAULT_SELECTED_LIST, position)
                .apply();
    }

}
