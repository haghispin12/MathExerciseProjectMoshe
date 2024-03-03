package com.example.mathexerciseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.time.Duration;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Button BtnTimes10;
    private Button BtnTimes20;
    private Button BtnCheckAnswer;
    private Button BtnChallenge;
    private EditText tvAnswer;
    private TextView tvNum1;
    private TextView tvNum2;
    private MainViewModel vMain;
    private Button btnRate;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int myRate = result.getData().getIntExtra("Rating", -1);
                createToast(Toast.LENGTH_LONG,myRate+"");
            }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onClickListener();
        models();
        myIntents();

    }
    public void myIntents(){
        Intent intent = getIntent();
        String userName=intent.getStringExtra("user");

        setTitle(userName);
    }

    public void initViews(){
        BtnTimes10 = findViewById(R.id.BtnTimes10);
        BtnTimes20 = findViewById(R.id.btnTimes20);
        BtnChallenge = findViewById(R.id.BtnChallenge);
        BtnCheckAnswer = findViewById(R.id.BtnCheckAnswer);
        tvAnswer = findViewById(R.id.tvAnswer);
        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        vMain = new MainViewModel();
        vMain = new ViewModelProvider(this).get(MainViewModel.class);
        btnRate = findViewById(R.id.btnRate);

    }

    public void onClickListener(){
        /*
        * challenge till 10 (לוח כפל)
         */
        BtnTimes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vTimes10();

            }
        });
        /*
        Challenge till 20 (כפל עד 20)
         */
        BtnTimes20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vTimes20();


            }
        });
        /*
        Challenging challenge (אתגר)
         */
        BtnChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vChallenge();
            }
        });
        /*
        Check answer (בדיקה)
         */
        BtnCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mAnswer = tvAnswer.getText().toString();
                if(vMain.vCheck(mAnswer)){
                    createToast(Toast.LENGTH_LONG, "Correct Answer");
                }else{
                    createToast(Toast.LENGTH_LONG, "Wrong Answer");
                }
            }
        });
        /*
        Go to rate page
         */
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RateActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
    }

    /*
     * Create toast (popup message)
     */

    public void createToast(int duration, String text){
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    /*
     * Models
     */
    public void models(){
        vMain.vNum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable @androidx.annotation.Nullable Integer num1) {
                tvNum1.setText(num1+"");
            }
        });
        vMain.vNum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable @androidx.annotation.Nullable Integer num2) {
                tvNum2.setText(num2+"");
            }
        });
    }

}
