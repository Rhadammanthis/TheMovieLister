package me.hugomedina.themovielister.business;

import android.content.Context;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
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

    public void getAllMovies()
    {
        list = new ArrayList<>();

        ParseQuery<Movie> query = ParseQuery.getQuery("Movie");
        query.findInBackground(new FindCallback<Movie>() {
            @Override
            public void done(List<Movie> objects, ParseException e) {
                if (e == null)
                {

                    for(Movie temp : objects)
                    {
                        MovieModel movie = new MovieModel();
                        movie.setTitle(temp.getTitle());
                        movie.setPosterPath(temp.getPosterPath());

                        list.add(movie);
                    }
                }

                onQueryFinished.onMovieQueryFinished(list);
            }
        });


    }

}
