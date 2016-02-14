package me.hugomedina.themovielister.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import me.hugomedina.themovielister.MovieListerApplication;
import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.objects.parse.User;
import me.hugomedina.themovielister.util.CustomDialog;

public class LogInActivity extends AppCompatActivity {

    private EditText textUsername;
    private EditText textPassword;
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        textUsername = (EditText) findViewById(R.id.logIn_username);
        textPassword = (EditText) findViewById(R.id.logIn_password);

        initDialogs();

        Button buttonSignUp = (Button) findViewById(R.id.logIn_signUp);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                user.setUsername(textUsername.getText().toString());
                user.setPassword(textPassword.getText().toString());
                user.setEmail(textUsername.getText().toString());

                // other fields can be set just like with ParseObject
                //user.put("phone", "650-253-0000");

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            dialog.show();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });
            }
        });

        if(ParseUser.getCurrentUser() != null) {

            if(MovieListerApplication.isOnline) {
                ParseUser.getCurrentUser().fetchInBackground(new GetCallback<User>() {

                    @Override
                    public void done(User user, ParseException e) {
                        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    }

    public void initDialogs()
    {
        //create the user created dialog
        dialog = new CustomDialog.Builder(LogInActivity.this)
                .setTitle("The Movie Lister")
                .setMessage("User created!")
                .setPositiveButtom("Accept", null)
                .create();
    }
}
