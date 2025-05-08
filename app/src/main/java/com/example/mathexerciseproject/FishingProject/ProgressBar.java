package com.example.mathexerciseproject.FishingProject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.mathexerciseproject.mathproject.LoginActivity;
import com.example.mathexerciseproject.mathproject.MainActivity;

public class ProgressBar {
    private int leftX;
    private int rightX;
    private boolean isOverLap;
    private int upSpeed;
    private int downSpeed;
    private Paint paint = new Paint(Color.GREEN);
    private Rect rect;
    private float rightBound;
    private int scoreTime = 0;
    private boolean isFinish = false;
    private boolean isCaught = false;



    public ProgressBar(int leftX, int rightX, boolean isOverLap, int speed, float gameHeight, float rightBound){
        rect = new Rect(leftX, (int)gameHeight-180, rightX, (int)gameHeight-130);
        this.upSpeed = speed;
        this.downSpeed = speed+(speed/2);
        this.isOverLap = isOverLap;
        this.leftX = leftX;
        this.rightX = rightX;
        this.rightBound = rightBound;
    }

    public void updatePosition(){
        scoreTime++;

        if(isOverLap && rightX>leftX){
            rightX+=upSpeed;
            if(rightX>rightBound){
                isFinish = true;
                isCaught = true;
            }
        }else if(!isOverLap && rightX>leftX){
            rightX-=downSpeed;
            if(rightX<=leftX){
                isFinish = true;
                isCaught = false;
            }
        }
    }

    /*
    getters
     */

    public int getScoreTime() {
        return scoreTime;
    }

    public int getRectBottom(){
        return rect.bottom;
    }
    public int getRectTop(){
        return rect.top;
    }
    public int getRectLeft(){
        return leftX;
    }
    public int getRectRight(){
        return rightX;
    }
    public boolean getIsFinish() {
        return isFinish;
    }
    public boolean isCaught() {
        return isCaught;
    }
    public Paint getPaint() {
        return paint;
    }
    /*
    setters
     */

    public void setOverLap(boolean overLap) {
        isOverLap = overLap;
    }

}
