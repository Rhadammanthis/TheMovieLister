package me.hugomedina.themovielister.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.MovieListAdapter;
import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 2/14/2016.
 */
public class MovieListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_movie_list, container, false);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) mainView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("My List");
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(getActivity(),android.R.color.transparent));

        Toolbar toolbar = (Toolbar) mainView.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_list);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        RecyclerView mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        ArrayList<MovieModel> dataSet = new ArrayList<>();

        dataSet.add(new MovieModel("Miller's Crossing", "/gmUAhNHY4bxQzgHjXci5JAW7u62.jpg"));
        dataSet.add(new MovieModel("Insomnia","/82smBAmO8by2dwrYL5kIVCJ8uFJ.jpg"));
        dataSet.add(new MovieModel("Breaking and Entering", "/tH4pFx2e8oxTKTn1DrZv8eqJACn.jpg"));

        MovieListAdapter mAdapter = new MovieListAdapter(dataSet, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return mainView;
    }
}
