package me.hugomedina.themovielister.objects.models;

import java.io.Serializable;

/**
 * Created by Hugo on 2/11/2016.
 */
public class MovieModel implements Serializable {
    public MovieModel(String title, String posterPath) {
        this.title = title;
        this.posterPath = posterPath;
    }

    public MovieModel()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    private int id;
    private String title;
    private String posterPath;
    private String genre;


}
