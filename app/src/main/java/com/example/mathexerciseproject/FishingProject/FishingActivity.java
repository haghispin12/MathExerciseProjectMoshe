package com.example.mathexerciseproject.FishingProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.mathexerciseproject.R;

public class FishingActivity extends AppCompatActivity {

    private GameView gameView;
    private Button btnStart;
    private Button btnStop;
    private Button btnFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);
        InnitViews();
        onClickListeners();
        Log.d("FishingActivity", "PositionX=" + gameView.getFish1().getXPosition() + " SpeedX=" + gameView.getFish1().getSpeed());
        Log.d("IsRunning", "THE GAME IS RUNNING");
    }
    private void onClickListeners() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.startGameLoop();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameView.stopGameLoop();
            }
        });
        btnFish.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        gameView.setIsFish(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        gameView.setIsFish(false);
                }
                    return false;

            }
        });
    }

    private void InnitViews() {
        btnStop = findViewById(R.id.btnStop);
        btnStart = findViewById(R.id.btnStart);
        btnFish = findViewById(R.id.btnFish);
        gameView = findViewById(R.id.game_view);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // It's still important to tell the GameView to stop when the Activity pauses
        if (gameView != null) {
            gameView.stopGameLoop();
        }
    }
}
