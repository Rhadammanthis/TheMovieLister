package me.hugomedina.themovielister.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import me.hugomedina.themovielister.InitPreferences;
import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.parse.MovieList;
import me.hugomedina.themovielister.util.*;

public class SettingsActivity extends BaseActivity {

    private List<String> collection;
    private RelativeLayout itemList;
    private Toolbar toolbar;

    private boolean isOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initComponents();
        initToolbar();

        isOnline = super.isOnline();

        itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isOnline) {

                    showListNameDialog();

                }
                else
                {
                    new CustomDialog.Builder(SettingsActivity.this)
                            .setMessage(R.string.system_no_internet)
                            .setPositiveButtom(R.string.system_affirmative, new CustomDialog.CallbackListener() {

                                @Override
                                public void onClick() {
                                }
                            })
                            .show();
                }
            }
        });

    }

    private void showListNameDialog()
    {
        collection = new ArrayList<>();

        ParseQuery<MovieList> query = ParseQuery.getQuery("MovieList");
        Log.d("pUser", ParseUser.getCurrentUser().getObjectId());
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<MovieList>() {
            @Override
            public void done(List<MovieList> objects, ParseException e) {
                if(e == null)
                {
                    for(MovieList object: objects) {
                        collection.add(object.getName());
                    }

                    int selection = InitPreferences.newInstance(SettingsActivity.this).getDefaultList();
                    new MaterialDialog.Builder(SettingsActivity.this)
                            .title("Escoge una sección")
                            .items(collection)
                            .itemsCallbackSingleChoice(selection, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                    InitPreferences.newInstance(SettingsActivity.this).setDefaultList(which);

                                    return true;
                                }
                            })
                            .positiveText(R.string.system_choose)
                            .negativeText(R.string.system_cancel)
                            .show();
                }
            }
        });

    }

    public void initToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.settings_text);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initComponents() {

        itemList = (RelativeLayout) findViewById(R.id.settings_main_list);

    }

    @Override
    public void onBackPressed()
    {
        finish();
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
    }
}
