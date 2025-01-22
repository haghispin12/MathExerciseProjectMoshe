package com.example.mathexerciseproject;


import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    MutableLiveData<ArrayList<User>> vArrUser;
    Exercise vExercise;
    User vUser;



    public MainViewModel(){
        vNum1 = new MutableLiveData<>();
        vNum2 = new MutableLiveData<>();
        vArrUser = new MutableLiveData<>(new ArrayList<>());
        vExercise = new Exercise();
        vUser = new User();
    }
    public void setUserName(String name){
        vUser.setUserName(name);
    }

    //כל הפעולות של EXERCISE
    public void vTimes10(){
        vExercise.setHasChecked(false);
        vExercise.generateNum1(0,10);
        vExercise.generateNum2(0,10);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    public void vChallenge(){
        vExercise.setHasChecked(false);
        vExercise.generateNum1(10,100);
        vExercise.generateNum2(10,100);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    public void vTimes20(){
        vExercise.setHasChecked(false);
        vExercise.generateNum1(0,20);
        vExercise.generateNum2(0,20);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    /*
        Check Answers
     */
    public void vPostCheck(){
        vExercise.setHasChecked(true);
    }
    public boolean vCheck(String answer){
        if(vExercise.checkAnswers(answer)){
            return true;
        }
        return false;
    }
    public void addScore(){
        int originalScore = vUser.getScore();
        originalScore+=vExercise.getBet();
        vUser.setScore(originalScore);
    }
    public void lowerScore(){
        int originalScore = vUser.getScore();
        originalScore-=vExercise.getBet();
        vUser.setScore(originalScore);
    }

    /*
    Getters and setters
     */
    public int getExerciseNum3(){return vExercise.getnum3();}
    public int getUserScore(){return vUser.getScore();}
    public int getUserRating(){return vUser.getRate();}
    public String getUserName(){return vUser.getUserName();}
    public void setUserRate(int num){vUser.setRate(num);}
    /*
    dbHelper functions
     */
    public long dbAddUser(Context context){
        DBHelper dbHelper = new DBHelper(context);

        long id= dbHelper.insert(vUser, context);
        Log.d("responseId", "id = "+id);
        return id;
    }
    /*
Grab all current users from the database, and insert into Array (vArrUser)
 */
    public void dbSelectAll(Context context){
        DBHelper dbHelper = new DBHelper(context);

        ArrayList<User> tmp = dbHelper.selectAll();
        vArrUser.setValue(tmp);
    }
    public boolean hasChecked(){
        if(vExercise.isHasChecked()){
            return true;
        }
        return false;
    }
    public void dbDeleteUser(Context context, long id){
        DBHelper dbHelper = new DBHelper(context);

        dbHelper.deleteById(id);
        dbSelectAll(context);
    }
    public void dbEditUsername(Context context, User user){
        DBHelper dbHelper = new DBHelper(context);

        dbHelper.update(user);
        dbSelectAll(context);
    }
}
