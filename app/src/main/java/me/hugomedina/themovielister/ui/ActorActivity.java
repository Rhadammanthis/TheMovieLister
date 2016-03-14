package me.hugomedina.themovielister.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.models.ActorModel;

public class ActorActivity extends AppCompatActivity {

    private List<ActorModel> actors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);

        Intent intent = getIntent();

        if (intent != null) {

            String jsonData = intent.getExtras().getString("jsonData");
            getActorsFrom(jsonData);

            Toast.makeText(ActorActivity.this, "Number of Actors: " + actors.size(), Toast.LENGTH_SHORT).show();

           // for(int i = 0; i < actors.size(); i++)
                Toast.makeText(ActorActivity.this, "ActorModel ID" + 0 +": " + actors.get(6).getId(), Toast.LENGTH_SHORT).show();
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w185" + actors.get(6).getPicturePath()).into(imageView);
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

            actors = new ArrayList<>(dataSize);

            for (int i = 0; i < dataSize; i++) {
                JSONObject jsonActor = data.getJSONObject(i);

                ActorModel tempActor = new ActorModel();
                tempActor.setId(jsonActor.getInt("id"));
                tempActor.setName(jsonActor.getString("name"));
                tempActor.setPicturePath(jsonActor.getString("profile_path"));

                JSONArray tempArray = jsonActor.getJSONArray("known_for");
                JSONObject knownFor = tempArray.getJSONObject(0);

                if (knownFor.getString("media_type").equals("movie")) {
                    tempActor.setKnownForFilm(knownFor.getString("original_title"));
                }

                actors.add(tempActor);
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