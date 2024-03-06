package com.example.mathexerciseproject;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise vExercise;
    User vUser;


    public MainViewModel(){
        vNum1 = new MutableLiveData<>();
        vNum2 = new MutableLiveData<>();
        vExercise = new Exercise();
        vUser = new User();
    }
    public void setUserName(String name){
        vUser.setUserName(name);
    }

    //כל הפעולות של EXERCISE
    public void vTimes10(){
        vExercise.generateNum1(0,10);
        vExercise.generateNum2(0,10);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    public void vChallenge(){
        vExercise.generateNum1(10,100);
        vExercise.generateNum2(10,100);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    public void vTimes20(){
        vExercise.generateNum1(0,20);
        vExercise.generateNum2(0,20);
        vNum1.setValue(vExercise.getNum1());
        vNum2.setValue(vExercise.getNum2());
    }
    /*
        Check Answers
     */
    public boolean vCheck(String answer){
        if(vExercise.checkAnswers(answer)){
            return true;
        } else{
            return false;
        }
    }

}
