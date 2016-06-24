package me.hugomedina.themovielister.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.SearchAdapter;
import me.hugomedina.themovielister.interfaces.OnItemClicked;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.service.GenericAsyncTask;
import me.hugomedina.themovielister.util.CustomDialogProgress;
import me.hugomedina.themovielister.util.JSONParser;

public class SearchActivity extends AppCompatActivity implements GenericAsyncTask.OnFinishTask, OnItemClicked{

    private ArrayList<MovieModel> movies;
    private Button searchButton;
    private EditText searchText;
    private CustomDialogProgress mDialog;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();
        initDialog();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Launch AsyncTask to query the text in the TextEdit
     * @param query Text to lookup for
     */
    private void performSearch(String query)
    {
        mDialog.show();
        GenericAsyncTask.newInstanceSearchMovie(
                query,
                SearchActivity.this,
                1).execute();
    }

    private void initComponents()
    {

        getSupportActionBar().setTitle(R.string.title_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDialog()
    {
        mDialog = new CustomDialogProgress
                .Builder(SearchActivity.this)
                .setMessage(R.string.system_loading)
                .setProgress(true, 0)
                .create();
    }


    @Override
    public void finishTask(String s) {

        mDialog.dismiss();

        movies = new JSONParser(this).getMovies(s);

        if(movies != null) {
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.Adapter mAdapter = new SearchAdapter(movies, this, this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onMovieClicked(View view, MovieModel movie) {
        Intent intent = new Intent(SearchActivity.this, MovieActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
