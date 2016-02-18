package me.hugomedina.themovielister.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.SearchAdapter;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.service.GenericAsyncTask;
import me.hugomedina.themovielister.service.MyGenericAsyncTask;
import me.hugomedina.themovielister.service.MovieDbUrl;
import me.hugomedina.themovielister.util.CustomDialogProgress;

public class SearchActivity extends Activity implements GenericAsyncTask.OnFinishTask{

    private ArrayList<MovieModel> movies;
    private Button searchButton;
    private EditText searchText;
    private CustomDialogProgress mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();
        initDialog();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.show();
                new GenericAsyncTask(
                        SearchActivity.this,
                        searchText.getText().toString(),
                        "Loading",
                        SearchActivity.this).execute();
            }
        });
//
//        Intent intent = getIntent();
//
//        if (intent != null) {
//
//            String jsonData = intent.getExtras().getString("jsonData");
//            getActorsFrom(jsonData);
//
//            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(layoutManager);
//            mRecyclerView.setHasFixedSize(true);
//
//            RecyclerView.Adapter mAdapter = new SearchAdapter(movies, this);
//            mRecyclerView.setAdapter(mAdapter);
//
//        }



//        Button searchButton = (Button) findViewById(R.id.button_search);

    }

    private void initComponents()
    {
        searchButton = (Button) findViewById(R.id.button_search);
        searchText = (EditText) findViewById(R.id.text_to_search);
    }

    private void initDialog()
    {
        mDialog = new CustomDialogProgress
                .Builder(SearchActivity.this)
                .setProgress(true, 0)
                .create();
    }

    private void showNotFoundNotification() {
        Toast.makeText(this,
                "Sorry we couldn't find anything, please try again",
                Toast.LENGTH_SHORT).
                show();
    }

    @Override
    public void finishTask(String s) {

        mDialog.dismiss();
        getActorsFrom(s);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.Adapter mAdapter = new SearchAdapter(movies, this);
        mRecyclerView.setAdapter(mAdapter);

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

}
