package me.hugomedina.themovielister.service;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Hugo on 2/17/2016.
 */
public class GenericAsyncTask extends AsyncTask<Void, Void, String> {

    private String mUrl;
    private OnFinishTask mListener;

    /**
     * this handles how to parse the JSON according to which query has been sent<p>
     * 1 = Movie<p>
     * 2 = Cast<p>
     * 3 = Actor<p>
     */
    private int mCode;

    /**
     * Generic implementation of class
     * @param query
     * @param listener
     * @param code
     */
    private GenericAsyncTask(String query, OnFinishTask listener, int code){
        mUrl = query;
        mListener = listener;
        mCode = code;
    }

    /**
     * To be used when querying a movie search
     * @param query The movie to look up
     * @param listener Handle onFinish behaviour
     * @param code To set behaviour in doInBackground
     */
    public static GenericAsyncTask newInstanceSearchMovie(String query, OnFinishTask listener, int code)
    {
        return new GenericAsyncTask(MovieDbUrl.getMovieSearchQuery(query), listener, code);
    }

    public static GenericAsyncTask newInstanceMovieInfo(String movieId, OnFinishTask listener, int code)
    {
        return new GenericAsyncTask(MovieDbUrl.getMovieInfoQuery(movieId), listener, code);
    }

    public static GenericAsyncTask newInstanceGetCastAndCrew(String movieId, OnFinishTask listener, int code)
    {
        return new GenericAsyncTask(MovieDbUrl.getCastQuery(movieId), listener, code);
    }

    public static GenericAsyncTask newInstancePhotos(String movieId, OnFinishTask listener, int code)
    {
        return new GenericAsyncTask(MovieDbUrl.getMovieImages(movieId), listener, code);
    }

    @Override
    protected String doInBackground(Void... params) {
        String url = mUrl;

        switch (mCode)
        {
            case 1:
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = client.newCall(request);

                Response response = null;

                String jsonData = null;

                try {
                    response = call.execute();

                    if (response.isSuccessful()) {
                        jsonData = response.body().string();

                    } else {
                        jsonData = null;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return jsonData;

        }

        return "empty";
    }

    @Override
    protected void onPostExecute(String jsonData) {
        mListener.finishTask(jsonData);
    }

    public interface OnFinishTask {
        /**
         * Funcion que se llama cuando la tarea es terminada y no hay errores ni la consulta es nulla
         *
         * @param result
         */
        void finishTask(String result);
    }
}
