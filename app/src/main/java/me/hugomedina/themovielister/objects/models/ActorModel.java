package me.hugomedina.themovielister.objects.models;

/**
 * Created by Hugo on 2/11/2016.
 */
public class ActorModel {
    private int id;
    private String name;
    private String picturePath;
    private String knownForFilm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getKnownForFilm() {
        return knownForFilm;
    }

    public void setKnownForFilm(String knownForFilm) {
        this.knownForFilm = knownForFilm;
    }
}
