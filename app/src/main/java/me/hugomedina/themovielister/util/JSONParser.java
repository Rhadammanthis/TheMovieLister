package me.hugomedina.themovielister.util;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.hugomedina.themovielister.objects.models.Cast;
import me.hugomedina.themovielister.objects.models.Crew;
import me.hugomedina.themovielister.objects.models.ImageModel;
import me.hugomedina.themovielister.objects.models.MovieData;
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
                tempActor.setId(jsonActor.getString("id"));
                tempActor.setTitle(jsonActor.getString("original_title"));
                tempActor.setPosterPath(jsonActor.getString("poster_path"));

                list.add(tempActor);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return list;
    }

    public ArrayList<Cast> getMovieCast(String rawJSON)
    {
        ArrayList<Cast> list = null;

        JSONObject results = null;

        try {
            results = new JSONObject(rawJSON);
            JSONArray data = results.getJSONArray("cast");

            int dataSize = data.length();

            if (dataSize == 0) {
                showNotFoundNotification();
                return null;
            }

            list = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                Cast cast = new Cast(
                        jsonActor.getString("character"),
                        jsonActor.getString("name"),
                        jsonActor.getString("profile_path")
                );

                list.add(cast);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return list;
    }

    public ArrayList<Crew> getMovieCrew(String rawJSON)
    {
        ArrayList<Crew> list = null;

        JSONObject results = null;

        try {
            results = new JSONObject(rawJSON);
            JSONArray data = results.getJSONArray("crew");

            int dataSize = data.length();

            if (dataSize == 0) {
                showNotFoundNotification();
                return null;
            }

            list = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                Crew crew = new Crew(
                        jsonActor.getString("job"),
                        jsonActor.getString("name"),
                        jsonActor.getString("profile_path")
                );

                list.add(crew);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return list;
    }

    public MovieData getMovieData(String rawJSON)
    {
        MovieData movieData = new MovieData();

        try {

            JSONObject jsonActor = null;
            jsonActor = new JSONObject(rawJSON);

            movieData.setRunTime(jsonActor.getString("runtime"));
            movieData.setReleaseDate(jsonActor.getString("release_date"));
            movieData.setSynopsis(jsonActor.getString("overview"));


        } catch (JSONException e) {
            e.printStackTrace();

        }

        return movieData;
    }

    public ArrayList<ImageModel> getMovieImages(String rawJSON)
    {
        ArrayList<ImageModel> list = null;

        JSONObject results = null;

        try {
            results = new JSONObject(rawJSON);
            JSONArray data = results.getJSONArray("backdrops");

            int dataSize = data.length();

            if (dataSize == 0) {
                showNotFoundNotification();
                return null;
            }

            list = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                ImageModel image = new ImageModel(
                        jsonActor.getDouble("aspect_ratio"),
                        jsonActor.getString("file_path"),
                        jsonActor.getInt("height"),
                        jsonActor.getString("iso_639_1"),
                        jsonActor.getDouble("vote_average"),
                        jsonActor.getInt("vote_count"),
                        jsonActor.getInt("width")
                );

                list.add(image);
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
