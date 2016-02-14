package me.hugomedina.themovielister.objects.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Hugo on 2/11/2016.
 * Indicates that Movie - BelongsTo - MovieList
 */
@ParseClassName("BelongsTo")
public class BelongsTo extends ParseObject{

    private String MOVIELIST = "idMovieList";
    private String MOVIE = "idMovie";

    public String getMovieList(){
        return getString(MOVIELIST);
    }
    public Movie getMovie(){
        return (Movie) getParseObject(MOVIE);
    }

    public void setMovieList(MovieList movieList){
        put(MOVIELIST, movieList);
    }
    public void setMovie(Movie movie){
        put(MOVIE, movie);
    }

}
