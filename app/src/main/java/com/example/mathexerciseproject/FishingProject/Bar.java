package com.example.mathexerciseproject.FishingProject;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bar {
    private Rect body; //The body of the Bar
    private Paint paint = new Paint(Color.GREEN); //Color of the bar
    private float barX; //The position of the bar from its center
    private float barLength; // the length of the bar
    private float barSpeed; //The speed at which the bar moves to the right
    private boolean GravityL; //A flag indicating whether or not to create the illusion of gravity
    private float barLBound; //The point in which the bar can no longer go left
    private float barRBound; //The point in which the bar can no longer go right

    public Bar(float barX, float barSpeed, boolean GravityL, float barRBound, float barLBound) {
//        this.body = new Rect(100, 25, 100, 25);
        this.barX = barX;
        this.barLength = 200;
        this.barSpeed = barSpeed;
        this.GravityL = GravityL;
        this.barLBound = barLBound;
        this.barRBound = barRBound;
    }

    public void updatePosition() {
        if(GravityL){
            barX -= barSpeed;
            if(barX - (barLength/2) <= barLBound){
                barX=barLBound+(barLength/2);
                GravityL = false;
            }
        }
    }

    /*
    getters and setters
     */

//    public Rect getBody() {
//        return body;
//    }

    public float getBarX() {
        return barX;
    }

    public float getBarLength() {
        return barLength;
    }

    public Paint getPaint() {
        return paint;
    }
}
