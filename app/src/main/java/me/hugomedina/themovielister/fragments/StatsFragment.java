package me.hugomedina.themovielister.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.hugomedina.themovielister.R;

/**
 * Created by Hugo on 2/28/2016.
 */
public class StatsFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_stats, container, false);

        return mainView;
    }

}
