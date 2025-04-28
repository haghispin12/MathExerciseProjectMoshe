package com.example.mathexerciseproject.mathproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mathexerciseproject.FishingProject.FishingActivity;
import com.example.mathexerciseproject.R;

import org.jetbrains.annotations.Nullable;


public class MainActivity extends AppCompatActivity {
    /*
    Variable list
     */

    private Button btnFishing;
    private Button btnUser;
    private Button BtnTimes10;
    private Button BtnTimes20;
    private Button BtnCheckAnswer;
    private Button BtnChallenge;
    private EditText tvAnswer;
    private TextView tvNum1;
    private TextView tvNum2;
    private MainViewModel vMain;
    private Button btnRate;
    private FragmentTransaction trans;
    private MenuItem menuItem;



    /*
    Return to MainActivity from RateActivity
     */
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int myRate = result.getData().getIntExtra("Rating", -1);
                vMain.setUserRate(myRate);
                createToast("You rated "+myRate+"");
            }
    });

    /*
    On create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initViews();
        onClickListeners();
        Observers();
        myIntents();

    }


    /*
    Receive all intents prior to MainActivity.onCreate
     */
    public void myIntents(){
        Intent intent = getIntent();
        String userName=intent.getStringExtra("user");
        setTitle(userName);
        vMain.setUserName(userName);
        createToast(vMain.vUser.getUserName());
    }
    /*
    Construct all variables from list
     */
    public void initViews(){
        btnFishing = findViewById(R.id.btnFishing);
        btnUser = findViewById(R.id.btnUser);
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
    /*
    All of the on click listeners
     */
    public void onClickListeners(){

        /*
        btnUser
         */
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trans = getSupportFragmentManager().beginTransaction();
                trans.add(R.id.MainFrame, new ShowUsers());
                trans.commit();
            }
        });
        /*
        * challenge till 10 (לוח כפל)
         */
        BtnTimes10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vTimes10();
                vMain.vExercise.setBet(10);

            }
        });
        /*
        Challenge till 20 (כפל עד 20)
         */
        BtnTimes20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vTimes20();
                vMain.vExercise.setBet(20);

            }
        });
        /*
        Challenging challenge (אתגר)
         */
        BtnChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vMain.vChallenge();
                vMain.vExercise.setBet(50);
            }
        });
        /*
        Check answer (בדיקה)
         */
        BtnCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mAnswer = tvAnswer.getText().toString();
                if(vMain.vCheck(mAnswer) && !vMain.hasChecked()){
                    createToast("Correct Answer");
                    vMain.addScore();
                }else if(!vMain.hasChecked()){
                    createToast("Wrong Answer");
                    createToast("The answer is "+ " "+ vMain.getExerciseNum3());
                    vMain.lowerScore();
                }
                vMain.vPostCheck();
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
        btnFishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FishingActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
     * Create toast (popup message)
     */
    public void createToast(String text){
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }


    /*
     * Observers that track any changes on num1 and num2
     */
    public void Observers(){
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
