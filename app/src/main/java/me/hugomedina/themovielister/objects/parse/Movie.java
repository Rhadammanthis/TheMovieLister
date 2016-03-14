package me.hugomedina.themovielister.objects.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Hugo on 2/2/2016.
 */

@ParseClassName("Movie")
public class Movie extends ParseObject{

    private String TITLE = "title";
    private String MDBID = "tMDBId";
    private String POSTER = "posterPath";

    public String getTitle(){
        return getString(TITLE);
    }
    public String getTMDBId(){
        return getString(MDBID);
    }
    public String getPosterPath(){
        return getString(POSTER);
    }

    public void setTitle(String title){
        put(TITLE, title);
    }
    public void setTMDBId(String tmdbId){
        put(MDBID, tmdbId);
    }
    public void setPosterPath(String posterPath){
        put(POSTER, posterPath);
    }

}
