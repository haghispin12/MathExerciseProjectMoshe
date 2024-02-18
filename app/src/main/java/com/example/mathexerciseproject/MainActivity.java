package com.example.mathexerciseproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        onClickListener();
        models();

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
        vMain = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

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

            }
        });
        vMain.vNum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable @androidx.annotation.Nullable Integer num2) {

            }
        });
    }

}
