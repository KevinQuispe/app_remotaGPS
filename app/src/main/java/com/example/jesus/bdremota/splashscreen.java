package com.example.jesus.bdremota;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kevin Quispe on 12/01/2018.
 */

public class splashscreen  extends AppCompatActivity {
    private static final long timersplash = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent().setClass(splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        };
        //clase tiemer
        Timer timer = new Timer();
        timer.schedule(task, timersplash);

    }
}
