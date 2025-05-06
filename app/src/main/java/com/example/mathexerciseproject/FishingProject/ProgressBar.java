package com.example.mathexerciseproject.FishingProject;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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



    public ProgressBar(int leftX, int rightX, boolean isOverLap, int speed, float gameHeight, float rightBound){
        rect = new Rect(leftX, (int)gameHeight-250, rightX, (int)gameHeight-200);
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

            }
        }else if(!isOverLap && rightX>leftX){
            rightX-=downSpeed;
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
