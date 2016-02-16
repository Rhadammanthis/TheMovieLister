package me.hugomedina.themovielister.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.SearchAdapter;
import me.hugomedina.themovielister.objects.models.ActorModel;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.objects.parse.Movie;

public class SearchActivity extends Activity {

    private ArrayList<MovieModel> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();

        if (intent != null) {

            String jsonData = intent.getExtras().getString("jsonData");
            getActorsFrom(jsonData);

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.Adapter mAdapter = new SearchAdapter(movies, this);
            mRecyclerView.setAdapter(mAdapter);

        }



//        Button searchButton = (Button) findViewById(R.id.button_search);

    }

    private void getActorsFrom(String rawJSON) {
        JSONObject results = null;

        try {
            results = new JSONObject(rawJSON);
            JSONArray data = results.getJSONArray("results");

            int dataSize = data.length();

            if (dataSize == 0) {
                showNotFoundNotification();
                super.onBackPressed();
            }

            movies = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                MovieModel tempActor = new MovieModel();
                tempActor.setId(jsonActor.getInt("id"));
                tempActor.setTitle(jsonActor.getString("original_title"));
                tempActor.setPosterPath(jsonActor.getString("poster_path"));

                movies.add(tempActor);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    private void showNotFoundNotification() {
        Toast.makeText(this,
                "Sorry we couldn't find anything, please try again",
                Toast.LENGTH_SHORT).
                show();
    }
}
