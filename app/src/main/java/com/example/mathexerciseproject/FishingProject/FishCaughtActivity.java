package com.example.mathexerciseproject.FishingProject;

import static com.example.mathexerciseproject.FishingProject.Consts.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathexerciseproject.R;

public class FishCaughtActivity extends AppCompatActivity {
    Button btnShop;
    TextView tvFishCaught;
    Boolean isCaught;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_caught);
        innit();
        intent();
        textViews();
//        Log.d("isCaught", isCaught); //this not working fix it
    }


    public void intent(){
        Intent intent = getIntent();
        isCaught = intent.getBooleanExtra(ISCAUGHT, false);
        Log.d("","");
    }

    public void innit(){
        btnShop = findViewById(R.id.btnShop);
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