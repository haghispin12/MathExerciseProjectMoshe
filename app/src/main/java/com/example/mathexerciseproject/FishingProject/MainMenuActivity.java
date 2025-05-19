package com.example.mathexerciseproject.FishingProject;

import static android.view.View.INVISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mathexerciseproject.R;

public class MainMenuActivity extends AppCompatActivity {
    private TextView tvLogo;
    private Button btnPlay;
    private TextView tvUserName1;
    private EditText etEditUser1;
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
        texts();
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
        tvUserName1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                etEditUser1.setVisibility(View.VISIBLE);
                tvUserName1.setVisibility(INVISIBLE);
                return true;
            }
        });
        etEditUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEditUser1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                            etEditUser1.setVisibility(INVISIBLE);
                            tvUserName1.setVisibility(View.VISIBLE);
                        }
                        return false;
                    }
                });
            }
        });

    }

    private void innitViews() {
        tvLogo = findViewById(R.id.tvLogo);
        btnPlay = findViewById(R.id.btnPlay);
        tvUserName1 = findViewById(R.id.tvUserName1);
        etEditUser1 = findViewById(R.id.etEditUser1);
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
    public void texts(){
        etEditUser1.setVisibility(View.INVISIBLE);
    }
}