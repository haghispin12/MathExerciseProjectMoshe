package com.example.mathexerciseproject.FishingProject;

import static com.example.mathexerciseproject.FishingProject.Consts.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathexerciseproject.R;

public class FishCaughtActivity extends AppCompatActivity {
    private Button btnLeaveDocks;
    private TextView tvFishCaught;
    private Boolean isCaught;
    private int fishcaught;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_caught);
        innit(); //initiates all xml variables for use
        intent(); //retrieves data from previous activity
        textViews(); //displays outcome of fish minigame (fish caught || fish got away)
        onClickers(); //initiates onclick
    }

    /*
    All setOnclickers will go here
     */
    private void onClickers() {
        btnLeaveDocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FishCaughtActivity.this, MainIslandActivity.class);
                intent.putExtra(ID_KEY, id);
                if (isCaught){
                    fishcaught = 1;
                    intent.putExtra(FISH_CAUGHT_AMOUNT, fishcaught);
                }
                startActivity(intent);
            }
        });
    }


    public void intent(){
        Intent intent = getIntent();
        id = intent.getIntExtra(ID_KEY, 0);
        isCaught = intent.getBooleanExtra(ISCAUGHT, false);
        Log.d("","");
    }

    public void innit(){
        btnLeaveDocks = findViewById(R.id.btnLeaveDocks);
        tvFishCaught = findViewById(R.id.tvFishCaught);
    }
    public void textViews(){
        if(isCaught){
            tvFishCaught.setText("You caught a FISH!");
        }else {
            tvFishCaught.setText("The fish got away!");
        }

    }
}