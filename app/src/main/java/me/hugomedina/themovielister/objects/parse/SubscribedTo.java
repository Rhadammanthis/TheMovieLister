package me.hugomedina.themovielister.objects.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Hugo on 2/11/2016.
 * Indicates that User - SubscribedTo - MovieList
 */
@ParseClassName("SubscribedTo")
public class SubscribedTo extends ParseObject {

    private String MOVIELIST = "idMovieList";
    private String USER = "idUser";

    public String getMovieList(){
        return getString(MOVIELIST);
    }
    public User getUser(){
        return (User) getParseObject(USER);
    }

    public void setMovieList(MovieList movieList){
        put(MOVIELIST, movieList);
    }
    public void setUser(ParseUser user){
        put(USER, user);
    }
}
