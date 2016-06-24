package me.hugomedina.themovielister.objects.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;

/**
 * Created by Hugo on 2/11/2016.
 */
@ParseClassName("MovieList")
public class MovieList extends ParseObject implements Serializable{

    private String NAME = "name";
    private String USER = "createdBy";

    public String getName(){
        return getString(NAME);
    }
    public User getUser(){ return (User) getParseObject(USER);}

    public void setName(String name){
        put(NAME, name);
    }
    public void setUser(ParseUser user){put(USER, user);}

}
