package me.hugomedina.themovielister.objects.models;

/**
 * Created by Hugo on 6/9/2016.
 */
public class ImageModel {

    private double aspect_ratio;
    private String file_path;
    private int height;
    private String iso_639_1;
    private double vote_average;
    private int float_count;
    private int width;

    public ImageModel(double aspect_ratio, String file_path, int height, String iso_639_1, double vote_average, int float_count, int width) {
        this.aspect_ratio = aspect_ratio;
        this.file_path = file_path;
        this.height = height;
        this.iso_639_1 = iso_639_1;
        this.vote_average = vote_average;
        this.float_count = float_count;
        this.width = width;
    }

    public double getAspect_ratio() {
        return aspect_ratio;
    }

    public void setAspect_ratio(double aspect_ratio) {
        this.aspect_ratio = aspect_ratio;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getFloat_count() {
        return float_count;
    }

    public void setFloat_count(int float_count) {
        this.float_count = float_count;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
