package me.hugomedina.themovielister.business;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.interfaces.OnQueryFinished;
import me.hugomedina.themovielister.objects.models.MovieModel;

import me.hugomedina.themovielister.service.GenericAsyncTask;

/**
 * Created by Hugo on 2/24/2016.
 */
public class MovieDAO {

    private Context context;
    private ArrayList<MovieModel> list;
    private OnQueryFinished onQueryFinished;

    public MovieDAO(Context context, OnQueryFinished onQueryFinished)
    {
        this.context = context;
        this.onQueryFinished = onQueryFinished;
    }

    public void getAllMovies()//MovieList movieList)
    {
        list = new ArrayList<>();

//        new GenericAsyncTask().execute();

//        ParseQuery<BelongsTo> queryMovie = ParseQuery.getQuery("BelongsTo");
//        queryMovie.whereEqualTo("idMovieList", movieList);
//        queryMovie.include("idMovie");
//        queryMovie.findInBackground(new FindCallback<BelongsTo>() {
//            @Override
//            public void done(List<BelongsTo> objects, ParseException e) {
//                if(e==null)
//                {
//                    for(BelongsTo temp : objects)
//                    {
//                        Movie movie = temp.getMovie();
//                        MovieModel movieModel = new MovieModel();
//                        movieModel.setTitle(movie.getTitle());
//                        movieModel.setPosterPath(movie.getPosterPath());
//                        movieModel.setId(movie.getTMDBId());
//
//                        list.add(movieModel);
//                    }
//
//                }
//                else
//                {
//                    e.printStackTrace();
//                }
//
//                onQueryFinished.onMovieQueryFinished(list);
//            }
//        });

    }

}
