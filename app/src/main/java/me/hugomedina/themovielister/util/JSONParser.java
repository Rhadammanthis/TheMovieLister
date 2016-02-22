package me.hugomedina.themovielister.util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 2/21/2016.
 */
public class JSONParser {

    private Context context;

    public JSONParser(Context context)
    {
        this.context = context;
    }

    public ArrayList<MovieModel> getMovies(String rawJSON)
    {
        ArrayList<MovieModel> list = null;

        JSONObject results = null;

        try {
            results = new JSONObject(rawJSON);
            JSONArray data = results.getJSONArray("results");

            int dataSize = data.length();

            if (dataSize == 0) {
                showNotFoundNotification();
                return null;
            }

            list = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                MovieModel tempActor = new MovieModel();
                tempActor.setId(jsonActor.getInt("id"));
                tempActor.setTitle(jsonActor.getString("original_title"));
                tempActor.setPosterPath(jsonActor.getString("poster_path"));

                list.add(tempActor);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return list;
    }


    private void showNotFoundNotification() {
        Toast.makeText(context,
                "Sorry we couldn't find anything, please try again",
                Toast.LENGTH_SHORT).
                show();
    }

}
