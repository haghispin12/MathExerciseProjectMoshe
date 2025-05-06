package com.example.mathexerciseproject.FishingProject;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Bar {
    private Paint paint = new Paint(Color.GREEN); //Color of the bar
    private float barX; //The position of the bar from its center
    private float barLength; // the length of the bar
    private float barSpeed; //The speed at which the bar moves to the right
    private boolean GravityL; //A flag indicating whether or not to create the illusion of gravity
    private boolean isPressing; //A flag indicating whether or not the bar should go right on the screen
    private float barLBound; //The point in which the bar can no longer go left
    private float barRBound; //The point in which the bar can no longer go right

    public Bar(float barX, float barSpeed, boolean GravityL, float barRBound, float barLBound) {
        this.barX = barX;
        this.barLength = 200;
        this.barSpeed = barSpeed;
        this.GravityL = GravityL;
        this.barLBound = barLBound;
        this.barRBound = barRBound;
    }

    public void updatePosition() {
        if(!isPressing) {
            barX -= barSpeed;
            if (barX - (barLength / 2) < barLBound) {
                barX = barLBound + (barLength / 2);
                GravityL = false;
            }
        }
        if(isPressing && barX <= barRBound){
            GravityL = false;
            barX += barSpeed;
            if(barX > barRBound){
                barX=barRBound;
            }
            Log.d("bar status1", "right bound = "+ barRBound + " BarX = "+barX);
        }

    }

    public float getBarRBound() {
        return barRBound;
    }

    public float getBarX() {
        return barX;
    }

    public float getBarLength() {
        return barLength;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPressing(boolean pressing) {
        isPressing = pressing;
    }
}
