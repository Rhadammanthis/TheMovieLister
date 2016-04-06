package me.hugomedina.themovielister.objects.models;

/**
 * Created by Hugo on 4/5/2016.
 */
public class Cast {



    public Cast(String character, String actor, String picture) {
        this.character = character;
        this.actor = actor;
        this.picture = picture;
    }

    private String character;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPicturePath() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String actor;
    private String picture;

}
