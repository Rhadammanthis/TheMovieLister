package me.hugomedina.themovielister.interfaces;

import android.view.View;

import me.hugomedina.themovielister.objects.models.MovieModel;

/**
 * Created by Hugo on 2/21/2016.
 */
public interface OnItemClicked {

    void onMovieClicked(View view, MovieModel movie);

}
