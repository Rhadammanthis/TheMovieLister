package me.hugomedina.themovielister.interfaces;

import java.util.ArrayList;

import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 2/24/2016.
 */
public interface OnQueryFinished {

    void onMovieQueryFinished(ArrayList<MovieModel> dataSet);

}
