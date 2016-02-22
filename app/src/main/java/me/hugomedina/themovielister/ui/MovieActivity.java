package me.hugomedina.themovielister.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.models.MovieModel;
import me.hugomedina.themovielister.objects.parse.BelongsTo;
import me.hugomedina.themovielister.objects.parse.Movie;
import me.hugomedina.themovielister.objects.parse.MovieList;
import me.hugomedina.themovielister.objects.parse.SubscribedTo;

public class MovieActivity extends Activity {

    MovieModel movie;
    MovieList movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent i = getIntent();
        movie = (MovieModel) i.getSerializableExtra("movie");

        TextView title = (TextView) findViewById(R.id.movieTitle);
        title.setText(movie.getTitle());

        ImageView poster = (ImageView) findViewById(R.id.moviePoster);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w300" +
                movie.getPosterPath()).into(poster);

        FloatingActionButton fAB = (FloatingActionButton) findViewById(R.id.movieFAB);
        fAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
                query.findInBackground(new FindCallback<MovieList>() {
                    @Override
                    public void done(List<MovieList> objects, ParseException e) {
                        if (e == null)
                        {
                            Toast.makeText(MovieActivity.this, "Size: " + String.valueOf(objects.size()), Toast.LENGTH_SHORT).show();
                            Movie movieParse = new Movie();
                            movieParse.setTMDBId(String.valueOf(movie.getId()));
                            movieParse.setTitle(movie.getTitle());

                            BelongsTo belongsTo = new BelongsTo();
                            belongsTo.setMovieList(objects.get(0));
                            belongsTo.setMovie(movieParse);

                            belongsTo.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e == null)
                                    {
                                        Toast.makeText(MovieActivity.this, "Movie added to List", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(MovieActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });

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

            }
        });
    }
}
