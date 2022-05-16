package com.st10120712.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the content view to the BirdGame class, not the xml layout file
        BirdGame birdgame = new BirdGame(this);
        Handler handler = new Handler();
        final long TIME_INTERVAL = 30;
        setContentView(birdgame);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        birdgame.invalidate();
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, TIME_INTERVAL);
    }
}