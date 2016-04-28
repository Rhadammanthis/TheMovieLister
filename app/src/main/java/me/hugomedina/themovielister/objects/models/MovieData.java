package me.hugomedina.themovielister.objects.models;

/**
 * Created by Hugo on 4/28/2016.
 */
public class MovieData {

    String synopsis;
    String releaseDate;
    String runTime;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

}
