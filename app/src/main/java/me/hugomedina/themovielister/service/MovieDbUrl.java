package me.hugomedina.themovielister.service;

/**
 * Created by Hugo on 2/8/2016.
 */
public class MovieDbUrl {

    //Volatile keyword ensures that multiple threads handle the unique/instance correctly
    private volatile static MovieDbUrl uniqueInstance;

    private static String url = "http://api.themoviedb.org/3/";
    private static String youTubeUrl = "https://www.youtube.com/watch?v=";
    private static String API_KEY = "531aec356bbd54359474847e57c79986";

    public MovieDbUrl() {
    }

    //Check for an instance and if there isn't one enter the synchronized method
    public static MovieDbUrl getInstance() {
        if (uniqueInstance == null) {
            synchronized (MovieDbUrl.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new MovieDbUrl(); //Once in the block, check again and if still null, create the instance
                }
            }
        }
        return uniqueInstance;
    }

    public static String getActorQuery(String nameToSearch){
        return url + "search/person?api_key=" + API_KEY + "&query=" + nameToSearch + "&sort_by=popularity";
    }

    public static String getMovieQuery(String nameToSearch){
        return url + "search/movie?api_key=" + API_KEY + "&query=" + nameToSearch + "&sort_by=popularity";
    }

    public static String getCastQuery(int movieId){
        return url + "movie/" + movieId + "/credits?api_key=" + API_KEY;
    }

    public static String getVideoQuery(int movieId){
        return url + "movie/" + movieId + "/videos?api_key=" + API_KEY;
    }

    public static String getYouTubePath(int videoId)
    {
        return youTubeUrl + videoId;
    }

}
