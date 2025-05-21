package com.example.mathexerciseproject.FishingProject;

import static com.example.mathexerciseproject.FishingProject.Consts.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathexerciseproject.R;

import java.util.ArrayList;

public class MainIslandActivity extends AppCompatActivity {
    private Button btnMainMenu;
    private Button btnGoFish;
    private TextView tvWelcome;
    private User selectedUser;
    private DBHelperFish dbHelperFish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_island);
        dbHelperFish = new DBHelperFish(this);//Initiates dbHelperFish
        innitViews();//Initiates all views from xml for use
        intents();//Retrieves data from previous Activity and use it
        updateViews();//Changes details like text or background color
        onClickers();//All my onClickListeners in one place
        Toast toast = Toast.makeText(this, "Bucket: "+selectedUser.getFishAmount()+"/"+selectedUser.getBucketSize(), Toast.LENGTH_SHORT);
        toast.show();
    }


    private void onClickers() {
        //go to MainMenuActivity
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainIslandActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //go to FishingActivity
        btnGoFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedUser.getFishAmount()<=selectedUser.getBucketSize()){
                    Intent intent = new Intent(MainIslandActivity.this, FishingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void intents() {
        if(selectedUser==null) {
            setSelectedUser(); //sets the user of this activity to be the same as the selected user in the main menu
        }
        updateFishAmount(); //updates the fishAmount value in case user returns with fish from docks
    }

    private void innitViews() {
        btnMainMenu = findViewById(R.id.btnMainMenu);
        tvWelcome = findViewById(R.id.tvWelcome);
        btnGoFish = findViewById(R.id.btnGoFish);
    }

    @SuppressLint("SetTextI18n")
    private void updateViews() {
        tvWelcome.setText("Hey there "+selectedUser.getUserName());
    }

    private void setSelectedUser(){
        Intent intent = getIntent();
        int selectedId = intent.getIntExtra(KEY1, 0);
        ArrayList<User> users = dbHelperFish.selectAll();
        selectedUser = new User(users.get(selectedId));
    }

    private void updateFishAmount() {
        Intent intent = getIntent();
        int fishCaught = intent.getIntExtra(KEY2, 0);
        int fishAmount = selectedUser.getFishAmount() + fishCaught;
        selectedUser.setFishAmount(fishAmount);
        dbHelperFish.update(selectedUser);
        Log.d(FISHAMOUNT, "updateFishAmount: "+"fishAmount= "+selectedUser.getFishAmount());
    }
}