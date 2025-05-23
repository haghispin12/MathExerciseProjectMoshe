package com.example.mathexerciseproject.FishingProject;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

public class fish1 {

    private Paint paint = new Paint(Color.rgb(51,153,255));
    private final float radius;
    private float xPosition;
    private float baseSpeed = 25f;
    private float dashSpeedMultiplier = 2.5f;
    private int dashDuration; // Duration will now be variable
    private int pauseDuration; // Duration will now be variable
    private int minDashDuration = 5;   // Minimum frames for a dash
    private int maxDashDuration = 15;  // Maximum frames for a dash
    private int minPauseDuration = 15; // Minimum frames for a pause
    private int maxPauseDuration = 15; // Maximum frames for a pause
    private enum State { MOVING_LEFT, MOVING_RIGHT, DASHING_LEFT, DASHING_RIGHT, PAUSING }
    private State currentState;
    private int stateTimer = 0;
    float leftBound;
    float rightBound;
    private Random random = new Random();
    private int moveInterval = 30; // Average interval for state change attempts

    public fish1(float startX, float moveSpeed, float left, float right) {
        this.radius = 50;
        this.xPosition = startX;
        this.baseSpeed = moveSpeed;
        this.leftBound = left;
        this.rightBound = right;
        this.currentState = random.nextBoolean() ? State.MOVING_RIGHT : State.MOVING_LEFT;
        // Initialize durations with a random value
        this.dashDuration = random.nextInt(maxDashDuration - minDashDuration + 1) + minDashDuration;
        this.pauseDuration = random.nextInt(maxPauseDuration - minPauseDuration + 1) + minPauseDuration;
    }



    public void updatePosition() {
        stateTimer++;

        //Define the states and give them meaning in relation ro base speed
        switch (currentState) {
            case MOVING_LEFT:
                xPosition -= baseSpeed;
                break;
            case MOVING_RIGHT:
                xPosition += baseSpeed;
                break;
            case DASHING_LEFT:
                xPosition -= baseSpeed * dashSpeedMultiplier;
                break;
            case DASHING_RIGHT:
                xPosition += baseSpeed * dashSpeedMultiplier;
                break;
            case PAUSING:
                break;
        }

        //radius is used to take into acount the fact that xPosition equals the center of the fish and not the left/right side
        if (xPosition-radius < leftBound) { //
            xPosition = leftBound+radius;
            Log.d("StatusAAAA", "updatePosition: left " + xPosition+" LeftBound: " + leftBound);
            currentState = State.MOVING_RIGHT;
            stateTimer = 0;
        } else if (xPosition+radius > rightBound) {
            xPosition = rightBound-radius;
            Log.d("StatusAAAA", "updatePosition: right "+ xPosition + " Rightbound: " + rightBound);
            currentState = State.MOVING_LEFT;
            stateTimer = 0;
        }

        if (stateTimer >= moveInterval) {
            stateTimer = 0;
            moveInterval = random.nextInt(40) + 20;//min = 20 | max = 40

            float chance = random.nextFloat();

            if (chance < 0.1f) {
                currentState = (currentState == State.MOVING_LEFT || currentState == State.DASHING_LEFT) ? State.DASHING_LEFT : State.DASHING_RIGHT;
                // Set a new random dash duration
                dashDuration = random.nextInt(maxDashDuration - minDashDuration + 1) + minDashDuration;
                moveInterval = dashDuration;
                stateTimer = 0;
            } else if (chance < 0.5f) {
                currentState = State.PAUSING;
                // Set a new random pause duration
                pauseDuration = random.nextInt(maxPauseDuration - minPauseDuration + 1) + minPauseDuration;
                moveInterval = pauseDuration;
                stateTimer = 0;
            } else {
                currentState = (currentState == State.MOVING_LEFT || currentState == State.DASHING_LEFT) ? State.MOVING_RIGHT : State.MOVING_LEFT;
            }
        } else if ((currentState == State.DASHING_LEFT || currentState == State.DASHING_RIGHT) && stateTimer >= dashDuration) {
            // Transition out of dash after the random duration
            currentState = (currentState == State.DASHING_LEFT) ? State.MOVING_LEFT : State.MOVING_RIGHT;
            stateTimer = 0;
        } else if (currentState == State.PAUSING && stateTimer >= pauseDuration) {
            // Transition out of pause after the random duration
            currentState = random.nextBoolean() ? State.MOVING_RIGHT : State.MOVING_LEFT;
            stateTimer = 0;
        }
    }

    /*
    getters
     */
    public float getXPosition() {
        return xPosition;
    }

    public float getSpeed() {
        switch (currentState) {
            case DASHING_LEFT:
            case DASHING_RIGHT:
                return baseSpeed * dashSpeedMultiplier;
            case MOVING_LEFT:
            case MOVING_RIGHT:
                return baseSpeed;
            case PAUSING:
            default:
                return 0;
        }
    }

    public float getRadius() {
        return radius;
    }

    public Paint getPaint() {
        return paint;
    }
}