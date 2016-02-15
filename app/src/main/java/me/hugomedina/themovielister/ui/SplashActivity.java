package me.hugomedina.themovielister.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.Timer;
import java.util.TimerTask;

import me.hugomedina.themovielister.MovieListerApplication;
import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.parse.User;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // The thread sleeps for 3 seconds then loads MainActivity
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                    if(ParseUser.getCurrentUser() != null){
                        if(MovieListerApplication.isOnline){
                            ParseUser.getCurrentUser().fetchInBackground(new GetCallback<User>() {

                                @Override
                                public void done(User user, ParseException e) {
                                    Intent iInicio = new Intent(SplashActivity.this, ListActivity.class);
                                    startActivity(iInicio);
                                    finish();
                                }
                            });
                        } else {
                            Intent iInicio = new Intent(SplashActivity.this, ListActivity.class);
                            startActivity(iInicio);
                            finish();
                        }

                    } else{
                        Intent iInicio = new Intent(SplashActivity.this, LogInActivity.class);
                        startActivity(iInicio);
                        finish();
                    }
                }
            };
// Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);


    }
}
