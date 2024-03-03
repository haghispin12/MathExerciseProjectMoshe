package com.example.mathexerciseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class RateActivity extends AppCompatActivity {
    private Button btnSubmit;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        innitViews();
        onClickListeners();
    }

    public void innitViews(){
        btnSubmit = findViewById(R.id.btnSubmit);
        seekBar = findViewById(R.id.seekBar);
    }

    public void onClickListeners(){
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Rating", seekBar.getProgress());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}