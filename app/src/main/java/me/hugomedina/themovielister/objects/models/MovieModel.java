package me.hugomedina.themovielister.objects.models;

import java.io.Serializable;

/**
 * Created by Hugo on 2/11/2016.
 */
public class MovieModel implements Serializable {
    public MovieModel(String title, String posterPath, String dbID) {
        this.title = title;
        this.posterPath = posterPath;
        this.id = dbID;
    }

    public MovieModel()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String id;
    private String title;
    private String posterPath;
    private String genre;


}
