package me.hugomedina.themovielister.objects.models;

/**
 * Created by Hugo on 4/5/2016.
 */
public class Crew {

    public Crew(String job, String name, String picture) {
        this.job = job;
        this.name = name;
        this.picture = picture;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String job;
    private String name;
    private String picture;

}
