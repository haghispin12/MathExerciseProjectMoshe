package com.example.mathexerciseproject.FishingProject;

public class Fish {

    private float xPosition; // Current horizontal position of the fish
    private float speed;       // Speed of the fish in pixels per update
    private boolean movingRight; // Flag indicating the direction of movement
    private float leftBound;   // Left boundary of the movement area
    private float rightBound;  // Right boundary of the movement area

    public Fish(float startX, float moveSpeed, float left, float right) {
        this.xPosition = startX;
        this.speed = moveSpeed;
        this.movingRight = true; // Start moving right
        this.leftBound = left;
        this.rightBound = right;
    }


    public void updatePosition() {
        if (movingRight) {
            xPosition += speed;
            if (xPosition > rightBound) {
                xPosition = rightBound; // Keep it within bounds
                movingRight = false;  // Change direction
            }
        } else {
            xPosition -= speed;
            if (xPosition < leftBound) {
                xPosition = leftBound;  // Keep it within bounds
                movingRight = true;   // Change direction
            }
        }
    }

    // You'll need a way to draw this fish at its xPosition in your game view

    /*
    Getters
     */

    public float getXPosition() {
        return xPosition;
    }

    public float getSpeed() {
        return speed;
    }

}
