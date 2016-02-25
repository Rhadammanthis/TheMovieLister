package me.hugomedina.themovielister.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.parse.MovieList;
import me.hugomedina.themovielister.objects.parse.SubscribedTo;

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

                startActivity(new Intent(MainActivity.this, ListActivity.class));

//                MovieList movieList = new MovieList();
//                movieList.setName("My First List");
//                movieList.setUser(ParseUser.getCurrentUser());
//
//                SubscribedTo belongsTo = new SubscribedTo();
//                belongsTo.setMovieList(movieList);
//                belongsTo.setUser(ParseUser.getCurrentUser());
//
//                belongsTo.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if(e == null)
//                        {
//                            Toast.makeText(MainActivity.this, "List created!", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();
//                        }
//                    }
//                });

           }
        });

        Button load = (Button) findViewById(R.id.button2);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ParseQuery<Movie> query = ParseQuery.getQuery("Movie");
//                query.findInBackground(new FindCallback<Movie>() {
//                    @Override
//                    public void done(MovieList<Movie> objects, ParseException e) {
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
               // MovieDbUrl movieDbUrl = new MovieDbUrl();

//                String actorQuery = MovieDbUrl.getActorQuery("John");
//                MyGenericAsyncTask downloader = new MyGenericAsyncTask(MainActivity.this, new ActorActivity().getClass());
//                downloader.execute(actorQuery);

//                String actorQuery = MovieDbUrl.getMovieQuery(title.getText().toString());
//                MyGenericAsyncTask downloader = new MyGenericAsyncTask(MainActivity.this, new SearchActivity().getClass());
//                downloader.execute(actorQuery);
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
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
