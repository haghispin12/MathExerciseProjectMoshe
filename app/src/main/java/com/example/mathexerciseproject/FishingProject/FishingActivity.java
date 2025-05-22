package com.example.mathexerciseproject.FishingProject;

import static com.example.mathexerciseproject.FishingProject.Consts.*;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathexerciseproject.R;

public class FishingActivity extends AppCompatActivity {

    private GameView gameView;
    private Button btnStart;
    private Button btnStop;
    private Button btnFish;
    private TextView tvFishScore;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing);
        InnitViews();//explanation
        intents();//Retrieve data from previous activity
        onClickListeners();//explanation
    }

    private void intents() {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID_KEY, 0);
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
        tvFishScore = findViewById(R.id.tvFishScore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //tell the GameView to stop when the Activity pauses
        if (gameView != null) {
            gameView.stopGameLoop();
        }
    }

    protected void onStop() {
        super.onStop();

    }

    public void onGameConclusion(){
        Intent intent = new Intent(this, FishCaughtActivity.class);
        boolean n = gameView.getIsCaught();
        intent.putExtra(ISCAUGHT, gameView.getIsCaught());
        intent.putExtra(ID_KEY, id);//save the id of the character that caught/lost the fish
        startActivity(intent);
        finish();
    }
//    public boolean onTouchEvent(MotionEvent motionEvent){
//
//    }
}
