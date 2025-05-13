package com.example.mathexerciseproject.FishingProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathexerciseproject.R;
import com.example.mathexerciseproject.mathproject.LoginActivity;
import com.example.mathexerciseproject.mathproject.MainActivity;

public class MainMenuActivity extends AppCompatActivity {
    private TextView tvLogo;
    private Button btnPlay;
    private TextView tvSave1;
    private TextView tvBalance1;
    private Button btnDelete1;
    private TextView tvSave2;
    private TextView tvBalance2;
    private Button btnDelete2;
    private TextView tvSave3;
    private TextView tvBalance3;
    private Button btnDelete3;
    private Button btnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        innitViews();
        onClickListeners();
    }

    private void onClickListeners() {
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, FishingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void innitViews() {
        tvLogo = findViewById(R.id.tvLogo);
        btnPlay = findViewById(R.id.btnPlay);
        tvSave1 = findViewById(R.id.tvSave1);
        tvBalance1 = findViewById(R.id.tvBalance1);
        btnDelete1 = findViewById(R.id.btnDelete1);
        tvSave2 = findViewById(R.id.tvSave2);
        tvBalance2 = findViewById(R.id.tvBalance2);
        btnDelete2 = findViewById(R.id.btnDelete2);
        tvSave3 = findViewById(R.id.tvSave3);
        tvBalance3 = findViewById(R.id.tvBalance3);
        btnDelete3 = findViewById(R.id.btnDelete3);
        btnSettings = findViewById(R.id.btnSettings);
    }
}