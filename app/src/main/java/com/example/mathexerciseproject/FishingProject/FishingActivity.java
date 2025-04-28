package com.example.mathexerciseproject.FishingProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mathexerciseproject.R;

public class FishingActivity extends AppCompatActivity {

    private GameView gameView;
    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);
        InnitViews();
        onClickListeners();

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
    }

    private void InnitViews() {
        btnStop = findViewById(R.id.btnStop);
        btnStart = findViewById(R.id.btnStart);
        gameView = findViewById(R.id.game_view);
    }




}