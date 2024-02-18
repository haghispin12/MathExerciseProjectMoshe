package com.example.mathexerciseproject;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData<Integer> vNum1;
    MutableLiveData<Integer> vNum2;
    Exercise exercise;


    public MainViewModel(){
        Exercise vExercise = new Exercise();
        vNum1 = new MutableLiveData<>();
        vNum2 = new MutableLiveData<>();
    }

    //כל הפעולות של EXERCISE
    public void vTimes10(){
        exercise.generateNum1(0,10);
        exercise.generateNum2(0,10);
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }
    public void vChallenge(){
        exercise.generateNum1(10,100);
        exercise.generateNum2(10,100);
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }
    public void vTimes20(){
        exercise.generateNum1(0,20);
        exercise.generateNum2(0,20);
        vNum1.setValue(exercise.getNum1());
        vNum2.setValue(exercise.getNum2());
    }
    /*
        Check Answers
     */
    public boolean vCheck(String answer){
        if(exercise.checkAnswers(answer)){
            return true;
        } else{
            return false;
        }
    }

}
