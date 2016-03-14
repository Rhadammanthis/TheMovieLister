package me.hugomedina.themovielister.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.MovieListAdapter;
import me.hugomedina.themovielister.business.MovieDAO;
import me.hugomedina.themovielister.interfaces.OnQueryFinished;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.objects.parse.MovieList;

/**
 * Created by Hugo on 2/14/2016.
 */
public class MovieListFragment extends android.support.v4.app.Fragment implements OnQueryFinished {

    private RecyclerView mRecyclerView;
    private MovieList movieList;

    public MovieListFragment(MovieList movieList)
    {
        this.movieList = movieList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_movie_list, container, false);

//        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.title_list);
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recycler);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
        Log.d("pUser", ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<MovieList>() {
            @Override
            public void done(List<MovieList> objects, ParseException e) {
                if(e == null)
                {
                    new MovieDAO(getActivity(), MovieListFragment.this).getAllMovies(movieList);
                }
            }
        });



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
        MovieListAdapter mAdapter = new MovieListAdapter(dataSet, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
}
