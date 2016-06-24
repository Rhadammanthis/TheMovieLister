package me.hugomedina.themovielister.business;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.interfaces.OnQueryFinished;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.objects.parse.BelongsTo;
import me.hugomedina.themovielister.objects.parse.Movie;
import me.hugomedina.themovielister.objects.parse.MovieList;

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

    public void getAllMovies(MovieList movieList)
    {
        list = new ArrayList<>();

        ParseQuery<BelongsTo> queryMovie = ParseQuery.getQuery("BelongsTo");
        queryMovie.whereEqualTo("idMovieList", movieList);
        queryMovie.include("idMovie");
        queryMovie.findInBackground(new FindCallback<BelongsTo>() {
            @Override
            public void done(List<BelongsTo> objects, ParseException e) {
                if(e==null)
                {
                    for(BelongsTo temp : objects)
                    {
                        Movie movie = temp.getMovie();
                        MovieModel movieModel = new MovieModel();
                        movieModel.setTitle(movie.getTitle());
                        movieModel.setPosterPath(movie.getPosterPath());
                        movieModel.setId(movie.getTMDBId());

                        list.add(movieModel);
                    }

                }
                else
                {
                    e.printStackTrace();
                }

                onQueryFinished.onMovieQueryFinished(list);
            }
        });

    }

}
