package com.example.mathexerciseproject.FishingProject;

import static android.view.View.INVISIBLE;

import static com.example.mathexerciseproject.FishingProject.Consts.BAL;
import static com.example.mathexerciseproject.FishingProject.Consts.ISCAUGHT;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private EditText etEditUser2;
    private TextView tvUserName2;
    private TextView tvBalance2;
    private Button btnDelete2;
    private EditText etEditUser3;
    private TextView tvUserName3;
    private TextView tvBalance3;
    private Button btnDelete3;
    private Button btnSettings;
    private User user1;
    private User user2;
    private User user3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        innitViews();
        onClickListeners();
        texts();
        SaveSystem();
        updateViews();
    }

    private void updateViews() {
        if(user1 != null) {
            tvUserName1.setText(user1.getUserName());
            etEditUser1.setText(tvUserName1.getText());
            tvBalance1.setText(BAL + user1.getBalance());
        }
        if(user2!=null) {
            tvUserName2.setText(user2.getUserName());
            etEditUser2.setText(tvUserName2.getText());
            tvBalance2.setText(BAL + user2.getBalance());
        }
        if(user3!=null) {
            tvUserName3.setText(user3.getUserName());
            etEditUser3.setText(tvUserName3.getText());
            tvBalance3.setText(BAL + user3.getBalance());
        }
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
    public void SaveSystem(){
        /*
        Save number 1
         */
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
                            tvUserName1.setText(etEditUser1.getText().toString());
                            tvUserName1.setVisibility(View.VISIBLE);
                            if(user1==null) {
                                user1 = new User(etEditUser1.getText().toString(), 0, 0, 3, 0);
                            } else {
                                user1.setUserName(etEditUser1.getText().toString());
                            }
                            DBHelperFish dbHelperFish = new DBHelperFish(MainMenuActivity.this);
                            dbHelperFish.insert(user1, MainMenuActivity.this);

                        }
                        return false;
                    }
                });
            }
        });
        /*
        Save number 2
         */
        tvUserName2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                etEditUser2.setVisibility(View.VISIBLE);
                tvUserName2.setVisibility(INVISIBLE);
                return true;
            }
        });
        etEditUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEditUser2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                            etEditUser2.setVisibility(INVISIBLE);
                            tvUserName2.setText(etEditUser2.getText().toString());
                            tvUserName2.setVisibility(View.VISIBLE);
                            if(user2==null) {
                                user2 = new User(etEditUser2.getText().toString(), 0, 0, 3, 0);
                            } else {
                                user2.setUserName(etEditUser2.getText().toString());
                            }
                            DBHelperFish dbHelperFish = new DBHelperFish(MainMenuActivity.this);
                            dbHelperFish.insert(user2, MainMenuActivity.this);
                            Log.d(ISCAUGHT, "username "+user2.getUserName() + "bucketsize "+ user2.getBucketSize());
                        }
                        return false;
                    }
                });
            }
        });
        /*
        Save number 3
         */
        tvUserName3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                etEditUser3.setVisibility(View.VISIBLE);
                tvUserName3.setVisibility(INVISIBLE);
                return true;
            }
        });
        etEditUser3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEditUser3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                            etEditUser3.setVisibility(INVISIBLE);
                            tvUserName3.setText(etEditUser3.getText().toString());
                            tvUserName3.setVisibility(View.VISIBLE);
                            if(user3==null) {
                                user3 = new User(etEditUser3.getText().toString(), 0, 0, 3, 0);
                            } else {
                                user3.setUserName(etEditUser3.getText().toString());
                            }
                            DBHelperFish dbHelperFish = new DBHelperFish(MainMenuActivity.this);
                            dbHelperFish.insert(user3, MainMenuActivity.this);
                            Log.d(ISCAUGHT, "username "+user3.getUserName() + "bucketsize "+ user3.getBucketSize());
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
        tvUserName2 = findViewById(R.id.tvUserName2);
        etEditUser2 = findViewById(R.id.etEditUser2);
        tvBalance2 = findViewById(R.id.tvBalance2);
        btnDelete2 = findViewById(R.id.btnDelete2);
        tvUserName3 = findViewById(R.id.tvUserName3);
        etEditUser3 = findViewById(R.id.etEditUser3);
        tvBalance3 = findViewById(R.id.tvBalance3);
        btnDelete3 = findViewById(R.id.btnDelete3);
        btnSettings = findViewById(R.id.btnSettings);

    }
    public void texts(){
        etEditUser1.setVisibility(View.INVISIBLE);
        etEditUser2.setVisibility(View.INVISIBLE);
        etEditUser3.setVisibility(View.INVISIBLE);
    }

    /*
    getters
     */

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public User getUser3() {
        return user3;
    }
}