package me.hugomedina.themovielister.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.parse.Movie;
import me.hugomedina.themovielister.service.GenericAsyncTask;
import me.hugomedina.themovielister.service.MovieDbUrl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(MovieListerApplication.isOnline)
//            Toast.makeText(MainActivity.this, "On line!", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(MainActivity.this, "Off line :(", Toast.LENGTH_SHORT).show();

        final EditText title = (EditText) findViewById(R.id.editText);
        final EditText title2 = (EditText) findViewById(R.id.editText2);

        Button save = (Button) findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Movie movie = new Movie();
                movie.setTitle(title.getText().toString());
                movie.setTMDBId(title2.getText().toString());
                movie.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null)
                        Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                        else
                            e.printStackTrace();
                    }
                });

            }
        });

        Button load = (Button) findViewById(R.id.button2);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ParseQuery<Movie> query = ParseQuery.getQuery("Movie");
//                query.findInBackground(new FindCallback<Movie>() {
//                    @Override
//                    public void done(List<Movie> objects, ParseException e) {
//                        if (e == null)
//                        {
//                            Toast.makeText(MainActivity.this, "Size: " + String.valueOf(objects.size()), Toast.LENGTH_SHORT).show();
//
//                            if(objects.size() > 0)
//                                title.setText(objects.get(0).getTitle());
//                                title2.setText(objects.get(0).getTMDBId());
//                        }
//                    }
//                });
                MovieDbUrl movieDbUrl = new MovieDbUrl();
                String actorQuery = movieDbUrl.getActorQuery("John");
                GenericAsyncTask downloader = new GenericAsyncTask(MainActivity.this, new ActorActivity().getClass());
                downloader.execute(actorQuery);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
