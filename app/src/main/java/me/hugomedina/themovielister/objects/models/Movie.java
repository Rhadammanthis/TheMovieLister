
package me.hugomedina.themovielister.objects.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String backdrop_path;
    private String original_title;
    private String overview;
    private String poster_path;
    private List<Cast> cast = new ArrayList<Cast>();
    private List<Crew> crew = new ArrayList<Crew>();
    private List<Backdrop> backdrops = new ArrayList<Backdrop>();
    private List<Poster> posters = new ArrayList<Poster>();

    /**
     * 
     * @return
     *     The backdropPath
     */
    public String getBackdropPath() {
        return backdrop_path;
    }

    /**
     * 
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return original_title;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The posterPath
     */
    public String getPosterPath() {
        return poster_path;
    }

    /**
     * 
     * @param posterPath
     *     The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    /**
     * 
     * @return
     *     The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * 
     * @param cast
     *     The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * 
     * @return
     *     The crew
     */
    public List<Crew> getCrew() {
        return crew;
    }

    /**
     * 
     * @param crew
     *     The crew
     */
    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    /**
     * 
     * @return
     *     The backdrops
     */
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    /**
     * 
     * @param backdrops
     *     The backdrops
     */
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     * 
     * @return
     *     The posters
     */
    public List<Poster> getPosters() {
        return posters;
    }

    /**
     * 
     * @param posters
     *     The posters
     */
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
