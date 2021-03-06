package me.hugomedina.themovielister.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.CastAdapter;
import me.hugomedina.themovielister.adapter.ImageAdapter;
import me.hugomedina.themovielister.objects.models.Cast;
import me.hugomedina.themovielister.objects.models.Crew;
import me.hugomedina.themovielister.objects.models.ImageModel;
import me.hugomedina.themovielister.objects.models.MovieData;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.objects.parse.BelongsTo;
import me.hugomedina.themovielister.objects.parse.Movie;
import me.hugomedina.themovielister.objects.parse.MovieList;
import me.hugomedina.themovielister.service.GenericAsyncTask;
import me.hugomedina.themovielister.util.CustomDialogProgress;
import me.hugomedina.themovielister.util.JSONParser;

public class MovieActivity extends Activity{

    /**
     * Holds basic movie info
     */
    MovieModel movie;

    CustomDialogProgress  mDialog;

    /**
     * To check if the movie is already in Parse altogether.
     */
    private boolean isInParse = false;
    private ArrayList<MovieList> movieLists = new ArrayList<>();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initToolbar();
        initDialog();

        //retrieves users's lists
        getMovieLists();

        //loads movie information
        Intent i = getIntent();
        movie = (MovieModel) i.getSerializableExtra("movie");
        toolbar.setTitle(movie.getTitle());

        //queries for data not stored in the MovieModel
        requestAdditionalMovieData();

        //Loads poster image
        ImageView poster = (ImageView) findViewById(R.id.moviePoster);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500" +
                movie.getPosterPath()).into(poster);

        //checks if movie is already in the Parse DB
        ParseQuery<Movie> queryMovie = ParseQuery.getQuery("Movie");
        queryMovie.whereEqualTo("title",movie.getTitle());
        queryMovie.getFirstInBackground(new GetCallback<Movie>() {
            @Override
            public void done(Movie object, ParseException e) {
                if(e == null)
                {
                    if(object != null)
                    {
                        isInParse = true;
                    }
                }
            }
        });

        //Behaviour to add movie to list when the FAB is clicked
        FloatingActionButton fAB = (FloatingActionButton) findViewById(R.id.movieFAB);
        fAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //If the user has more than 1 list, show dialog with all the Movie Lists so the user can choose which will the Movie be added to
                if (movieLists.size() > 1) {
                    new MaterialDialog.Builder(MovieActivity.this)
                            .title("Select a List")
                            .items(getListNameCollection())
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                    Movie movieParse = new Movie();
                                    movieParse.setTMDBId(String.valueOf(movie.getId()));
                                    movieParse.setTitle(movie.getTitle());
                                    movieParse.setPosterPath(movie.getPosterPath());

                                    BelongsTo belongsTo = new BelongsTo();
                                    belongsTo.setMovieList(movieLists.get(which));
                                    belongsTo.setMovie(movieParse);

                                    belongsTo.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            if (e == null) {
                                                Toast.makeText(MovieActivity.this, "Movie added to List", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(MovieActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    return true;
                                }
                            })
                            .positiveText("Choose")
                            .negativeText("Cancel")
                            .show();
                } else {

                    //Look for Movie Lists created by user
                    Movie movieParse = new Movie();
                    movieParse.setTMDBId(String.valueOf(movie.getId()));
                    movieParse.setTitle(movie.getTitle());
                    movieParse.setPosterPath(movie.getPosterPath());

                    BelongsTo belongsTo = new BelongsTo();
                    belongsTo.setMovieList(movieLists.get(0));
                    belongsTo.setMovie(movieParse);

                    belongsTo.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(MovieActivity.this, "Movie added to List", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MovieActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }



//                ParseQuery<MovieList> query = new ParseQuery<MovieList>.getQuery("");
//                query.getInBackground("QMUiO809j9", new GetCallback<MovieList>() {
//                    @Override
//                    public void done(MovieList object, ParseException e) {
//                        if(e != null)
//                            movieList = object;
//                        else
//                            Toast.makeText(MovieActivity.this, "No List", Toast.LENGTH_SHORT).show();
//                    }
//                });
//


        });
    }

    /**
     * Starts the chained async data request
     */
    private void requestAdditionalMovieData()
    {
       // mDialog.show();

        GenericAsyncTask.newInstanceGetCastAndCrew(
                movie.getId(),
                taskListenerCastCrew,
                1).execute();
    }

    /**
     * Initializes toolbar
     */
    private void initToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Movie");
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * Loads a local instance of movie lists in Parse into a collection
     */
    private void getMovieLists()
    {
        final ArrayList<MovieList> list = new ArrayList<>();

        ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
        Log.d("pUser", ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<MovieList>() {
            @Override
            public void done(List<MovieList> objects, ParseException e) {
                if(e == null)
                {
                    for(MovieList temp: objects)
                        movieLists.add(temp);
                }
            }
        });
    }

    /**
     *
     * @return Collection with the names of the user's lists
     */
    private List<String> getListNameCollection()
    {
        List<String> collection = new ArrayList<>();

        for(MovieList temp: movieLists)
        {
            collection.add(temp.getName());
        }

        return collection;
    }

    /**
     * Initializes global dialog instance
     */
    private void initDialog()
    {
        mDialog = new CustomDialogProgress
                .Builder(MovieActivity.this)
                .setMessage(R.string.system_loading)
                .setProgress(true, 0)
                .create();
    }

    //To change date String format
    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    //AsyncTask response listener for Cast and Crew
    private GenericAsyncTask.OnFinishTask taskListenerCastCrew = new GenericAsyncTask.OnFinishTask() {
        @Override
        public void finishTask(String result) {

            ArrayList<Crew> crewList = new JSONParser(MovieActivity.this).getMovieCrew(result);
            ArrayList<Cast> castList = new JSONParser(MovieActivity.this).getMovieCast(result);

            if(crewList != null)
            {
                TextView director = (TextView) findViewById(R.id.movie_director);
                TextView writer = (TextView) findViewById(R.id.movie_writer);

                String directorComplete = "", writerComplete = "";

                //Checks if the movie has multiple credits for Director and Writer
                for(Crew crew:crewList)
                {
                    if(crew.getJob().equals("Director"))
                        directorComplete += crew.getName() + " & ";
                    if(crew.getJob().equals("Writer") || crew.getJob().equals("Screenplay"))
                        writerComplete += crew.getName() + " & ";
                }

                //Cleans the formatting
                if(!directorComplete.isEmpty())
                    directorComplete = directorComplete.substring(0, directorComplete.lastIndexOf("&") - 1);
                if(!writerComplete.isEmpty())
                    writerComplete = writerComplete.substring(0, writerComplete.lastIndexOf("&") - 1);

                writer.setText(writerComplete);
                director.setText(directorComplete);

            }
            if(castList != null) {

                //Populates RecyclerView with cast info
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.movie_cast);
                mRecyclerView.setNestedScrollingEnabled(false);
                mRecyclerView.setHasFixedSize(true);

                RecyclerView.Adapter mAdapter = new CastAdapter(castList, MovieActivity.this);
                mRecyclerView.setAdapter(mAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
            }

            //Chained async request
            GenericAsyncTask.newInstanceMovieInfo(
                    movie.getId(),
                    movieInfoListener,
                    1).execute();

        }
    };

    private GenericAsyncTask.OnFinishTask movieInfoListener = new GenericAsyncTask.OnFinishTask() {
        @Override
        public void finishTask(String result) {

            MovieData movieData = new JSONParser(MovieActivity.this).getMovieData(result);

            //Loads additional Movie data
            if(movieData != null)
            {
                TextView synopsis = (TextView) findViewById(R.id.movie_synopsis);
                TextView releaseDate = (TextView) findViewById(R.id.movie_release_date);
                TextView runTime = (TextView) findViewById(R.id.movie_runtime);

                synopsis.setText(movieData.getSynopsis());
                releaseDate.setText(parseDateToddMMyyyy(movieData.getReleaseDate()));
                runTime.setText(movieData.getRunTime() + " minutes");

            }

            //Chained async request
            GenericAsyncTask.newInstancePhotos(
                    movie.getId(),
                    moviePhotosListener,
                    1).execute();

        }
    };

    private GenericAsyncTask.OnFinishTask moviePhotosListener = new GenericAsyncTask.OnFinishTask() {
        @Override
        public void finishTask(String result) {

            ArrayList<ImageModel> imageList = new JSONParser(MovieActivity.this).getMovieImages(result);

            if(imageList != null)
            {

                //Populates horizontal RecyclerView with Movie images
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.movie_pictures);
                mRecyclerView.setHasFixedSize(true);

                RecyclerView.Adapter mAdapter = new ImageAdapter(imageList, MovieActivity.this);
                mRecyclerView.setAdapter(mAdapter);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MovieActivity.this,LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setLayoutManager(layoutManager);

            }

            mDialog.dismiss();

        }
    };



}
