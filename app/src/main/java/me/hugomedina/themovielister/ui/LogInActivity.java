package me.hugomedina.themovielister.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.hugomedina.themovielister.R;
import me.hugomedina.themovielister.util.CustomDialog;
import me.hugomedina.themovielister.util.CustomDialogProgress;

public class LogInActivity extends AppCompatActivity {

    private CustomDialogProgress mProgress;

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
        if(buttonSignUp != null)
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ParseUser user = new ParseUser();
//                user.setUsername(textUsername.getText().toString());
//                user.setPassword(textPassword.getText().toString());
//                user.setEmail(textUsername.getText().toString());
//
//                // other fields can be set just like with ParseObject
//                //user.put("phone", "650-253-0000");
//
//                user.signUpInBackground(new SignUpCallback() {
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            dialog.show();
//                        } else {
//
//                            // Sign up didn't succeed. Look at the ParseException
//                            // to figure out what went wrong
//                        }
//                    }
//                });
            }
        });

        Button buttonLogIn = (Button) findViewById(R.id.logIn_logIn);
        if(buttonLogIn != null)
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(i);
                finish();


//                mProgress.show();
//                ParseUser.logInInBackground(textUsername.getText().toString(), textPassword.getText().toString(), new LogInCallback() {
//                    @Override
//                    public void done(ParseUser user, ParseException e) {
//                        mProgress.dismiss();
//                        if(e == null)
//                        {
//                            Intent i = new Intent(LogInActivity.this, MainActivity.class);
//                            startActivity(i);
//                            finish();
//                        }
//                        else
//                        {
//                            new CustomDialog.Builder(LogInActivity.this)
//                                    .setTitle("Error")
//                                    .setMessage("User not found")
//                                    .setPositiveButtom("Accept", null)
//                                    .create()
//                                    .show();
//                        }
//                    }
//                });
            }
        });


    }

    public void initDialogs()
    {
        //create the user created dialog
        dialog = new CustomDialog.Builder(LogInActivity.this)
                .setTitle("The Movie Lister")
                .setMessage("User created!")
                .setPositiveButtom("Accept", null)
                .create();

        mProgress = new CustomDialogProgress.Builder(LogInActivity.this)
                .setMessage(R.string.system_loading)
                .setProgress(true, 0)
                .setCancelable(false)
                .create();
    }
}
