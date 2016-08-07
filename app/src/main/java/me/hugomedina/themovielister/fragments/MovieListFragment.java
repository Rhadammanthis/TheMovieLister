package me.hugomedina.themovielister.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.CustomGridAdapter;
import me.hugomedina.themovielister.adapter.MovieListAdapter;
import me.hugomedina.themovielister.business.MovieDAO;
import me.hugomedina.themovielister.interfaces.OnItemClicked;
import me.hugomedina.themovielister.interfaces.OnQueryFinished;
import me.hugomedina.themovielister.objects.models.Movie;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.ui.MovieActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Hugo on 2/14/2016.
 */
public class MovieListFragment extends android.support.v4.app.Fragment implements OnQueryFinished, OnItemClicked {

    private RecyclerView mRecyclerView;
    //private MovieList movieList;

    private static final String KEY_MOVIE_LIST = "fragment_movie_list";

    /**
     * Creates a new instance of the MovieListFragment class bundled with data.
     * @return new Fragment instance with bundled data
     */
    public static MovieListFragment newInstance()//MovieList movieList)
    {
        MovieListFragment movieListFragment = new MovieListFragment();
//        Bundle arg = new Bundle();
//        arg.putSerializable(KEY_MOVIE_LIST, movieList);
//        movieListFragment.setArguments(arg);

        return movieListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        if(getArguments() != null)
        {
           // movieList = (MovieList) getArguments().getSerializable(KEY_MOVIE_LIST);
        }

//        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.title_list);
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        new GetMovies().execute();

        //new MovieDAO(getActivity(), MovieListFragment.this).getAllMovies(movieList);


//        ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
//        Log.d("pUser", ParseUser.getCurrentUser().getObjectId());
//        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
//        query.findInBackground(new FindCallback<MovieList>() {
//            @Override
//            public void done(List<MovieList> objects, ParseException e) {
//                if(e == null)
//                {
//                    new MovieDAO(getActivity(), MovieListFragment.this).getAllMovies(movieList);
//                }
//            }
//        });



//        dataSet.add(new MovieModel("Miller's Crossing", "/gmUAhNHY4bxQzgHjXci5JAW7u62.jpg"));
//        dataSet.add(new MovieModel("Insomnia","/82smBAmO8by2dwrYL5kIVCJ8uFJ.jpg"));
//        dataSet.add(new MovieModel("Breaking and Entering", "/tH4pFx2e8oxTKTn1DrZv8eqJACn.jpg"));
//        dataSet.add(new MovieModel("Miller's Crossing", "/gmUAhNHY4bxQzgHjXci5JAW7u62.jpg"));
//        dataSet.add(new MovieModel("Insomnia","/82smBAmO8by2dwrYL5kIVCJ8uFJ.jpg"));
//        dataSet.add(new MovieModel("Breaking and Entering", "/tH4pFx2e8oxTKTn1DrZv8eqJACn.jpg"));



        return mainView;
    }

    @Override
    public void onMovieQueryFinished(ArrayList<MovieModel> dataSet) {
        CustomGridAdapter mAdapter = new CustomGridAdapter(dataSet, getActivity(), MovieListFragment.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onMovieClicked(View view, MovieModel movie) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra("movie", movie);
        // Pass data object in the bundle and populate details activity.
        //intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), view, "movie_poster");
        startActivity(intent, options.toBundle());
    }

    private class GetMovies extends AsyncTask<Void, Void, String>
    {

        OnQueryFinished onQueryFinished;

        @Override
        protected String doInBackground(Void... voids)
        {

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\"id\":\"76341\"}");
            Request request = new Request.Builder()
                    .url("http://192.168.1.70:9000/api/movies")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache")
                    .build();

            try {
                Response response = client.newCall(request).execute();

                int i = 0;
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String json)
        {
            Movie movie = new Gson().fromJson(json, Movie.class);
            int i = 0;
        }
    }
}
