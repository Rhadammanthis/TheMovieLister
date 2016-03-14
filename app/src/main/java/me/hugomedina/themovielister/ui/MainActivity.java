package me.hugomedina.themovielister.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import me.hugomedina.themovielister.InitPreferences;
import me.hugomedina.themovielister.MovieListerApplication;
import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.adapter.MainPagerAdapter;
import me.hugomedina.themovielister.business.MovieDAO;
import me.hugomedina.themovielister.objects.parse.MovieList;
import me.hugomedina.themovielister.objects.parse.SubscribedTo;
import me.hugomedina.themovielister.util.CustomDialogProgress;

public class MainActivity extends AppCompatActivity {

    private CustomDialogProgress dialog;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private MovieList defaultMovieList;
    private NavigationView navigationView;

    private String listTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initDrawerLayout();
        initDialog();
        initNavigation();

        final int defaultList = InitPreferences.newInstance(MainActivity.this).getDefaultList();

        ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
        Log.d("pUser", ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<MovieList>() {
            @Override
            public void done(List<MovieList> objects, ParseException e) {
                if(e == null)
                {
                    defaultMovieList = objects.get(defaultList);
                    listTitle = defaultMovieList.getName();
                    initPagerAdapter();
                }
            }
        });




//        if(MovieListerApplication.isOnline)
//            Toast.makeText(MainActivity.this, "On line!", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(MainActivity.this, "Off line :(", Toast.LENGTH_SHORT).show();

//        final EditText title = (EditText) findViewById(R.id.editText);
//        final EditText title2 = (EditText) findViewById(R.id.editText2);

//        Button save = (Button) findViewById(R.id.button);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(MainActivity.this, ListActivity.class));
//
////                MovieList movieList = new MovieList();
////                movieList.setName("My First List");
////                movieList.setUser(ParseUser.getCurrentUser());
////
////                SubscribedTo belongsTo = new SubscribedTo();
////                belongsTo.setMovieList(movieList);
////                belongsTo.setUser(ParseUser.getCurrentUser());
////
////                belongsTo.saveInBackground(new SaveCallback() {
////                    @Override
////                    public void done(ParseException e) {
////                        if(e == null)
////                        {
////                            Toast.makeText(MainActivity.this, "List created!", Toast.LENGTH_SHORT).show();
////                        }
////                        else
////                        {
////                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
////                            e.printStackTrace();
////                        }
////                    }
////                });
//
//           }
//        });

//        Button load = (Button) findViewById(R.id.button2);
//        load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                ParseQuery<Movie> query = ParseQuery.getQuery("Movie");
////                query.findInBackground(new FindCallback<Movie>() {
////                    @Override
////                    public void done(MovieList<Movie> objects, ParseException e) {
////                        if (e == null)
////                        {
////                            Toast.makeText(MainActivity.this, "Size: " + String.valueOf(objects.size()), Toast.LENGTH_SHORT).show();
////
////                            if(objects.size() > 0)
////                                title.setText(objects.get(0).getTitle());
////                                title2.setText(objects.get(0).getTMDBId());
////                        }
////                    }
////                });
//               // MovieDbUrl movieDbUrl = new MovieDbUrl();
//
////                String actorQuery = MovieDbUrl.getActorQuery("John");
////                MyGenericAsyncTask downloader = new MyGenericAsyncTask(MainActivity.this, new ActorActivity().getClass());
////                downloader.execute(actorQuery);
//
////                String actorQuery = MovieDbUrl.getMovieQuery(title.getText().toString());
////                MyGenericAsyncTask downloader = new MyGenericAsyncTask(MainActivity.this, new SearchActivity().getClass());
////                downloader.execute(actorQuery);
//                startActivity(new Intent(MainActivity.this, SearchActivity.class));
//            }
//        });
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

    private void initPagerAdapter()
    {
        ViewPager mPager = (ViewPager) findViewById(R.id.main_pager);
        PagerAdapter mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), defaultMovieList);
        mPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);

        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.fab);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });

        toolbar.setTitle(listTitle);

        dialog.dismiss();
    }

    private void initNavigation() {
        navigationView = (NavigationView) findViewById(R.id.navigation);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(false);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                if(MovieListerApplication.isLollipop){
                    toolbar.setElevation(17f);
                }

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){
                    //Replacing the main content with ContentFragment Which is our Inbox View;
//                    case R.id.action_sections:
//                        menuItem.setChecked(true);
//                        if(getFragmentManager().findFragmentByTag("section")==null) {
//                            replaceFragment(new SectionsFragment(),"section");
//                        }

                    case R.id.action_search:
                        startActivity(new Intent(MainActivity.this,SearchActivity.class));
                        return true;

                    case R.id.action_settings:
                        finish();
                        startActivity(new Intent(MainActivity.this,SettingsActivity.class));
                        return true;

                    default:return true;
                }
            }
        });
    }

    private void initToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("The Movie Lister");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initDrawerLayout() {
        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.menu_main, R.string.menu_main){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    private void initDialog() {
        dialog = new CustomDialogProgress.Builder(this)
                .setMessage(R.string.system_loading)
                .setCancelable(false)
                .setProgress(true, 0)
                .create()
                .show();
    }
}
