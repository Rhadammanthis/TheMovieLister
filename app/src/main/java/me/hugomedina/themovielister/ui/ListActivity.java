package me.hugomedina.themovielister.ui;

import android.app.Fragment;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.fragments.MovieListFragment;
import me.hugomedina.themovielister.util.BaseActivity;

public class ListActivity extends BaseActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initToolbar();

        //addFragment(new MovieListFragment(), "movieListFragment");
    }

//    private void addFragment(android.support.v4.app.Fragment fragment, String tag){
//        getFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, fragment, tag)
//                .commit();
//    }

    @Override
    public void initToolbar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.title_list);
//        toolbar.setTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbarLayout.setTitle("My List");
//        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this,android.R.color.transparent));
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }
}
