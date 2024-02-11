package com.example.mathexerciseproject;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

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
        exercise.setNum1(exercise.generateNumbers(0,10));
        exercise.setNum2(exercise.generateNumbers(0,10));
    }
    public void vChallenge(){
        exercise.setNum1(exercise.generateNumbers(0,10));
        exercise.setNum2(exercise.generateNumbers(10,100));
    }
    public void vTimes20(){
        exercise.setNum1(exercise.generateNumbers(0,20));
        exercise.setNum2(exercise.generateNumbers(0,20));
    }
    /*
        Check Answers
     */
    public void vCheck(){
        if(exercise.checkAnswers(tvAnswer.getText().toString())){
            createToast(Toast.LENGTH_LONG, "Correct Answer");

        } else{
            createToast(Toast.LENGTH_LONG, "Wrong Answer");
        }

    }

}
